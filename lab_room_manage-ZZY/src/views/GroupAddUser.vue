<template>
  <div style="margin-top: 60px;margin-left:330px;width: 300px;height: 500px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px">
      <el-form-item label="动物组编号" prop="animalGroupId">
        <el-input v-model="ruleForm.animalGroupId"></el-input>
      </el-form-item>
      <el-form-item label="所在房间">
        <el-select v-model="ruleForm.animalRoomId" placeholder="选择该组所在的房间">
          <el-option v-for="item in roomList" :label="item.animalRoomId" :value="item.animalRoomId"></el-option>
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
        animalGroupId: '',
        animalNum: '',
        animalRoomId: ''
      },
      roomList: '',
      rules: {
        animalGroupId: [
          {required: true, message: '请输入动物组编号', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      _this.ruleForm.animalNum = 0;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8181/animalGroupInfo/add', _this.ruleForm).then(function (resp) {
            if (resp.data.code === 0) {
              _this.$alert(_this.ruleForm.animalGroupId + '添加成功', '', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/groupManage$User')
                }
              });
            } else {
              _this.$alert(_this.ruleForm.animalGroupId + '添加失败', '', {
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
    axios.get('http://localhost:8181/animalRoomInfo/roomList').then(function (resp) {
      _this.roomList = resp.data.data
    })
  }
}
</script>
