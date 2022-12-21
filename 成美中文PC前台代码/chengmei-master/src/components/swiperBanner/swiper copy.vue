<template>
  <div style="height: 100%">
    <div class="menu-ul">
      <div class="swiper-button-prev" slot="button-prev" @click="prev"></div>
      <swiper class="swiper" ref="mySwiper" :options="swiperOption">
        <swiper-slide>
          <img src="../../assets/image/banner.png" alt="" />
        </swiper-slide>
        <swiper-slide>
          1
        </swiper-slide>
        <swiper-slide>
          <img src="../../assets/image/banner.png" alt="" />
        </swiper-slide>
      </swiper>
      <div class="swiper-button-next" slot="button-next" @click="next"></div>
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
  data() {
    return {
      swiperOption: {
        // autoplay: {
        //   delay: 500,
        //   stopOnLastSlide: false,
        //   disableOnInteraction: true,
        // }, 
        autoplay: 3500,
        pagination : '.swiper-pagination',
        speed: 500, //滑动速度   
        loop: true, //会在原本slide前后复制若干个slide(默认一个)并在合适的时候切换，让Swiper看起来是循环的 
        // navigation: {
        //   nextEl: ".swiper-button-next",
        //   prevEl: ".swiper-button-prev",
        // },
        on: {
          //slice点击事件
          click: function () {
            const that = this;
            vm.handleClickSlide(that); //引用：this 指向的是 swpier 实例，而不是当前的 vue， 因此借助 vm，来调用 methods 里的方法
          },
        },
        // allowTouchMove: true, //禁止手动拖动滑动
        // observer: true, //修改swiper自己或子元素时，自动初始化swiper
        // observeParents: true, //修改swiper的父元素时，自动初始化swiper
        // preventLinksPropagation: false, //拖动Swiper时阻止click事件,如果没有禁止拖动需要设置此项
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
    }
  },
  created() {
    vm = this;
  },
};
</script>

<style scoped lang='scss'>
.menu-ul {
  height: 100%;
  position: relative;
  img{
    width: 100%;
    height: 100%;
  }
}
.swiper-container{
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
