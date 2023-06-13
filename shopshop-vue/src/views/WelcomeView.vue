<template>
  <div class="main-container">
    <router-view v-slot="{ Component }">
      <transition name="el-fade-in-linear" mode="out-in">
        <component :is="Component"/>
      </transition>
    </router-view>
  </div>

</template>

<style scoped>
.main-container {
  display: flex;
  overflow: hidden;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background-image: url(@/assets/image/CaliforniaScene.png);
  background-size:100% 100%;
  margin: 0;
  padding: 0;
}
</style>
<!--<script setup>-->
<!--</script>-->

<!--<template>-->
<!--  <div class="main-container">-->
<!--    <div class="glass-window">-->
<!--      <router-view v-slot="{ Component }">-->
<!--        <transition name="el-fade-in-linear" mode="out-in">-->
<!--          <component :is="Component"/>-->
<!--        </transition>-->
<!--      </router-view>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<style scoped>-->
<!--.main-container {-->
<!--  display: flex;-->
<!--  overflow: hidden;-->
<!--  justify-content: center;-->
<!--  align-items: center;-->
<!--  height: 100vh;-->
<!--  width: 100%;-->
<!--  background-color: #333;-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--}-->

<!--.glass-window {-->
<!--  backdrop-filter: blur(10px); /* 添加毛玻璃效果 */-->
<!--  background-color: rgba(255, 255, 255, 0.2); /* 设置背景颜色透明度 */-->
<!--  padding: 20px;-->
<!--  border-radius: 10px;-->
<!--}-->
<!--</style>-->

<!--<script setup>-->
<!--</script>-->
