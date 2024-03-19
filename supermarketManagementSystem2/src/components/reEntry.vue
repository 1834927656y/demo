<template>
    <div class="entry">
      <form action="" name="01" class="entryForm">
        <fieldset>
          <legend>出库</legend>
          名称：<input type="text" v-model="formData.name">
          数量：<input type="text" v-model="formData.sage">
          保质期：<input type="text" v-model="formData.saveTime">
          价格：<input type="text" v-model="formData.price">
          <input type="file" ref="fileInput" @change="handleFileChange" value="选择图片">
          <input type="button" value="添加" @click="dj"/>

        </fieldset>
      </form>
      <div class="dataTotal">共有数据{{dataset.length}}条</div>
      <table border="1" width="80%" class="bg" autocapitalize="option"  style="margin: auto;">
        <caption>出库记录</caption>
        <tr style="background-color: skyblue;">
          <td>序号</td>
          <td>名称</td>
          <td>数量</td>
          <td>保质期</td>
          <td>价格</td>
          <td>录入时间</td>
          <td>图片</td>

        </tr>
        <tr v-for="(data, index) in dataset" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ data.name }}</td>
          <td>{{ data.sage }}</td>
          <td>{{ data.savetime }}</td>
          <td>{{ data.price }}</td>
          <td>{{ data.time }}</td>
          <td><img :src="data.img" class="produceImg"></td>
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
        name: '',
        saveTime: '',
        sage: '',
        price: '',
        imgData: '',
        time: ''
      },
      changeData: {
        name: '',
        saveTime: '',
        sage: '',
        price: '',
        imgData: null,
        time: ''
      },
      index: '',
      imgSrc: new FormData()

    }
  },
  // 获取前端存储数据
  created () {
    // axios({
    //   url: 'http://127.0.0.1:8088/pustorage/queryAll',
    //   method: 'get'
    // }).then((result) => {
    //   for (let i = 0; i < result.data.length; i++) {
    //     this.dataset.push(result.data[i])
    //   }
    // })
    this.dataset = JSON.parse(localStorage.getItem('datasetDel'))
  },
  methods: {
    handleFileChange (event) {
      const selectedFile = event.target.files[0]
      this.imgSrc.append('image', selectedFile)
      console.log(this.imgSrc)
    },
    dj () {
      axios({
        url: 'http://127.0.0.1:8088/api/uploadImage',
        method: 'post',
        data: this.imgSrc,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then((result) => {
        const imageUrl = result.data // URL.createObjectURL(new Blob ([result.data]))
        console.log(imageUrl)
        if (result !== '文件上传失败') {
          if (this.formData.name && this.formData.sage && this.formData.saveTime && this.formData.price) {
            const newData = {
              id: 0,
              name: this.formData.name,
              savetime: this.formData.saveTime,
              sage: this.formData.sage,
              img: imageUrl,
              price: this.formData.price,
              time: new Date().toLocaleString()
            }
            const admin = JSON.stringify(newData, null, 4)
            // 这里可以将数据提交到后端或者做其他处理
            axios({
              url: 'http://127.0.0.1:8088/pustorage/de',
              method: 'post',
              data: admin,
              headers: {
                'Content-Type': 'application/json'
              }
            }).then((result) => {
              alert(result.data.msg)
              if (result.data.msg === '添加成功') {
                this.dataset.push(newData)
                const datasetString = JSON.stringify(this.dataset)
                localStorage.setItem('datasetDel', datasetString)
                console.log(this.dataset[0].img + '数组添加成功')
              }
            })
            this.formData = {
              name: '',
              saveTime: '',
              sage: '',
              price: '',
              imgData: '',
              time: ''
            }
          } else {
            return alert('输入内容不能为空')
          }
        }
      })
    },
    handleDelete (index) {
      if (confirm('确定删除')) {
        console.log(this.dataset[index].id)
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
                if (result.data[i].status === 1) {
                  this.dataset.push(result.data[i])
                }
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
    width: 80% !important;;
    /* background-color: #80aba9; */
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
    table-layout: auto;
    border-collapse: collapse;
    position: relative;
    top: 54px;
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
    width: 400px;
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
  .produceImg{
    width: 100%;
    height: 100%;
  }
  td{
    width: 50px;
    height: 50px;
  }
  </style>
