<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="房间编号" prop="animalRoomId">
        <el-input v-model="ruleForm.animalRoomId"></el-input>
      </el-form-item>
      <el-form-item label="房间总容量" prop="animalRoomTotalCapacity">
        <el-input v-model="ruleForm.animalRoomTotalCapacity"></el-input>
      </el-form-item>
      <el-form-item label="房间可用性">
        <div style="width: 170px;height: 30px;">
          <template>
            <el-radio v-model="ruleForm.animalRoomAvailability" label="Y">可用</el-radio>
            <el-radio v-model="ruleForm.animalRoomAvailability" label="N">不可用</el-radio>
          </template>
        </div>
      </el-form-item>
      <el-form-item label="房间负责人" prop="staffId">
        <el-select v-model="ruleForm.staffId" placeholder="选择负责该房间的员工">
          <el-option v-for="item in staffList" :label="item.staffId" :value="item.staffId"></el-option>
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
        animalRoomId: '',
        animalRoomTotalCapacity: '',
        animalRoomCurrentCapacity: '',
        animalRoomAvailability: 'Y',
        staffId: ''
      },
      staffList: '',
      rules: {
        animalRoomId: [
          {required: true, message: '请输入房间编号', trigger: 'blur'}
        ],
        animalRoomTotalCapacity: [
          {required: true, message: '请输入房间总容量', trigger: 'blur'}
        ],
        staffId: [
          {required: true, message: '请选择房间负责人', trigger: 'change'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      _this.ruleForm.animalRoomCurrentCapacity = _this.ruleForm.animalRoomTotalCapacity
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8181/animalRoomInfo/add', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.animalRoomId + '添加成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/roomManage')
                }
              });
            } else {
              _this.$alert(_this.ruleForm.animalRoomId + '添加失败', '', {
                confirmButtonText: '确定',
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
    axios.get('http://localhost:8181/staffInfo/staffInfoList').then(function (resp) {
      _this.staffList = resp.data.data
    })
  }
}
</script>
