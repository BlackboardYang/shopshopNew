import {ref, watch} from 'vue'
import { defineStore } from 'pinia'
import { getCategoryAPI } from '@/api/layout'
export const useCategoryStore = defineStore('category', () => {
  // 导航列表的数据管理
  // state 导航列表数据
  const categoryList = ref([])

  // action 获取导航数据的方法
  const getLv0Category = async () => {
    const level = 1; // 设置 level 参数的值
    const parentId = 0; // 设置 parentId 参数的值
    const res = await getCategoryAPI(level, parentId)
    categoryList.value = res.data
  }

// // computed property 进行数据筛选
//   const filteredCategoryList = ref([])
//
//   // 在 categoryList 更新时触发筛选
//   watch(categoryList, () => {
//     filteredCategoryList.value = categoryList.value.filter(item => item.level === 1 && item.parentId === 0)
//   }, { immediate: true })

  return {
    categoryList,
    getLv0Category,
    //filteredCategoryList,
  }
})
