<template>
  <footer>
    <div class="container">
      <div>
        <img src="@/assets/ssafy_logo.png" alt="" width="100" />
      </div>
      <div class="footer-top">
        <span class="title">Find Us</span>
        <hr />
        <div class="location">
          <i class="bi bi-geo-alt-fill"></i>
          <span>(SSAFY) 서울시 강남구 테헤란로 멀티스퀘어</span>
        </div>
        <div class="phone">
          <i class="bi bi-telephone-fill"></i>
          <span>1544-9001</span>
        </div>
        <div class="email">
          <i class="bi bi-person-circle"></i>
          <a>김진회 kku64r@gmail.com</a>
          <br />
          <i class="bi bi-person-circle"></i>
          <a>황우진 hwangwoojin.dev@gmail.com</a>
        </div>
      </div>
    </div>
    <!-- footer-bottom -->
    <section class="footer-bottom">
      <span>Copyright 2022. by 김진회&황우진. All rights reserved.</span>
    </section>
  </footer>
</template>

<script>
export default {
  name: "FooterBar",
};
</script>

<style>
footer {
  background-color: rgb(220, 220, 220);
}

footer > .container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

footer > .container > div {
  margin: 20px 40px 20px 0;
}

.title {
  font-size: x-large;
}

.footer-top i,
.footer-top a {
  color: #09afdf;
}

.footer-top i {
  margin-right: 4px;
}

.footer-top a {
  text-decoration: none;
}

.footer-top hr {
  width: 100%;
}

.footer-top .logo,
.footer-top .info {
  display: flex;
  padding-left: 30px;
  padding-right: 30px;
}

.footer-top .logo {
  align-items: center;
}

.footer-top .info {
  flex-direction: column;
  justify-content: center;
}

.footer-top .title {
  font-size: x-large;
}

.footer-top .location,
.footer-top .phone,
.footer-top .email {
  font-size: 0.8rem;
  margin-top: 10px;
}

.footer-bottom {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70px;
  background-color: #2e3537;
  color: white;
}

@media screen and (max-width: 600px) {
  footer > .container > div {
    display: none;
  }
}

@media screen and (min-width: 600px) {
  .footer-bottom {
    display: none;
  }
}
</style>
