import axios from 'axios'
import Qs from 'qs'

/* axios配置 */
var instance = axios.create({
    // baseURL: 'https://some-domain.com/api/',
    timeout: 20000,
    headers: {
        // 'X-Custom-Header': 'foobar',
        'Accept': 'application/json'
    }
});

// 添加一个请求拦截器
instance.interceptors.request.use(config => {
    // Do something before request is sent
    return config;
},err => {
    // Do something with request error
    return Promise.reject(err);
});

//返回一个Promise(默认发送post请求)
/**
 * option.method  类型  post
 * option.url   地址
 * params 传参
 * **/

const http = (option, params) => {
    if(typeof option == 'undefined'){
       option = {};
    }
    option.method = option.method || 'post';
    //  params.key = localStorage.getItem('key') ? localStorage.getItem('key') : '';
    return new Promise((resolve, reject) => {
        instance({method:option.method,url:option.url,data:params}).then(response => {
            resolve(response.data);
        }, err => {
            reject(err);
        })
        .catch((error) => {
            reject(error)
        })
    })
}

const Header_http = (option, params) => {
    var token = sessionStorage.getItem('token') ? sessionStorage.getItem('token') : '';
    // console.log(token)
    let config = {
        headers: {
            'jwt_token':token.replace("\"", ""),
            'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
        }
    }
    return new Promise((resolve, reject) => {
        axios.post(option.url,Qs.stringify(params), config).then(response => {
            resolve(response.data);
        },err => {
            reject(err);
        }).catch((error) => {
            reject(error)
        })
     })
}

const Header_http_1 = (option, params) => {
    // var token = sessionStorage.getItem('token') ? sessionStorage.getItem('token') : '';
    // console.log(jwt_token)
    let config = {
        headers: {
            'Content-Type':'application/x-www-form-urlencoded'
        }
    }
    return new Promise((resolve, reject) => {
        axios.post(option.url,Qs.stringify(params), config).then(response => {
            resolve(response.data);
        },err => {
            reject(err);
        }).catch((error) => {
            reject(error)
        })
     })
}

const http_json = (option, params) => {
    let config = {
        headers: {
            'Content-Type': 'application/json',
        }
    }
    return new Promise((resolve, reject) => {
        axios.post(option.url, params, config).then(response => {
            resolve(response.data);
        },err => {
            reject(err);
        }).catch((error) => {
            reject(error)
        })
     })
}
const fileHttp = (option, params) =>{
    let config = {
        headers: {'Content-Type': 'multipart/form-data'}
    }
    return new Promise((resolve, reject) => {
        axios.post(option.url,params, config).then(response => {
            resolve(response.data);
        },err => {
            reject(err);
        }).catch((error) => {
            reject(error)
        })
    })
}
const http_get ={
	get (url, data) {
        return new Promise((resolve, reject) => {
            axios.get(url, {params:data}).then(response => {
                resolve(response.data)
            }, err => {
                reject(err)
            }).catch((error) => {
	            reject(error)
	        })
        })
    }
}
export {
    axios,
    http,
    Header_http,
    fileHttp,
    http_json,
    http_get,
    Header_http_1
}