import React, {useState, useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';
import CineSelector from './CineSelector';
import CiudadSelector from './CiudadSelector';
import * as actions from '../actions';

const FindCartelera = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    const [cineId, setCineId] = useState('');
    const [ciudadId, setCiudadId] = useState('');

    const toNumber = value => value.length > 0 ? Number(value) : null;

    const cineIdFav = localStorage.getItem('cineId');
    const ciudadIdFav = localStorage.getItem('ciudadId');

    useEffect(() => {

        if (cineIdFav !== null && ciudadIdFav !== null) {

            setCiudadId(ciudadIdFav);
            setCineId(cineIdFav);
            dispatch(actions.getCines(toNumber(ciudadIdFav)));
            dispatch(actions.getCartelera(
                {cineId: toNumber(cineIdFav), 
                date: null}));
            
        }

        return () => dispatch(actions.clearCartelera());

    }, [cineIdFav, ciudadIdFav, history, dispatch]);

    return (

        <form className="form-inline mt-2 mt-md-0">
            
            <CiudadSelector id="ciudadId" className="custom-select my-2 mr-sm-2"
                value={ciudadId} onChange={e => {
                    dispatch(actions.getCines(toNumber(e.target.value)));
                    dispatch(actions.clearCartelera());
                    setCiudadId(e.target.value);
                    setCineId(0);
                    history.push('/');
                }}/>

            <CineSelector id="cineId" className="custom-select my-2 mr-sm-2"
                value={cineId} onChange={e => {                    
                    dispatch(actions.getCartelera(
                        {cineId: toNumber(e.target.value), 
                           date: null}));
                    setCineId(e.target.value);
                    history.push('/');
                }}/>
            
            {cineId > 0 && ciudadId > 0  ?
            // loggedIn &&

                <div>
                    <button type="button" className="btn btn-outline-info" 
                            onClick={() => {
                                localStorage.setItem('cineId', cineId)
                                localStorage.setItem('ciudadId', ciudadId)
                            }}>
                            <FormattedMessage id='project.global.buttons.Favorito'/>
                    </button>
                </div>

            :

            null}

        </form>
    );

}

export default FindCartelera;
