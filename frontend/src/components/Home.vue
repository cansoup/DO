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
                      src="data:image/jpeg;base64,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"
                    >
                      <v-card-title>{{post.title}}</v-card-title>
                    </v-img>

                    <v-card-subtitle class="pb-0">{{ item }}</v-card-subtitle>

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
        <Sidebar/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import Sidebar from '../components/Sidebar.vue'
import axios from 'axios'

// const API_URL = 'http://i3a507.p.ssafy.io:8081/'
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
        thumbnail:'',
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
      axios.get(API_URL+`api/v2/p/${pid}`)
        .then(res => {
          console.log(res)
          this.$router.push({name: 'postdetail', params: { username:res.data.author, title:res.data.title, content: res.data.content, data: res}})
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
