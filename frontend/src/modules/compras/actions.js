import backend from '../../backend';
import * as actionTypes from './actionTypes';

export const buyCompleted = (compraId) => ({
    type: actionTypes.COMPRA_COMPLETED,
    compraId
});

export const buy = (creditCard, sesionId, quantity, onSuccess, 
    onErrors) => dispatch =>
    backend.compraService.buy(sesionId, quantity, creditCard,
        ({id}) => {
            dispatch(buyCompleted(id));
            onSuccess();
        },
        onErrors);


const getComprasCompleted = (compras) => ({
    type: actionTypes.GET_COMPRAS_COMPLETED,
    compras
});

const clearComprasSearch = () => ({
    type: actionTypes.CLEAR_COMPRAS_SEARCH
});

export const getCompras = criteria => dispatch => {
    dispatch(clearComprasSearch());
    backend.compraService.getCompras(criteria,
       result => dispatch(getComprasCompleted({criteria, result})));
}   

export const previousHistorialComprasPage = criteria => 
    getCompras({userId: criteria.userId, page: criteria.page-1});

export const nextHistorialComprasPage = criteria => 
    getCompras({userId: criteria.userId, page: criteria.page+1});

export const deliverTicket = (compraId, creditCard, onSuccess, onErrors) => dispatch =>
    backend.compraService.deliverTicket(compraId, creditCard, onSuccess, onErrors);