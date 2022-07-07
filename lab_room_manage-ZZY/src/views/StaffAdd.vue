<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="工号" prop="staffId">
        <el-input v-model="ruleForm.staffId"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="staffName">
        <el-input v-model="ruleForm.staffName"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <div style="width: 170px;height: 30px;">
          <template>
            <el-radio v-model="ruleForm.staffGender" label="M">男</el-radio>
            <el-radio v-model="ruleForm.staffGender" label="F">女</el-radio>
          </template>
        </div>
      </el-form-item>
      <el-form-item label="职位" prop="staffPosition">
        <el-input v-model="ruleForm.staffPosition"></el-input>
      </el-form-item>
      <el-form-item label="级别">
        <el-select v-model="ruleForm.staffTier" placeholder="请选择级别">
          <el-option label="级别1" value="1"></el-option>
          <el-option label="级别2" value="2"></el-option>
          <el-option label="级别3" value="3"></el-option>
          <el-option label="级别4" value="4"></el-option>
          <el-option label="级别5" value="5"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
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
        staffId: '',
        staffName: '',
        staffGender: 'M',
        staffPosition: '',
        staffTier: '5'
      },
      rules: {
        staffId: [
          {required: true, message: '请输入工号', trigger: 'blur'}
        ],
        staffName: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        staffPosition: [
          {required: true, message: '请输入职位', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      //console.log(_this.ruleForm)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8181/staffInfo/add', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.staffId + '添加成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/staffManage')
                }
              });
            } else {
              _this.$alert(_this.ruleForm.staffId + '创建失败', '', {})
            }
          })
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>
