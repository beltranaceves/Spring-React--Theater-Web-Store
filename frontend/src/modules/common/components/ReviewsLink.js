import React from 'react';

import {FormattedMessage} from 'react-intl';
import {Link} from 'react-router-dom';

const ReviewsLink = ({id}) => {

    return (
        <Link className="btn btn-outline-dark" to={`/cartelera/review-form/${id}`}>
            <FormattedMessage id='project.global.buttons.review_film'/>
        </Link>
    )
}



export default ReviewsLink;