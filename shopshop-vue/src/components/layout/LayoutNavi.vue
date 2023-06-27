<script setup>

import router from "@/router";
import {get} from "@/api";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/stores/userStore";

const userStore = useUserStore()

const confirm = () => {
  userStore.clearUserInfo();
  get('api/auth/logout', (message) => {
    ElMessage.success(message)
    router.push('/welcome/login')
  })
}
</script>

<template>
  <nav class="app-topnav">
    <div class="container">
      <ul>
        <template v-if="userStore.userInfo.token">
          <li><a href="javascript:"><i class="iconfont icon-user"></i>{{ userStore.userInfo.username }}</a></li>
          <li><a href="javascript:">
            <el-popconfirm @confirm=confirm>
              <template #reference>
                <el-button type="danger" text="Logout" link>Logout</el-button>
              </template>
            </el-popconfirm>
          </a>
          </li>
          <li><a href="javascript:;">My Order</a></li>
          <li><a href="javascript:;">My Space</a></li>
        </template>
        <template v-else>
          <li><a>
            <el-button type="success" text="Login" link @click="router.push('../welcome/login')">Login</el-button>
          </a></li>
          <li><a href="javascript:;">Help Center</a></li>
          <li><a href="javascript:;">About us</a></li>
        </template>
      </ul>
    </div>
  </nav>
</template>


<style scoped lang="scss">
.app-topnav {
  background: #333;

  ul {
    display: flex;
    height: 53px;
    justify-content: flex-end;
    align-items: center;

    li {
      a {
        padding: 0 15px;
        color: #cdcdcd;
        line-height: 1;
        display: inline-block;

        i {
          font-size: 14px;
          margin-right: 2px;
        }

        &:hover {
          color: $xtxColor;
        }
      }

      ~ li {
        a {
          border-left: 2px solid #666;
        }
      }
    }
  }
}
</style>