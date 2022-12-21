<template>
  <el-card :body-style="{padding: '0'}" class="el-card-item">
    <img
      :src=" link + item.bgImageUrl" 
      style="width: 100%; height: 13vw;;object-fit:cover"
      />
    <div class="box-list">
      <h5>{{ item.title }}</h5>
      <p>
        {{ item.introduce }}
      </p>
      <div class="details">
        <span>查看详情</span>
      </div>
    </div>
  </el-card>
</template>
<script>
import {link} from '@/utils/common.ts'
export default {
  data(){
    return{
      link,
    }
  },
  props: {
    item: Object
  }
}
</script>
<style lang="scss" scoped>
.el-card-item {
  margin-bottom: 5%;
  .image {
    display: block;
    width: 100%;

  }
  .box-list {
    h5 {
      font-family: "微软雅黑";
      font-size: min(20px, 0.9vw);
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    .details {
      position: relative;
      top: 5px;
      font-size: 18px;
      text-align: center;
      line-height: 46px;
      color: #ffa62e;
      cursor: default;
      border-top: 1px solid #f5f5f5;
      padding: 2px;
    }
  }
}
.box-list{
  padding: 14px
}
.box-list > p {
  color: #8a8889;
  font-size: 12px;
  height:4em;
  line-height: 2em;
  word-wrap: break-word;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  margin-top: 12px;
  margin-bottom: 10px;
}
.el-card-item:hover p {
  color: #ffa62e;
  cursor: default;
}
.el-card-item:hover h5 {
  color: #ffa62e;
  cursor: default;
}
</style>
