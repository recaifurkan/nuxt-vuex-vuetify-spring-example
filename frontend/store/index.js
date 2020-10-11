import Vuex from "vuex";

import books from "./modules/books";
import localAuth from "./modules/auth";

const store = new Vuex.Store({
  state: {
    env: {
      URL: "http://localhost:8080"
    }
  },
  modules: {
    books,
    localAuth
  }
});

export default () => {
  return store;
};
