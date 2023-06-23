<template>
  <div class="app-container">
    <div>
      <el-button type="success" @click="onInsert()">Add a new product!</el-button>
    </div>
    <el-divider/>
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="Index" width="60">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="Id" width="80" align="center">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="Parent id" width="70" align="center">
        <template slot-scope="scope">
          {{ scope.row.parentId }}
        </template>
      </el-table-column>
      <el-table-column label="Name" width="170" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="Price" width="80" align="center">
        <template slot-scope="scope">
          {{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column label="Category Level" width="90" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.level }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Stock" width="80" align="center">
        <template slot-scope="scope">
          {{ scope.row.productCount }}
        </template>
      </el-table-column>
      <el-table-column label="Description" align="center">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column label="Action" width="150" align="center">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="Edit" placement="top" open-delay="400">
          <el-button size="small" type="primary" icon="el-icon-edit" circle @click="onEdit(scope.row.id)">
          </el-button>
          </el-tooltip>
          <el-divider direction="vertical"></el-divider>
          <el-tooltip class="item" effect="dark" content="Delete" placement="top" open-delay="400">
          <el-popconfirm
            title="Are you sure to delete this product？"
            confirm-button-text="Yes"
            cancel-button-text="Cancel"
            @onConfirm="onDelete(scope.row.id)">
<!--            @click="onDelete(scope.row.id)"-->
          <el-button slot="reference" size="small" type="danger" icon="el-icon-delete" circle>
          </el-button>
          </el-popconfirm>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      list: [

      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      axios.get("http://localhost:8081/product/selectAllProduct")
        .then(response => {
          this.list = response.data.data; // 将 response.data.data 赋值给 this.list
          console.log(this.list); // 打印数据进行验证
        })
        .catch(error => {
          console.log(error);
        });
    },
    onEdit(id){
      console.log(id)
      this.$router.push({
        path: "/product/edit",
        query: { id: id }
      });
    },
    onInsert(){
      this.$router.push({
        path: "/product/edit",

      });
    },
    onDelete(id){
      var vm = this;
      axios.get("http://localhost:8081/product/deleteById", {
        params: { id: id }
      })
        .then(response => {
          // 处理请求成功的逻辑
          console.log(response.data);
          vm.$message('id: ' + id + ' Delete successful!');
        })
        .catch(error => {
          // 处理请求失败的逻辑
          console.log(error);
          vm.$message.error('Delete failed!');
        });
    }
  }
}
</script>
