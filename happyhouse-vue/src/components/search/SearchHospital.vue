<template>
  <div class="form-group form-inline justify-content-center">
    <select class="form-control" id="sido" v-model="si">
      <option disabled value="null">시 / 도</option>
      <option v-for="si in options.si" :value="si.name" :key="si.code">
        {{ si.name }}
      </option>
    </select>
    <select class="form-control" id="gugun" v-model="gu">
      <option disabled value="null">구 / 군</option>
      <option v-for="gu in options.gu" :value="gu.name" :key="gu.code">
        {{ gu.name }}
      </option>
    </select>
  </div>
</template>

<script>
import http from "@/api/http";
import juso from "@/api/juso";

export default {
  data() {
    return {
      si: null,
      gu: null,
      options: {
        si: [],
        gu: [],
      },
    };
  },
  created() {
    juso.get(`?regcode_pattern=*00000000`).then(({ data }) => {
      this.options.si = data.regcodes;
    });
  },
  watch: {
    si() {
      const pattern = `${this.siCode.slice(0, 2)}*000000`;
      juso.get(`?regcode_pattern=${pattern}`).then(({ data }) => {
        this.options.gu = data.regcodes
          .filter((el) => el.name !== this.si)
          .map((el) => ({
            code: el.code,
            name: el.name.split(" ").slice(-1)[0],
          }));
      });
      this.gu = null;
    },
    gu() {
      if (!this.gu) {
        return;
      }
      http
        .get(`/hospital/?sido=${this.si.slice(0, 2)}&gugun=${this.gu}`)
        .then(({ data }) => {
          this.$emit("onFetchItems", data);
        });
    },
  },
  computed: {
    siCode() {
      return this.options.si.find((el) => el.name === this.si).code;
    },
  },
};
</script>

<style scoped>
.form-group {
  background-color: #343a40;
  border-radius: 0.25rem;
}
.form-control {
  margin: 10px;
  background-color: #343a40;
  color: #e9ecef;
}
</style>
