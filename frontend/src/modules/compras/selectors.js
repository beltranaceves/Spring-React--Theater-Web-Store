const getModuleState = state => state.compras;

export const getLastCompraId = state =>
    getModuleState(state).lastCompraId;

export const getCompras = state =>
    getModuleState(state).compras;