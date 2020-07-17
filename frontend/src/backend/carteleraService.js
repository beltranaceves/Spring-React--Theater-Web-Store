import {config, appFetch} from './appFetch';

export const getCartelera = ({cineId, date}, onSuccess) => {

    let path = `/cartelera/show?cineId=`

    path += cineId ? `${cineId}` : '';

    path += date ? `&date=${date}` : '';

    appFetch(path, config('GET'), onSuccess);
}


export const publish = (puntuacion, titulo, comentario, peliculaId, 
    onSuccess, onErrors) => {

        appFetch('/cartelera/publish',
        config('POST', {puntuacion, titulo, comentario, peliculaId}), onSuccess, onErrors);
    }

export const getCriticas = ({peliculaId, page}, onSuccess, onErrors) =>
    appFetch(`/cartelera/criticas?peliculaId=${peliculaId}&page=${page}`, config('GET'), onSuccess, onErrors);

export const getCiudades = (onSuccess) =>
    appFetch('/cartelera/ciudades', config('GET'), onSuccess);

export const getCines = (id, onSuccess) =>
    appFetch(`/cartelera/ciudades/${id}/cines`, config('GET'), onSuccess);

export const findByPeliculaId = (id, onSuccess) => 
    appFetch(`/cartelera/peliculas/${id}`, config('GET'), onSuccess);

export const findSesionById = (id, onSuccess, onErrors) =>
    appFetch(`/cartelera/sesiones/${id}`, config('GET'), onSuccess, onErrors);