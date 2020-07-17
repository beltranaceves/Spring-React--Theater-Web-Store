import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';



const publishCompleted = (reviewId) => ({
    type: actionTypes.PUBLISH_COMPLETED,
    reviewId
});

export const publish = (puntuacion, titulo, comentario, peliculaId, onSuccess, 
    onErrors) => dispatch =>
    backend.carteleraService.publish(puntuacion, titulo, comentario, peliculaId,
        ({id}) => {
            dispatch(publishCompleted(id));
            onSuccess();
        },
        onErrors);

export const getCriticas = criteria => dispatch => {
    dispatch(clearCriticasSearch());
    backend.carteleraService.getCriticas(criteria,
        result => dispatch(getCriticasCompleted({criteria, result})));
};



const getCriticasCompleted = criticas => ( {
    type: actionTypes.FIND_CRITICA_BY_PELICULA_COMPLETED,
    criticas
});

const clearCriticasSearch = () => ({
    type: actionTypes.CLEAR_CRITICAS_SEARCH
});

export const previousHistorialCriticasPage = criteria => 
    getCriticas({peliculaId: criteria.peliculaId, page: criteria.page-1});

export const nextHistorialCriticasPage = criteria => 
    getCriticas({peliculaId: criteria.peliculaId, page: criteria.page+1});

export const getCarteleraCompleted = (carteleraSearch) => ({
    type: actionTypes.GET_CARTELERA_COMPLETED,
    carteleraSearch
});

export const getCartelera = criteria => (dispatch, getState)=> {
    if(criteria){
    backend.carteleraService.getCartelera(criteria,
        result => dispatch(getCarteleraCompleted({criteria, result}))
    );}
};

const getCiudadesCompleted = ciudades => ({
    type: actionTypes.GET_CIUDADES_COMPLETED,
    ciudades
}); 

export const getCiudades = () => (dispatch, getState) => {
    
    const ciudades = selectors.getCiudades(getState());
    if (!ciudades) {
        backend.carteleraService.getCiudades(
            ciudades => dispatch(getCiudadesCompleted(ciudades))
        );
    }
}

const getCinesCompleted = cines => ({
    type: actionTypes.GET_CINES_COMPLETED,
    cines
}); 

export const getCines = ciudadId => (dispatch, getState) => {
        backend.carteleraService.getCines(ciudadId,
            cines => dispatch(getCinesCompleted(cines)))
};

const findSesionByIdCompleted = sesion => ({
    type:actionTypes.FIND_SESION_BY_ID_COMPLETED,
    sesion
});

export const findSesionById = (id, onErrors) => dispatch => {
    backend.carteleraService.findSesionById(id,
        sesion => dispatch(findSesionByIdCompleted(sesion)), onErrors);
}

const findPeliculaByIdCompleted = pelicula => ({
    type:actionTypes.FIND_PELICULA_BY_ID_COMPLETED,
    pelicula
});

export const findPeliculaById = id => dispatch => {
    backend.carteleraService.findByPeliculaId(id,
        pelicula => dispatch(findPeliculaByIdCompleted(pelicula)));
}

export const clearPelicula = () => ({
    type: actionTypes.CLEAR_PELICULA
});

export const clearSesion = () => ({
    type: actionTypes.CLEAR_SESION
});

export const clearCartelera = () => ({
    type: actionTypes.CLEAR_CARTELERA
});