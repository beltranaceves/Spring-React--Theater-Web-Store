import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedTime, FormattedDate, FormattedNumber} from 'react-intl';
import {useParams, Link} from 'react-router-dom';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink,Errors} from '../../common';
import {BuyForm} from '../../compras';
import users from '../../users';


const SesionDetails = () => {

    const sesion = useSelector(selectors.getSesion);
    const dispatch = useDispatch();
    const {id} = useParams();
    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const role = useSelector(users.selectors.getUserRole)
    const [backendErrors, setBackendErrors] = useState(null);

    useEffect(() => {

        const sesionId = Number(id);

        if (!Number.isNaN(sesionId)) {
            dispatch(actions.findSesionById(sesionId,
                errors => setBackendErrors(errors)));
        }

        return () => dispatch(actions.clearSesion());

    }, [id, dispatch]);


    if (backendErrors) {
        return(
            <Errors errors={backendErrors}
                onClose={() => setBackendErrors(null)}/>
        )
    }

    if(!sesion){
        return null;  
    }

    return (
        <div>
            <BackLink/>
         
            <div className="row row-cols-md-2 mt-3">
                <div className="col md-4">
                    <div className="card border-dark text-center">
                        <h1 className="card-header text-center display-4">{sesion.pelicula}</h1>
                        
                        <div className="card-body">
                            <h4 className="card-title"><FormattedDate value={sesion.hora}/> <FormattedMessage id='project.global.fields.at'/> <FormattedTime value={sesion.hora}/></h4>
                            <p className=" card-text text-muted">{sesion.cine}</p>
                        </div>
                       
                        <div className="list-group text-left">
                            <div className="list-group-item list-group-item-action">
                                <div  className="row  row-cols-md-2">
                                    <div className="col h6 border-right"> <FormattedMessage id='project.global.fields.sala'/>:</div>
                                    <div className="col">{sesion.sala}</div>
                                </div>
                            </div>
                            
                            <div className="list-group-item list-group-item-action">
                                <div  className="row row-cols-md-2">
                                    <div className="col h6 border-right"> <FormattedMessage id='project.global.fields.duration'/>:</div>
                                    <div className="col">{sesion.duracion} <FormattedMessage id='project.global.fields.minutos'/></div>
                                </div>
                            </div>

                            <div className="list-group-item list-group-item-action">
                                <div  className="row row-cols-md-2">
                                    <div className="col h6 border-right"> <FormattedMessage id='project.global.fields.price'/>:</div>
                                    <div className="col">
                                        <FormattedNumber minimumFractionDigits= '2' maximumFractionDigits= '2' value={sesion.precio}/>€
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="card-footer">
                            <FormattedMessage id='project.global.fields.quedan'/> <b>{sesion.butacasLibres} </b>
                            <FormattedMessage id='project.global.fields.seats'/></div>
                    </div>
                </div>
                
                <div>
                    {sesion.butacasLibres > 0 ? '' :
                        <div className="alert alert-danger" role="alert">
                            <h4 class="alert-heading">
                                <FormattedMessage id='project.cartelera.Sesion.sorry'/>
                            </h4> <hr/>
                            <FormattedMessage id='project.shopping.BuyForm.SalaLLena'/>
                        </div>
                    }
                    
                    {loggedIn && role === "USER" ? <BuyForm sesion={id}/> :
                        <div className="alert alert-warning" role="alert">
                            <FormattedMessage id='project.cartelera.Sesion.NoRegistrado'/>.
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
            
        </div>
        
    );
}

export default SesionDetails;