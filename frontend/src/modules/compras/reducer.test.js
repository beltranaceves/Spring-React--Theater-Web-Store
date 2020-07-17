import reducer from './reducer';
import * as actions from './actions';

test('BUY_COMPLETED', () => {

    const compraId = 1;

    const initialState = {lastCompraId: null};

    const state = reducer(initialState, actions.buyCompleted(compraId));

    expect(state.lastCompraId).toEqual(compraId);

});