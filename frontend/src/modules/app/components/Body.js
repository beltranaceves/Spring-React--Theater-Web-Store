import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import {FindCriticas, HistorialCriticas, PeliculaDetails, SesionDetails, ReviewForm} from '../../cartelera';
import {CompraCompleted, FindCompras, HistorialCompras, CheckTicketForm, CheckTicketFormCompleted} from '../../compras';
import users from '../../users';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const role = useSelector(users.selectors.getUserRole);
    // <!-- <Route exact path="/cartelera/criticas/:id"><CriticaDetails/></Route> -->

   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/cartelera/pelicula-details/:id"><PeliculaDetails/></Route>
                <Route exact path="/cartelera/sesion-details/:id"><SesionDetails/></Route>
                <Route exact path="/cartelera/review-form/:id"><ReviewForm/></Route>
                <Route exact path="/cartelera/find-orders/:id"><FindCriticas/></Route>
                <Route exact path="/cartelera/find-reviews-result"><HistorialCriticas/></Route>
                {loggedIn && role === "USER" && <Route exact path="/compras/compra-completed"><CompraCompleted/></Route>}
                {loggedIn && role === "USER" && <Route exact path="/compras/find-orders"><FindCompras/></Route>}
                {loggedIn && role === "USER" && <Route exact path="/compras/find-orders-result"><HistorialCompras/></Route>}
                {loggedIn && role === "ADMIN" && <Route exact path = "/compras/deliver-tickets"><CheckTicketForm/></Route>}
                {loggedIn && role === "ADMIN" && <Route exact path = "/compras/deliver-ticket-completed"><CheckTicketFormCompleted/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;

