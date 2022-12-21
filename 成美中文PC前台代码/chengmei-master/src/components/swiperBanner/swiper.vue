<template>
  <div style="height: 100%;width: 100%">
    <div class="menu-ul">
      <!-- <div class="swiper-button-prev" slot="button-prev" @click="prev"></div> -->
      <swiper class="swiper" ref="mySwiper" :options="swiperOption">
        <swiper-slide style="width:100% !important" v-for="(item, key) in bannerList[1]" :key='key'>
          <!--<img :src="imgUrl+item" style="object-fit: cover" v-if="key==0||key==(item.length-1)">
          <img style="width: 100%;object-fit: cover" v-lazy="imgUrl+item" alt="" />-->
          <img style="width: 100%;object-fit: cover" :src="imgUrl+item" alt="" />
        </swiper-slide>
        <!-- <swiper-slide> <img src="../../assets/image/banner.png" alt="" /> </swiper-slide>
        <swiper-slide>
          <img src="../../assets/image/banner.png" alt="" />
        </swiper-slide> -->
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
      <!-- <div class="swiper-button-next" slot="button-next" @click="next"></div> -->
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";

import Swiper2, { Navigation, Pagination, Autoplay } from "swiper"
Swiper2.use([Autoplay, Navigation, Pagination])

import {link} from '@/utils/common.ts'

// import style (>= Swiper 6.x)
import "swiper/swiper-bundle.css";
let vm = null;
export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    bannerList: {
      default:{}
    }
  },
  data() {
    return {
      imgUrl:link,
      swiperOption: {
        //自动进行轮播3.5s
        // autoplay: 3500,
        // //设置尺寸
        setWrapperSize :true,
        // //监控轮播图下面的小白点
        // pagination : '.swiper-pagination',
        // pagination: ".swiper-pagination",
        // //触发点击事件
        paginationClickable :true,
        // //鼠标滑轮
        // mousewheelControl : true,
        // observeParents:true,
        // //左右箭头
        // prevButton: ".swiper-button-prev",
        // nextButton: ".swiper-button-next",
        // //设置轮播速度
        // speed:2000
        autoplay: {
          disableOnInteraction: false, // 用户操作swiper之后，是否禁止autoplay
          delay: 5000, // 自动切换的时间间隔（单位ms）
        },
        initialSlide: 0,
        loop: true,
        pagination: { el: ".swiper-pagination",clickable: true  }, // 分页按钮
        paginationClickable: true,
      },
    };
  },
  methods: {
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
  },
  created() {
    vm = this;
  },
};
</script>

<style scoped lang='scss'>
.swiper-slide{
  // width: 100%!important;
}
.menu-ul {
  height: 100%;
  width: 100%;
  position: relative;
  img {
    width: 100%;
    height: 100%;
    // margin-top:5.5rem;
    // object-fit: fill;
  }
}
.swiper-container {
  height: 100%;
}
.swiper-button-prev,
.swiper-button-next {
  display: inline-block;
  width: 65px;
  height: 65px;
  border-radius: 50%;
  border: 5px solid #aab0c3;
  text-align: center;
  line-height: 65px;
  &::after {
    line-height: 65px;
    color: #aab0c3;
  }
}
.swiper-button-prev {
  top: 35%;
  left: 60px;
}
.swiper-button-next {
  left: 60px;
}
.item {
  border-radius: 20px;
  overflow: hidden;
  background: #fff;
  height: 470px;
  position: relative;
  width: 388px;
  img {
    display: block;
    height: 317px;
    width: 100%;
  }
  .text {
    padding: 0 20px;
    h3 {
      margin: 25px 0 20px 0;
      font-size: 30px;
      color: #444;
    }
    p {
      font-size: 24px;
      color: #938b8b;
    }
  }
  .i1 {
  }
  .i2 {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    padding: 0 25px;
    background: #fff;
    h2 {
      margin: 45px 0 20px 0;
      text-align: center;
    }
    p {
      line-height: 30px;
      font-size: 20px;
    }
    .timeT {
      margin-top: 50px;
      font-weight: bold;
    }
    .time {
      font-size: 14px;
      color: #8a8a8a;
    }
    .btn {
      margin-top: 20px;
      border-radius: 10px;
      cursor: pointer;
      width: 325px;
      line-height: 65px;
      color: #fff;
      font-size: 18px;
      text-align: center;
      background: linear-gradient(to right, #fbbb31, #f58634);
    }
  }
}
.item .hvrFade {
  opacity: 0;
  backface-visibility: hidden;
  transition-duration: 0.3s;
}

.item:hover .hvrFade,
.item:focus .hvrFade,
.item:active .hvrFade {
  opacity: 1;
}
</style>
<style scoped lang="scss">
// 动画
.fadeIn {
  //   animation: fadeIn 3s linear 2s none;
  //   animation-iteration-count: infinite
  animation-duration: 1s;
  animation-iteration-count: infinite;
  animation-name: fadeIn;
}
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
