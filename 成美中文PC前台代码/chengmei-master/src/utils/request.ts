import router from "@/router";
import { Message, MessageBox } from "element-ui"
import axios from 'axios'

export const isProduction = process.env.NODE_ENV === 'production'

export const baseURL = isProduction ? process.env.VUE_APP_API : '/axios'
// export const baseURL = 'http://47.107.47.129:8080'
const requestPath = async () => {
    const service = axios.create({
        // baseURL: res.defaults.baseURL,
        baseURL, //请求路径
        withCredentials: false, // send cookies when cross-domain requests
        // timeout: 10000
    })
    // request interceptor
    service.interceptors.request.use(
        config => {
            if (config.headers.url === "/api/page/addDocutionUser") {
                config.headers = {
                    'Content-Type': 'multipart/form-data'
                }
            } else {
                config.headers['X-Requested-With'] = 'XMLHttpRequest'
            }
            return config
        },
        error => {
            return Promise.reject(error)
        }
    )
    // response interceptor
    service.interceptors.response.use(
        response => {
            //接收到响应数据并成功后的一些共有的处理，
            const res = response.data
            return res
        },
        error => {
            let _msg
            error.message.includes('timeout') ? _msg = '请求超时，请稍后再试！' : _msg = error.message
            // @ts-ignore
            Message.closeAll()
            Message.error(_msg)
            return error
        }
    )
    return service
}



export default requestPath
