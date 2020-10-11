import axios from "axios";
const url = "http://localhost:8080/books";

const state = {
    books : []


}

const mutations = {
    setBooks(state, payload) {
        state.books = payload;
    },
    addBook(state, payload) {
        state.books.push(payload);
        

    },
     updateBook(state, payload) {
         let book = state.books.find((book) => book.id == payload.id);
         Object.assign(book, payload);
    },
    deleteBook(state, payload) {
        state.books = state.books.filter((book) => book.id != payload.id);
    },
     
     
    
    


}

const actions = {
    setBooks: async ({ commit }, payload) => {
         let rsp = await axios.get(url);
        const instances = rsp.data;
        commit('setBooks' , instances)
        
    },

    addBook: async ({ commit }, payload) => {
       
        let rsp = await axios.post(url,payload);
        const instance = rsp.data;
        commit('addBook' , instance)
        
    },

    updateBook: async ({ commit }, payload) => {
       
        let rsp = await axios.put(url + '/'+ payload.id,payload);
        const instance = rsp.data;
        commit('updateBook' , instance)
        
    },
     deleteBook: async ({ commit }, payload) => {
       
        let rsp = await axios.delete(url + '/'+ payload.id);
        const instance = rsp.data;
        commit('deleteBook' , instance)
        
    },
    

}

const getters = {
    allBooks(state) {
        return state.books;
        
    }

}

export default  {
    namespaced:true,
    state,
    actions,
    mutations,
    getters
}