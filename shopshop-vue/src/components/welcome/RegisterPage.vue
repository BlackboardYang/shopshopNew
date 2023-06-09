<template>
  <div class="custom-container">
    <div class="main-contents">
      <div style="text-align: center">
        <h1 style="font-weight: bolder">Sign Up</h1>
      </div>
      <el-form :label-position="'top'"
               label-width="100px"
               style="max-width: 500px"
               :model="form" :rules="rules" @validate="onValidate" ref="formRef">

        <el-form-item label="Username" prop="username">
          <el-input v-model="form.username" placeholder="Enter your username" :prefix-icon="User">
          </el-input>
        </el-form-item>

        <el-form-item label="Password" prop="password">
          <el-input v-model="form.password" placeholder="Enter your password" type="password" :prefix-icon="Key">
          </el-input>
        </el-form-item>

        <el-form-item label="Confirm Password" prop="confirm_password">
          <el-input v-model="form.confirm_password" placeholder="Confirm your password" type="password"
                    :prefix-icon="CircleCheck">
          </el-input>
        </el-form-item>

        <el-form-item label="Email" prop="email">
          <el-input v-model="form.email" placeholder="Enter your email" :prefix-icon="Message">
          </el-input>
        </el-form-item>

        <el-form-item label="Verify Code" prop="code">
          <el-input v-model="form.code" placeholder="Code" :prefix-icon="Bell">
            <template #append>
              <el-button @click="validateEmail" :disabled="!isEmailValid"><span style="font-size: x-small">Get verify code</span></el-button>
            </template>
          </el-input>
        </el-form-item>



      <div>
        <el-row justify="space-between">
          <el-col span=12>
            <el-button type="primary" @click="register()" size="default" color="#626aef">
              Register
            </el-button>
          </el-col>
          <el-col span=12>
            <el-checkbox v-model="form.agree" prop="agree">
              <i>
                <span style="font-size: x-small">I agree to the terms and conditions</span>
              </i>
            </el-checkbox>
          </el-col>
        </el-row>
      </div>
      </el-form>
      <el-divider>
        <span style="font-size: x-small">Already have an account?</span>
      </el-divider>
      <div>
        <el-button @click="router.push('../')" color="#6495ed">Sign In</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ElMessage} from "element-plus";
import {reactive, ref} from "vue";
import router from "@/router";
import {Bell, CircleCheck, Key, Message, User} from "@element-plus/icons-vue";
import {post} from "@/api";

const form = reactive({
  username: '',
  password: '',
  confirm_password: '',
  email: '',
  code: '',
  agree: false,
});

const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('Please enter a username'))
  } else if (!/^[\u4E00-\u9FA5\u3040-\u309F\u30A0-\u30FFa-zA-Z0-9]+$/.test(value)) {
    callback(new Error('A username cannot contain special characters'))
  } else {
    callback()
  }
}

const validatePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('Please enter password again'))
  } else if (value !== form.password) {
    callback(new Error("Please ensure it is same as password"))
  } else {
    callback()
  }
}

const rules = {
  username: [
    {required: true},
    {validator: validateUsername, trigger: ['blur', 'change']},
    {min: 3, max: 16, message: 'Length should be 3 to 16', trigger: ['blur', 'change']},
  ],
  password: [
    {required: true},
    {min: 6, max: 16, message: 'Length should be 6 to 16', trigger: ['blur', 'change']},
  ],
  confirm_password: [
    {required: true, message: 'Please enter password again to confirm the password'},
    {validator: validatePassword, trigger: 'blur'},
  ],
  email: [
    {required: true, message: 'Please enter an email address'},
    {type: 'email', message: 'Please enter a correct email address', trigger: 'blur'}
  ],
  code: [
    {required: true, message: 'Please enter the verify code'}
  ],
}

const formRef = ref()
const isEmailValid = ref(false)

const onValidate = (prop, isValid) => {
  if (prop === 'email') {
    isEmailValid.value = isValid;
  }
}

const register = () => {
  console.log('form.agree:', form.agree);
  let a = true;
  a = !form.agree
  console.log('!form.agree:', a);

  formRef.value.validate((isValid) => {
    if (isValid) {
      ElMessage.success("ok!")
    } else if (!form.agree) {
      ElMessage.warning("Please agree to the terms and conditions.");
    } else {
      ElMessage.warning("Please enter all required information.");
    }
  })
};

const validateEmail = () => {
  post('/api/auth/valid-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message)
  })
}

</script>

<style scoped>
.custom-container {
  height: 650px;
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

.main-contents {
  width: 400px;
}

</style>
