<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
        >
          글수정
        </b-button>
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">
          글삭제
        </b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.no}.
          ${article.title} [${article.view}]</h3><div><h6>${article.author}</div><div>${article.datetime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
        <comment-write :no="this.no" />
        <comment-write
          v-if="isModifyShow && this.modifyComment != null"
          :modifyComment="this.modifyComment"
          @modify-comment-cancel="onModifyCommentCancel"
        />
        <comment-view
          v-for="(comment, index) in comments"
          :key="index"
          :comment="comment"
          @m-comment="onModifyComment"
        />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import { mapState, mapGetters, mapActions } from "vuex";
import Swal from "sweetalert2";
import CommentWrite from "@/components/board/comment/CommentWrite.vue";
import CommentView from "@/components/board/comment/CommentView.vue";

const memberStore = "memberStore";
const commentStore = "commentStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      no: null,
      isModifyShow: false,
      modifyComment: Object,
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(commentStore, ["comments"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  components: {
    CommentWrite,
    CommentView,
  },
  created() {
    this.no = this.$route.params.articleno;
    this.getComments(this.no);
    http.get(`/notice/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    ...mapActions(commentStore, ["getComments"]),
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      if (
        !!this.userInfo &&
        (this.userInfo.userid === this.article.author ||
          this.userInfo.userid == "admin")
      ) {
        this.$router.replace({
          name: "boardModify",
          params: { articleno: this.article.no },
        });
      } else {
        Swal.fire("ㅠ.ㅠ", "권한이 없습니다.", "warning");
      }
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (
        !!this.userInfo &&
        (this.userInfo.userid === this.article.author ||
          this.userInfo.userid == "admin")
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
            this.$router.replace({
              name: "boardDelete",
              params: { articleno: this.article.no },
            });
          }
        });
      } else {
        Swal.fire("ㅠ.ㅠ", "권한이 없습니다.", "warning");
      }
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
