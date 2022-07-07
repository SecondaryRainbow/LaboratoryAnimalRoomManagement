<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="动物编号" prop="animalId">
        <el-input v-model="ruleForm.animalId"></el-input>
      </el-form-item>
      <el-form-item label="动物类型" prop="animalType">
        <el-input v-model="ruleForm.animalType"></el-input>
      </el-form-item>
      <el-form-item label="动物性别">
        <div style="width: 170px;height: 30px;">
          <template>
            <el-radio v-model="ruleForm.animalSex" label="M">雄</el-radio>
            <el-radio v-model="ruleForm.animalSex" label="F">雌</el-radio>
          </template>
        </div>
      </el-form-item>
      <el-form-item label="动物年龄" prop="animalAge">
        <el-input v-model="ruleForm.animalAge"></el-input>
      </el-form-item>
      <el-form-item label="动物组别" prop="animalGroupId">
        <el-select v-model="ruleForm.animalGroupId" placeholder="请选择该动物要加入的组">
          <el-option v-for="item in groupList" :label="item.animalGroupId" :value="item.animalGroupId"></el-option>
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
        animalId: '',
        animalType: '',
        animalSex: 'M',
        animalAge: '',
        animalGroupId: ''
      },
      groupList: '',
      rules: {
        animalId: [
          {required: true, message: '请输入动物编号', trigger: 'blur'}
        ],
        animalType: [
          {required: true, message: '请输入动物类型', trigger: 'blur'}
        ],
        animalAge: [
          {required: true, message: '请输入动物年龄', trigger: 'blur'}
        ],
        animalGroupId: [
          {required: true, message: '请选择动物组别', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8181/animalInfo/add', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.animalId + '添加成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/animalManage$User')
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
    axios.get('http://localhost:8181/animalGroupInfo/animalGroupList').then(function (resp) {
      _this.groupList = resp.data.data
    })
  }
}
</script>
