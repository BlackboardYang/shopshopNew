<script setup>
import {get} from "@/api";
import {ElMessage} from "element-plus";
import router from "@/router";

const logout = () => {
  get('api/auth/logout', (message) => {
    ElMessage.success(message)
    router.push('/')
  })
}

</script>

<template>

<div class="centered-content">
  <p>confirm logout?</p>
  <el-button @click="logout()" type="danger">Logout</el-button>
</div>
</template>

<style scoped>
.centered-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>