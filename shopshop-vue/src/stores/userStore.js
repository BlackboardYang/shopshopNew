import {defineStore} from 'pinia'
import {computed, ref} from "vue";
import {loginAPI} from "@/api/user";
import {ElMessage} from "element-plus";
import router from "@/router";

export const useUserStore = defineStore('user', () => {
    const userInfo = ref({})
    const isLoggedIn = computed(() => Object.keys(userInfo.value).length > 0)

    const getUserInfo = async ({username, password, rememberme}) => {
        const res = await loginAPI({username, password, rememberme});
        console.log("userStore res", res);
        userInfo.value = res;
        console.log("userInfo.value ", userInfo.value);
        ElMessage.success('Login Success!');
        await router.replace({path: '/'});
    }

    const clearUserInfo = () => {
        userInfo.value = {};
    }

    return {
        userInfo,
        getUserInfo,
        clearUserInfo
    }
}, {
    persist: true,
})

// const getUserInfo = async ({username, password, rememberme}) => {
//     try {
//         const res = await loginAPI({username, password, rememberme});
//         console.log("res", res);
//         ElMessage.success('Login Success!');
//         await router.replace({path: '/'});
//         userInfo.value = res.data;
//     } catch (error) {
//         console.error(error);
//     }
// }


// const getUserInfo = async ({username, password, rememberme}) => {
//     const response = await loginAPI({username, password, rememberme})
//         .then((response) => {
//             console.log("res " + response)
//             ElMessage.success('Login Success!')
//             router.replace({path: '/'})
//             userInfo.value = response.data
//         })
//         .catch((error) => {
//             console.error(error)
//         });
//
// }