<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" v-auth="'sys:role:save'" @click="add"></el-button>
				<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length==0" v-auth="'sys:role:delete'" @click="batch_del"></el-button>
				<el-button type="primary" plain :disabled="selection.length!=1" v-auth="'sys:role:roleLinkMenus'"  @click="permission">权限设置</el-button>
			</div>
			<div class="right-panel">
				<div class="right-panel-search">
					<el-input v-model="search.name" placeholder="角色名称" clearable></el-input>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
		</el-header>
		<el-main class="nopadding">
			<scTable ref="table" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="#" type="index" width="100"></el-table-column>
				<el-table-column label="角色名称" prop="name" width="150"></el-table-column>
				<el-table-column label="角色编码" prop="roleCode" width="150"></el-table-column>
				<el-table-column label="排序" prop="sort" width="100"></el-table-column>
				<el-table-column label="状态" prop="status" width="150">
					<template #default="scope">
						<el-tag v-if="scope.row.status==1" type="success">启用</el-tag>
						<el-tag v-if="scope.row.status==0" type="danger">未启用</el-tag>
					</template>
				</el-table-column>
				<el-table-column label="创建时间" prop="createTime" width="200"></el-table-column>
				<el-table-column label="备注" prop="remark" min-width="220"></el-table-column>
				<el-table-column label="操作" fixed="right" align="center" width="220">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" @click="table_show(scope.row, scope.$index)">查看</el-button>
							<el-button text type="primary" size="small" v-auth="'sys:role:update'" @click="table_edit(scope.row, scope.$index)">编辑</el-button>
							<el-popconfirm title="确定删除吗？" v-auth="'sys:role:delete'" @confirm="table_del(scope.row, scope.$index)">
								<template #reference>
									<el-button text type="primary" v-auth="'sys:role:delete'" size="small">删除</el-button>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>

			</scTable>
		</el-main>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>

	<permission-dialog v-if="dialog.permission" ref="permissionDialog" @closed="dialog.permission=false"></permission-dialog>

</template>

<script>
	import saveDialog from './save'
	import permissionDialog from './permission'

	export default {
		name: 'role',
		components: {
			saveDialog,
			permissionDialog
		},
		data() {
			return {
				dialog: {
					save: false,
					permission: false
				},
				apiObj: this.$API.system.role.list,
				selection: [],
				search: {
					keyword: null
				}
			}
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
			//查看
			table_show(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('show').setData(row)
				})
			},
			//权限设置
			permission(){
				this.dialog.permission = true
				this.$nextTick(() => {
					this.$refs.permissionDialog.open(this.selection[0].id, this.selection[0].dataScope)
				})
			},
			//删除
			async table_del(row){
				var reqData = {id: row.id}
				var res = await await this.$API.system.role.delete.get(reqData);
				if(res.code == 200){
					this.$refs.table.refresh()
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
			//批量删除
			async batch_del(){
				this.$confirm(`确定删除选中的 ${this.selection.length} 项吗？如果删除项中含有子集将会被一并删除`, '提示', {
					type: 'warning'
				}).then(async () => {
					//获取选中id集合列表
					const id = this.selection.map(item => item.id).join(',');
					const loading = this.$loading();
					var res = await await this.$API.system.role.delete.get({"id":id});
					if (res.code == 200) {
						this.$refs.table.refresh()
						this.$message.success("删除成功")
						this.$refs.table.refresh()
						loading.close();
					} else {
						this.$alert(res.msg, "提示", {type: 'error'})
					}
				}).catch(() => {

				})
			},
			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//表格内开关
			changeSwitch(val, row){
				row.status = row.status == '1'?'0':'1'
				row.$switch_status = true;
				setTimeout(()=>{
					delete row.$switch_status;
					row.status = val;
					this.$message.success("操作成功")
				}, 500)
			},
			//搜索
			upsearch(){
				this.$refs.table.upData(this.search)
			},
			//根据ID获取树结构
			filterTree(id){
				var target = null;
				function filter(tree){
					tree.forEach(item => {
						if(item.id == id){
							target = item
						}
						if(item.children){
							filter(item.children)
						}
					})
				}
				filter(this.$refs.table.tableData)
				return target
			},
			//本地更新数据
			handleSaveSuccess(data, mode){
				if(mode=='add'){
					this.$refs.table.refresh()
				}else if(mode=='edit'){
					this.$refs.table.refresh()
				}
			}
		}
	}
</script>

<style>
</style>
