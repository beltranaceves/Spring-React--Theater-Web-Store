import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';
import ScoreSelector from './ScoreSelector';

import {Errors, BackLink} from '../../common';
import * as actions from '../actions';
import {useParams} from 'react-router-dom';



const ReviewForm = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [puntuacion, setPuntuacion] = useState('');
    const [titulo, setTitulo] = useState('');
    const [comentario, setComentario] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    const {id} = useParams();
    let form

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.publish(puntuacion, titulo, comentario, id,
                () => history.goBack(),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }
    
    return (
        
        <div>
            <div style={{paddingBottom: "15px"}}>
            <BackLink/>
            </div>
            
            <Errors errors={backendErrors}
                onClose={() => setBackendErrors(null)}/>
            <div className="card border-dark">
                <h5 className="card-header">
                    <FormattedMessage id='project.cartelera.ReviewForm.title'/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="puntuacion" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.cartelera.score"/>
                            </label>
                            <div className="col-md-4">
                                <ScoreSelector className="custom-select my-2 mr-sm-2"
                                    value={puntuacion}
                                    onChange={e => setPuntuacion(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="titulo" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.cartelera.title"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="titulo" className="form-control"
                                    value={titulo}
                                    onChange={e => setTitulo(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                        <label htmlFor="comentario" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.cartelera.comment"/>
                            </label>
                            <div className="col-md-4">
                                <textarea id="comentario" rows="6" className="form-control"
                                    value={comentario}
                                    onChange={e => setComentario(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-lg btn-outline-dark">
                                    <FormattedMessage id="project.cartelera.publish"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}


export default ReviewForm;