<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="工号">
        <el-input v-model="ruleForm.staffId" disabled="true"></el-input>
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
      <el-form-item label="职务" prop="staffPosition">
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
        staffId: '',
        staffName: '',
        staffGender: '',
        staffPosition: '',
        staffTier: ''
      },
      rules: {
        staffName: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        staffPosition: [
          {required: true, message: '请输入职务', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      //console.log(_this.ruleForm)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:8181/staffInfo/update', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.staffId + '修改成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/staffManage')
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
    axios.get('http://localhost:8181/staffInfo/findById/' + _this.$route.query.staffId).then(function (resp) {
      _this.ruleForm = resp.data.data
    })
  }
}
</script>
