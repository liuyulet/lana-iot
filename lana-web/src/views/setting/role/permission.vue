<template>
	<el-dialog title="角色权限设置" v-model="visible" :width="500" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-tabs tab-position="top">
			<el-tab-pane label="菜单权限">
				<div class="treeMain">
					<el-tree ref="menu" node-key="name" :data="menu.list" :props="menu.props" show-checkbox></el-tree>
				</div>
			</el-tab-pane>
			<el-tab-pane label="数据权限">
				<el-form label-width="100px" label-position="left">
					<el-form-item label="规则类型">
						<el-select v-model="data.dataType" placeholder="请选择">
							<el-option label="全部可见" value="0"></el-option>
							<el-option label="所在部门及子级可见" value="1"></el-option>
							<el-option label="所在部门可见" value="2"></el-option>
							<el-option label="本人可见" value="3"></el-option>
<!--							<el-option label="选择的部门可见" value="4"></el-option>-->
						</el-select>
					</el-form-item>
<!--					<el-form-item label="选择部门" v-show="data.dataType=='4'">
						<div class="treeMain" style="width: 100%;">
							<el-tree ref="dept" node-key="id" :data="data.list" :props="data.props" show-checkbox></el-tree>
						</div>
					</el-form-item>
					<el-form-item label="规则值" v-show="data.dataType=='6'">
						<el-input v-model="data.rule" clearable type="textarea" :rows="6" placeholder="请输入自定义规则代码"></el-input>
					</el-form-item>-->
				</el-form>
			</el-tab-pane>
<!--			<el-tab-pane label="控制台模块">
				<div class="treeMain">
					<el-tree ref="grid" node-key="key" :data="grid.list" :props="grid.props" :default-checked-keys="grid.checked" show-checkbox></el-tree>
				</div>
			</el-tab-pane>-->
<!--			<el-tab-pane label="控制台">
				<el-form label-width="100px" label-position="left">
					<el-form-item label="控制台视图">
						<el-select v-model="dashboard" placeholder="请选择">
							<el-option v-for="item in dashboardOptions" :key="item.value" :label="item.label" :value="item.value">
								<span style="float: left">{{ item.label }}</span>
								<span style="float: right; color: #8492a6; font-size: 12px">{{ item.views }}</span>
							</el-option>
						</el-select>
						<div class="el-form-item-msg">用于控制角色登录后控制台的视图</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>-->
		</el-tabs>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script>
	export default {
		emits: ['success', 'closed'],
		data() {
			return {
				visible: false,
				isSaveing: false,
				menu: {
					list: [],
					checked: ["home","dashboard","userCenter","user","user.page","user.update","user.password"],
					props: {
						label: (data)=>{
							return data.meta.title
						}
					}
				},
				grid: {
					list: [],
					checked: ["welcome", "ver", "time", "progress", "echarts", "about"],
					props: {
						label: (data)=>{
							return data.title
						},
						disabled: (data)=>{
							return data.isFixed
						}
					}
				},
				data: {
					roleId:'',
					dataType :"1",
					list: [],
					checked: [],
					props: {},
					rule: ""
				},
				dashboard: "0",
				dashboardOptions: [
					{
						value: '0',
						label: '数据统计',
						views: 'stats'

					},
					{
						value: '1',
						label: '工作台',
						views: 'work'
					},
				]
			}
		},
		mounted() {
			// 菜单权限
			this.getMenu()
			// 数据权限
			this.getGrid()
		},
		methods: {
			open(roleId,dataScope){
				this.visible = true;
				this.data.roleId = roleId;
				this.data.dataType = dataScope.toString();

			},
			async submit() {
				this.isSaveing = true;
				//选中的和半选的合并后传值接口
				var checkedKeys = this.$refs.menu.getCheckedKeys().concat(this.$refs.menu.getHalfCheckedKeys())
				//控制台
				var checkedKeys_grid = this.$refs.grid.getCheckedKeys().concat(this.$refs.grid.getHalfCheckedKeys())
				//数据类型
				var form = {
					"menus": checkedKeys,
					"grids": checkedKeys_grid,
					"grid": this.data.dataType,
					"roleId": this.data.roleId
				}
				var result = await this.$API.system.role.roleLinkMenus.post(form);

				if(result.code == 200){
					this.$emit('success', this.form, this.mode)
					this.isSaveing = false;
					this.visible = false;
					this.$message.success("操作成功")
				}else{
					this.$alert(result.msg, "提示", {type: 'error'})
				}

			},
			async getMenu(){
				//获取当前用户所能分配的权限列表
				var res = await this.$API.system.menu.list.get({"roleId":this.data.roleId})
				this.menu.list = res.data
				//获取该角色已经绑定的菜单
				var resRoleList = await this.$API.system.menu.roleList.get({"roleId":this.data.roleId})
				//这个组件如果不是拥有全部菜单权限，则不要增加父级节点，
				if(resRoleList.data.length>0){
					this.menu.checked = resRoleList.data;
				}
				console.log(resRoleList.data)
				this.$nextTick(() => {
					this.$refs.menu.setCheckedKeys(this.menu.checked);
				})
			},
			getGrid(){
				this.grid.list = [
					{
						key: "welcome",
						title: "欢迎",
						isFixed: true
					},
					{
						key: "ver",
						title: "版本信息",
						isFixed: true
					},
					{
						key: "time",
						title: "时钟"
					},
					{
						key: "progress",
						title: "进度环"
					},
					{
						key: "echarts",
						title: "实时收入"
					},
					{
						key: "about",
						title: "关于项目"
					}
				]
			}
		}
	}
</script>

<style scoped>
	.treeMain {height:280px;overflow: auto;border: 1px solid #dcdfe6;margin-bottom: 10px;}
</style>
