<template>
  <div id="app">
    <Header class="fixed" type="main.vue"></Header>
    <keep-alive>
    <router-view />
    </keep-alive>
    <f></f>
  </div>
</template>
<script lang='ts'>
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Home extends Vue {
  
  // created() {
  //   this.fnResize();
  // }
  // fnResize() {
  //   var deviceWidth = document.documentElement.clientWidth || window.innerWidth;
  //   if (deviceWidth >= 750) {
  //     deviceWidth = 750;
  //   }
  //   if (deviceWidth <= 320) {
  //     deviceWidth = 320;
  //   }
  //   document.documentElement.style.fontSize = deviceWidth / 7.5 + "px";
  // }
}
</script>
<style lang="scss">

.fixed{
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 999;
}
*{
  -webkit-tap-highlight-color: rgba(0,0,0,0);
  -webkit-tap-highlight-color: transparent;
}
</style>
