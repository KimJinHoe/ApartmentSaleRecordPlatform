<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <button class="loadbtn" :id="val" @click="change">
      최신 뉴스 불러오기
    </button>
    <b-row v-show="val">
      <b-col>
        <b-table-simple hover responsive striped>
          <b-thead>
            <b-tr>
              <b-th>no</b-th>
              <b-th>제목</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <b-tr v-for="i in 10" :key="i">
              <b-td>{{ page * 10 + i }}</b-td>
              <b-th class="text-left">
                <!-- <a :href="links[page * 10 + i]" class="news" target="_blank">
                  {{ titles[page * 10 + i] }}
                </a> -->
                <div class="news" @click="goPage(page * 10 + i)">
                  {{ titles[page * 10 + i] }}
                </div>
              </b-th>
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <div class="m-3 row justify-content-center">
          <ul class="pagination">
            <li class="page-item">
              <button @click="mvPage(0)" class="page-link">&#60;&#60;</button>
            </li>
            <li v-for="i in 10" :key="i" :id="'pg' + i" class="page-item">
              <button @click="mvPage(i - 1)" class="page-link">{{ i }}</button>
            </li>
            <li class="page-item">
              <button @click="mvPage(9)" class="page-link">&#62;&#62;</button>
            </li>
          </ul>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const newsStore = "newsStore";

export default {
  name: "NewsList",
  data() {
    return {
      test: "www.naver.com",
      page: 0,
      val: false,
      titles: [],
      links: [],
    };
  },
  computed: {
    ...mapState(newsStore, ["newsTextList", "newsLinkList"]),
  },
  async created() {
    await this.getNewsList();
    this.titles = this.newsTextList;
    this.links = this.newsLinkList;
  },
  methods: {
    ...mapActions(newsStore, ["getNewsList"]),
    change() {
      this.val = !this.val;
      var id = "pg" + (this.page + 1);
      document.getElementById(id).classList.add("active");
    },
    mvPage(pg) {
      var id = "pg" + (this.page + 1);
      document.getElementById(id).classList.remove("active");
      this.page = pg;
      id = "pg" + (this.page + 1);
      document.getElementById(id).classList.add("active");
    },
    goPage(idx) {
      console.log(this.links[idx]);
      window.open(this.links[idx]);
    },
  },
};
</script>

<style scope>
.page-link {
  font-size: 0.8rem;
}
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}

.loadbtn {
  background-color: #d4e4fc;
  border-color: #d4e4fc;
  border-block-end-color: skyblue;
  margin-bottom: 5px;
}
.news:hover {
  cursor: pointer;
  color: gray;
}
</style>
