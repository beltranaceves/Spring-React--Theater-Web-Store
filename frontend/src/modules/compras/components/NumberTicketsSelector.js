import React from 'react';
import PropTypes from 'prop-types';

const NumberTicketsSelector = (selectProps) => {

    const numbersTickets = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

    return (

        <select {...selectProps}>
            <option hidden defaultValue={0}>0</option>
            {numbersTickets.map(number =>
                    <option value={number} key={number}>{number}</option>
            )}
        </select>

    )
}

NumberTicketsSelector.propTypes = {
    selectProps: PropTypes.object
};

export default NumberTicketsSelector;