import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import Compras from './Compras';

const HistorialCompras = () => {

    const compras = useSelector(selectors.getCompras);
    const dispatch = useDispatch();

    if (!compras) {
        return null;
    }

    if (compras.result.items.length === 0) {

        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.compras.FindOrdersResult.noOrders'/>
            </div>
        );
    }

    return (

        <div>

            <div>
                <h1 className="display-3 text-center"><FormattedMessage id='project.global.fields.compras'/></h1>
                <div className="mt-3">
                <Compras compras={compras.result.items}/>
                <Pager 
                    back={{
                        enabled: compras.criteria.page >= 1,
                        onClick: () => dispatch(actions.previousHistorialComprasPage(compras.criteria))}}
                    next={{
                        enabled: compras.result.existMoreItems,
                        onClick: () => dispatch(actions.nextHistorialComprasPage(compras.criteria))}}/>
                </div>
            </div>
        </div>
    );

}

export default HistorialCompras;