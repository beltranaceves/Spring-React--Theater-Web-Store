import {config, appFetch} from './appFetch';

export const buy = (sesionId, quantity, creditCard, onSuccess, 
    onErrors) => {
    
        appFetch(`/compras/buy`, 
        config('POST', {sesionId, quantity, creditCard}), onSuccess, onErrors);

    }

export const getCompras = ({userId, page}, onSuccess) =>
    appFetch(`/compras?userId=${userId}&page=${page}`, config('GET'), onSuccess);

export const deliverTicket = (compraId, creditCard, onSuccess,
    onErrors) => 
    appFetch(`/compras/${compraId}/entregarTicket`,
    config('POST', {creditCard}), onSuccess, onErrors);