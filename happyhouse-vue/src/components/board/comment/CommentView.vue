<template>
  <div v-show="isShow" class="comment">
    <div class="head">{{ comment.username }} ({{ comment.time }})</div>
    <div class="content" v-html="enterToBr(comment.comment)"></div>
    <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
    <div class="cbtn">
      <label class="label" @click="modifyCommentView">수정</label>
      |
      <label class="label" @click="deleteComment">삭제</label>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapActions, mapState } from "vuex";
import Swal from "sweetalert2";

const commentStore = "commentStore";
const memberStore = "memberStore";

export default {
  name: "comment-view",
  data() {
    return {
      isShow: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    comment: Object,
  },
  methods: {
    ...mapActions(commentStore, ["getComments"]),
    modifyCommentView() {
      if (!this.userInfo) {
        Swal.fire("ㅠ.ㅠ", "로그인이 필요한 기능입니다.", "warning");
        return;
      }
      if (
        this.userInfo.username == this.comment.username ||
        this.userInfo.userid == "admin"
      ) {
        this.$emit("m-comment", {
          commentno: this.comment.commentno,
          comment: this.comment.comment,
          no: this.comment.no,
        });
      } else {
        Swal.fire("ㅠ.ㅠ", "본인의 댓글만 삭제할 수 있습니다.", "warning");
      }
    },
    deleteComment() {
      if (!this.userInfo) {
        Swal.fire("ㅠ.ㅠ", "로그인이 필요한 기능입니다.", "warning");
        return;
      }
      if (
        this.userInfo.username == this.comment.username ||
        this.userInfo.userid == "admin"
      ) {
        Swal.fire({
          title: "삭제하시겠습니까?",
          text: "이 작업은 복구가 불가능합니다.",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Yes",
        }).then((result) => {
          if (result.isConfirmed) {
            http
              .delete(`/comment/${this.comment.commentno}`)
              .then(({ data }) => {
                let msg = "삭제 처리시 문제가 발생했습니다.";
                if (data === "success") {
                  msg = "삭제가 완료되었습니다.";
                }
                Swal.fire("", msg, "success");
                // 도서평(댓글) 얻기.
                this.getComments(this.$route.params.articleno);
              });
          }
        });
      } else {
        Swal.fire("ㅠ.ㅠ", "본인의 댓글만 삭제할 수 있습니다.", "warning");
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

<style>
.label:hover {
  cursor: pointer;
}
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px 20px;
  margin: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>
