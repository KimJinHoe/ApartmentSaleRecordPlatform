<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">
          글쓰기
        </b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        ></b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import Swal from "sweetalert2";

const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "no", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "author", label: "작성자", tdClass: "tdClass" },
        { key: "datetime", label: "작성일", tdClass: "tdClass" },
        { key: "view", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    http.get(`/notice`).then(({ data }) => {
      this.articles = data;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    moveWrite() {
      if (!this.userInfo) {
        Swal.fire("ㅠ.ㅠ", "로그인이 필요한 기능입니다.", "warning");
      } else {
        this.$router.push({ name: "boardRegister" });
      }
    },
    viewArticle(article) {
      var no = article.no + "";
      this.$router.push({
        name: "boardDetail",
        params: { articleno: no },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
