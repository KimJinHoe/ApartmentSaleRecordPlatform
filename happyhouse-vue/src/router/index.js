import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkuserInfo = store.getters["memberStore/checkuserInfo"];
  const userInfo = store._actions["memberStore/userInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkuserInfo == null && token) {
    await userInfo(token);
  }
  if (checkuserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signin",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signup",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "findpwd",
        name: "findpwd",
        component: () => import("@/components/user/MemberFindPwd.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/news",
    name: "news",
    component: () => import("@/views/NewsView.vue"),
    redirect: "/news/list",
    children: [
      {
        path: "list",
        name: "newsList",
        component: () => import("@/components/news/NewsList.vue"),
      },
    ],
  },
  {
    path: "/commercial",
    name: "commercial",
    component: () => import("@/views/CommercialView.vue"),
  },
  {
    path: "/hospital",
    name: "hospital",
    component: () => import("@/views/HospitalView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
