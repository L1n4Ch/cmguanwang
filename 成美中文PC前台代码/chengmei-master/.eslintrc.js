module.exports = {
    root: true,
    env: {
        node: true
    },
    'extends': [
        'plugin:vue/essential',
        'eslint:recommended',
        '@vue/typescript/recommended'
    ],
    parserOptions: {
        ecmaVersion: 2020, //指定解析器的版本
        sourceType: "module", //设置为 “script” (默认)或"module"（如果代码是 ECMAScript 模块)
        // ecmaFeature:{}使用额外的语言配置
    },
    rules: {
        // 这里是在在开发环境下出现问题会警报生产环境下不提升
        'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
        // 生产环境下不允许出现段点开发环境下警告
        'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
        "@typescript-eslint/no-this-alias": ["off"],
        "no-irregular-whitespace": ["off"]
    }
}