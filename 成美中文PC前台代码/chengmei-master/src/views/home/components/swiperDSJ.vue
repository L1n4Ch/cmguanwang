<template>
  <div>
    <div class="menu-ul2">
      <div class="swiper-button-prev" slot="button-prev" @click="prev"></div>
      <swiper class="swiper" ref="mySwiper" :options="swiperOption">
        <swiper-slide v-for="(item, key) in bigEventList" :key="key"
          ><span
            class="sTitle"
            :class="{ menu: menuId === key }"
            @click="tabs(key)"
            >{{ item.year }}</span
          ></swiper-slide
        >
      </swiper>
      <div class="swiper-button-next" slot="button-next" @click="next"></div>
    </div>
    <!-- 标签页内容 -->
    <div class="menu-content">
      <div
        v-for="(item, key) in bigEventList"
        :key="key"
        v-show="menuId === key"
      >
        <h1></h1>
        <p v-html="item.detail" style="width: 45%" class="detail">
          <!-- 2018年9月7日，海南成美慈善基金会的“爱助事实孤儿”项目在内蒙古呼和浩特正式启动，该地区也成为“爱助事实孤儿”项目新增14家合作伙伴后的首个正式启动地点，未来内蒙古地区将有300名事实孤儿因项目受益。 -->
        </p>
        <!-- <a @click="showDialog(item)">查看详情</a> -->
      </div>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";

// import style (>= Swiper 6.x)
import "swiper/swiper-bundle.css";
let vm = null;
export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    bigEventList: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      dialogData: {},
      dialogVisible: false,
      menuId: 0,
      swiperOption: {
        slidesPerView: 7, //设置slider容器能够同时显示的slides数量
        speed: 500, //滑动速度
        spaceBetween: 0, //slide之间的距离（单位px
        slidesPerGroup: 1, //定义slides的数量多少为一组
        centeredSlides: false, //设定为true时，活动块会居中，而不是默认状态下的居左
        loop: false, //会在原本slide前后复制若干个slide(默认一个)并在合适的时候切换，让Swiper看起来是循环的
        loopFillGroupWithBlank: true, //不到6个的组，剩下的slice会用空白填满
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        on: {
          //slice点击事件
          click: function (e) {
            const that = this;
            vm.handleClickSlide(that); //引用：this 指向的是 swpier 实例，而不是当前的 vue， 因此借助 vm，来调用 methods 里的方法
            console.log(that.clickedIndex);
            vm.$emit("changeBg", that.clickedIndex);
          },
        },
        allowTouchMove: false, //禁止手动拖动滑动
        observer: true, //修改swiper自己或子元素时，自动初始化swiper
        observeParents: true, //修改swiper的父元素时，自动初始化swiper
        preventLinksPropagation: false, //拖动Swiper时阻止click事件,如果没有禁止拖动需要设置此项
      },
    };
  },
  methods: {
    tabs(item) {
      this.menuId = item;
    },
    handleClose() {
      this.dialogVisible = false;
    },
    showDialog(item) {
      this.$emit("showItem", item);
    },
    //左右切换
    prev() {
      this.$refs.mySwiper.$swiper.slidePrev();
    },
    next() {
      this.$refs.mySwiper.$swiper.slideNext();
    },
    //切换标签页
    handleClickSlide(vmm) {
      this.menuId = vmm.clickedIndex - vmm.activeIndex + vmm.realIndex; //获取点击的slice下标赋值给menuId来控制标签页的显示;
      //activeIndex返回当前活动块(激活块)的索引。loop模式下注意该值会被加上复制的slide数。
      //realIndex当前活动块的索引，与activeIndex不同的是，在loop模式下不会将复制的块的数量计算在内。
      //clickedIndex返回最后点击Slide的索引
      //可能是分组的原因，realIndex，activeIndex返回的是组的索引，而不是单个slice的索引，clickedIndex从6开始索引，所以需要这样处理获取准确的slice下标
    },
    enter(index) {
      this.current = index;
    },
    leave() {
      this.current = -1;
    },
  },
  created() {
    vm = this;
  },
};
</script>

