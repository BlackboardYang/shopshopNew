
<template>
  <div class="custom-container">
    <div>
      <div style="text-align: center">
        <h1 style="font-weight: bolder">Sign In</h1>
      </div>
      <el-form>
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
            <el-button type="primary" @click="login()" size="default" color="#626aef">
              Login
            </el-button>
          </el-col>
          <el-col :span="9" style="margin-top: -10px">
            <el-row>
              <el-checkbox v-model="form.rememberme"><i><span style="font-size: x-small">
                Remember Me</span></i></el-checkbox>
            </el-row>
            <el-row>
              <el-link type="info"><i><span style="font-size: x-small">
                Forget password?</span></i></el-link>
            </el-row>
          </el-col>
        </el-row>
      </div>
      <el-divider style="background-color: lightsteelblue"><span style="font-size: x-small; background-color: lightsteelblue">
        Not one of us?</span></el-divider>
      <div>
        <el-button @click="router.push('/register')">
          Register now!
        </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>

import {User, Unlock} from "@element-plus/icons-vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/api";
import router from "@/router";
const form = reactive({
  username: '',
  password: '',
  rememberme: false,
})

const login = () => {
  if((!form.username) || (!form.password)){
    ElMessage.warning('please enter something')
  } else {
    post('/api/auth/login', {
      username: form.username,
      password: form.password,
      rememberme: form.rememberme
    }, (message) => {
      ElMessage.success(message)
      router.push('/index')
    })
  }
}

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
  background-color: lightsteelblue;
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