import React from 'react';
import {FormattedDate, FormattedMessage} from 'react-intl';

import PropTypes from 'prop-types';

import common from '../../common';

const DateSelector = (selectProps) => {

    const dates = common.dateUtils.getArrayWithNextDays(6);

    return(
        
        <select {...selectProps}>
            <FormattedMessage id='project.cartelera.DateSelector.default'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>
            {dates.map(date =>
                <FormattedDate value={date} key={date.getTime()}>
                    {dateAsString =>
                    (<option value={common.dateUtils.formatDate(date)}>
                        {dateAsString}
                        </option>)
                    }
                </FormattedDate>
            )}
        </select>
    )

}

DateSelector.propTypes = {
    selectProps: PropTypes.object
};

export default DateSelector;