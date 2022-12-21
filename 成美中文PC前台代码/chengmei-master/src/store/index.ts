import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        baseInfo: {}
    },
    mutations: {
        setBaseInfo (state, n) {
            // 变更状态
            console.log('mmmmm', n);
            state.baseInfo = n
        }
    },
    actions: {
    },
    modules: {
    }
})
