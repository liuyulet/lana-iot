<template>
	<el-container>
		<el-aside width="300px" v-loading="showGrouploading">
			<el-container>
				<el-header>
					<el-input placeholder="输入关键字进行过滤" v-model="groupFilterText" clearable></el-input>
				</el-header>
				<el-main class="nopadding">
					<el-tree ref="group" class="menu" node-key="id" :data="group" :current-node-key="''" :highlight-current="true" :expand-on-click-node="false" :filter-node-method="groupFilterNode" @node-click="groupClick"></el-tree>
				</el-main>
			</el-container>
		</el-aside>
		<el-container>
				<el-header>
					<div class="left-panel">
						<el-button type="primary" v-auth="'sys:user:save'" icon="el-icon-plus"  @click="add"></el-button>
						<el-button type="danger" v-auth="'sys:user:delete'" plain icon="el-icon-delete" :disabled="selection.length==0" @click="batch_del"></el-button>
<!--						<el-button type="primary" plain :disabled="selection.length==0">分配角色</el-button>-->
<!--						<el-button type="primary" plain :disabled="selection.length==0">密码重置</el-button>-->
					</div>
					<div class="right-panel">
						<div class="right-panel-search">
							<el-input v-model="search.name" placeholder="登录账号 / 姓名" clearable></el-input>
							<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
						</div>
					</div>
				</el-header>
				<el-main class="nopadding">
					<scTable ref="table" :apiObj="apiObj" @selection-change="selectionChange" stripe remoteSort remoteFilter>
						<el-table-column type="selection" width="50"></el-table-column>
						<el-table-column label="ID" prop="id" width="100" sortable='custom'></el-table-column>
						<el-table-column label="头像" width="120" column-key="filterAvatar" :filters="[{text: '已上传', value: '1'}, {text: '未上传', value: '0'}]">
							<template #default="scope">
								<el-avatar :src="scope.row.avatar" size="small"></el-avatar>
							</template>
						</el-table-column>
						<el-table-column label="登录账号" prop="username" width="150" sortable='custom' column-key="filterUserName" :filters="[{text: '系统账号', value: '1'}, {text: '普通账号', value: '0'}]"></el-table-column>
						<el-table-column label="昵称" prop="realName" width="150" sortable='custom'></el-table-column>
						<el-table-column label="用户状态" prop="status" width="150" sortable='custom'>
							<template #default="scope">
								<el-tag v-if="scope.row.status==1" type="success">正常</el-tag>
								<el-tag v-if="scope.row.status==0" type="danger">停用</el-tag>
							</template>
						</el-table-column>
<!--						<el-table-column label="所属角色" prop="groupName" width="200" sortable='custom'></el-table-column>-->
						<el-table-column label="加入时间" prop="createTime" width="150" sortable='custom'></el-table-column>
						<el-table-column label="操作" fixed="right" align="center" width="250">
							<template #default="scope">
								<el-button-group>
									<el-button text type="primary" size="small"  @click="table_show(scope.row, scope.$index)">查看</el-button>
									<el-button text type="primary" size="small" v-auth="'sys:user:update'" @click="table_edit(scope.row, scope.$index)">编辑</el-button>
									<el-popconfirm title="确定删除吗？" v-auth="'sys:user:delete'" @confirm="table_del(scope.row, scope.$index)">
										<template #reference>
											<el-button text type="primary" v-auth="'sys:user:delete'" size="small">删除</el-button>
										</template>
									</el-popconfirm>
									<el-button text type="primary" size="small" v-auth="'sys:user:password'" @click="reset_password(scope.row.id)">重置密码</el-button>
								</el-button-group>
							</template>
						</el-table-column>

					</scTable>
				</el-main>
		</el-container>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSuccess" @closed="dialog.save=false"></save-dialog>

</template>

<script>
	import saveDialog from './save'

	export default {
		name: 'user',
		components: {
			saveDialog
		},
		data() {
			return {
				dialog: {
					save: false
				},
				showGrouploading: false,
				groupFilterText: '',
				group: [],
				apiObj: this.$API.system.user.list,
				selection: [],
				search: {
					name: null
				}
			}
		},
		watch: {
			groupFilterText(val) {
				this.$refs.group.filter(val);
			}
		},
		mounted() {
			//加载组织机构
			this.getGroup()
			//加载用户列表
		},
		methods: {

			//添加
			add(){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open()
				})
			},
			//编辑
			table_edit(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('edit').setData(row)
				})
			},
			async reset_password(id){
				var res = await this.$API.system.user.resetPassword.post({"id":id});
				if(res.code == 200){
					this.$message.success("操作成功，重置后的原始密码为：123456")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
			//查看
			table_show(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('show').setData(row)
				})
			},
			//删除
			async table_del(row, index){
				var reqDataArray = [row.id];
				var res = await this.$API.system.user.delete.post(reqDataArray);
				if(res.code == 200){
					//这里选择刷新整个表格 OR 插入/编辑现有表格数据
					this.$refs.table.tableData.splice(index, 1);
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
			//批量删除
			async batch_del(){
				this.$confirm(`确定删除选中的 ${this.selection.length} 项吗？`, '提示', {
					type: 'warning'
				}).then(() => {
					const loading = this.$loading();
					this.selection.forEach(item => {
						this.$refs.table.tableData.forEach((itemI, indexI) => {
							if (item.id === itemI.id) {
								this.$refs.table.tableData.splice(indexI, 1)
							}
						})
					})
					loading.close();
					this.$message.success("操作成功")
				}).catch(() => {

				})
			},
			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//加载树数据
			async getGroup(){
				this.showGrouploading = true;
				var res = await this.$API.system.dept.list.get();
				this.showGrouploading = false;
				var allNode ={id: '', label: '所有'}
				res.data.unshift(allNode);
				this.group = res.data;
			},
			//树过滤
			groupFilterNode(value, data){
				if (!value) return true;
				return data.label.indexOf(value) !== -1;
			},
			//树点击事件
			groupClick(data){
				var params = {
					groupId: data.id
				}
				this.$refs.table.reload(params)
			},
			//搜索
			upsearch(){
				this.$refs.table.upData(this.search)
			},
			//本地更新数据
			handleSuccess(data, mode){
				if(mode=='add'){
					data.id = new Date().getTime()
					this.$refs.table.tableData.unshift(data)
				}else if(mode=='edit'){
					this.$refs.table.tableData.filter(item => item.id===data.id ).forEach(item => {
						Object.assign(item, data)
					})
				}
			}
		}
	}
</script>

<style>
</style>
