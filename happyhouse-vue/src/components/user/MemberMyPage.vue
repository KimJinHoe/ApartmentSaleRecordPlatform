<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="primary" show>
          <h3>회원정보</h3>
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                readonly
                style="background-color: #d4e4fc"
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="가입일" label-for="joindate">
              <b-form-input
                id="joindate"
                v-model="user.joindate"
                readonly
                style="background-color: #d4e4fc"
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름*" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="공백 및 빈칸은 불가능합니다."
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일*" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 형식을 지켜주세요."
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="새 비밀번호" label-for="newpwd">
              <b-form-input
                id="newpwd"
                v-model="newpwd"
                type="password"
                placeholder="변경 희망 시, 입력해주세요."
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="새 비밀번호 확인" label-for="newpwd2">
              <b-form-input
                id="newpwd2"
                v-model="newpwd2"
                type="password"
                placeholder="변경 희망 시, 입력해주세요."
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="현재 비밀번호*" label-for="userpwd">
              <b-form-input
                id="userpwd"
                v-model="user.userpwd"
                type="password"
                placeholder="필수"
                required
                @keyup.enter="updateUser"
              ></b-form-input>
            </b-form-group>
          </b-form>
          <hr class="my-4" />
          <b-button class="m-1" variant="primary" @click="movePage">
            취소
          </b-button>
          <b-button class="m-1" variant="primary" @click="updateUser">
            저장
          </b-button>
          <b-button class="m-1" variant="danger" @click="checkMsg">
            회원탈퇴
          </b-button>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import Swal from "sweetalert2";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
        username: null,
        joindate: null,
        email: null,
      },
      newpwd: null,
      newpwd2: null,
    };
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.joindate = this.userInfo.joindate;
    this.user.email = this.userInfo.email;
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    ...mapActions(memberStore, ["userDelete", "userUpdate"]),
    logout() {
      this.SET_IS_LOGIN = false;
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    deleteUser() {
      if (this.userInfo.userpwd == this.user.userpwd) {
        this.userDelete(this.user);
        this.logout();
        Swal.fire("ㅜ.ㅜ", "우리 언젠가 또 만나요..", "success");
      } else {
        Swal.fire("ㅠ.ㅠ", "현재 비밀번호가 일치하지 않습니다.", "warning");
      }
    },
    checkMsg() {
      Swal.fire({
        title: "정말 회원 탈퇴하시겠습니까?",
        text: "탈퇴 시 계정 복구가 불가능합니다!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "OK",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteUser();
        }
      });
    },
    async updateUser() {
      if (this.userInfo.userpwd != this.user.userpwd) {
        Swal.fire("ㅠ.ㅠ", "현재 비밀번호가 일치하지 않습니다.", "warning");
      } else if (!this.user.username || !this.user.email) {
        Swal.fire("ㅠ.ㅠ", "빈 칸이 존재합니다.", "warning");
      } else if (
        this.user.userid
          .concat(this.user.userpwd)
          .concat(this.user.username)
          .concat(this.user.email)
          .concat(this.newpwd)
          .concat(this.newpwd2)
          .search(" ") != -1
      ) {
        Swal.fire("ㅠ.ㅠ", "공백은 포함될 수 없습니다.", "warning");
      } else if (this.user.email.search("@") == -1) {
        Swal.fire("ㅠ.ㅠ", "이메일 형식을 지켜주세요.", "warning");
      } else if (!!this.newpwd || !!this.newpwd2) {
        //새 비밀번호가 있다면
        if (this.newpwd != this.newpwd2) {
          Swal.fire("ㅠ.ㅠ", "새 비밀번호를 확인해주세요.", "warning");
        } else {
          this.user.userpwd = this.newpwd;
          await this.userUpdate(this.user);
          Swal.fire("", "회원 정보가 수정되었습니다.", "success");
          this.movePage();
        }
      } else {
        await this.userUpdate(this.user);
        Swal.fire("", "회원 정보가 수정되었습니다.", "success");
        this.movePage();
      }
    },
    movePage() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
