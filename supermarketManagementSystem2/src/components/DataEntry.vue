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
    <table border="1" width="80%" class="bg" autocapitalize="option"  style="margin: auto;">
      <caption>管理员</caption>
      <tr style="background-color: skyblue;">
        <th>序号</th>
        <th>账户</th>
        <th>密码</th>
        <th>性别</th>
        <th>姓名</th>
        <th>账户权限</th>
        <th>录入时间</th>
        <th colspan="2">操作</th>
      </tr>
      <tr v-for="(data, index) in dataset" :key="index">
        <td>{{ index + 1 }}</td>
        <td>{{ data.username }}</td>
        <td>{{ data.password }}</td>
        <td>{{ data.gender }}</td>
        <td>{{ data.name }}</td>
        <td>{{ data.status }}</td>
        <td>{{data.time}}</td>
        <td><button class="editButton" @click="show(index)">修改</button></td>
        <td><button class="deleteButton" @click="handleDelete(index)">删除</button></td>
        <td>
          <!-- 这里可以添加操作按钮 -->
        </td>
      </tr>
    </table>

    <div class="hiddenDiv">

      <form action="" class="hidden">
        <legend>修改内容</legend>
        账户：<input type="text" v-model="changeData.username"><br>
        密码：<input type="text" v-model="changeData.password"><br>
        性别：<input type="text" v-model="changeData.gender"><br>
        姓名：<input type="text" v-model="changeData.name"><br>
        <button @click="close" class="close">关闭</button>
        <button class="changeButton" @click="change">修改</button>

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
      },
      changeData: {
        username: '',
        password: '',
        gender: '',
        name: ''
      },
      index: ''

    }
  },
  // 获取前端存储数据
  created () {
    axios({
      url: 'http://127.0.0.1:8088/admin/queryAll',
      method: 'get'
    }).then((result) => {
      for (let i = 0; i < result.data.length; i++) {
        if (result.data[i].status === 0) {
          this.dataset.push(result.data[i])
        }
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
    },
    show (index) {
      if (confirm('确定修改')) {
        const table = document.querySelector('.hiddenDiv')
        table.style.display = 'block'
        this.index = index
        console.log(this.index)
      }
    },
    close () {
      const table = document.querySelector('.hiddenDiv')
      table.style.display = 'none'
    },
    change () {
      if (this.changeData.username && this.changeData.password && this.changeData.gender && this.changeData.name) {
        this.dataset[this.index].username = this.changeData.username
        this.dataset[this.index].password = this.changeData.password
        this.dataset[this.index].gender = this.changeData.gender
        this.dataset[this.index].name = this.changeData.name
        console.log(this.dataset[this.index])
        const table = document.querySelector('.hiddenDiv')
        const admin = JSON.stringify(this.dataset[this.index], null, 4)
        axios({
          url: 'http://127.0.0.1:8088/admin/modify',
          method: 'post',
          data: admin,
          headers: {
            'Content-Type': 'application/json'
          }

        }).then((result) => {
          alert(result.data.msg)
          if (result.data.msg === '修改失败') {
            axios({
              url: 'http://127.0.0.1:8088/admin/queryAll',
              method: 'get'
            }).then((result) => {
              for (let i = 0; i < result.data.length; i++) {
                this.dataset.push(result.data[i])
              }
            })
          }
        })
        table.style.display = 'none'
      } else {
        return alert('输入内容不能为空')
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

.firstBox {
  background-color: skyblue;
}
.dataTotal {
  position:fixed;
  z-index: 1;
  top: 144px;
  background-color: gray;
}
table.bg {
  table-layout: auto;
  border-collapse: collapse;
  position: relative;
  top:80px;
  max-height: 400px; /* 设置最大高度 */
  overflow-y: auto; /* 添加垂直滚动条 */
}
.hiddenDiv{
  display: none;
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
  width: 800px;
  margin: 0 auto;
  background-color: #00a381;
}
.hiddenDiv input{
  padding: 5px; /* 内边距 */
  font-size: 10px; /* 字体大小 */
  border: 1px solid #ccc; /* 边框样式 */
  border-radius: 5px; /* 边框圆角 */
}
.hiddenDiv input:focus{
  outline: none; /* 去除获取焦点时的虚线框 */
  border-color: #2c4f54; /* 获取焦点时的边框颜色 */
}
.changeButton{
  width: 20%;
  height: 30px;
  color: #1e50a2;
  border: 1px solid #1e50a2;
}
.changeButton:hover{
  background-color: white;
}
.close{
  position: absolute;
  top: 0px;
  right: 0px;
  width: 20%;
  height: 30px;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px; /* 调整上边距 */
}

th, td {
  padding: 10px;
  text-align: center;
}

th {
  background-color: #f0f0f0; /* 表头背景色 */
}

tr:nth-child(even) {
  background-color: #f9f9f9; /* 偶数行背景色 */
}

tr:hover {
  background-color: #e9e9e9; /* 鼠标悬停时背景色 */
}

caption {
  font-size: 1.2em;
  margin-bottom: 10px;
}
input[type="button"] {
  padding: 8px 16px;
  background-color: transparent; /* 背景色透明 */
  color: #3271ae; /* 文字颜色为蓝色 */
  border: 2px solid #3271ae; /* 蓝色边框 */
  position: relative;
  right: -40px;
  cursor: pointer;
  top: -4px;
}

input[type="button"]:hover {
  background-color: #3271ae; /* 鼠标悬停时背景色变为蓝色 */
  color: #fff; /* 文字颜色变为白色 */
}

 /* 添加删除按钮样式 */
 .deleteButton {
    padding: 8px 16px;
    background-color: transparent;
    color: red;
    border: 2px solid red;
    cursor: pointer;
    border-radius: 30px;
  }

  .deleteButton:hover {
    background-color: red;
    color: #fff;
  }

  /* 添加修改按钮样式 */
  .editButton {
    padding: 8px 16px;
    background-color: transparent;
    color: #ce93bf;
    border: 2px solid #ce93bf;
    cursor: pointer;
    border-radius: 30px;
  }

  .editButton:hover {
    background-color: #ce93bf;
    color: #fff;
  }
  th {
    font-family: Arial, sans-serif; /* 设置字体 */
    font-size: 16px; /* 设置字体大小 */
    font-weight: bold; /* 设置字体粗细 */
    color: white; /* 设置字体颜色 */
    background-color: #3271ae; /* 设置背景颜色 */
    text-align: center; /* 设置文字居中 */
    padding: 10px; /* 设置内边距 */
  }
</style>
