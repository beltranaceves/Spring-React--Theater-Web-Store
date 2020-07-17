const getModuleState = state => state.cartelera;

export const getCarteleraSearch = state =>
    getModuleState(state).carteleraSearch; 

export const getCiudades = state =>
    getModuleState(state).ciudades;

export const getCines = state =>
    getModuleState(state).cines;

export const getCriticas = state =>
    getModuleState(state).criticas;

export const getPelicula = state =>
    getModuleState(state).pelicula;

export const getSesion = state =>
    getModuleState(state).sesion;

export const getCineName = (cines, id) => {

    if (!cines) {
        return '';
    }
    const cine = cines.find(cine => cine.id === id);
    return cine.nombre;
}