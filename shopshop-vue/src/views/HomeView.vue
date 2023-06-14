<script setup>
import LayoutNav from '../components/layout/LayoutNavi.vue'
import LayoutHeader from '../components/layout/LayoutHeader.vue'
import LayoutFooter from '../components/layout/LayoutFooter.vue'
import LayoutFixed from "@/components/layout/LayoutFixed.vue";

import {onMounted} from "vue";
import {useCategoryStore} from "@/stores/categoryStore";

const categoryStore = useCategoryStore()

onMounted(()=> categoryStore.getCategory())

</script>

<template>
  <LayoutFixed />
  <LayoutNav />
  <LayoutHeader />
  <RouterView />
  <LayoutFooter />
</template>