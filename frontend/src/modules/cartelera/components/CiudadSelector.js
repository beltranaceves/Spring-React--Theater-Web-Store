import React from 'react';
import {useSelector} from 'react-redux';
import PropTypes from 'prop-types';

import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

const CiudadSelector = (selectProps) => {

    const ciudades = useSelector(selectors.getCiudades);
    
    if (!ciudades) return null
    
    return (

        <select style={{width: '200px'}} {...selectProps}>

            <FormattedMessage id='project.cartelera.CiudadSelector.default'>
                {message => (<option selected disabled hidden value="">{message}</option>)}
            </FormattedMessage>
            
            {ciudades && ciudades.map(ciudad =>
                <option key={ciudad.id} value={ciudad.id}>{ciudad.name}</option>
                
            )}

        </select>

    );

}

CiudadSelector.propTypes = {
    selectProps: PropTypes.object
};

export default CiudadSelector;