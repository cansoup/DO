<template>
  <v-container style="width:70%">
		<v-row class="mb-10">
			<v-col cols="12" md="3">
				<div class="wrapper">
					<div class="inner">
						<div class="circle">
							<img src="https://i.pinimg.com/originals/bb/9d/fc/bb9dfc5015d24a880f6f32758de8ac7d.jpg" alt="profile">
						</div>
						<div class="my-2">
							<v-btn depressed color="#FF5733" width="120px">이미지 변경</v-btn>
						</div>
						<div>
							<v-btn depressed width="120px">이미지 제거</v-btn>
						</div>
					</div>
				</div>
			</v-col>
			<v-col v-if="!clickmod">
				<h2>{{userinfo.nickname}}</h2>
				<p>{{userinfo.introduce}}</p>	
				<div class="my-2">
						<v-btn depressed small class="btn-success" @click="modprofile">수정</v-btn>
				</div>
			</v-col>
			<!-- 클릭 시 name, description 부분 수정 -->
			<v-col v-if="clickmod">
				<v-text-field outlined v-model="userinfo.nickname" hide-details style="font-size: 2em; margin-bottom:5px;"></v-text-field>
				<v-text-field outlined v-model="userinfo.introduce" hide-details dense></v-text-field>
				<div class="my-2">
							<v-btn depressed small class="btn-success" @click="submit">Submit</v-btn>
					</div>
			</v-col>
		</v-row>
					<!-- 설정 영역 -->
		<v-flex>
		<v-row dense>
			<v-col cols="3">
					<h3>Blog Title</h3>
			</v-col>
			<v-col cols="9" align-self>
					<div>
							{{accountinfo.blogtitle}}
					</div>
			</v-col>
			<v-col cols="12" class="msg">
					개인 페이지의 좌측 상단에 나타나는 페이지 제목입니다.
			</v-col>
		</v-row>
		<hr>		
					<v-row dense>
							<v-col cols="3">
									<h3>SNS info</h3>
							</v-col>
							<v-col cols="9">
									<a href="/setting">수정</a>
							</v-col>
							<v-col cols="12" class="msg">
									포스트 및 블로그에서 보여지는 프로필에 공개되는 소셜 정보입니다.
							</v-col>
					</v-row>
					<hr>
					<v-row dense>
							<v-col cols="3">
									<h3>Email Address</h3>
							</v-col>
							<v-col cols="9">
									Albo7@ssafy.com
							</v-col>
							<v-col cols="12" class="msg">
									회원 인증 또는 시스템에서 발송하는 이메일을 수신하는 주소입니다.
							</v-col>
					</v-row>
					<hr>
					<v-row dense>
							<v-col cols="3">
									<h3>Subscribe</h3>
							</v-col>
							<v-col cols="9">
									<v-col cols="12">
											<v-switch value inset color="#FF5733" v-model="commentAlarm" style="margin:0px" label="댓글 알림"></v-switch>
											<v-switch value inset color="#FF5733" v-model="updateAlarm" style="margin:0px" label="업데이트 소식"></v-switch>
									</v-col> 
							</v-col>
					</v-row>
					<hr>
					<v-row dense>
							<v-col cols="3">
									<h3>Sign Out</h3>
							</v-col>
							<v-col cols="9">
									<v-btn class="ma-2" tile outlined color="error">
											회원탈퇴
									</v-btn>
							</v-col>
							<v-col cols="12" class="msg">
									탈퇴 시 작성하신 포스트 및 댓글이 모두 삭제되며 복구되지 않습니다.
							</v-col>
					</v-row>
					<hr>
		</v-flex>
  </v-container>
</template>

<script>
export default {
	data() {
		return {
			clickmod : false,
			mandatory : false,
			commentAlarm : false,
			updateAlarm : false,
			userinfo : {
					nickname : 'albo7',
					introduce : 'this is team albo7'
			},
			accountinfo : {
					blogtitle : 'Albo7',
					snsinfo : null,
					emailaddr : 'albo7@albo.com',
			}
		}
	},
	methods: {
		modprofile() {
			if(this.clickmod===false){
				this.clickmod = true;
			}
		},
		submit() {
			if(this.clickmod===true){
				this.clickmod = false;
				//axios.put
			}
		}
	}
}
</script>

<style scoped>
	.wrapper {
		text-align: center;
	}
	.inner {
		max-width:150px;
		display: inline-block;
	}
	.circle {
		width:8rem;
		height:8rem;
		border-radius: 50%;
		overflow: hidden;
	}
  img {
		display: block;
		margin: 0 auto;
		max-height: 100%
	}
</style>