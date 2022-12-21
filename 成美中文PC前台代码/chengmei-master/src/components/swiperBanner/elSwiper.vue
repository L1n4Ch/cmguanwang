<template>
    <div class="block" style="height: 100%">
        <el-carousel trigger="click" ref="carousel" height="100%">
            <el-carousel-item v-for="(item, key) in bannerList[1]" :key="key">
                <img style="width: 100%;height: 100%;object-fit: cover" :src="imgUrl+item" alt="" />
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script lang="ts">
    import {Vue, Component, Prop, Watch} from 'vue-property-decorator';
    import {link} from '@/utils/common';
    @Component({
        name: "elSwiper",
        components: {}
    })

    export default class extends Vue {
        // eslint-disable-next-line @typescript-eslint/ban-types
        @Prop({ required: true }) private bannerList!: {};

        private imgUrl = link


    }
</script>

<style scoped lang="scss">
.block{
    ::v-deep .el-carousel{
        height: 100%;
    }
    ::v-deep .el-carousel__arrow--left{
        left: 10%;
    }
    ::v-deep .el-carousel__arrow--right{
        right: 10%;
    }
    ::v-deep .el-carousel__indicator--horizontal .el-carousel__button{
        background: #ccc;
    }
    ::v-deep .is-active .el-carousel__button{
        background: #1e5ba7;
    }
}
</style>