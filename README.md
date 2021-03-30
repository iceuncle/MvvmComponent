### 项目介绍

* 基于MVVM模式集成了Jetpack相关组件库LiveData+ViewModel+DataBinding+Room+Paging等
* Room数据库进行本地数据管理
* Paging3实现分页加载（包括普通分页模式和feeds流）
* Hilt进行依赖注入，实现NetRepository(网络层)、DataRepository(本地数据库)、ViewModel等的注入
* 通过ARouter实现组件化，主要按照通用基础模块和业务模块进行划分，业务模块单独编译配置。

#### 组件化

* moudle_base:通用模块 包括基础组件、通用功能组件等  被其他业务模块依赖
  * lib_net: 基础组件 网络
* moudle_login: 业务模块 注册登录模块
* moudle_home: 业务模块 首页模块
* module_project: 业务模块 项目模块

项目没有实现多少业务功能，主要是想通过这个项目简单封装和使用一下最新的Jetpack组件，后续有时间会慢慢完善功能。

