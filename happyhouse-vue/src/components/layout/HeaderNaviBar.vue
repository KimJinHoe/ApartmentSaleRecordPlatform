<template>
  <div>
    <!--로그인바-->
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="header-btn" v-if="userInfo">
          <div class="logout-btn" @click.prevent="onClickLogout">
            <router-link to="#" class="link align-self-center">
              로그아웃
            </router-link>
          </div>
          <div class="logout-btn">
            <router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center"
            >
              마이페이지
            </router-link>
          </div>
          <b-nav-item class="align-self-center">
            <b-avatar
              src="https://placekitten.com/300/300"
              size="2em"
            ></b-avatar>
            {{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="header-btn" v-else>
          <div class="logout-btn">
            <router-link :to="{ name: 'signin' }" class="link">
              로그인
            </router-link>
          </div>
          <div class="logout-btn">
            <router-link :to="{ name: 'signup' }" class="link">
              회원가입
            </router-link>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <div class="container">
      <!--HappyHouse 이미지-->
      <a href="/">
        <div class="text-center mb-1" style="height: 300px">
          <div class="main-img">
            <img src="@/assets/mainhouse.jpg" alt="" />
          </div>
          <div class="main-text">
            HAPPY HOUSE
            <br />
            SEOUL 12
          </div>
        </div>
      </a>
      <!-- <a href="/"><img id="logo" src="@/assets/happyhouse.png" /></a> -->
      <!--메뉴 시작-->
      <nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
        <b-navbar-toggle target="nav-collapse2"></b-navbar-toggle>
        <b-collapse id="nav-collapse2" is-nav>
          <b-navbar-nav>
            <b-nav-item href="#">
              <router-link :to="{ name: 'home' }" class="link">홈</router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link :to="{ name: 'board' }" class="link">
                QnA
              </router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link :to="{ name: 'news' }" class="link">
                뉴스
              </router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link :to="{ name: 'commercial' }" class="link">
                주변상권
              </router-link>
            </b-nav-item>
            <b-nav-item href="#">
              <router-link :to="{ name: 'hospital' }" class="link">
                안심 병원
              </router-link>
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </nav>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
.link {
  color: white;
}

.link:hover {
  text-decoration-line: none;
  font-weight: bold;
}

.header-top {
  height: 50px;
  background-color: rgb(54, 58, 63);
}

.header-btn {
  height: 50px;
  width: 40%;
  background-color: rgb(54, 58, 63);
  margin-left: 15%;
}

.header-btn div {
  display: inline-block;
  margin-top: 10px;
  height: 30px;
  width: 80px;
  text-align: center;
  /* 모서리 둥글게 */
  border-radius: 3px 3px 3px 3px;
  border: 1px solid rgb(29, 29, 29);
  background-color: rgb(43, 43, 43);
  margin-right: 10px;
}

.header-btn div a {
  width: 200px;
  font-size: small;
  color: white;
  text-decoration: none;
}

#logo {
  width: 400px;
}
.main-img img {
  position: relative;
  width: 100%;
  height: 300px;
  object-fit: cover;
  filter: brightness(40%);
  /* 이미지공백없애기 */
  display: block;
}

.main-text {
  color: white;
  font-size: xx-large;
  /* 가운데정렬 */
  top: -10rem;
  left: 50%;
  position: relative;
  transform: translate(-50%, -50%);
  text-decoration: none;
}

.container a:hover {
  text-decoration: none;
}

#nav-collapse > ul {
  height: 100%;
}

#nav-collapse2 > ul {
  height: 100%;
}

#app > div > div:nth-child(1) > nav {
  position: sticky;
  top: 0;
  z-index: 999;
}
</style>
