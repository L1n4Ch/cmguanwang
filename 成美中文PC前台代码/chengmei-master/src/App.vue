<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script lang='ts'>
import { Component, Vue  } from "vue-property-decorator";

@Component
export default class Home extends Vue {
}
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #000000;
  font-family: "微软雅黑";
}
body,
ul,
li,
a,
div,
p,
h1,
h2,
h3,
h4,
h5 {
  margin: 0;
  padding: 0;
  list-style: none;
  text-decoration: none;
  font-family: "微软雅黑";
}
.center {
  text-align: center;
}
.disFlex{
  display: flex;
}
* {
  -webkit-tap-highlight-color: rgba(0,0,0,0);
  -webkit-tap-highlight-color: transparent;
}
</style>
