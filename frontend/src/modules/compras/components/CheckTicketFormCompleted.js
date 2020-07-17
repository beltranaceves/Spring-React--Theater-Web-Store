import React from 'react';
import {FormattedMessage} from 'react-intl';
import {BackLink} from '../../common';

const CheckTicketFormCompleted = () => {
   
    return (
        <div>
            <BackLink/>

            <div className="alert alert-success mt-3" role="alert">
                <FormattedMessage id="project.compras.CompraCompleted.CheckTicketCompleted"/>
            </div>
        </div>
    );

}

export default CheckTicketFormCompleted;