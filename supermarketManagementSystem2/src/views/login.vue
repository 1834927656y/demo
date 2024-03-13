<template>
  <div class="login">
    <TimeFlow></TimeFlow>
    <div class="form-box">
            <div class="form-value">
                <form @submit.prevent="login">
                    <h2>login</h2>
                    <div class="inputbox">
                        <ion-icon name="mail-outline"></ion-icon>
                        <input type="text" required v-model="username">
                        <!-- required -->
                        <label for="">账号</label>
                    </div>
                    <div class="inputbox">
                        <ion-icon name="lock-closed-outline"></ion-icon>
                        <input type="password" required v-model="password">
                        <label for="">密码</label>
                    </div>
                    <div class="forget">
                        <label for=""><input type="checkbox" />记住密码</label>
                        <a href=""></a>
                    </div>
                    <button type="submit">登录</button>
                    <div class="register">
                        <!-- <p>Don't have a account <a href="">注册</a> </p> -->
                    </div>
                </form>
            </div>
        </div>
  </div>
</template>

<script>
import TimeFlow from '@/components/TimeFlow.vue'
import axios from 'axios'
export default {
  components: { TimeFlow: TimeFlow },
  name: 'loginViews',
  data () {
    return {
      username: '',
      password: ''
    }
  },

  methods: {
    login () {
      axios({
        url: 'http://127.0.0.1:8088/admin/login',
        method: 'get',
        params: { username: this.username, password: this.password }
      }).then((result) => {
        console.log(result)
        if (result.data.success && result.data.data.status === 0) {
          this.$router.push({
            name: 'home',
            query: {
              ParameterName: 'home'
            }
          })
        } else if (result.data.success && result.data.data.status === 1) {
          console.log('user')
        } else if (result.data.success === false) {
          return alert(result.data.msg)
        }
      })
    }
  }

}
</script>
<style >
  .login {
    width: 100vw;
    height: 100vh;
    margin: -9px;
    background: linear-gradient(45deg, #ffc107, deeppink, #9c27b0);
    animation: hueRotate 10s infinite alternate;
}

@keyframes hueRotate {
    100% {
        filter: hue-rotate(360deg);
    }
}

.form-box{
  position: relative;
  top: 85px;
  left: 900px;
}

/* @import url('http://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900');  */

* {
    margin: 0;
    padding: 0;
}

section {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    width: 100%;
    /* background-color: aquamarine; */

    /* background: url('image.webp') no-repeat; */
    background-position: center;
    background-size: cover;
}

.form-box {
    position: relative;
    width: 400px;
    height: 450px;
    /* background-color: red; */
    background: transparent;
    border: 2px solid rgba(255, 255, 255, 0.5);
    backdrop-filter: blur(5px);

    display: flex;
    justify-content: center;
    align-items: center;
}

h2 {
    font-size: 32px;
    color: #fff;
    text-align: center;
}

.inputbox {
    position: relative;
    margin: 30px 0;
    width: 310px;
    border-bottom: 2px solid #fff;
}

.inputbox label {
    position: absolute;
    top: 50%;
    left: 5px;
    transform: translateY(-50%);
    color: #fff;
    font-size: 1em;
    pointer-events: none;
    transition: 0.5s;
}

input:focus ~ label,
input:valid ~ label {
    top: -5px;
}

.inputbox input {
    width: 100%;
    height: 50px;
    background-color: transparent;
    border: none;
    outline: none;
    font-size: 1em;
    padding: 0 35px 0 5px;
    color: #fff;
}

.inputbox ion-icon {
    position: absolute;
    right: 8px;
    color: #fff;
    font-size: 18px;
    top: 20px;
}

.forget {
    margin: -15px 0 15px;
    font-size: 14px;
    color: #fff;
    display: flex;
    justify-content: center;
}

.forget label input {
    margin-right: 3px;
}

.forget a {
    text-decoration: none;
    color: #fff;
}

.forget label a:hover {
    text-decoration: underline;
}

button {
    width: 100%;
    height: 40px;
    border-radius: 40px;
    background: #fff;
    border: none;
    outline: none;
    cursor: pointer;
    font-size: 16px;
    font-weight: 600;
}

.register {
    font-size: 14px;
    color: #fff;
    text-align: center;
    margin: 25px 0 10px;
}

.register p a {
    text-decoration: none;
    color: #fff;
    font-weight: 600;
}

.register p a:hover {
    text-decoration: underline;
}

</style>
