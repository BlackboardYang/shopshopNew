import axios from "axios";
import ElMessage from "autoprefixer/lib/utils";



const defaultError = () => ElMessage.error('I have a bad feeling about this...')
const defaultFailure = (message) => ElMessage.error(message)

function post(url, data, success, failure = defaultFailure, error = defaultError){
  axios.post(url, data, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    withCredentials: true
  }).then(({data}) => {
    if (data.success)
      success(data.message, data.status)
    else
      failure(data.message, data.status)
  }).catch(error)
}

function get(url, success, failure = defaultFailure, error = defaultError){
  axios.get(url, {
    withCredentials: true
  }).then(({data}) => {
    if (data.success)
      success(data.message, data.status)
    else
      failure(data.message, data.status)
  }).catch(error)
}

// axios基础的封装
const httpInstance = axios.create({
  baseURL: 'http://localhost:8081',
  timeout: 5000
})

// 拦截器

// axios请求拦截器
httpInstance.interceptors.request.use(config => {
  // 1. 从pinia获取token数据
  const userStore = useUserStore()
  // 2. 按照后端的要求拼接token数据
  const token = userStore.userInfo.token
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
}, e => Promise.reject(e))

// axios响应式拦截器
httpInstance.interceptors.response.use(res => res.data, e => {
  // 统一错误提示
  ElMessage({
    type: 'warning',
    message: e.response.data.message
  })
  return Promise.reject(e)
})

export default httpInstance

export{ get, post }

export function getCategoryAPI () {
  return httpInstance({
    url: '/home/category/cat-level'
  })
}

// export function getCategoryAPI() {
//     return httpInstance("/home/category/cat-level", {
//         params: {
//             level: level,
//             parent_id: parentId
//         }
//     });
// }
