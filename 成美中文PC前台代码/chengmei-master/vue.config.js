// 引入等比适配插件
const px2rem = require('postcss-px2rem')
// 配置基本大小
const postcss = px2rem({
    // 基准大小 baseSize，需要和rem.js中相同
    remUnit: 16
})

const devServerPort = 8088

function isProd() {
    return process.env.ENV === 'dev'
}

// 使用等比适配插件
module.exports = {
    publicPath: './',
    lintOnSave: !isProd(),
    productionSourceMap: false,
    transpileDependencies: [],

    devServer: {
        disableHostCheck: true,
        port: devServerPort,
        open: true,
        overlay: {
            warnings: false,
            errors: true
        },
        proxy: {
            '/axios': {
//               target: 'http://47.107.47.129:8080/',
//              target: 'http://139.9.48.17:8089/',
                //target: 'http://47.107.47.129:8080/',
                target: 'http://localhost:8082',
                changeOrigin: true, // needed for virtual hosted sites
                ws: false, // proxy websockets
                pathRewrite: {
                    '^/axios': ''
                }
            }
        }
    },
    css: {
        loaderOptions: {
            css: {},
            postcss: {
                plugins: [
                    // eslint-disable-next-line @typescript-eslint/no-var-requires
                    require('postcss-px2rem')({
                        remUnit: 188
                    })
                ]
            }
        }
    }
}
