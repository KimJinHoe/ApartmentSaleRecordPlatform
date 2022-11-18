<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">아파트 거래 내역</h3>
    <article
      id="item-selected"
      class="item-selected"
      v-show="isSelectedHouseInfo"
    >
      <section>
        <div>{{ selectedHouseInfo.aptName }}</div>
      </section>
      <section>
        <div>{{ selectedHouseInfo.address }}</div>
        <div>{{ selectedHouseInfo.buildYear }} 완공</div>
        <div>{{ selectedHouseInfo.recentPrice }} 만원</div>
      </section>
    </article>
    <map-item
      ref="mapItem"
      v-bind:items="houseinfo"
      v-on:onCurrentItem="setCurrentItem"
    ></map-item>
    <br />
    <search-apt v-on:onFetchItems="fetchItems"></search-apt>
    <b-alert show><h3>아파트 목록</h3></b-alert>
    <section id="index_section">
      <div class="card col-sm-12 mt-1">
        <div class="card-body">
          <table v-if="isHouseInfo" class="table mt-2">
            <colgroup>
              <col width="150" />
              <col width="*" />
              <col width="120" />
              <col width="120" />
            </colgroup>
            <thead>
              <tr>
                <th>아파트이름</th>
                <th class="text-center">주소</th>
                <th class="clickable-th" v-on:click="alignBuildYear">
                  건축연도
                </th>
                <th class="clickable-th" v-on:click="alignPrice">
                  최근거래금액
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="info in houseinfo" :key="info.address">
                <td>{{ info.aptName }}</td>
                <td>
                  {{
                    `${info.sidoName} ${info.gugunName} ${info.dongName} ${info.jibun}`
                  }}
                </td>
                <td>{{ info.buildYear }}</td>
                <td>{{ info.recentPrice }} 만원</td>
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
import MapItem from "@/components/map/MapItem.vue";
import SearchApt from "@/components/search/SearchApt.vue";
import NoticeModal from "@/components/layout/NoticeModal.vue";
import NoResult from "@/components/search/NoResult.vue";

export default {
  name: "HomeView",
  data: function () {
    return {
      isHouseInfo: false,
      isSelectedHouseInfo: false,
      houseinfo: [],
      selectedHouseInfo: {
        aptName: null,
        buildYear: null,
        address: null,
        recentPrice: null,
      },
      isBuildYearAsc: false,
      isPriceAsc: false,
      alertMessage: "",
      messageCount: 0,
    };
  },
  props: {
    msg: String,
  },
  components: {
    "map-item": MapItem,
    "search-apt": SearchApt,
    "notice-modal": NoticeModal,
    "no-result": NoResult,
  },
  methods: {
    setCurrentItem(item) {
      const {
        aptName,
        sidoName,
        gugunName,
        dongName,
        jibun,
        buildYear,
        recentPrice,
      } = item;
      this.selectedHouseInfo = {
        aptName,
        address: `${sidoName} ${gugunName} ${dongName} ${jibun}`,
        buildYear,
        recentPrice,
      };
      this.isSelectedHouseInfo = true;
      location.href = "#item-selected";
    },
    fetchItems(items) {
      if (items.length === 0) {
        this.alertMessage = "해당 주소의 아파트 거래 내역이 없습니다.";
        this.messageCount++;
        this.isHouseInfo = false;
        return;
      }
      this.houseinfo = items;
      this.isHouseInfo = true;
    },
    alignBuildYear() {
      const alignFlag = this.isBuildYearAsc ? 1 : -1;
      this.houseinfo.sort((a, b) => (a.buildYear - b.buildYear) * alignFlag);
      this.isBuildYearAsc = !this.isBuildYearAsc;
    },
    alignPrice() {
      const alignFlag = this.isPriceAsc ? 1 : -1;
      this.houseinfo.sort((a, b) => {
        if (!a.recentPrice || !b.recentPrice) {
          return 1;
        }
        const numA = parseInt(a.recentPrice.trim().replace(/,/g, ""), 10);
        const numB = parseInt(b.recentPrice.trim().replace(/,/g, ""), 10);
        return (numA - numB) * alignFlag;
      });
      this.isPriceAsc = !this.isPriceAsc;
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
