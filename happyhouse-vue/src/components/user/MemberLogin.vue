<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="primary" show>
          <h3>로그인</h3>
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <hr class="my-4" />
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
            >
              로그인
            </b-button>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="movePage2"
            >
              비밀번호 찾기
            </b-button>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="movePage"
            >
              회원가입
            </b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "userInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.userInfo(token);
        this.$router.push({ name: "home" });
      } else {
        Swal.fire("로그인실패", "아이디와 비밀번호를 확인하세요.", "warning");
      }
    },
    movePage() {
      this.$router.push({ name: "signup" });
    },
    movePage2() {
      this.$router.push({ name: "findpwd" });
    },
  },
};
</script>

<style></style>
