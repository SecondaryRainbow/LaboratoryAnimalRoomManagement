<template>
    <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="工号">
            <el-input v-model="ruleForm.jobId" readonly></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <div style="width: 170px;height: 30px;">
              <template>
                <el-radio v-model="ruleForm.sex" label="男">男</el-radio>
                <el-radio v-model="ruleForm.sex" label="女">女</el-radio>
              </template>
            </div>
          </el-form-item>
          <el-form-item label="职务" prop="post">
            <el-input v-model="ruleForm.post"></el-input>
          </el-form-item>
          <el-form-item label="级别" prop="level">
            <el-input v-model="ruleForm.level"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="ruleForm.password"></el-input>
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
                category:null,
              ruleForm: {
                jobId:'',
                name: '',
                sex: '男',
                post:'',
                level:'',
                username: '',
                password: ''
              },
                rules: {
                  jobId: [
                    { required: true, message: '请输入工号', trigger: 'blur' }
                  ],
                  name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' }
                  ],
                  level: [
                    { required: true, message: '请输入级别', trigger: 'blur' }
                  ],
                  username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                  ],
                  password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                  ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/systemUser/update',_this.ruleForm).then(function (resp) {
                            if(resp.data.code == 0){
                                _this.$alert(_this.ruleForm.username+'修改成功', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/systemUserManagement')
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
            axios.get('http://localhost:8181/systemUser/findById/'+_this.$route.query.jobId).then(function (resp) {
                _this.ruleForm = resp.data.data
            })
        }
    }
</script>