<style scoped lang='scss'>
.detail {
  height: 300px;
  overflow: auto;
  padding-right: 20px;
}
.detail::-webkit-scrollbar {
  width: 5px !important;
  background: none;
}
.detail::-webkit-scrollbar-thumb {
  width: 5px;
  background: #ffa631;
  border-radius: 5px;
}
.menu-content {
  padding: 0 150px;
  color: #fff;
  position: relative;
  &::before {
    content: "";
    display: block;
    height: 2px;
    width: 1580px;
    position: absolute;
    top: -59px;
    background: #fff;
  }
  // &::after{
  //   content: "";
  //   display: block;
  //   height: 2px;
  //   width: 50px;
  //   position: absolute;
  //   top: -59px;
  //   background: #fff;
  // }
  h1 {
    font-size: 36px;
    margin: 60px 0 20px;
    height: 36px;
  }
  p {
    // width: 825px;
    // font-size: 18px;
    line-height: 30px;
  }
  a {
    display: block;
    width: 200px;
    line-height: 75px;
    border-radius: 10px;
    background: linear-gradient(to right, #fbbb31, #f58634);
    text-align: center;
    color: #fff;
    font-size: 24px;
    margin-top: 40px;
  }
}
.swiper-button-prev,
.swiper-button-next {
  position: relative;
  margin: 16px 0 9px 0;
  color: rgb(101, 101, 101);
  top: 0;
  height: 30px;
  width: 30px;
  border: 2px solid #f7f8fa;
  border-radius: 50%;
}
.swiper-button-prev {
  top: 30px;
  left: -50px;
  // background: #476682
}
.swiper-button-next {
  top: 30px;
  right: -50px;
  // background: #0b0b0b;
}
.swiper-button-prev:after {
  content: "prev";
  font-size: 15px;
  height: 30px;
  width: 30px;
  text-align: center;
  line-height: 30px;
  color: #fff;
}
.swiper-button-next:after {
  content: "next";
  font-size: 15px;
  height: 30px;
  width: 30px;
  text-align: center;
  line-height: 30px;
  color: #fff;
}
.menu-ul2 {
  // border-bottom: 2px solid #fff;
  display: flex;
  align-items: center;
  height: 62px;
  padding: 0 165px;
  position: relative;
  &:before,
  &:after {
    content: "";
    width: 115px;
    height: 2px;
    background: #fff;
    position: absolute;
    bottom: -2px;
  }
  &:before {
    left: 0;
  }
  &:after {
    right: 0;
  }
  .swiper-container {
    // border-bottom: 2px solid #fff;
    position: relative;
  }
  .swiper-container::before {
    content: "";
    display: block;
    width: 100%;
    height: 2px;
    /*background: #fff;*/
    position: absolute;
    bottom: 0;
  }
}
.menu-list {
  width: 1128px;
  display: flex;
  align-items: center;
  height: 62px;
  background: #ffffff;
  font-size: 16px;
  color: #666666;
}
>>> .swiper-slide {
  width: 120px;
  position: relative;
  height: 62px;
  line-height: 36px;
  flex-shrink: 0;
}
.swiper-slide span {
  display: block;
  padding: 16px 0 9px 0;
  height: 43px;
  font-size: 24px;
  color: #fff;
  cursor: pointer;
}
/* 以下样式是为了提升用户体验，增加的鼠标移入移出动效 */

.swiper {
}
.swiper-slide:after {
  content: "";
  position: absolute;
  display: inline-block;
  width: 0px;
  height: 3px;
  left: 0;
  right: 0;
  margin: auto;
  bottom: 0;
  background-color: #ffa631;
  transition-property: all;
  transition-duration: 0.15s;
  transition-timing-function: ease;
  transition-delay: 0s;
}
.swiper-slide:hover {
  color: #222222;
  cursor: pointer;
}
.swiper-slide:hover:after {
  content: "";
  width: 120px;
  height: 3px;
  left: 0;
  right: 0;
  margin: auto;
  bottom: 0;
  background-color: #ffa631;
}
.sTitle {
  color: #222222;
  text-align: center;
  padding: 55px;
}
.swiper-slide .menu {
  color: #ffa631;
  font-size: 28px;
  line-height: 27px;
  /*border-bottom: 3px solid #ffa631 !important;*/
}
.menu:after {
  content: "";
  position: absolute;
  display: inline-block;
  height: 3px;
  left: 0;
  right: 0;
  margin: auto;
  bottom: 0;
  /*background-color: #ffa631 !important;*/
  transform: scaleX(1) !important;
}
</style>
