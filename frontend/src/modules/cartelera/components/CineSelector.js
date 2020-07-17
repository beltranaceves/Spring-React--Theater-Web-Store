import React from 'react';
import {useSelector} from 'react-redux';

import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';

const CineSelector = (selectProps) => {

    const cines = useSelector(selectors.getCines);
    
    return (

        <select style={{width: '200px'}} {...selectProps}>

            <FormattedMessage id='project.cartelera.CineSelector.default'>
                {message => (<option selected hidden value="">{message}</option>)}
            </FormattedMessage>

            {cines && cines.map(cine =>
                <option key={cine.id} value={cine.id}>{cine.nombre}</option>
            )}

        </select>

    );

}

CineSelector.propTypes = {
    selectProps: PropTypes.object
};

export default CineSelector;