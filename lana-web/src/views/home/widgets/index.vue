<template>
	<el-main>
		<el-alert title="以下数据均为暂时写死的数据，后续会不断地调整和对接正式的数据进行展示。目前仅仅是为了首页的数据展示" type="success" style="margin-bottom:20px;"></el-alert>
		<el-row :gutter="15">
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option2"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option3"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option4"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option5"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option6"></scEcharts>
				</el-card>
			</el-col>
		</el-row>
	</el-main>
</template>

<script>
import scEcharts from '@/components/scEcharts';

/**
 * 引入组件 @/components/scEcharts
 * 组件内部会自动加载主题 @/components/scEcharts/echarts-theme-T.js
 * 支持props包括 height，width，option
 * 组件export百度Echarts所有方法，使用方式: new scEcharts[fun]
 */

export default {
	name: 'chart',
	components: {
		scEcharts
	},
	data() {
		return {
			option: {
				title: {
					text: '设备日均在线统计',
					subtext: '日常设备平均在线情况',
				},
				grid: {
					top: '80px'
				},
				tooltip: {
					trigger: 'axis'
				},
				xAxis: {
					type: 'category',
					data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
				},
				yAxis: {
					type: 'value'
				},
				series: [{
					name: '在线',
					data: [120, 200, 150, 80, 70, 110, 130],
					type: 'bar',
					barWidth: '15px',
					itemStyle: { color: '#66b48c' }
				},
				{
					name: '离线',
					data: [110, 180, 120, 120, 60, 90, 110],
					type: 'bar',
					barWidth: '15px',
					itemStyle: { color: '#c8645d' }
				}]
			},
			option2: {
				title: {
					text: '情景模式统计',
					subtext: '规则命中处理折线图',
				},
				grid: {
					top: '80px'
				},
				tooltip: {
					trigger: 'axis'
				},
				xAxis: {
					type: 'category',
					data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
				},
				yAxis: {
					type: 'value'
				},
				series: [
					{
						name: '条件命中',
						data: [120, 200, 150, 80, 70, 110, 130],
						type: 'line',
					},
					{
						name: '设备控制',
						data: [110, 180, 120, 120, 60, 90, 110],
						type: 'line',
					}
					,
					{
						name: '数据转发',
						data: [130, 150, 90, 170, 120, 50, 150],
						type: 'line',
					}
				]
			},
			option3: {
				title: {
					text: '设备数量统计',
					subtext: '设备在线、离线饼图',
				},
				tooltip: {
					trigger: 'item'
				},
				series: [
					{
						name: '设备数量统计',
						type: 'pie',
						label: false,
						data: [

						]
					}
				]
			},
			option4: {
				title: {
					text: '消息数据统计',
					subtext: '基础雷达(属性)图',
				},
				tooltip: {
					trigger: 'item'
				},
				radar: {
					radius: 100,
					center: ['50%', '55%'],
					indicator: [
						{ name: '系统操作(crud)', max: 100},
						{ name: '设备操作', max: 100},
						{ name: '设备告警', max: 100},
						{ name: '设备接触告警', max: 100},
						{ name: '情景模式处理', max: 100}
					]
				},
				series: [{
					name: "SCUI",
					type: 'radar',
					areaStyle: {},
					data: [
						{
							value: [74, 90, 95, 65, 80, 31],
						}
					]
				}]
			},
			option5: {
				title: {
					text: '接入管理统计',
					subtext: '设备接入统计k线图',
				},
				grid: {
					top: '80px'
				},
				tooltip: {
					trigger: 'axis',
					axisPointer: {
						type: 'cross'
					}
				},
				xAxis: {
					data: ['2017-10-23', '2017-10-24', '2017-10-25', '2017-10-26', '2017-10-27', '2017-10-28', '2017-10-29', '2017-10-30'],
				},
				yAxis: {
					scale: true,
				},
				series: [{
					type: 'k',
					data: [
						[2213.19,2199.31,2191.85,2224.63],
						[2203.89,2177.91,2173.86,2210.58],
						[2170.78,2174.12,2161.14,2179.65],
						[2179.05,2205.5,2179.05,2222.81],
						[2212.5,2231.17,2212.5,2236.07],
						[2227.86,2235.57,2219.44,2240.26],
						[2242.39,2246.3,2235.42,2255.21],
						[2246.96,2232.97,2221.38,2247.86]
					]
				}]
			},
			option6: {
				title: {
					text: '网络速率统计',
					subtext: '当前网速仪表盘',
				},
				series: [{
					center: ['50%', '60%'],
					type: 'gauge',
					anchor: {
						show: true,
						showAbove: true,
						size: 20,
						itemStyle: {
							borderWidth: 5
						}
					},
					progress: {
						show: true
					},
					data: [{
						value: 5
					}]
				}]
			}
		}
	},
	mounted() {
		this.devicesPin()
	},
	methods: {
		async devicesPin(){
			var res = await this.$API.device.deviceItem.deviceHomeDataController.get();
			if(res.code == 200){
				//这里选择刷新整个表格 OR 插入/编辑现有表格数据
				const formattedData = res.data.map(item => ({
					value: item.value,
					name: item.name
				}));
				this.option3.series[0].data = formattedData;
				//this.$message.success("删除成功")

			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		}

	}
}
</script>

<style>
</style>
