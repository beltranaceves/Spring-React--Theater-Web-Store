import {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {useHistory} from 'react-router-dom';
import * as selectors from '../../users/selectors'
import * as actions from '../actions';

const FindCompras = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const user = useSelector(selectors.getUser);

    useEffect(() => {

        dispatch(actions.getCompras({userId: user.id, page: 0}));
        history.push('/compras/find-orders-result');

    });

    return null;

}

export default FindCompras;
