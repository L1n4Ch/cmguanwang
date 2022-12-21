<template>
  <Swiper
    ref="swiperRef"
    :options="{
      pagination: '.swiper-pagination',
      autoplay: {
        disableOnInteraction: true,
      },
      slidesPerView: 3,
      spaceBetween: 30,
      loop: list.length > 3 ? true : false,
      delay: 2000,
      speed: 500,
    }"
    @click-slide="handleClickSlide"
  >
    <template v-for="(item, index) in list">
      <SwiperSlide :key="index">
        <slot :item="item"></slot>
      </SwiperSlide>
    </template>
  </Swiper>
</template>
<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/swiper-bundle.css";
import Swiper2, { Navigation, Pagination, Autoplay } from "swiper";
Swiper2.use([Navigation, Pagination, Autoplay]);
export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    list: Array,
  },
  watch: {
    list: function (val1, val2) {
      console.log("val1: " + val1 + ", val2: " + val2);
    },
  },
  data() {
    return {
     
    };
  },
  methods: {
    prev() {
      this.$refs.swiperRef.$swiper.slidePrev();
    },
    next() {
      this.$refs.swiperRef.$swiper.slideNext();
    },
    handleClickSlide(index, reallyIndex) {
      this.$emit("click-slide", reallyIndex);
    },
  },
};
</script>
