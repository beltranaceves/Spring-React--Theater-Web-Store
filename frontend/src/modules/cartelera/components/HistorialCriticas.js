import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager, DoubleBackLink} from '../../common';
import Criticas from './Criticas';

const HistorialCriticas = () => {

    const criticas = useSelector(selectors.getCriticas);
    const dispatch = useDispatch();

    if (!criticas) {
        return (
            <div>
                <div style={{paddingBottom: '20px'}}>
                <DoubleBackLink />
                </div>
                <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.cartelera.FindCriticasResult.noCriticas'/>
            </div>
            </div>
        );
    }

    if (criticas.result.items.length === 0) {

        return (
            <div>
                <div style={{paddingBottom: '20px'}}>
                <DoubleBackLink />
                </div>
                <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.cartelera.FindCriticasResult.noCriticas'/>
            </div>
            </div>
        );
    }

    return (

        <div>

            <div>
            <DoubleBackLink/>
                <h1 className="display-3 text-center"><FormattedMessage id='project.global.fields.criticas'/></h1>
                <div className="mt-3">
                <Criticas criticas={criticas.result.items}/>
                <Pager 
                    back={{
                        enabled: criticas.criteria.page >= 1,
                        onClick: () => dispatch(actions.previousHistorialCriticasPage(criticas.criteria))}}
                    next={{
                        enabled: criticas.result.existMoreItems,
                        onClick: () => dispatch(actions.nextHistorialCriticasPage(criticas.criteria))}}/>
                </div>
            </div>
        </div>
    );

}

export default HistorialCriticas;