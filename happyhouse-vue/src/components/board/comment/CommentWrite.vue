<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <textarea
        id="modicomment"
        name="modicomment"
        v-model="modicomment"
        cols="35"
        rows="2"
      ></textarea>
      <div>
        <b-button variant="outline-info" @click="updateComment">수정</b-button>
        <b-button variant="outline-danger" @click="updateCancel">취소</b-button>
      </div>
    </div>
    <div v-else class="regist_form">
      <textarea
        id="comment"
        name="comment"
        v-model="comment"
        cols="35"
        rows="2"
      ></textarea>
      <div>
        <b-button variant="outline-primary" @click="registComment">
          등록
        </b-button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";

const memberStore = "memberStore";
const commentStore = "commentStore";

export default {
  name: "comment-write",
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      username: null,
      comment: "",
      modicomment: this.modifyComment?.comment, //props 는 직접 변경 X
    };
  },
  props: {
    no: { type: String },
    modifyComment: { type: Object },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(commentStore, ["getComments"]),
    registComment() {
      if (this.comment) {
        if (!this.userInfo) {
          Swal.fire("ㅠ.ㅠ", "로그인이 필요한 기능입니다.", "warning");
          return;
        }
        this.username = this.userInfo.username;
        http
          .post("/comment/", {
            username: this.username,
            comment: this.comment,
            no: this.no,
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            Swal.fire("", msg, "success");
            // 작성글 지우기
            this.comment = "";
            // 도서평(댓글) 얻기.
            this.getComments(this.no);
          });
      } else {
        Swal.fire("ㅠ.ㅠ", "댓글을 작성해주세요.", "warning");
      }
    },
    updateComment() {
      http
        .put(`/comment`, {
          commentno: this.modifyComment.commentno,
          //  comment: this.modifyComment.comment, //에러나요 ~~
          //부모뷰에서 자식뷰로 전달한 데이터는 수정하지말고 따로 값을 변수화하여 사용
          comment: this.modicomment,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          Swal.fire("", msg, "success");

          // 댓글 얻기.
          this.getComments(this.modifyComment.no);

          this.$emit("modify-comment-cancel", false);
        });
    },
    updateCancel() {
      this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>

<style scoped>
.regist {
  padding: 10px;
}
.regist_form {
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 20px;
}

.regist_form > div {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  height: 76px;
  min-width: 45px;
  margin-left: 10px;
}

textarea {
  width: 90%;
  padding: 10px 20px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

button {
  font-size: x-small;
  background-color: white;
  margin-top: 5px;
}
</style>
