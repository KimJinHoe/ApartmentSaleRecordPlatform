<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show>
          <h3>비밀번호 찾기</h3>
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디*" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="ID"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름*" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="Name"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일*" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                placeholder="Email"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="movePage"
            >
              취소
            </b-button>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
            >
              찾기
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
  name: "MemberFindPwd",
  data() {
    return {
      user: {
        userid: null,
        username: null,
        email: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userFindInfo", "isFindPwdError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userFindPwd"]),
    async confirm() {
      await this.userFindPwd(this.user);
      if (!this.isFindPwdError) {
        Swal.fire("ㅠ.ㅠ", "해당하는 계정이 존재하지 않습니다.", "warning");
      } else {
        Swal.fire(
          "",
          `아이디: ` +
            this.userFindInfo.userid +
            `     비밀번호: ` +
            this.userFindInfo.userpwd,
          "success",
        );
      }
    },
    movePage() {
      this.$router.push({ name: "signin" });
    },
  },
};
</script>

<style></style>
