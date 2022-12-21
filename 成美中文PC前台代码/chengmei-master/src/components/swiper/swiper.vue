<template>
  <div style="overflow: hidden">
    <div class="menu-ul">
      <div class="swiper-button-prev" slot="button-prev" @click="prev"></div>
      <swiper class="swiper" ref="mySwiper" :options="swiperOption">
        <swiper-slide v-for="(item, index) in box4Obj" :key="index">
          <div class="item" @mouseenter="enter(1)" @mouseleave="leave">
            <div class="i1">
              <div><img :src="imgLink + item.bgImageUrl" alt="" /></div>
              
              <div class="text1" v-show="box4Obj[0].menuId==21">
                <span class="title">{{ item.title.split("（")[0] }}</span>
                <span class="name"
                  >({{ item.title.split("）")[0].split("（")[1] }})</span
                >
                <!-- <p>腾讯公益</p> -->
              </div>
              <div class="text0" v-show="box4Obj[0].menuId==18">
                <span class="title">{{ item.title }}</span>
              </div>
            </div>
            <div class="i2 hvrFade">
              <h2 v-show="box4Obj[0].menuId==21" class="h2Title">{{ item.title.split("（")[0] }}</h2>
              <h2 v-show="box4Obj[0].menuId==18" class="h2Title">{{ item.title }}</h2>
              <p>{{ item.introduce }}</p>
              <div>
                <div>
                  <p class="timeT">日期：</p>
                  <p class="time">{{ item.createTime.split(" ")[0] }}</p>
                </div>
                <div class="btn" :data-index="index" :data-isTrue="true">了解详情</div>
              </div>
            </div>
          </div>
        </swiper-slide>
      </swiper>
      <div class="swiper-button-next" slot="button-next" @click="next"></div>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import { link } from "@/utils/common.ts";
import Swiper2, { Navigation, Pagination, Autoplay } from "swiper";
Swiper2.use([Navigation, Pagination, Autoplay]);
// import style (>= Swiper 6.x)
import "swiper/swiper-bundle.css";

let vm = null;
export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    box4Obj: {
      type: Array,
      default: () => [],
    }
  },
  data() {
    return {
      imgLink: link,
      curent: -1,
      menuId: 0,
      swiperOption: {
        slidesPerView: 4, //设置slider容器能够同时显示的slides数量
        //autoplay: true, //自动切换
        // autoplay: {
        //   delay: 3000, //自动切换的时间间隔，单位ms
        //   disableOnInteraction: false, //用户操作swiper之后，是否禁止autoplay
        // },
        speed: 500, //滑动速度
        spaceBetween: 30, //slide之间的距离（单位px
        freeMode: true, //free模式，slide会根据惯性滑动且不会贴合
        mousewheel: {
          sensitivity: 2, //滚动一次切换几个slides
        },
        centeredSlides: true,
        loopAdditionalSlides: 100,
        slidesPerGroup: 2, //定义slides的数量多少为一组
        centeredSlides: false, //设定为true时，活动块会居中，而不是默认状态下的居左
        loop: true, //会在原本slide前后复制若干个slide(默认一个)并在合适的时候切换，让Swiper看起来是循环的
        loopFillGroupWithBlank: true, //不到6个的组，剩下的slice会用空白填满
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        on: {
          //slice点击事件
          click:(s,e)=>{
            const index = e.target.dataset.index;
            const isTrue = e.target.dataset.istrue;
            if(isTrue){
              this.$emit('box4setpop',this.box4Obj[index])
            }
            const that = this;
            vm.handleClickSlide(that); //引用：this 指向的是 swpier 实例，而不是当前的 vue， 因此借助 vm，来调用 methods 里的方法
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
    //左右切换
    prev() {
      this.$refs.mySwiper.$swiper.slidePrev();
    },
    next() {
      this.$refs.mySwiper.$swiper.slideNext();
    },
    jumpTo(val, index, id) {
      this.index = index;
      sessionStorage.setItem("index", index);
      if (val == "/aboutUs" && id == "6") {
        this.$router.push({
          path: "/#page3",
        });
        return;
      }
      if (id) {
        sessionStorage.setItem("menuId", id);
        this.$router.push({
          path: val,
          query: {
            menuId: id,
          },
        });
      } else {
        this.$router.push({
          path: val,
        });
      }
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
    const that = this;
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth;
        that.screenWidth = window.screenWidth;
        console.log(that.screenWidth);
      })();
    };
  }
};
</script>

<style scoped lang='scss'>
.menu-ul {
  margin-top: 15px;
  margin-bottom: 15px;
}
.swiper-button-prev,
.swiper-button-next {
  display: inline-block;
  // margin-top: -30px;
  margin-left: 10px;
  width: 55px;
  height: 55px;
  border-radius: 50%;
  border: 5px solid #aab0c3;
  text-align: center;
  line-height: 55px;
  &::after {
    line-height: 55px;
    color: #aab0c3;
  }
}
.swiper-button-prev {
  top: 50%;
  left: 60px;
  transform: translateY(-50%);
}
.swiper-button-next {
  top: 60%;
  left: 60px;
}
.item {
  border-radius: 20px;
  overflow: hidden;
  background: #fff;
  height: 270px;
  position: relative;
  img {
    display: block;
    height: 200px;
    width: 100%;
    object-fit: contain;
  }
  .text0 {
    padding: 10px 20px 0;
    display: flex;
    flex-direction: column;
    .title {
      font-size: 18px;  
      line-height: 26px;
      color: #222;
      text-align: center;
      word-wrap: break-word;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
    }
  }
  .text1 {
    padding: 12px 20px 0;
    display: flex;
    flex-direction: column;
    // justify-content: space-between;
    align-items: center;
    .title {
      // margin: 25px 0 20px 0;
      font-size: 18px;
      line-height: 20px;
      color: #222;
      text-align: center;
    }
    .name {
      margin-top: 5px;
      font-size: 14px;
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
    > div {
      display: flex;
      align-items: center;
      justify-content: space-between;
      > div {
        margin-left: -10px;
        margin-top: 20px;
      }
    }
    h2 {
      font-size: 22px;
      margin: 20px 0 20px -10px;
      color: #fdc729;
    }
    .h2Title {
      margin: 20px 0 0 -10px;
      color: #fdc729;
      word-wrap: break-word;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 1;
      -webkit-box-orient: vertical;
    }
    > p {
      margin: 15px 0 10px -10px;
      line-height: 30px;
      height: 114px;
      font-size: 16px;
      word-wrap: break-word;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 4;
      -webkit-box-orient: vertical;
    }
    .timeT {
      // font-weight: bold;
      color: #666;
    }
    .time {
      font-size: 14px;
      color: #8a8a8a;
    }
    .btn {
      margin-top: 20px;
      border-radius: 10px;
      cursor: pointer;
      width: 150px;
      height: 46px;
      line-height: 46px;
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
