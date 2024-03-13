<template>
  <div class="entry">
    <form action="" name="01" class="entryForm">
      <fieldset>
        <legend>新增管理员</legend>
        账户：<input type="text" v-model="formData.username">
        密码：<input type="text" v-model="formData.password">
        性别：<select v-model="formData.gender">
          <option selected="selected">男</option>
          <option>女</option>
        </select>
        姓名：<input type="text" v-model="formData.name">
        账户权限：<select v-model="formData.status">
          <option selected="selected">0</option>
        </select>
        <input type="button" value="添加" @click="dj"/>
      </fieldset>
    </form>
    <div class="dataTotal">共有数据{{dataset.length}}条</div>
    <table border="1" width="80%" class="bg" autocapitalize="option" style="margin: auto;">
      <caption>管理员</caption>
      <tr style="background-color: skyblue;">
        <td>序号</td>
        <td>账户：</td>
        <td>密码：</td>
        <td>性别:</td>
        <td>姓名:</td>
        <td>账户权限：</td>
        <td>录入时间</td>
        <td colspan="2">操作</td>
      </tr>
      <tr v-for="(data, index) in dataset" :key="index">
        <td>{{ index + 1 }}</td>
        <td>{{ data.username }}</td>
        <td>{{ data.password }}</td>
        <td>{{ data.gender }}</td>
        <td>{{ data.name }}</td>
        <td>{{ data.status }}</td>
        <td>{{data.time}}</td>
        <td>修改</td>
        <td @click="handleDelete(index)">删除</td>
        <td>
          <!-- 这里可以添加操作按钮 -->
        </td>
      </tr>
    </table>

    <div class="hiddenDiv">
      <form action="" class="hidden">
        <legend>修改内容</legend>
        账户：<input type="text" ><br>
        密码：<input type="text" ><br>
        性别：<input type="text"><br>

        姓名：<input type="text" ><br>
        <button class="changeButton">修改</button>

      </form>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      dataset: [],
      formData: {
        password: '',
        username: '',
        gender: '男',
        name: '',
        status: '0'
      }

    }
  },
  // 获取前端存储数据
  created () {
    axios({
      url: 'http://127.0.0.1:8088/admin/queryAll',
      method: 'get'
    }).then((result) => {
      for (let i = 0; i < result.data.length; i++) {
        this.dataset.push(result.data[i])
      }
    })
  },
  methods: {
    dj () {
      if (this.formData.username && this.formData.password && this.formData.name) {
        const newData = {
          id: 0,
          username: this.formData.username,
          password: this.formData.password,
          time: new Date().toLocaleString(),
          status: parseInt(this.formData.status),
          salt: '',
          name: this.formData.name,
          gender: this.formData.gender
        }
        const admin = JSON.stringify(newData, null, 4)
        // 这里可以将数据提交到后端或者做其他处理
        axios({
          url: 'http://127.0.0.1:8088/admin/reg',
          method: 'post',
          data: admin,
          headers: {
            'Content-Type': 'application/json'
          }

        }).then((result) => {
          alert(result.data.msg)
          if (result.data.msg === '添加成功') {
            this.dataset.push(newData)
          }
        })
        this.formData = {
          password: '',
          username: '',
          gender: '男',
          name: '',
          status: '0'
        }
      } else {
        return alert('输入内容不能为空')
      }
    },
    handleDelete (index) {
      if (confirm('确定删除')) {
        axios({
          url: 'http://127.0.0.1:8088/admin/del',
          method: 'delete',
          params: { id: this.dataset[index].id }
        }).then((result) => {
          console.log(result)
        }
        )
        console.log(this.dataset[index].id)
        this.dataset.splice(index, 1)
      }
    }
  }
  // 将数据存储到本地

}
</script>

<style>
.entry {
  position: absolute;
  width: 92vw;
  height: 86vh;
  right: 0px;
  top: 89px;
  background-color: #80aba9;
  overflow-y: auto;
}
.entryForm {
  z-index: 1;
  position: fixed;
  left: 140px;
  top: 89px;
  width: 70%;
  background-color: #80aba9;
}
table {
  table-layout: auto;
  border-collapse: collapse;

}
.firstBox {
  background-color: skyblue;
}
.dataTotal {
  position:fixed;
  z-index: 1;
  top: 144px;
  background-color: gray;
}
.bg {
  position: relative;
  top: 54px;
  max-height: 400px; /* 设置最大高度 */
  overflow-y: auto; /* 添加垂直滚动条 */

}
.hiddenDiv{
  display: block;
  position: fixed;
  z-index: 2;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  text-align: center;
  background-color: rgba(0, 0, 0, 0.4);
}
.hidden{
  position: relative;
  z-index: 3;
  top: 50%;
  transform: translateY(-50%);
  width: 400px;
  margin: 0 auto;
  background-color: #00a381;
}
.changeButton{
  width: 20%;
  height: 30px;
}
</style>
