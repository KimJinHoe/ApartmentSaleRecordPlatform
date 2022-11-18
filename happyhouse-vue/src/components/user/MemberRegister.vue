<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="primary" show>
          <h3>회원가입</h3>
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
            <b-form-group label="비밀번호*" label-for="userpwd">
              <b-form-input
                id="userpwd"
                type="password"
                v-model="user.userpwd"
                required
                placeholder="PASSWORD"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호 확인*" label-for="userpwd2">
              <b-form-input
                id="userpwd2"
                type="password"
                v-model="userpwd2"
                required
                placeholder="PASSWORD"
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
            <hr class="my-4" />
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
              확인
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
  name: "MemberRegister",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        email: null,
      },
      userpwd2: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["isRegistError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userRegist"]),
    async confirm() {
      // console.log(this.user.userid.search(" "));
      if (
        !this.user.userid ||
        !this.user.userpwd ||
        !this.user.username ||
        !this.user.email ||
        !this.userpwd2
      ) {
        Swal.fire("ㅠ.ㅠ", "빈 칸이 존재합니다.", "warning");
      } else if (
        this.user.userid
          .concat(this.user.userpwd)
          .concat(this.user.username)
          .concat(this.user.email)
          .concat(this.userpwd2)
          .search(" ") != -1
      ) {
        Swal.fire("ㅠ.ㅠ", "공백은 포함될 수 없습니다.", "warning");
      } else if (this.user.userpwd != this.userpwd2) {
        Swal.fire("ㅠ.ㅠ", "비밀번호를 확인하세요.", "warning");
      } else if (this.user.email.search("@") == -1) {
        Swal.fire("ㅠ.ㅠ", "이메일 형식을 지켜주세요.", "warning");
      } else {
        await this.userRegist(this.user);
        if (!this.isRegistError) {
          console.log(this.isRegistError);
          Swal.fire("ㅠ.ㅠ", "이미 존재하는 아이디입니다.", "warning");
        } else {
          console.log(this.isRegistError);
          Swal.fire("", "환영합니다!!", "success");
          this.movePage();
        }
      }
    },
    movePage() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
