import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';

import * as actions from './actions';
import backend from '../../backend';

const middlewares = [thunk]
const mockStore = configureMockStore(middlewares);

afterEach(() => backend.compraService.buy.mockRestore());

test('buy - success', () => {

    
    const compraId = 1;
    const backendBuySpy = jest.spyOn(backend.compraService, 'buy').mockImplementation(
        (_sesionId, _quantity, _creditCard, onSuccess, _onErrors) => 
            onSuccess({id: compraId}));
    
    const sesionId = '1';
    const quantity = '2';
    const creditCard = "1234567890123456";
    const onSuccess = jest.fn(); 
    const onErrors = jest.fn();
    const action = actions.buy(creditCard, sesionId, quantity, onSuccess, onErrors);
    const expectedActions = [actions.buyCompleted(compraId)];
    const store = mockStore({});

    store.dispatch(action);

    expect(backendBuySpy.mock.calls[0][0]).toEqual(sesionId);
    expect(backendBuySpy.mock.calls[0][1]).toEqual(quantity);
    expect(backendBuySpy.mock.calls[0][2]).toEqual(creditCard);

    expect(store.getActions()).toEqual(expectedActions);
    expect(onSuccess).toHaveBeenCalled();
    expect(onErrors).not.toHaveBeenCalled();

});

test('buy - backend errors', () => {

    const backendErrors = {globalError: "Some backend error"};

    jest.spyOn(backend.compraService, 'buy').mockImplementation(
        (_sesionId, _quantity, _creditCard, _onSuccess, onErrors) => 
            onErrors(backendErrors));
    
    const sesionId = '1';
    const quantity = '2';
    const creditCard = "1234567890123456";
    const onSuccess = jest.fn(); 
    const onErrors = jest.fn();
    const action = actions.buy(creditCard, sesionId, quantity, onSuccess, onErrors);
    const expectedActions = [];
    const store = mockStore({});

    store.dispatch(action);

    expect(store.getActions()).toEqual(expectedActions);
    expect(onSuccess).not.toHaveBeenCalled();
    expect(onErrors).toHaveBeenCalled();
    expect(onErrors.mock.calls[0][0]).toEqual(backendErrors);

});