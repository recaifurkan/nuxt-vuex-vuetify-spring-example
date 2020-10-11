const url = "http://localhost:8080/books";

const state = {
  token: [],
  user: {}
};

const mutations = {
  setToken(state, payload) {
    state.token = payload;
  },
  setUser(state, payload) {
    state.user = payload;
  }
};

const actions = {
  setUserData: async ({ commit }, payload) => {
    console.log("test");
    localStorage.setItem("Authorization", payload.token);
    commit("setUser", payload.user);
    commit("setToken", payload.token);
  }
};

const getters = {
  getToken(state) {
    return state.token;
  },
  getUser(state) {
    return state.user;
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
};
