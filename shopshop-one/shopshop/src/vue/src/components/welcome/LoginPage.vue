<template>
  <div class="custom-container">
    <div>
      <div style="text-align: center">
        <h1 style="font-weight: bolder">Sign In</h1>
      </div>
      <el-form ref="formRef" :model="form" :rules="rules">
        <el-form-item label="Username">
          <div class="form-item-wrapper">
            <el-input v-model="form.username" placeholder="Enter your username">
              <template #prefix>
                <el-icon class="icon">
                  <user/>
                </el-icon>
              </template>
            </el-input>
          </div>
        </el-form-item>
        <el-form-item label="Password">
          <div>
            <el-input v-model="form.password" placeholder="Enter your password" type="password">
              <template #prefix>
                <el-icon class="icon">
                  <Unlock/>
                </el-icon>
              </template>
            </el-input>
          </div>
        </el-form-item>
      </el-form>

      <div>
        <el-row justify="space-between">
          <el-col :span="6">
            <el-button type="primary" @click="doLogin" size="default" color="#626aef">
              Login
            </el-button>
          </el-col>
          <el-col :span="9" style="margin-top: -10px">
            <el-row>
              <el-checkbox v-model="form.rememberme"><i><span style="font-size: x-small">
                Remember Me</span></i></el-checkbox>
            </el-row>
            <el-row>
              <el-link type="info" @click="router.push('../welcome/forget')"><i><span style="font-size: x-small">
                Forget password?</span></i></el-link>
            </el-row>
          </el-col>
        </el-row>
      </div>
      <el-divider style="margin-bottom: 10px"/>
      <div style="margin-top: 0">
        <span style="font-size: x-small; color: slategrey">
        Not one of us?</span>
      </div>
      <div style="margin-top: 30px">
        <el-button @click="router.push('../welcome/register')" color="#6495ed">
          Register now!
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>

import {User, Unlock} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import router from "@/router";
import {loginAPI} from "@/api/user";
import {useUserStore} from "@/stores/userStore";

const form = reactive({
  username: '',
  password: '',
  rememberme: false,
})

// const form = ref({
//   username: '',
//   password: '',
//   rememberme: false,
// })

const rules = {
  username: [
    {required: true, message: '用户名不能为空', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '密码不能为空', trigger: 'blur'},
    {min: 6, max: 14, message: '密码长度为6-14个字符', trigger: 'blur'},
  ]
}
const formRef = reactive({value: null});

const validateForm = () => {
  const {username, password} = form;
  if (!username) {
    ElMessage.error("请输入用户名");
    return false;
  }
  if (!password) {
    ElMessage.error("请输入密码");
    return false;
  }
  return true;
};
//const formRef = ref(null)
const userStore = useUserStore()

const doLogin = async () => {
  if (validateForm()) {
    const {username, password, rememberme} = form;
    // 执行登录逻辑
    await userStore.getUserInfo({username, password, rememberme});
    // 其他登录逻辑
  }
// const doLogin = async () => {
//   const { username, password, rememberme } = form.value
//   await formRef.value.validate(async (valid) => {
//     console.log(valid)
//     if (valid) {
//       await userStore.getUserInfo({username, password, rememberme})
//       // loginAPI({ username, password, rememberme })
//       //     .then((res) => {
//       //       console.log(res)
//       //       ElMessage.success('Login Success!')
//       //       router.replace({ path: '/' })
//       //     })
//       //     .catch((error) => {
//       //       console.error(error)
//       //     })
//     }
  // else {
  //   ElMessage.error('Check your username and password')
  // }
  // })
}

// const doLogin = async () => {
//   const { username, password, rememberme } = form.value
//   await formRef.value.validate(async (valid) => {
//     console.log(valid)
//     if (valid) {
//       try {
//         const res = await loginAPI({ username, password, rememberme })
//         console.log(res)
//         ElMessage.success('Login Success!')
//         await router.replace({path: '/'})
//       } catch (error) {
//         console.error(error)
//       }
//     } else {
//       ElMessage.error('Check your username and password')
//     }
//   })
// }


// const login = () => {
//   if ((!form.username) || (!form.password)) {
//     ElMessage.warning('please enter something')
//   } else {
//     post('/api/auth/login', {
//       username: form.username,
//       password: form.password,
//       rememberme: form.rememberme
//     }, (message) => {
//       ElMessage.success(message)
//       router.push('/')
//     })
//   }
// }


</script>

<style scoped>

.custom-container {
  height: 400px;
  width: 600px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  backdrop-filter: blur(15px);
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.form-item-wrapper {
  display: flex;
  align-items: center;
}

.icon {
  margin-right: 10px;
}

</style>