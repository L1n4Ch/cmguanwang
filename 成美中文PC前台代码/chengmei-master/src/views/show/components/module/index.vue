<template>
  <div class="module-wrapper" v-if="list.length>0">
    <div class="module">
      <div class="module__header">
        <h2 ref="name" :data-id="title" @click="getScroll">{{title}}</h2>
        <h3>{{subTitle}}</h3>
      </div>
      <div>
        <Swiper ref="swiperRef" :list="list" @click-slide="handleClickSlide">
          <template slot-scope="{item}">
            <Card :item="item"></Card>
          </template>
        </Swiper>
      </div>
      <div class="module__footer">
        <div class="icon">
          <i class="iconfont icon-010jiantouzuo" @click="prev"></i>
          <i class="iconfont icon-jiantou" @click="next"></i>
        </div>
        <div class="btn">
          <router-link :to="moreRoute">
            <button class="btn btn-default">了解更多</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Swiper from './swiper'
import Card from './card'
export default {
  components: {Swiper,Card},
  props: {
    list: Array,
    title: String,
    subTitle: String,
    moreRoute: Object,
  },
  methods: {
    prev (){
      this.$refs.swiperRef.prev()
    },
    next (){
      this.$refs.swiperRef.next()
    },
    handleClickSlide(index){
      this.$emit('click-item', this.list[index])
    },
    getScroll() {
      return this.$refs.name.offsetTop
    },
  },
}
</script>
<style lang="scss" scoped>
.module-wrapper{
  width: 100%;
  overflow: hidden;
  .module{
    width: 72%;
    margin: 0 auto;
  }
  &:nth-child(odd){
    background-color: white;
  }
  &:nth-child(even){
    background-color: #fafafc;
  }
  h2{
    text-align: center;
    color: #656565;
    font-size: 34px;
  }
  h3{
    text-align: center;
    padding-top: 5px;
    color: #cdcdcd;
    font-size: 20px;
  }
  .module__header{
    margin: 28px;
  }
  .module__footer{
    display: flex;
    height: 50px;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 40px;
    .icon {
      i {
        font-size: 38px;
        color: #cccccc;
        cursor: pointer;
      }
      i:first-child {
        padding-right: 16px;
      }
      i:hover {
        color: #ffa632;
      }
    }
    .btn {
      cursor: pointer;
      button {
        width: 120px;
        border: 1px solid #ffa632;
        height: 36px;
        background-color: transparent;
        color: #666666;
      }
      button:hover {
        background: #ffa632;
        color: white;
      }
    }
  }
}
</style>
