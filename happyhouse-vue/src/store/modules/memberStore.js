import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";
import http from "@/api/http";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isFindPwdError: false,
    isRegistError: false,
    userInfo: null,
    userFindInfo: null,
  },
  getters: {
    checkuserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    FIND_USER_INFO: (state, user) => {
      state.userFindInfo = user;
    },
    SET_IS_FindPwd_ERROR: (state, isFindPwdError) => {
      state.isFindPwdError = isFindPwdError;
    },
    SET_IS_REGIST_ERROR: (state, isRegistError) => {
      state.isRegistError = isRegistError;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    userInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async userFindPwd({ commit }, user) {
      await http
        .post("/user/findpwd", user)
        .then(({ data }) => {
          commit("FIND_USER_INFO", data);
          if (typeof data == "object") {
            commit("SET_IS_FindPwd_ERROR", true);
          } else {
            commit("SET_IS_FindPwd_ERROR", false);
          }
        })
        .catch(() => {
          alert("Error 발생");
        });
    },
    async userRegist({ commit }, user) {
      await http
        .post("/user/register", user)
        .then(({ data }) => {
          if (typeof data == "object") {
            commit("SET_IS_REGIST_ERROR", true);
          } else {
            commit("SET_IS_REGIST_ERROR", false);
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    userDelete({ commit }, user) {
      http
        .delete("/user/" + user.userid)
        .then(() => {
          console.log(commit);
        })
        .catch((e) => {
          console.log("에러: " + e);
        });
    },
    async userUpdate({ commit }, user) {
      await http
        .put("/user/update", user)
        .then(() => {
          commit("SET_USER_INFO", user);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};

export default memberStore;
