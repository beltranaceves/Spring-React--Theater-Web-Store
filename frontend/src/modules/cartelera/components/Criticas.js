import React from 'react';
import {FormattedDate, FormattedTime, FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';


const Criticas = ({criticas}) => (

    <div>
        <div style={{justifyContent:'center', alignItems:'center'}}>
            
            {criticas.map(critica => 

                <span key={criticas.indexOf(critica)} style={{width: '100%'}}>
                    <div className="card border-dark text-center mt-3">
                        <div className="card-body">
                            <FormattedDate value={critica.fecha}/> - <FormattedTime value={critica.fecha}/>
                            <h6 className="card-text text-muted">
                            <FormattedMessage id='project.cartelera.score'/>:  {critica.puntuacion}/5</h6>
                            <h4 className="card-title display-5">{critica.titulo}</h4>
                            <p className="card-text">{critica.comentario}</p>
                        </div>
                    </div>
                </span>
                
            )}
        </div>
    </div>
    



);

Criticas.propTypes = {
    criticas: PropTypes.array.isRequired
};

export default Criticas;