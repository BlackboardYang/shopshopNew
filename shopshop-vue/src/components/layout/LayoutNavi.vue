<script setup>

import router from "@/router";
import {get} from "@/api";
import {ElMessage} from "element-plus";

const logout = () => {
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
        <template v-if="true">
          <li><a href="javascript:"><i class="iconfont icon-user"></i>用户</a></li>
          <li><a>
            <el-button type="success" text="登录" link @click="router.push('../welcome/login')">登录</el-button>
          </a></li>
          <li><a>
            <el-button type="danger" text="退出登录" link @click="logout()">退出登录</el-button>
          </a></li>
          <li><a href="javascript:;">我的订单</a></li>
          <li><a href="javascript:;">会员中心</a></li>
        </template>
        <template v-else>
          <li><a href="javascript:;">请先登录</a></li>
          <li><a href="javascript:;">帮助中心</a></li>
          <li><a href="javascript:;">关于我们</a></li>
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