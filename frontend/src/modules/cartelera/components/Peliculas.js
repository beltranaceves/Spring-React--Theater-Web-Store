import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';

import {PeliculaLink, SesionLink} from '../../common';

const Peliculas = ({peliculas}) => {

    if (!peliculas) {
        return null;
    }

    return (

        <div>
            <table className="table table-striped table-hover" >

            <thead className="thead-dark">
                <tr>
                    <th className="h2" scope="col" style={{width: '50%'}}>
                        <FormattedMessage id='project.global.fields.peliculas'/>
                    </th>
                    <th className="h2" scope="col" colSpan="3" style={{width: '50%'}}>
                        <FormattedMessage id='project.global.fields.sesiones'/>
                    </th>
                </tr>
            </thead>

            <tbody>
                {peliculas.map(pelicula => 
                    <tr key={pelicula.idPelicula}>
                        <td className="movieTitle"><PeliculaLink id={pelicula.idPelicula} titulo={pelicula.pelicula}/></td>
                        {pelicula.sesiones.map(sesion=>
                                <td key={sesion.id}><SesionLink id={sesion.id} duracion={sesion.hora}/></td>
                            )}
                    </tr>
                )}
            </tbody>

            </table>
        </div>
    )
};

Peliculas.propTypes = {
    peliculas : PropTypes.array.isRequired
};

export default Peliculas;