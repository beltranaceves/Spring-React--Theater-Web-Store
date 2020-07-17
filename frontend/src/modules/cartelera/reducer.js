import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    carteleraSearch: null,
    ciudades: null,
    cines: null,
    pelicula: null,
    sesion: null,
    criticas: null
};

const carteleraSearch = (state = initialState.carteleraSearch, action) => {

    switch (action.type) {

        case actionTypes.GET_CARTELERA_COMPLETED:
            return action.carteleraSearch;
        
        case actionTypes.CLEAR_CARTELERA:
            return initialState.carteleraSearch;

        default:
            return state;

    }

}

const ciudades = (state = initialState.ciudades, action) => {

    switch (action.type) {

        case actionTypes.GET_CIUDADES_COMPLETED:
            return action.ciudades;

        default:
            return state;

    }

}

const cines = (state = initialState.cines, action) => {

    switch (action.type) {

        case actionTypes.GET_CINES_COMPLETED:
            return action.cines;

        default:
            return state;

    }

}

const pelicula = (state = initialState.pelicula, action) => {
    
    switch (action.type) {

        case actionTypes.FIND_PELICULA_BY_ID_COMPLETED:
            return action.pelicula;

        case actionTypes.CLEAR_PELICULA:
            return initialState.pelicula;

        default:
            return state;

    }
}

const sesion = (state = initialState.sesion, action) => {
    
    switch (action.type) {

        case actionTypes.FIND_SESION_BY_ID_COMPLETED:
            return action.sesion;

       case actionTypes.CLEAR_SESION:
            return initialState.sesion;

        default:
            return state;

    }
}

const criticas = (state = initialState.criticas, action) => {
    switch(action.type) {
        case actionTypes.FIND_CRITICA_BY_PELICULA_COMPLETED:
            return action.criticas;

        case actionTypes.CLEAR_CRITICAS_SEARCH:
            return initialState.criticas;
        
        default: 
            return state;
    }

}

const reducer = combineReducers({
    carteleraSearch,
    ciudades,
    cines,
    pelicula,
    sesion, 
    criticas
});

export default reducer;


