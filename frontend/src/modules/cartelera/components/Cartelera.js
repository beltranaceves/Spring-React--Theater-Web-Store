import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';
import * as actions from '../actions';
import Peliculas from './Peliculas';
import DateSelector from './DateSelector';

const Cartelera = () => {

    const carteleraSearch = useSelector(selectors.getCarteleraSearch);
    const cines = useSelector(selectors.getCines);
    
    const dispatch = useDispatch();

    if (!carteleraSearch) {
        return null;
    }

    if (carteleraSearch.result.length === 0) {

        return (
            <div className="text-center">

            <h1 className="display-3">{selectors.getCineName(cines, carteleraSearch.criteria.cineId)}</h1>

            <div className="w-25 p-3">
                
                <DateSelector className="custom-select my-2 mr-sm-2"
                value={carteleraSearch.criteria.date ? carteleraSearch.criteria.date : ''}
                onChange={e => dispatch(actions.getCartelera(
                    {cineId: carteleraSearch.criteria.cineId,
                    date: e.target.value}))}/>
            </div>
                <div className="alert alert-danger" role="alert">
                    <FormattedMessage id='project.cartelera.FindCarteleraResult.noMoviesFound'/>
                </div>
            </div>
            
        );
    }

    return (
        
        <div className="text-center">

            <h1 className="display-3">{selectors.getCineName(cines, carteleraSearch.criteria.cineId)}</h1>

            <div className="w-25 p-3">
                
                <DateSelector className="custom-select my-2 mr-sm-2"
                value={carteleraSearch.criteria.date ? carteleraSearch.criteria.date : ''}
                onChange={e => dispatch(actions.getCartelera(
                    {cineId: carteleraSearch.criteria.cineId,
                    date: e.target.value}))}/>
                           
            </div>
            <Peliculas peliculas = {carteleraSearch.result}/>
        </div>
    );
};

export default Cartelera;