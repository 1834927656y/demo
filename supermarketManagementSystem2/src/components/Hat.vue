<template>
    <div class="hat">
        <div class="icon-demo">
            <tiny-icon-Administrator></tiny-icon-Administrator>
        </div>
        <div class="user">{{user}}</div>
        <div class="quit" @mouseover="handleMouseOver" @mouseleave="handleMouseLeave" @click="handleClick" :style="{ color: quitFontColor }">退出登录</div>

    </div>

  </template>

<script setup>
import { IconAdministrator } from '@opentiny/vue-icon'
import { ref } from 'vue'
</script>

<script>
import { defineComponent } from '@vue/composition-api'
const TinyIconAdministrator = IconAdministrator()

const user = 'admin'
const quitFontColor = ref('#000')
const handleMouseOver = () => {
  quitFontColor.value = '#40a9ff'
}
const handleMouseLeave = () => {
  quitFontColor.value = '#fff' // 恢复字体颜色为白色
}

export default defineComponent({
  methods: {
    handleClick () {
      console.log('用户已退出登录')
      this.$router.push({
        name: 'login',
        query: {
          ParameterName: 'Login'
        }
      })
    }

  }
})
</script>

  <style scoped>
  .icon-demo .tiny-svg {
    position: absolute;
    fill: #8994aa;

    vertical-align: middle;
    font-size: 48px;
    top: 10px;
    left: 0;
  }
  .hat{
    width: 100vw;
    height: 70px;
    background-color: #bfbfbf;
    position: fixed;
    z-index: 99999;
  }
  .user{
    position: absolute;
    left: 60px;
    top: 26px;
  }
  .quit{
    position: absolute;
    top: 20px;
    right: 20px;
    transition: color 0.3s; /* 添加过渡效果 */
  }
  </style>
