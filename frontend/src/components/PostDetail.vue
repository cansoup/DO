<template>
  <!-- navbar(로고에 개인 블로그 이름 추가되야함) -->
  <v-container>
    <v-row>
      <!-- 왼쪽 사이드바(좋아요, 공유버튼) -->
      <v-col cols="1" class="pa-0"></v-col>
      <v-col class="pa-0">  
        <v-layout column="12">
        <!-- 제목 -->
          <div class="title-area">
            <h1>{{ $route.params.title }}</h1>
            <h6>작성자: {{ $route.params.username }}</h6>
          </div>
        
        <!-- TextEditor 미리보기만(마크다운) -->
        <div class="col-lg-12">
          <Viewer :initialValue="this.content"/>
        </div>
        <!-- 블로그 작성자 Thumnail -->
        
        <!-- 짧은 자기 소개 -->
        
        <!-- 설정한 SNS주소 있으면 나오게 -->

        <!-- 다음글 이전글 버튼-->

        <!-- 댓글 작성창 -->
        <div class="col-lg-9">          
          <v-textarea
            clearable
            label="댓글 작성"
            placeholder="댓글을 작성하세요"
            :value="CommentInput"
          ></v-textarea><!-- <v-textarea
            clearable
            clear-icon="지우기"
            label="댓글 작성"
            placeholder="댓글을 작성하세요"
            :value="CommentInput"
          ></v-textarea> -->
        </div>
        <div class="col-lg-3">
          <button>작성 완료</button>
        </div>
        <!-- 댓글 -->
        <h1>댓글창</h1>
        <div v-for="comment in Comments" :key="comment.username" class="col-lg-12">
          <p>작성자: {{ comment.username }} 내용:{{ comment.content }}</p>
        </div>
        </v-layout>
      </v-col>
      <!-- 오른쪽 사이드바 -->
      <v-col id="side" class="hidden-sm-and-down pa-0" cols="3">
        <h3> 게시글 목차 </h3>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { Viewer } from '@toast-ui/vue-editor'

export default {
    name: 'postdetail',
    components: {
      Viewer,
    },
    data() {
        return {
          Comments:[
            {
              username: '우주호',
              content: 'hello'
            },
            {
              username: '안시원',
              content: 'hihi'
            },
          ],
          CommentInput: null,
          content: 'hello everyone'
        }
    },
    mounted() {
    },
    created(){
      this.fetchData()
    },
    methods: {
      fetchData(){
        if (this.$route.params.content) {
          this.content = this.$route.params.content
        }
      }
    }
}
</script>

<style scoped>
#side {
  margin-left:2vw;
  position: sticky;
  top : 130px;
}
</style>