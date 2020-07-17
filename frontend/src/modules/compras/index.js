import * as actions from './actions';
import reducer from './reducer';
import * as selectors from './selectors';

export {default as BuyForm} from './components/BuyForm';
export {default as CompraCompleted} from './components/CompraCompleted';
export {default as FindCompras} from './components/FindCompras';
export {default as HistorialCompras} from './components/HistorialCompras';
export {default as CheckTicketForm} from './components/CheckTicketForm';
export {default as CheckTicketFormCompleted} from './components/CheckTicketFormCompleted';

export default {actions, reducer, selectors};