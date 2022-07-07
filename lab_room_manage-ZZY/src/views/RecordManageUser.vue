<template>
  <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm" label-width="100px"
             style="margin-left: -40px">
      <el-form-item label="字段：" prop="key">
        <el-select v-model="ruleForm.key" placeholder="请选择字段" style="width: 160px;float: left">
          <el-option label="动物编号" value="animal_id"></el-option>
          <el-option label="员工编号" value="staff_id"></el-option>
        </el-select>
      </el-form-item>
      <div style="border: 0px solid red;width: 400px;height: 60px;position: relative;top: -64px;left: 270px">
        <el-form-item label="值：">
          <el-input v-model="ruleForm.value" placeholder="请输入关键字" style="width: 160px;"></el-input>
          <el-button icon="el-icon-search" style="position: relative;left: 10px;" type="primary"
                     @click="submitForm('ruleForm')">搜索
          </el-button>
        </el-form-item>
      </div>
    </el-form>

    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%;position: relative;top:-30px">
      <el-table-column
          fixed
          label="动物编号"
          prop="animalId"
          width="130">
      </el-table-column>
      <el-table-column
          label="员工编号"
          prop="staffId"
          width="130">
      </el-table-column>
      <el-table-column
          label="进出行为"
          prop="animalIoBehavior"
          width="130">
      </el-table-column>
      <el-table-column
          label="进出时间"
          prop="animalIoTime"
          width="130">
      </el-table-column>
    </el-table>
    <el-pagination :current-page.sync="currentPage"
                   :page-size="pageSize"
                   :total="total"
                   background
                   layout="prev, pager, next"
                   style="margin-top: 20px;float: right"
                   @current-change="page">
    </el-pagination>
  </div>

</template>

<script>
export default {
  data() {
    return {
      tableData: null,
      currentPage: 1,
      pageSize: 5,
      total: null,
      key: '',
      value: '',
      ruleForm: {
        key: '',
        value: '',
        page: '',
        size: 5
      },
      rules: {
        key: [
          {required: true, message: '请选择字段', trigger: 'change'}
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      const _this = this
      //让翻页复原
      _this.currentPage = 1
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          _this.ruleForm.page = _this.currentPage
          axios.get('http://localhost:8181/animalIoRecord/search', {params: _this.ruleForm}).then(function (resp) {
            _this.tableData = resp.data.data.data
            _this.total = resp.data.data.total
          })
        }
      });
    },
    page(currentPage) {
      const _this = this
      if (_this.ruleForm.value === '') {
        axios.get('http://localhost:8181/animalIoRecord/list/' + currentPage + '/' + _this.pageSize).then(function (resp) {
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      } else {
        _this.ruleForm.page = _this.currentPage
        axios.get('http://localhost:8181/animalIoRecord/search', {params: _this.ruleForm}).then(function (resp) {
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      }

    },
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/animalIoRecord/list/1/' + _this.pageSize).then(function (resp) {
      _this.tableData = resp.data.data.data
      _this.total = resp.data.data.total
    })
  }
}
</script>