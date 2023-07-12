// 封装所有和用户相关的接口函数
import request from '@/api/index'
import {ElMessage} from "element-plus";

export const loginAPI = ({username, password, rememberme}) => {
    return request({
        url: '/api/auth/login',
        method: 'POST',
        data: {
            username,
            password,
            rememberme
        },
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        withCredentials: true,
        responseType: 'json'
    })
        .then(response => {
            console.log('api response ' + JSON.stringify(response))
            console.log('api response.data ' + response.message)
            const data = response.message; // 后端返回的数据
            // const message = data.message; // 获取 message 参数
            const status = response.status;
            if (status === 200) {
                console.log('api status ' + status)
                console.log('api data ' + data)
                // 请求成功
                return data;
            } else {
                // 请求失败
                ElMessage.error('Check your username and password')
                throw new Error('fail'); // 抛出一个异常，在调用方进行错误处理
            }
        })
        .catch(error => {
            console.log(error)
            // 请求出错
            throw new Error('error'); // 抛出一个异常，在调用方进行错误处理
        });
}


export const getLikeListAPI = ({limit = 4}) => {
    return request({
        url: '/goods/relevant',
        params: {
            limit
        }
    })
}