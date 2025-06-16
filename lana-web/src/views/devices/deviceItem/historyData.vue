<template >
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1400" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-container>
<!--			<el-alert title="最大只能查看三个月的数据" type="warning" show-icon :closable="false"/>-->
			<el-header>
				<div class="left-panel">
					<el-date-picker v-model="search.date" @change="upsearch" type="datetimerange" range-separator="至"
					start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
				</div>
			</el-header>

			<el-main class="nopadding">
				<template>
					<sc-table ref="table" :data="apiData" stripe height="500"  :pagination="{ pageSize: 10 }" paginationLayout="">
						<el-table-column v-for="column in columns" :key="column.prop" :label="column.label" :prop="column.prop"></el-table-column>
					</sc-table>
				</template>
			</el-main>

		</el-container>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
		</template>
	</sc-dialog>
</template>
<script>

export default {
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "show",
			titleMap: {
				show: '设备历史数据'
			},
			visible: false,
			//表单数据
			dialog: {
				save: false
			},
			apiData: [],
			columns: [],
			search: {
				date: []
			}
		}
	},

	methods: {
		setData(data){
			console.log(data)
			//获取物模型
			this.gteDeviceMode(data.id)
			//获取历史数据
			this.gteHistoryData(data.id)
		},
		//显示
		open(mode='show'){
			this.mode = mode;
			this.visible = true;
			return this
		},
		async gteDeviceMode(deviceId){
			var res = await this.$API.device.deviceItem.getDeviceModeMap.get({'deviceId':deviceId});
			if(res.code == 200) {
				this.columns = res.data;
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},
		async gteHistoryData(deviceId){
			var res = await this.$API.device.historyData.page.get({'deviceId':deviceId});
			if(res.code == 200) {
				this.apiData = res.data;
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		}

	}
}


</script>

<style>

</style>
