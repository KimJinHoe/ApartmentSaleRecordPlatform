<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">주변 상권 정보</h3>
    <article
      id="item-selected"
      class="item-selected"
      v-show="isSelectedCommercial"
    >
      <section>
        <div>{{ selectedCommercial.name }}</div>
      </section>
      <section>
        <div>{{ selectedCommercial.category_s_name }}</div>
        <div>{{ selectedCommercial.load_address }}</div>
      </section>
    </article>
    <map-item
      ref="mapItem"
      v-bind:items="commercials"
      v-on:onCurrentItem="setCurrentItem"
    ></map-item>
    <br />
    <search-commercial v-on:onFetchItems="fetchItems"></search-commercial>
    <b-alert show><h3>주변 상가 목록</h3></b-alert>
    <section id="index_section">
      <div class="card col-sm-12 mt-1">
        <div class="card-body">
          <h2 v-show="isCommercial">주변 상권 목록</h2>
          <table v-if="isCommercial" class="table mt-2">
            <colgroup>
              <col width="150" />
              <col width="*" />
              <col width="300" />
            </colgroup>
            <thead>
              <tr>
                <th>카테고리</th>
                <th>상가이름</th>
                <th class="text-center">주소</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="commercial in commercials" :key="commercial.no">
                <td>{{ commercial.category_s_name }}</td>
                <td>{{ commercial.name }}</td>
                <td>
                  {{ commercial.load_address }}
                </td>
              </tr>
            </tbody>
          </table>
          <no-result v-else />
        </div>
      </div>
    </section>
    <br />
  </b-container>
</template>

<script>
import MapItem from "@/components/map/MapItem.vue";
import SearchCommercial from "@/components/search/SearchCommercial.vue";
import NoResult from "@/components/search/NoResult.vue";

export default {
  name: "HomeView",
  data: function () {
    return {
      isSelectedCommercial: false,
      commercials: [],
      selectedCommercial: {
        name: null,
        load_address: null,
      },
      isCommercial: false,
    };
  },
  props: {
    msg: String,
  },
  components: {
    "map-item": MapItem,
    "search-commercial": SearchCommercial,
    "no-result": NoResult,
  },
  methods: {
    setCurrentItem(item) {
      this.selectedCommercial = item;
      this.isSelectedCommercial = true;
      location.href = "#item-selected";
    },
    fetchItems(items) {
      if (items.length === 0) {
        this.isCommercial = false;
        return;
      }
      this.commercials = items;
      this.isCommercial = true;
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
  font-size: 2rem;
  font-weight: bold;
}

.item-selected section:nth-child(2) {
  text-align: right;
}
</style>
