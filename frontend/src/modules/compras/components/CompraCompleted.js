import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';
import { BackLink } from '../../common';

const CompraCompleted = () => {

    const compraId = useSelector(selectors.getLastCompraId);

    if (!compraId) {
        return null;
    }
    
    return (
        <div>
            <BackLink/>
            <div className="alert alert-success mt-3" role="alert">
                <FormattedMessage id="project.compras.CompraCompleted.compraCodeGenerated"/>:
                &nbsp;
                {compraId}
            </div>
        </div>
    );

}

export default CompraCompleted;