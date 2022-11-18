<template>
  <article :style="{ opacity: opacity }">
    <span>{{ message }}</span>
  </article>
</template>

<script>
export default {
  props: ["messageCount", "message"],
  watch: {
    messageCount() {
      this.opacity = 100;
      setTimeout(() => {
        this.opacity = 0;
        setTimeout(() => {
          this.$emit("endMessage");
        }, 1000);
      }, 2000);
    },
  },
  data() {
    return {
      opacity: 0,
    };
  },
};
</script>

<style scoped>
article {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  min-height: 70px;
  bottom: 30px;
  right: 30px;
  background-color: #e74c4a;
  z-index: 1;
  border-radius: 0.25rem;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  transition: opacity 1s;
}

article span {
  align-items: center;
  color: #e9ecef;
  margin-left: 50px;
  margin-right: 50px;
}
</style>
