<template>
  <v-container>
    <!-- 네비게이션 바 -->
    <v-col cols="12">
      <Navbar></Navbar>
    </v-col>
    <!-- 게시글 탭 -->
    <v-tabs v-model="tab" grow class="nav">
      <v-tab v-for="item in items" :key="item">
        {{ item }}
      </v-tab>
    </v-tabs>
    <v-row>
      <v-col>
        <v-tabs-items v-model="tab">
          <v-tab-item v-for="(item) in items" :key="item">
            <v-row v-if="posts.length">
              <!-- <div v-if="item.length"> -->
                <v-col col="12" md="6" lg="4" v-for="(post, index) in posts" :key="index+'_posts'">
                  <v-card
                    hover
                    @click="testpostdetail(post.pid)"
                  >
                    <v-img
                      class="white--text align-end"
                      height="200px"
                      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                    >
                      <v-card-title>{{post.title}}</v-card-title>
                    </v-img>

                    <v-card-subtitle class="pb-0">{{ item }} {{ count }}</v-card-subtitle>

                    <v-card-text class="text--primary">
                      <div>{{post.content}}</div>

                      <div>{{post.publishedTime}}</div>
                    </v-card-text>

                    <v-card-actions>
                      <v-btn
                        color="orange"
                        text
                      >
                        Share
                      </v-btn>

                      <v-btn
                        color="orange"
                        text
                      >
                        Explore
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-col>
              </v-row>
              <v-row v-else>
                게시글이 없습니다.
            </v-row>
          </v-tab-item>
        </v-tabs-items>
      </v-col>
      <v-col id="side" class="hidden-sm-and-down" cols="3">
        <Sidebar></Sidebar>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import Sidebar from '../components/Sidebar.vue'
import axios from 'axios'

const API_URL = 'http://localhost:8081/'

export default {
  name: 'Home',
  data() {
    return {
      tab: null,
      items: [
        'Latest', 'Hits', 'Likes'
      ],
      posts : {
        pid: '',
        title: '',
        content: '',
        publishedTime: '',
      }
    }
  },
  created() {
    axios
    .get(API_URL+'api/v2/latest')
    .then(({data})=>{
      // console.log(data)
      this.posts = data;
    })
  },
  components: {
    Navbar,
    Sidebar
  },
  mounted() {
  },
  methods: {
    
    testpostdetail(pid) {
      // console.log(this.posts.data)
      // var pid = this.$route.params.pid
      axios.get(API_URL+`api/v2/p/${pid}`)
        .then(res => {
          console.log(res)
          this.$router.push({name: 'postdetail', params: { username:res.data.author, title:res.data.title, content: res.data.content}})
        })
        .catch(err => console.log(err))
        
    },
  }
}
</script>

<style scoped>
#side {
  margin-left:2vw;
}
/* .colortest {
  background-color : 
} */
</style>
