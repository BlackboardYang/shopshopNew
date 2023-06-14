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