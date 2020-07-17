import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    lastCompraId: null,
    compras: null
};

const lastCompraId = (state = initialState.lastCompraId, action) => {
    
    switch (action.type) {

        case actionTypes.COMPRA_COMPLETED:
            return action.compraId;

        default:
            return state;

    }

}

const compras = (state = initialState.compras, action) => {
    
    switch (action.type) {

        case actionTypes.GET_COMPRAS_COMPLETED:
            return action.compras;

        case actionTypes.CLEAR_COMPRAS_SEARCH:
            return initialState.compras;

        default:
            return state;

    }

}

const reducer = combineReducers({
    lastCompraId,
    compras
});

export default reducer;