import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useParams} from 'react-router-dom';
import {Link} from 'react-router-dom';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink, ReviewsLink} from '../../common';
import users from '../../users';



const PeliculaDetails = () => {

    const pelicula = useSelector(selectors.getPelicula);
    const dispatch = useDispatch();
    const {id} = useParams();
    const loggedIn = useSelector(users.selectors.isLoggedIn);

    useEffect(() => {

        const peliculaId = Number(id);

        if (!Number.isNaN(peliculaId)) {
            dispatch(actions.findPeliculaById(peliculaId));
        }

        return () => dispatch(actions.clearPelicula());

    }, [id, dispatch]);

    if (!pelicula) {
        return null;
    }

    return (

        <div>

            <BackLink/>

            <div className="card border-dark text-center mt-3">
                <div className="card-body">
                    <h5 className="card-title display-3">{pelicula.titulo}</h5>
                    <h6 className="card-text text-muted">{pelicula.duracion} <FormattedMessage id='project.global.fields.minutos'/></h6>
                    <p className="card-text">{pelicula.resumen}</p>
                    <Link type="submit" className="btn btn-lg btn-outline-dark" to={`/cartelera/find-orders/${id}`}>
                        <FormattedMessage id='project.global.fields.review_score'/>: {pelicula.valoracion}/5. ({pelicula.numeroCriticas}) <FormattedMessage id="project.global.buttons.review"/>
                    </Link>
                </div>
                {loggedIn ? 
                    <ReviewsLink id={id}/>
                    :
                    <div className="alert alert-warning" role="alert">
                        <FormattedMessage id='project.cartelera.Sesion.NoRegistrado.Critica'/>.
                            <Link className="alert-link" to={'/users/login'}>
                                <FormattedMessage id='project.users.SignUp.login'/>
                            </Link>
                            <FormattedMessage id='project.users.SignUp.or'/>
                            <Link className="alert-link" to={'/users/signUp'}>
                                <FormattedMessage id='project.users.SignUp.signup'/>
                            </Link>
                        </div>
                    }
            </div>

        </div>
    );
}

export default PeliculaDetails;