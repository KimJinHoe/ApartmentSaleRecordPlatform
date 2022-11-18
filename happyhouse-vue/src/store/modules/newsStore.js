import http from "@/api/http";

const newsStore = {
  namespaced: true,
  state: {
    newsTextList: null,
    newsLinkList: null,
  },
  mutations: {
    SET_NEWS_LIST: (state, list) => {
      state.newsTextList = list.slice(0, 100);
      state.newsLinkList = list.slice(100);
    },
  },
  actions: {
    async getNewsList({ commit }) {
      await http
        .get("/news/list")
        .then(({ data }) => {
          commit("SET_NEWS_LIST", data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};

export default newsStore;
