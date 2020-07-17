import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const PeliculaLink = ({id, titulo}) => {

    return (
        <Link className="text-dark btn-lg" to={`cartelera/pelicula-details/${id}`}>
            {titulo}
        </Link>
    )
}

PeliculaLink.propTypes = {
    id: PropTypes.number.isRequired,
    titulo: PropTypes.string.isRequired
};

export default PeliculaLink;