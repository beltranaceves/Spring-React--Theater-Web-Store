import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';
import { FormattedTime } from 'react-intl';

const SesionLink = ({id, duracion}) => {

    return (
        <Link className="btn btn-outline-dark" to={`cartelera/sesion-details/${id}`}>
            <FormattedTime value={duracion}/>
        </Link>
    )
}

SesionLink.propTypes = {
    id: PropTypes.number.isRequired,
    duracion: PropTypes.number.isRequired
};

export default SesionLink;