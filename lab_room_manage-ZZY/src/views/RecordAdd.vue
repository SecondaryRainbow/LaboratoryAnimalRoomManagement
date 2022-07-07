<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="动物编号" prop="animalId">
        <el-select v-model="ruleForm.animalId" placeholder="请选择动物">
          <el-option v-for="item in animalList" :label="item.animalId" :value="item.animalId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="员工编号" prop="staffId">
        <el-select v-model="ruleForm.staffId" placeholder="请选择员工">
          <el-option v-for="item in staffList" :label="item.staffId" :value="item.staffId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="动物进/出">
        <div style="width: 170px;height: 30px;">
          <template>
            <el-radio v-model="ruleForm.animalIoBehavior" label="I">进</el-radio>
            <el-radio v-model="ruleForm.animalIoBehavior" label="O">出</el-radio>
          </template>
        </div>
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
        animalId: '',
        staffId: '',
        animalIoBehavior: 'I',
      },
      animalList: '',
      staffList: '',
      rules: {
        animalId: [
          {required: true, message: '请选择动物编号', trigger: 'change'}
        ],
        staffId: [
          {required: true, message: '请选择员工编号', trigger: 'change'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8181/animalIoRecord/add', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.animalId + '的进/出记录添加成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/recordManage')
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
    axios.get('http://localhost:8181/animalInfo/animalInfoList').then(function (resp) {
      _this.animalList = resp.data.data
    })
    axios.get('http://localhost:8181/staffInfo/staffInfoList').then(function (resp) {
      _this.staffList = resp.data.data
    })
  }
}
</script>
