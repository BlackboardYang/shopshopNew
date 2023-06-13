<template>
  <div class="custom-container">
    <div style="width: 80%">
      <el-steps :active="active" finish-status="success" align-center>
        <el-step title="Email Check"/>
        <el-step title="Password Reset"/>
        <el-step title="Reset Complete"/>
      </el-steps>
    </div>
    <transition name="el-fade-in-linear" mode="out-in">
      <div class="main-contents" v-if="active === 0">
        <div style="text-align: center">
          <h1 style="font-weight: bolder">Forgot Password</h1>
        </div>
        <el-form :label-position="'top'"
                 label-width="100px"
                 style="max-width: 500px"
                 :model="form" :rules="rules" @validate="onValidate" ref="formRef">
          <el-form-item label="Email" prop="email">
            <el-input v-model="form.email" placeholder="Enter your email" :prefix-icon="Message">
            </el-input>
          </el-form-item>
          <el-form-item label="Verify Code" prop="code">
            <el-input v-model="form.code" placeholder="Code" :prefix-icon="Bell" :maxlength="6">
              <template #append>
                <el-button v-if="counting.value">
                  <span style="font-size: x-small">Resend at {{ countdown.value }} seconds</span>
                </el-button>
                <el-button v-else @click="validateEmail(), startCountdown()" :disabled="!isEmailValid">
                  <span style="font-size: x-small">Get verify code</span>
                </el-button>
              </template>
            </el-input>
          </el-form-item>
          <el-button @click="startReset()">next</el-button>
        </el-form>
      </div>
    </transition>

    <transition name="el-fade-in-linear" mode="out-in">
      <div class="main-contents" v-if="active === 1">
        <div style="text-align: center">
          <h1 style="font-weight: bolder">Forgot Password</h1>
        </div>
        <el-form :label-position="'top'"
                 label-width="100px"
                 style="max-width: 500px"
                 :model="form" :rules="rules" @validate="onValidate" ref="formRef">
          <el-form-item label="Password" prop="password">
            <el-input v-model="form.password" placeholder="Enter your password" type="password" :prefix-icon="Key">
            </el-input>
          </el-form-item>

          <el-form-item label="Confirm Password" prop="confirm_password">
            <el-input v-model="form.confirm_password" placeholder="Confirm your password" type="password"
                      :prefix-icon="CircleCheck">
            </el-input>
          </el-form-item>
          <el-button @click="doReset()">next</el-button>
        </el-form>
      </div>
    </transition>

    <transition name="el-fade-in-linear" mode="out-in">
      <div v-if="active === 2">
        <el-result
            icon="success"
            title="Reset Complete!"
            sub-title="Login now">
          <template #extra>
            <el-button @click="router.push('../')" color="#6495ed">Sign In</el-button>
          </template>
        </el-result>
      </div>
    </transition>
  </div>
</template>

<script setup>
import {ElMessage} from "element-plus";
import {reactive, ref} from "vue";
import {Bell, CircleCheck, Key, Message} from "@element-plus/icons-vue";
import {post} from "@/api";
import router from "@/router";

const active = ref(0)
const isEmailValid = ref(false)
const formRef = ref()

const form = reactive({
  email: '',
  code: '',
  password: '',
  confirm_password: '',
});

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
  email: [
    {required: true, message: 'Please enter an email address'},
    {type: 'email', message: 'Please enter a correct email address', trigger: 'blur'}
  ],
  code: [
    {required: true, message: 'Please enter the verify code'}
  ],
  password: [
    {required: true},
    {min: 6, max: 16, message: 'Length should be 6 to 16', trigger: ['blur', 'change']},
  ],
  confirm_password: [
    {required: true, message: 'Please enter password again to confirm the password'},
    {validator: validatePassword, trigger: 'blur'},
  ],
}

const onValidate = (prop, isValid) => {
  if (prop === 'email') {
    isEmailValid.value = isValid;
  }
}

const countdown = reactive({value: 60});
const counting = reactive({value: false});
const validateEmail = () => {
  post('/api/auth/valid-reset-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message);
    startCountdown();
  });
};

const startCountdown = () => {
  counting.value = true;
  const timer = setInterval(() => {
    if (countdown.value > 0) {
      countdown.value--;
    } else {
      counting.value = false;
      clearInterval(timer);
    }
  }, 1000);
};

const startReset = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('/api/auth/start-reset', {
        email: form.email,
        code: form.code
      }, () => {
        active.value++
      })
    } else {
      ElMessage.warning('Enter email address and code please')
    }
  })
}

const doReset = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('/api/auth/do-reset', {
        password: form.password
      }, (message) => {
        active.value++
        ElMessage.success(message)
      })
    } else {
      ElMessage.warning('Please enter a new password')
    }
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
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  flex-direction: column;
  backdrop-filter: blur(15px);
  background-color: rgba(255, 255, 255, 0.2);
}

.main-contents {
  width: 400px;
}

</style>
