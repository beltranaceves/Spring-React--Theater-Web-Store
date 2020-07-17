import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory, useParams} from 'react-router-dom';
import * as actions from '../actions';

const FindCriticas = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const {id} = useParams();

    useEffect(() => {

        dispatch(actions.getCriticas({peliculaId: id, page: 0}), 
        history.push('/cartelera/find-reviews-result'));

    });

    return null;

}

export default FindCriticas;
