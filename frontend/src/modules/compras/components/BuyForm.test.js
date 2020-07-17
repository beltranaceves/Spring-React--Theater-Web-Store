import React from 'react';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import {render, fireEvent} from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'
import {createMemoryHistory} from 'history'

import BuyForm from './BuyForm';
import {IntlProvider} from 'react-intl';
import messages from '../../../i18n/messages';
import {Router} from 'react-router-dom';
import * as actions from '../actions';

const renderComponent = (component, initialState={})=> {

    const store = createStore(() => initialState);
    store.dispatch = jest.fn();
    const history = createMemoryHistory();

    return {history, ...render(
        <Provider store={store}>
            <IntlProvider locale="en" messages={messages['en']}>
                <Router history={history}>
                    {component}
                </Router>
            </IntlProvider>
        </Provider>
    )};
}

afterEach(() => actions.buy.mockRestore());

test('buy - success', () => {

    const sesionId = '1';

    const initialState = {cartelera: {sesion: {butacasLibres : 1}}};

    const buySpy = jest.spyOn(actions, 'buy').mockImplementation(
        (_creditCard, _sesion, _quantity, onSuccess, _onErrors) => 
            onSuccess());

    const {getByDisplayValue, getByLabelText, getByRole, history} = renderComponent(<BuyForm sesion={sesionId}/>,
        initialState);
 
    const quantitySelector = getByDisplayValue('0');
    const creditCardInput = getByLabelText('Credit card number');
    const buyButton = getByRole('button');
    const quantity = '2';
    const creditCard = "1234567890123456"; 

    fireEvent.change(quantitySelector, {target: {value: quantity}});
    fireEvent.change(creditCardInput, {target: {value: creditCard}});

    expect(quantitySelector.value).toEqual(quantity);
    expect(creditCardInput.value).toEqual(creditCard);

    fireEvent.click(buyButton);

    expect(buySpy.mock.calls[0][0]).toEqual(creditCard);
    expect(buySpy.mock.calls[0][1]).toEqual(sesionId);
    expect(buySpy.mock.calls[0][2]).toEqual(quantity);
    expect(history.length).toEqual(2);
    expect(history.location.pathname).toEqual('/compras/compra-completed');

});

test('buy - backend errors', () => {

    const sesionId = '1';

    const initialState = {cartelera: {sesion: {butacasLibres : 1}}};

    const backendError = "Some backend error";

    jest.spyOn(actions, 'buy').mockImplementation(
        (_creditCard, _sesion, _quantity, _onSuccess, onErrors) => 
            onErrors({globalError: backendError}));

    const {getByDisplayValue, getByLabelText, getByRole, container, history} =
        renderComponent(<BuyForm sesion={sesionId}/>,
        initialState);
 
    const quantitySelector = getByDisplayValue('0');
    const creditCardInput = getByLabelText('Credit card number');
    const buyButton = getByRole('button');
    const quantity = '2';
    const creditCard = "1234567890123456"; 

    fireEvent.change(quantitySelector, {target: {value: quantity}});
    fireEvent.change(creditCardInput, {target: {value: creditCard}});
    fireEvent.click(buyButton);
    
    expect(container).toHaveTextContent(backendError);
    expect(history.length).toEqual(1);
    expect(history.location.pathname).toEqual('/');

});