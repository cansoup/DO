import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue';
import MyPage from '../components/MyPage.vue';
import Register from '../components/Register.vue'
import Post from '../components/Post.vue'
import TextEditor from '../components/TextEditor.vue'
import Setting from '../components/Setting.vue'
import PostDetail from '../components/PostDetail.vue'

Vue.use(VueRouter)

export default new VueRouter({
  mode: 'history'
  , routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPage,
      // beforeEnter: requireAuth
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/texteditor',
      name : 'texteditor',
      component: TextEditor
    },
    {
      path: '/setting',
      name : 'setting',
      component: Setting
    },
    {
      path: '/create',
      name: 'create',
      component: Post
    },
    {
      path: '/:username/:title',
      name: 'postdetail',
      component: PostDetail
    },
  ]
})
