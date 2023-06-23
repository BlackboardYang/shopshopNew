<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="name">
        <el-input v-model="product.name"/>
      </el-form-item>
      <el-form-item label="parent id">
        <el-input v-model="product.parentId"/>
      </el-form-item>
      <el-form-item label="level">
        <el-input v-model="product.level"/>
      </el-form-item>
      <el-form-item label="price">
        <el-input v-model="product.price"/>
      </el-form-item>
      <el-form-item label="product count">
        <el-input v-model="product.productCount"/>
      </el-form-item>
      <el-form-item label="description">
        <el-input v-model="product.description"/>
      </el-form-item>
      <div>
        <!-- 判断是否显示"add"按钮 -->
        <el-button v-if="$route.query.id === undefined" type="success" @click="onSubmit">Confirm add product!</el-button>
        <!-- 判断是否显示"change!"按钮 -->
        <el-button v-else type="primary" @click="onSubmit">Confirm change!</el-button>
        <el-divider direction="vertical"></el-divider>
        <el-popconfirm
          title="Are you sure leave with out any change？"
          confirm-button-text="Yes"
          cancel-button-text="Cancel"
          @onConfirm="goBack">
          <el-button slot="reference" type="primary">Back</el-button>
        </el-popconfirm>
      </div>

    </el-form>
  </div>
</template>

<script>
import axios from "axios";
import form from "element-ui/packages/form";
import router from "@/router";

export default {
  computed: {
    form() {
      return form
    }
  },
  data() {
    return {
      product: {}
    }
  },
  created() {
    this.fetchDataById();
  },
  methods: {
    router() {
      return router
    },
    fetchDataById() {
      var id = this.$route.query.id;
      var vm = this;
      axios.get("http://localhost:8081/product/selectById", {
        params: {id: id}
      })
        .then(response => {
          this.list = response.data.data;
          console.log(this.list);
          vm.product = response.data.data;
        })
        .catch(error => {
          console.log(error);
        })
    },
    goBack(){
      this.$router.push({
        path: "/product/product"
    })},
    onSubmit() {
      var vm = this;
      var productData = {
        // 将你要发送的数据组装成一个对象，例如：
        //id: this.product.id,
        name: this.product.name,
        parentId : this.product.parentId,
        level : this.product.level,
        productCount : this.product.productCount,
        price : this.product.price,
        description : this.product.description
      };

      if (this.$route.query.id === undefined) {
        //没有 id 就添加条目
        console.log("add" + productData)
        axios.post("http://localhost:8081/product/insert", productData)
          .then(response => {
            // 处理请求成功的逻辑
            console.log(response.data);
            vm.$message('Product add successful!');
          })
          .catch(error => {
            // 处理请求失败的逻辑
            console.log(error);
            vm.$message.error('Submit failed!');
          });
      } else {
        // 有 id 就改条目
        // 填充id属性
        productData.id = this.product.id;
        console.log(productData)
        axios.put("http://localhost:8081/product/updateById", productData)
          .then(response => {
            // 处理请求成功的逻辑
            console.log(response.data);
            vm.$message('Information update successful!');
          })
          .catch(error => {
            // 处理请求失败的逻辑
            console.log(error);
            vm.$message.error('Submit failed!');
          });
      }

      // axios.put("http://localhost:8081/product/updateById", productData)
      //   .then(response => {
      //     // 处理请求成功的逻辑
      //     console.log(response.data);
      //     vm.$message('Information updated!');
      //   })
      //   .catch(error => {
      //     // 处理请求失败的逻辑
      //     console.log(error);
      //     vm.$message.error('Submit failed!');
      //   });
    }
    // onCancel() {
    //   this.$message({
    //     message: 'cancel!',
    //     type: 'warning'
    //   })
    // }
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

