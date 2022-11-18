import http from "@/api/http";

const commentStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
  },
  mutations: {
    SET_COMMENT_LIST(state, comments) {
      state.comments = comments;
    },
  },
  actions: {
    getComments({ commit }, no) {
      http.get("/comment/" + no).then(({ data }) => {
        commit("SET_COMMENT_LIST", data);
      });
    },
  },
};

export default commentStore;
