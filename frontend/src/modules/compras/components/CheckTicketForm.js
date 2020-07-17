import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors, BackLink} from '../../common';
import * as actions from '../actions';

const CheckTicketForm = () => {

    const [compraId, setCompraId] = useState('');
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const dispatch = useDispatch();
    const history = useHistory();
    let form

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.deliverTicket(compraId, creditCard,
                () => history.push('/compras/deliver-ticket-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (        

        <div>

            <BackLink/>

            <div className="mt-3">

                <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>

                <div className="card border-dark mt-3">
                    <h5 className="card-header">
                        <FormattedMessage id="project.compras.CheckForm.title"/>
                    </h5>
                    <div className="card-body">
                        <form ref={node => form = node}
                            className="needs-validation" noValidate 
                            onSubmit={e => handleSubmit(e)}>
                            <div className="form-group row">
                                <label htmlFor="quantity" className="col-md-3 col-form-label">
                                    <FormattedMessage id="project.global.fields.compraId"/>
                                </label>
                                <div className="col-md-4">
                                    <input type="text" id="compraId" className="form-control"
                                        value={compraId}
                                        onChange={e => setCompraId(e.target.value)}
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
                                        <FormattedMessage id="project.global.buttons.validar"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CheckTicketForm;