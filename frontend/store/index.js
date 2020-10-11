import Vuex from 'vuex';

import books from './modules/books';

const store = new Vuex.Store({
    modules: {
        books
    }
});

export default () => {
    return store;
};