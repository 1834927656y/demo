<template>
  <div class="home">
    <Hat></Hat>
    <SideNavigationBar></SideNavigationBar>
    <router-view ></router-view>

  </div>
</template>

<script>
export default {
  mounted () {
    console.log(this.$route.query.id)
  }
}
</script>

<style>
  .home{
    width: 100vw;
    height: 100vh;
    background-color: white;
  }
  .secondRoute:hover {
    overflow-y: auto;
    }
  .secondRoute {
      padding-right: 17px;
      padding-left: 3px;
      height: 70vh;
      width: 100%;
      overflow-y: hidden;
      overflow-x: hidden;
  }
</style>
