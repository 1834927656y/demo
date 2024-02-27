<template>
  <div class="entry">
    <form action="" name="01" class="entryForm">
      <fieldset>
        <legend>新增管理员</legend>
        账户：<input type="text" v-model="formData.name">
        密码：<input type="text" v-model="formData.age">
        性别：<select v-model="formData.gender">
          <option selected="selected">男</option>
          <option>女</option>
        </select>
        姓名：<input type="text" v-model="formData.salary">
        账户权限：<select v-model="formData.city">
          <option selected="selected">0</option>
        </select>
        <input type="button" value="添加" @click="dj"/>
      </fieldset>
    </form>
    <div class="dataTotal">共有数据{{dataset.length}}条</div>

      <table border="1" width="80%" class="bg" autocapitalize="option" style="margin: auto;">
      <caption>管理员</caption>
      <tr style="background-color: skyblue;">
        <td>id</td>
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
        <td>{{ data.name }}</td>
        <td>{{ data.age }}</td>
        <td>{{ data.gender }}</td>
        <td>{{ data.salary }}</td>
        <td>{{ data.city }}</td>
        <td>{{data.time}}</td>
        <td>修改</td>
        <td>删除</td>
        <td>
          <!-- 这里可以添加操作按钮 -->
        </td>
      </tr>
    </table>

  </div>
</template>

<script>

export default {
  data () {
    return {
      dataset: [],
      formData: {
        name: '',
        age: '',
        gender: '男',
        salary: '',
        city: '0'
      }

    }
  },
  // 获取本地存储数据
  created () {
    const savedData = localStorage.getItem('user')
    if (savedData) {
      this.dataset = JSON.parse(savedData)
      console.log(this.dataset)
    }
  },
  methods: {
    dj () {
      if (this.formData.name && this.formData.age && this.formData.salary) {
        const newData = {
          name: this.formData.name,
          age: this.formData.age,
          gender: this.formData.gender,
          salary: this.formData.salary,
          city: this.formData.city,
          time: new Date().toLocaleString()
        }
        this.dataset.push(newData)
        // 这里可以将数据提交到后端或者做其他处理
        console.log(this.dataset)
        this.formData = {
          name: '',
          age: '',
          gender: '男',
          salary: '',
          city: '0'
        }
      } else {
        return alert('输入内容不能为空')
      }
    }

  },
  // 将数据存储到本地
  watch: {
    dataset: {
      handler (newVal) {
        localStorage.setItem('user', JSON.stringify(newVal))
      },
      deep: true
    }
  }

}
</script>

<style>
.entry {
  position: absolute;
  width: 92vw;
  height: 86vh;
  right: 0px;
  top: 89px;
  background-color: #bfbfbf;
  overflow-y: auto;
}
.entryForm {
  z-index: 9999;
  position: fixed;
  right: -140px;
  top: 84px;
  width: 100%;
  background-color: #bfbfbf;
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
  z-index: 9999;
  top: 144px;
  background-color: gray;
}
.bg {
  position: relative;
  top: 54px;
  max-height: 400px; /* 设置最大高度 */
  overflow-y: auto; /* 添加垂直滚动条 */

}

</style>
