<script setup>
import HomeCategory from '@/components/home/HomeCategory.vue'
import HomeBanner from '@/components/home/HomeBanner.vue'
import HomeNew from '@/components/home/HomeNew.vue'
import HomeHot from '@/components/home/HomeHot.vue'
import homeProduct from '@/components/home/HomeProduct.vue'

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
  <div class="container">
    <HomeCategory/>
    <HomeBanner/>
  </div>
  <HomeNew/>
  <HomeHot/>
  <homeProduct/>
</template>