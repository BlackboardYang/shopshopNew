<script setup>
import {get} from "@/api";
import {ElMessage} from "element-plus";
import router from "@/router";

const logout = () => {
  get('api/auth/logout', (message) => {
    ElMessage.success(message)
    router.push('../welcome/login')
  })
}

</script>

<template>

  <div class="main-container">
    <p style="color: antiquewhite">confirm logout?</p>
    <el-button @click="logout()" type="danger">Logout</el-button>
  </div>

</template>

<style scoped>
.main-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #333;
}
</style>
