<template>
<div class="time">
  <!-- <div class="loading" v-if="{{ loading }}"></div> -->
  <span class="send">{{text}}</span>

</div>
</template>

<script>
export default {
  data () {
    return {
      time: '',
      text: '',
      loading: true
    }
  },
  mounted () {
    const newTime = new Date()
    if (newTime.getHours() >= 6 && newTime.getHours() < 11) {
      this.text = '早上好'
    } else if (newTime.getHours() >= 11 && newTime.getHours() < 13) {
      this.text = '中午好'
    } else if (newTime.getHours() >= 13 && newTime.getHours() < 19) {
      this.text = '下午好'
    } else if (newTime.getHours() >= 19 && newTime.getHours() <= 23) {
      this.text = '晚上好'
    } else if (newTime.getHours() >= 0 && newTime.getHours() < 6) {
      this.text = '该休息了'
    }
  }

}
</script>

<style>
  html, body {
    width: 100%;
    height: 100%;
    display: flex;
}

.g-container {
    margin: auto;
}

span {

    /* width: 100vw;
    height: 100vh;
    margin: -9px;
    background: linear-gradient(45deg, #ffc107, deeppink, #9c27b0);
    animation: hueRotate 10s infinite alternate; */

    position: relative;
    font-size: 120px;
    font-weight: bold;
    /* background: #fff; */
    /* color: #000; */
    overflow: hidden;

    &::before,
    &::after {
        content: "";
        position: absolute;
        top: -923px;
        left: 50%;
        width: 2000px;
        height: 2000px;
        border-radius: 45% 48% 43% 47%;
        transform: translate(-50%, -50%);
        background: rgba(3, 169, 244, .85);
        animation: rotate 10s infinite linear;
        z-index: 1;
        mix-blend-mode: lighten;
    }

    &::after {
        border-radius: 43% 47% 44% 48%;
        animation: rotate 10s infinite .5s linear;
    }
}

@keyframes rotate {
    0% {
        transform: translate(-50%, -50%) rotate(0);
    }
    100% {
        transform: translate(-50%, -50%) rotate(360deg);
    }
}
</style>
