<template>
    <tiny-collapse class="demo-collapse-wrap" v-model="activeName" >
      <tiny-collapse-item title="首页" name="5" disabled>
      </tiny-collapse-item>
      <tiny-collapse-item title="用户中心" name="1">
        <router-link to="/admin"><div class="menu_label" tabindex="1" >管理员</div></router-link>
        <router-link to="/user"><div class="menu_label" tabindex="1">销售员</div></router-link>
      </tiny-collapse-item>
      <tiny-collapse-item title="库存管理" name="2">
        <router-link to="/SEEntry"><div class="menu_label" tabindex="1">入库记录</div></router-link>
        <router-link to="/reduceEntry"><div class="menu_label" tabindex="1">出库记录</div></router-link>
        <router-link to="/Entry"><div class="menu_label" tabindex="1">总库存</div></router-link>
      </tiny-collapse-item>
      <tiny-collapse-item title="销售管理" name="3">
       <router-link to="/Market"> <div class="menu_label" tabindex="1">销售记录</div></router-link>
        <router-link to="/sales"><div class="menu_label" tabindex="1">退货记录</div></router-link>
      </tiny-collapse-item>
      <tiny-collapse-item title="会员管理" name="4" disabled>
      </tiny-collapse-item>

      <div class="time">{{time}}</div>
    </tiny-collapse>

  </template>

<script>
import { ref } from 'vue'
import { Collapse as TinyCollapse, CollapseItem as TinyCollapseItem, Modal, ColorPicker } from '@opentiny/vue'
const activeName = ref('1')

export default ({
  data () {
    return {
      time: ''
    }
  },
  methods: {
    handlelick () {
      console.log('点击')
    }
  },
  created () {
    // this.timer = setInterval(() => {
    //   this.time = new Date().toLocaleString()
    // }, 1000) // 每秒执行一次
  }
})
</script>

  <style scoped lang="less">
  .demo-collapse-wrap {
    .tiny-collapse-item__content > * {
      line-height: 1.8;
    }
  }
  .tiny-collapse{
    height: 100vh;
    top: 80px;
   position: fixed;
  }
  .time{
    position: absolute;
    bottom: 90px;
  }
  div{
    text-align: center;
    // display:inline-block;
  }

  .menu_label {
      // float: left;
      border: 1px rgb(67, 150, 202) solid;
      padding-left: 10px;
      padding-right: 10px;
      padding-bottom: 3px;
      padding-top: 1px;
      border-radius: 15px;
      font-size: 10px;
      margin-left: 15px;
      margin-top: 8px;
      color: rgb(67, 150, 202);
      /* font-weight: bold; */
      margin-bottom: 7px;
    }
    .menu_label:active {
      background-color: rgb(67, 150, 202);
      color: white;
    }
    .menu_label:focus {
      background-color: rgb(67, 150, 202);
      color: white;
    }
    // .tiny-collapse {
    //   background-color: black;
    // }
  </style>
