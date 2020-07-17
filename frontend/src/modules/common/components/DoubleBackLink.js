import React from 'react';
import {useHistory} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

const DoubleBackLink = ({id}) => {

    const history = useHistory();

    if (history.length <= 1) {
        return null;
    }

    return (

        <button type="button" className="btn btn-outline-info" 
            onClick={() => history.go(-2)}>
                

            <FormattedMessage id='project.global.buttons.back'/>

        </button>

    );

};

export default DoubleBackLink;
