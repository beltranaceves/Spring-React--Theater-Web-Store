import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';


const Compras = ({compras}) => (

    <table className="table table-striped table-hover">

        <thead className="thead-dark text-center">
            <tr>
                <th  scope="col">
                    <FormattedMessage id='project.global.fields.fechaCompra'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.sesion'/>
                </th>
                <th  scope="col">
                    <FormattedMessage id='project.global.fields.date'/>
                </th>
                <th  scope="col">
                    <FormattedMessage id='project.global.fields.time'/>
                </th>
                <th  scope="col">
                    <FormattedMessage id='project.global.fields.cine'/>
                </th>
                <th  scope="col">
                    <FormattedMessage id='project.global.fields.quantity'/>
                </th>
               
                <th  scope="col">
                    <FormattedMessage id='project.global.fields.price'/>
                </th>
            </tr>
        </thead>

        <tbody className="text-center">
            {compras.map(compra => 
                <tr key={compras.indexOf(compra)}>
                    <td>
                        <FormattedDate value={compra.fecha}/> - <FormattedTime value={compra.fecha}/>
                    </td>
                    <td>{compra.tituloPelicula}</td>
                    <td>
                        <FormattedDate value={compra.fechaSesion}/>
                    </td>
                    <td>
                        <FormattedTime value={compra.fechaSesion}/>
                    </td>
                    <td>{compra.cine}</td>
                    <td>{compra.localidades}</td>
                    <td><FormattedNumber minimumFractionDigits= '2' maximumFractionDigits= '2' value={compra.precioTotal}/>€</td>
                </tr>
            )}
        </tbody>

    </table>

);

Compras.propTypes = {
    compras: PropTypes.array.isRequired
};

export default Compras;