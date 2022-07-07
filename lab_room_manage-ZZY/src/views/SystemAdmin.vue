<template>
  <el-container class="home_container">
    <el-header class="home_header">
      <div class="home_title">动物分组系统--管理员</div>
      <div class="home_userinfoContainer">
        <el-dropdown>
                  <span class="el-dropdown-link home_userinfo">
                    {{ admin.username }}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
                  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>

      <el-aside class="home_aside" width="200px">

        <el-menu router>

          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>员工模块</template>
            <el-menu-item :class="$route.path==='/staffAdd'?'is-active':''" index="/staffAdd">
              <i class="el-icon-folder-add"></i>添加员工
            </el-menu-item>
            <el-menu-item :class="$route.path==='/staffManage'?'is-active':''" index="/staffManage">
              <i class="el-icon-document-copy"></i>员工管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>用户模块</template>
            <el-menu-item :class="$route.path==='/userAdd'?'is-active':''" index="/userAdd">
              <i class="el-icon-folder-add"></i>添加用户
            </el-menu-item>
            <el-menu-item :class="$route.path==='/userManage'?'is-active':''" index="/userManage">
              <i class="el-icon-document-copy"></i>用户管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-menu"></i>动物模块</template>
            <el-menu-item :class="$route.path==='/animalAdd'?'is-active':''" index="/animalAdd">
              <i class="el-icon-folder-add"></i>添加动物
            </el-menu-item>
            <el-menu-item :class="$route.path==='/animalManage'?'is-active':''" index="/animalManage">
              <i class="el-icon-document-copy"></i>动物管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title"><i class="el-icon-menu"></i>动物组模块</template>
            <el-menu-item :class="$route.path==='/groupAdd'?'is-active':''" index="/groupAdd">
              <i class="el-icon-folder-add"></i>添加动物组
            </el-menu-item>
            <el-menu-item :class="$route.path==='/groupManage'?'is-active':''" index="/groupManage">
              <i class="el-icon-document-copy"></i>动物组管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="5">
            <template slot="title"><i class="el-icon-menu"></i>房间模块</template>
            <el-menu-item :class="$route.path==='/roomAdd'?'is-active':''" index="/roomAdd">
              <i class="el-icon-folder-add"></i>添加房间
            </el-menu-item>
            <el-menu-item :class="$route.path==='/roomManage'?'is-active':''" index="/roomManage">
              <i class="el-icon-document-copy"></i>房间管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="6">
            <template slot="title"><i class="el-icon-menu"></i>进出记录</template>
            <el-menu-item :class="$route.path==='/recordAdd'?'is-active':''" index="/recordAdd">
              <i class="el-icon-folder-add"></i>添加记录
            </el-menu-item>
            <el-menu-item :class="$route.path==='/recordManage'?'is-active':''" index="/recordManage">
              <i class="el-icon-document-copy"></i>记录查看
            </el-menu-item>
          </el-submenu>

        </el-menu>

      </el-aside>

      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <router-view></router-view>
        </el-main>
        <el-footer class="home_footer">2022 © LARM</el-footer>
      </el-container>

    </el-container>

  </el-container>
</template>
<script>
export default {
  methods: {
    logout() {
      let _this = this;
      this.$confirm('注销登录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        localStorage.removeItem('systemAdmin')
        _this.$router.replace({path: '/'})
      })
    }
  },
  data() {
    return {
      admin: ''
    }
  },
  created() {
    let admin = JSON.parse(window.localStorage.getItem('systemAdmin'))
    this.admin = admin
  }
}
</script>

<style>
.home_container {
  height: 100%;
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
}

.home_header {
  background-color: #2B2B2B;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.home_title {
  color: #C2C2C2;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}

.home_userinfoContainer {
  display: inline;
  margin-right: 20px;
}

.home_aside {
  background-color: #FFFFFF;
}

.home_footer {
  background-color: #FFFFFF;
  color: #000000;
  font-size: 18px;
  line-height: 60px;
  text-align: center;
}
</style>
