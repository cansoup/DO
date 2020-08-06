<template>
  <nav class="navbar navbar-expand fixed-top navbar-light bg-light pa-0">
    <div class="container">
      <div class="navbar-brand" v-if="renderComponent">
        <router-link to="/" class="nav-link">Albo7</router-link>
      </div>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/wiki" class="nav-link">Wiki</router-link>
        </li>
      </div>
      <div v-if="!isLogin" class="navbar-nav ml-auto" >
        <li class="nav-item">
          <router-link to="/register" class="nav-link">회원가입</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">로그인</router-link>
        </li>
      </div> 

      <!-- 로그인 했을때 -->
      <div v-if="isLogin" class="navbar-nav ml-auto" >
        <li class="nav-item">
          <router-link to="/setting" class="nav-link">
            <font-awesome-icon icon="user" /> 내 정보
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" @click="onClickLogout">
            <font-awesome-icon icon="sign-out-alt" /> 로그아웃
          </a>
        </li>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      isLogin: this.$store.state.isLoggedIn,
      renderComponent: true,
    }
  },
  computed: {
    // 현재 로그인 한 상태인지 아닌지 체크
  },
  methods: {
    onClickLogout() {
      // LOGOUT 변이 실행 후 리다이렉트
      this.$store.dispatch('logout')
      this.isLogin = this.$store.getters.isAuthenticated
    },
    loginChecker() {
      this.$store.commit('logincheck')
      this.isLogin = this.$store.getters.isAuthenticated
    },
    forceRender() {
      this.renderComponent = false
      this.$nextTick(() => {
        this.renderComponent = true
      })
    },
  },
  created() {
    this.loginChecker()
  },
  // computed() {
  //   this.loginChecker()
  // }
  // watch: {
  //   isLogin (val, prev) {
  //     if (val !== prev) {
  //       val = this.$store.getters.isAuthenticated
  //     }
  //   }
  // }
}
</script>

<style scoped>
  @import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);
  .nav-link {
    font-size : 0.8 rem;
    font-family : 'NanumSquare', sans-serif;
  }
</style>