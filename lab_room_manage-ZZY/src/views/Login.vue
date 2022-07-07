<template>
  <div class="login-container">
    <el-form ref="ruleForm" :model="ruleForm"
             :rules="rules"
             class="demo-ruleForm login-page"
             label-position="left"
             label-width="0px"
             status-icon>
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input v-model="ruleForm.username"
                  placeholder="用户名"
                  type="text"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="ruleForm.password"
                  placeholder="密码"
                  type="password"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-radio v-model="type" label="systemAdmin">管理员</el-radio>
        <el-radio v-model="type" label="systemUser" style="position: relative;left: 80px">用户</el-radio>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button :loading="logining" style="width:100%;" type="primary" @click="handleSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      logining: false,
      ruleForm: {
        username: 'admin01',
        password: '123456'
      },
      type: 'systemAdmin',
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}]
      }
    }
  },
  methods: {
    handleSubmit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true
          let _this = this
          if (_this.type === 'systemAdmin') {
            axios.get('http://localhost:8181/systemAdmin/login', {params: _this.ruleForm}).then(function (resp) {
              _this.logining = false
              if (resp.data.code === -1) {
                _this.$alert('用户名不存在', '提示', {
                  confirmButtonText: '确定'
                })
              } else if (resp.data.code === -2) {
                _this.$alert('密码错误', '提示', {
                  confirmButtonText: '确定'
                })
              } else if (resp.data.code === 0) {
                localStorage.setItem('systemAdmin', JSON.stringify(resp.data.data))
                _this.$router.replace({path: '/systemAdmin'})
              }
            })
          }
          if (_this.type === 'systemUser') {
            axios.get('http://localhost:8181/systemUser/login', {params: _this.ruleForm}).then(function (resp) {
              _this.logining = false
              if (resp.data.code === -1) {
                _this.$alert('用户名不存在', '提示', {
                  confirmButtonText: '确定'
                })
              } else if (resp.data.code === -2) {
                _this.$alert('密码错误', '提示', {
                  confirmButtonText: '确定'
                })
              } else if (resp.data.code === 0) {
                localStorage.setItem('systemUser', JSON.stringify(resp.data.data))
                _this.$router.replace({path: '/systemUser'})
              }
            })
          }
        }
      })
    }
  }
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}

.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>