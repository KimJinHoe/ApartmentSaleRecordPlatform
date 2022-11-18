<template>
  <div id="app">
    <div class="screen">
      <navi-bar />
      <router-view />
      <footer-bar />
    </div>
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    FooterBar,
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

html {
  scroll-behavior: smooth;
}

#app {
  font-family: "Do Hyeon", sans-serif;
}

a.router-link-exact-active {
  font-weight: bold;
  color: #3aa4ca;
}

.screen {
  min-height: 100vh;
}
</style>
