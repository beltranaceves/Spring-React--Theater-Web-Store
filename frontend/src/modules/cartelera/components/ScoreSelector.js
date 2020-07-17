import React from 'react';
import PropTypes from 'prop-types';

const ScoreSelector = (selectProps) => {

    const score = [1, 2, 3, 4, 5];

    return (

        <select {...selectProps}>
            <option hidden defaultValue={0}>0</option>
            {score.map(number =>
                    <option value={number} key={number}>{number}</option>
            )}
        </select>

    )
}

ScoreSelector.propTypes = {
    selectProps: PropTypes.object
};

export default ScoreSelector;