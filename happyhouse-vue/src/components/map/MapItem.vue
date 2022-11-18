<template>
  <div id="map" style="width: 100%; height: 500px"></div>
</template>

<script>
export default {
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  props: ["items"],
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.drawMarker();
    },
    resetMarker() {
      if (!this.markers) return;
      this.markers.forEach((el) => {
        el.setMap(null);
      });
    },
    drawMarker() {
      if (this.items.length === 0) {
        new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng("37.5012780", "127.0396220"),
          title: "삼성청년 SW 아카데미",
        });
        const bounds = new kakao.maps.LatLngBounds();
        bounds.extend(new kakao.maps.LatLng("37.5012780", "127.0396220"));
        this.map.setBounds(bounds);
        return;
      }

      const originalImage = new kakao.maps.MarkerImage(
        "http://t1.daumcdn.net/mapjsapi/images/2x/marker.png",
        new kakao.maps.Size(31, 35),
      );
      const starImage = new kakao.maps.MarkerImage(
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
        new kakao.maps.Size(24, 35),
      );
      const positions = this.items.map((el) => ({
        title: el.aptName || el.name,
        latlng: new kakao.maps.LatLng(el.lat, el.lng),
      }));
      this.markers = positions.map((el) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: el.latlng,
          title: el.title,
          clickable: true,
        });
        kakao.maps.event.addListener(marker, "click", () => {
          if (this.selectedMarker) this.selectedMarker.setImage(originalImage);
          this.selectedMarker = marker;
          this.selectedMarker.setImage(starImage);
          this.selectedMarker.setZIndex(3);

          this.$emit(
            "onCurrentItem",
            this.items[this.markers.indexOf(this.selectedMarker)],
          );
        });
        return marker;
      });

      const points = this.items.map(
        (el) => new kakao.maps.LatLng(el.lat, el.lng),
      );

      const bounds = new kakao.maps.LatLngBounds();
      for (const point of points) {
        bounds.extend(point);
      }
      this.map.setBounds(bounds);
    },
  },
  watch: {
    items() {
      this.resetMarker();
      this.drawMarker();
    },
  },
};
</script>

<style></style>
