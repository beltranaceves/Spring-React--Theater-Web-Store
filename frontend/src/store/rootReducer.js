import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import cartelera from '../modules/cartelera';
import compras from '../modules/compras';


const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    cartelera: cartelera.reducer,
    compras: compras.reducer
});

export default rootReducer;
