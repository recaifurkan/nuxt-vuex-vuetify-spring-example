import axios from "axios";
const url = "http://localhost:8080/books";

const getHeaders = () => {
  let token = localStorage.getItem("Authorization");
  // console.log(token);
  return {
    headers: {
      Authorization: token
    }
  };
};

const state = {
  books: []
};

const mutations = {
  setBooks(state, payload) {
    state.books = payload;
  },
  addBook(state, payload) {
    state.books.push(payload);
  },
  updateBook(state, payload) {
    let book = state.books.find(book => book.id == payload.id);
    Object.assign(book, payload);
  },
  deleteBook(state, payload) {
    state.books = state.books.filter(book => book.id != payload.id);
  }
};

const actions = {
  setBooks: async (context, payload) => {
    let rsp = await axios.get(url, getHeaders());
    console.log(rsp);
    const instances = rsp.data;
    context.commit("setBooks", instances);
  },

  addBook: async ({ commit }, payload) => {
    let rsp = await axios.post(url, payload, getHeaders());
    const instance = rsp.data;
    commit("addBook", instance);
  },

  updateBook: async ({ commit }, payload) => {
    let rsp = await axios.put(url + "/" + payload.id, payload, getHeaders());
    const instance = rsp.data;
    commit("updateBook", instance);
  },
  deleteBook: async ({ commit }, payload) => {
    let rsp = await axios.delete(url + "/" + payload.id, getHeaders());
    const instance = rsp.data;
    commit("deleteBook", instance);
  }
};

const getters = {
  allBooks(state) {
    return state.books;
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
};
