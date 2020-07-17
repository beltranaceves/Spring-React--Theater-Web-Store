import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';
import NumberTicketsSelector from './NumberTicketsSelector';

import PropTypes from 'prop-types';
import {Errors} from '../../common';
import * as actions from '../actions';

import * as cartelera from '../../cartelera/selectors';

const BuyForm = ({sesion}) => {

    const butacasLibres = useSelector(cartelera.getSesion).butacasLibres;

    const dispatch = useDispatch();
    const history = useHistory();
    const [creditCard, setCreditCard] = useState('');
    const [quantity, setQuantity] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.buy(creditCard, sesion, quantity, 
                () => history.push('/compras/compra-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }
    
    if (butacasLibres === 0) {
        
        return null;
    }
    
    return (

        <div>
            <Errors errors={backendErrors}
                onClose={() => setBackendErrors(null)}/>
            <div className="card border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.BuyForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="quantity" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.quantity"/>
                            </label>
                            <div className="col-md-4">
                                <NumberTicketsSelector className="custom-select my-2 mr-sm-2"
                                    value={quantity}
                                    onChange={e => setQuantity(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="creditCard" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.creditCard"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="creditCard" className="form-control"
                                    value={creditCard}
                                    onChange={e => setCreditCard(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-lg btn-outline-dark">
                                    <FormattedMessage id="project.global.buttons.buy"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

BuyForm.propTypes = {
    sesion: PropTypes.string.isRequired
};

export default BuyForm;