<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="动物组编号">
        <el-input v-model="ruleForm.animalGroupId" disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="组内动物数">
        <el-input v-model="ruleForm.animalNum" disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="所在房间">
        <el-select v-model="ruleForm.animalRoomId" placeholder="选择该组所在的房间">
          <el-option v-for="item in roomList" :label="item.animalRoomId" :value="item.animalRoomId"></el-option>
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
        animalGroupId: '',
        animalNum: '',
        animalRoomId: ''
      },
      roomList: '',
      rules: {}
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          axios.put('http://localhost:8181/animalGroupInfo/update', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.animalGroupId + '修改成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/groupManage$User')
                }
              });
            } else {
              _this.$alert(_this.ruleForm.animalGroupId + '修改失败', '', {
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
    axios.get('http://localhost:8181/animalGroupInfo/findById/' + _this.$route.query.animalGroupId).then(function (resp) {
      _this.ruleForm = resp.data.data
    })
    axios.get('http://localhost:8181/animalRoomInfo/availableRoom/' + _this.$route.query.animalGroupId).then(function (resp) {
      _this.roomList = resp.data.data
    })
  }
}
</script>
