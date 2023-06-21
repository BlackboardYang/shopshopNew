import {onMounted, ref, watch} from 'vue'

export const useProductStore = defineStore('product', () => {
    // 导航列表的数据管理
    // state 导航列表数据
    const productList = ref([])

    const getProduct = async () => {
        const res = await getProductAPI()
        productList.value = res.data
    }
    // 请求 param 获取导航数据的方法
    // const getLv0Product = async () => {
    //   const level = 1; // 设置 level 参数的值
    //   const parentId = 0; // 设置 parentId 参数的值
    //   const res = await getProductAPI(level, parentId)
    //   productList.value = res.data
    // }

    return {
        productList,
        getProduct
        //getLv0Product,
        //filteredProductList,
    }
})
export function getCategoryAPI () {
  return httpInstance({
    url: '/home/category/cat-level'
  })
}
