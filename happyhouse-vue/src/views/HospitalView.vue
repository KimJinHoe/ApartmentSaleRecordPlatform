<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">주변 안심 병원</h3>
    <search-hospital v-on:onFetchItems="fetchItems"></search-hospital>
    <b-alert show><h3>안심 병원 목록</h3></b-alert>
    <section id="index_section">
      <div class="card col-sm-12 mt-1">
        <div class="card-body">
          <table v-if="ishospital" class="table mt-2">
            <colgroup>
              <col width="*" />
              <col width="300" />
              <col width="150" />
            </colgroup>
            <thead>
              <tr>
                <th class="text-center">병원 이름</th>
                <th class="text-center">주소</th>
                <th class="text-center">휴대폰 번호</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="info in hospital" :key="info.no">
                <td>{{ info.name }}</td>
                <td>{{ info.address }}</td>
                <td>{{ info.phone }}</td>
              </tr>
            </tbody>
          </table>
          <no-result v-else />
        </div>
      </div>
    </section>
    <br />
    <notice-modal
      v-bind:message="alertMessage"
      v-bind:messageCount="messageCount"
      v-on:endMessage="resetAlertMessage"
    ></notice-modal>
  </b-container>
</template>

<script>
import SearchHospital from "@/components/search/SearchHospital.vue";
import NoticeModal from "@/components/layout/NoticeModal.vue";
import NoResult from "@/components/search/NoResult.vue";

export default {
  name: "HomeView",
  data: function () {
    return {
      ishospital: false,
      isSelectedhospital: false,
      hospital: [],
      selectedhospital: {
        name: null,
        address: null,
        phone: null,
      },
      alertMessage: "",
      messageCount: 0,
    };
  },
  props: {
    msg: String,
  },
  components: {
    "search-hospital": SearchHospital,
    "notice-modal": NoticeModal,
    "no-result": NoResult,
  },
  methods: {
    fetchItems(items) {
      if (items.length === 0) {
        this.alertMessage = "해당 주소의 아파트 거래 내역이 없습니다.";
        this.messageCount++;
        this.ishospital = false;
        return;
      }
      this.hospital = items;
      this.ishospital = true;
    },
    resetAlertMessage() {
      this.alertMessage = "";
    },
  },
};
</script>

<style scoped>
h3 {
  margin-bottom: 20px;
}

.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}

.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}

.card {
  max-height: 450px;
  overflow: scroll;
}

.item-selected {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100px;
  padding: 16px;
  background-color: #feb609;
  color: #343a40;
  border-radius: 0.25rem;
  margin-bottom: 5px;
}

.item-selected section:nth-child(1) {
  font-size: 3rem;
  font-weight: bold;
}

.item-selected section:nth-child(2) {
  text-align: right;
}

.clickable-th:hover {
  cursor: pointer;
  border-color: #007bff;
  transition: color 1s;
}
</style>
