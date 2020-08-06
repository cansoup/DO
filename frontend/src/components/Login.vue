<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent="login">
        <div class="form-group">
          <label for="username">email</label>
          <input
            type="email"
            class="form-control"
            name="email"
            v-model="email"
            v-validate="'required'"
          />
          <div
            class="alert alert-danger"
            role="alert"
            v-if="errors.has('username')"
          >Username is required!</div>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            class="form-control"
            name="password"
            v-model="password"
            v-validate="'required'"
          />
          <div
            class="alert alert-danger"
            role="alert"
            v-if="errors.has('password')"
          >Password is required!</div>
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span class="spinner-border spinner-border-sm" v-show="loading"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div class="alert alert-danger" role="alert" v-if="message">{{message}}</div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import User from '../models/user'

const storage = window.sessionStorage
const API_URL = 'http://localhost:8081/'

export default {
  name: 'login',
  computed: {
  },
  data() {
    return {
      user: new User('', ''),
      email: "",
      password: "",
      loading: false,
      message: "",
      renderComponent: true,
    }
  },
  methods: {
    login() {
      this.loading = true
      this.$validator.validateAll()

      if (this.errors.any()) {
        this.loading = false
        return
      }

      if (this.email && this.password) {
        storage.setItem("jwt-auth-token", "")
        storage.setItem("login_user", "")
        axios.post(API_URL+'api/signin', {
          email: this.email,
          password: this.password
        }).then(res => {
            if (res.data.status) {
              this.message = res.data.request_body.email + "로 로그인 되었습니다."
              storage.setItem("jwt-auth-token", res.data["jwt-auth-token"])
              storage.setItem("login_user", res.data.request_body.email)
              this.$router.push('/')
            } else {
              this.message = "로그인해주세요."
              alert("입력 정보를 확인해주세요.")
            }
          })
          .catch(err => console.log(err))
      }
    },
    // logout() {
    //   storage.setItem("jwt-auth-token", "")
    //   storage.setItem("login_user", "")
    //   this.message = "로그인해주세요."
    // },
    
    // 로그인 했는지 체크
    init() {
      // 스토리지에 토큰 있으면
      if (storage.getItem("jwt-auth-toekn")) {
        this.message = storage.getItem("login_user") + "로 로그인 되었습니다."
      } else {
        
        storage.setItem("jwt-auth-token", "");
      }
    },
    forceRender() {
      this.renderComponent = false
      this.$nextTick(() => {
        this.renderComponent = true
      })
    }
  },
  mounted() {
    this.init()
    // 로그인한 상태로 로그인 페이지에 진입하면 홈으로 돌려보냄
    if (this.loggedIn) {
      this.$router.push('/')
    }
  },
}
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>