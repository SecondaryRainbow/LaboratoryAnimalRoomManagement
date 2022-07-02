<template>
  <div style="margin-top: 60px;margin-left:80px;border: 0px solid red;" >
    <el-form style="margin-left: -40px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="字段：" prop="key">
        <el-select v-model="ruleForm.key" style="width: 160px;float: left" placeholder="请选择字段">
          <el-option label="工号" value="job_id"></el-option>
          <el-option label="姓名" value="name"></el-option>
          <el-option label="职务" value="post"></el-option>
        </el-select>
      </el-form-item>
      <div style="border: 0px solid red;width: 400px;height: 60px;position: relative;top: -64px;left: 270px">
        <el-form-item label="值：">
          <el-input v-model="ruleForm.value" placeholder="请输入关键字" style="width: 160px;"></el-input>
          <el-button type="primary" icon="el-icon-search" style="position: relative;left: 10px;" @click="submitForm('ruleForm')">搜索</el-button>
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
          prop="jobId"
          label="工号"
          width="140">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="140">
      </el-table-column>
      <el-table-column
          prop="sex"
          label="性别"
          width="140">
      </el-table-column>
      <el-table-column
          prop="post"
          label="职务"
          width="140">
      </el-table-column>
      <el-table-column
          prop="level"
          label="级别"
          width="140">
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名"
          width="140">
      </el-table-column>
      <el-table-column
          prop="password"
          label="密码"
          width="140">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="edit(scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="margin-top: 20px;float: right"
                   background
                   layout="prev, pager, next"
                   :page-size="pageSize"
                   :total="total"
                   :current-page.sync="currentPage"
                   @current-change="page">
    </el-pagination>
  </div>

</template>

<script>
export default {
  data() {
    return {
      tableData:null,
      currentPage: 1,
      pageSize: 3,
      total: '',
      key: '',
      value: '',
      ruleForm: {
        key: '',
        value: '',
        page: '',
        size: 3
      },
      rules: {
        key: [
          { required: true, message: '请选择字段', trigger: 'change' }
        ]
      }
    }
  },
  methods:{
    submitForm(formName) {
      const _this = this
      //让翻页复原
      _this.currentPage = 1
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          _this.ruleForm.page = _this.currentPage
          axios.get('http://localhost:8181/systemUser/search',{params:_this.ruleForm}).then(function (resp) {
            _this.tableData = resp.data.data.data
            _this.total = resp.data.data.total
          })
        }
      });
    },
    page(currentPage){
      const _this = this
      if(_this.ruleForm.value == ''){
        axios.get('http://localhost:8181/systemUser/list/'+currentPage+'/'+_this.pageSize).then(function (resp) {
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      } else {
        _this.ruleForm.page = _this.currentPage
        axios.get('http://localhost:8181/systemUser/search',{params:_this.ruleForm}).then(function (resp) {
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      }
    },
    edit(row){
      this.$router.push('/systemUserUpdate?jobId='+row.jobId)
    },
    del(row) {
      const _this = this
      this.$confirm('确认删除【'+row.jobId+' '+row.username+'】吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete('http://localhost:8181/systemUser/deleteById/'+row.jobId).then(function (resp) {
          if(resp.data.code==0){
            _this.$alert('【'+row.jobId+' '+row.username+'】已删除', '', {
              confirmButtonText: '确定',
              callback: action => {
                location.reload()
              }
            });
          }
        });
      }).catch(()=>{
        this.$message({
          type:'info',
          message:'已取消删除'
        });
      });
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/systemUser/list/1/'+_this.pageSize).then(function (resp) {
      _this.tableData = resp.data.data.data
      _this.total = resp.data.data.total
    })
  }
}
</script>