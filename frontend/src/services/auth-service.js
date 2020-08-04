import axios from 'axios'

const API_URL = 'http://localhost:8399/'
const storage = window.sessionStorage

class AuthService {
    // 로그인, 로그아웃, 접속 체크
    // Login
    login(user){
        return axios
            .post(API_URL+'signin',{
                email: user.email,
                password: user.password
            })
            .then(this.handelResponse)
            .then(response => {
                if(response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data))
                }
                return response.data
            })
    }
    // Logout
    logout() {
        storage.setItem("jwt-auth-token", "")
        storage.setItem("login_user", "")
        // this.message = "로그인 해주세요"
        // this.setInfo("로그아웃 성공", "", "")
    }
    // 

    // User CRUD
    // C: User
    register(user){
        return axios.post(API_URL + 'api/v1/', {
            nickname: user.username,
            email: user.email,
            password: user.password,
        })
    }
    // R: User
    // detail(user){
    //     return axios.get(API_URL + `api/v1/${user.id}`)
    // }
    detail() {
        return axios.get(API_URL + `api/v1/`)
    }
    // U: User
    update(){
        return axios.put(API_URL + 'api/v1/', {
            // Body, 유저가 수정한 정보만 업데이트
            // 백엔드랑 상의하고 정하자
        })
    }
    // D: User
    delete(user){
        // uid를 이렇게 확인해서 받아온 뒤에 가야하나?
        // let tmp_uid = 
        // if (tmp_uid !== null) {}
        return axios.delete(API_URL + `api/v1/${user.id}`)
    }


    // ??
    handelResponse(response) {
        if(response.status === 401) {
            this.logout()
            location.reload(true)
            const error = response.data && response.data.message
            return Promise.reject(error)
        }
        return Promise.resolve(response)
    }
}

export default new AuthService()