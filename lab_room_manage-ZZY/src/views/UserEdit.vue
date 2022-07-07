<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="用户名">
        <el-input v-model="ruleForm.username" disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" show-password></el-input>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input v-model="ruleForm.createTime" disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="工号">
        <el-input v-model="ruleForm.staffId" disabled="true"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即修改</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  data() {
    return {
      category: null,
      ruleForm: {
        username: '',
        email: '',
        password: '',
        createTime: '',
        staffId: ''
      },
      rules: {
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:8181/systemUser/update', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.username + '修改成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/userManage')
                }
              });
            }
          })
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/systemUser/findById/' + _this.$route.query.username).then(function (resp) {
      _this.ruleForm = resp.data.data
    })
  }
}
</script>
