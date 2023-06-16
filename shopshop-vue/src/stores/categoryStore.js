import {onMounted, ref, watch} from 'vue'
import { defineStore } from 'pinia'
import { getCategoryAPI } from '@/api/layout'
export const useCategoryStore = defineStore('category', () => {
  // 导航列表的数据管理
  // state 导航列表数据
  const categoryList = ref([])

  const getCategory = async () => {
    const res = await getCategoryAPI()
    categoryList.value = res.data
  }


  // 请求 param 获取导航数据的方法
  // const getLv0Category = async () => {
  //   const level = 1; // 设置 level 参数的值
  //   const parentId = 0; // 设置 parentId 参数的值
  //   const res = await getCategoryAPI(level, parentId)
  //   categoryList.value = res.data
  // }


  return {
    categoryList,
    getCategory
    //getLv0Category,
    //filteredCategoryList,
  }
})
