<template>
	<div class="base-charts">
		<el-row>
			<!-- <el-col :span="12" class="list-item">
				<div class="grid-content bg-purple">
					<Dotted/>
				</div>
			</el-col>
			<el-col :span="12" class="list-item">
				<div class="grid-content bg-purple-light">
					<Stacked/>
				</div>
			</el-col> -->
			<el-col :span="12" class="list-item">
				<h1>年龄分布</h1>
				<div class="grid-content bg-purple-light">
					<ageAnalysis/>
				</div>
			</el-col>
           <el-col :span="12" class="list-item">
			    <h1>地区分布</h1>
				<div class="grid-content bg-purple-light">
					<regionalDistribution/>
				</div>
			</el-col>
			<el-col :span="12" class="list-item">
			    <h1>好花榜</h1>
				<div class="grid-content bg-purple-light">
					<flowerRanking/>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import Stacked from './echarts/Stacked'
	import Dotted from './echarts/Dotted'
	import ageAnalysis from './echarts/ageAnalysis'
	import regionalDistribution from './echarts/regionalDistribution'
	import flowerRanking from './echarts/flowerRanking'
	export default {
	  name: 'Nofind',
		data(){
			return {
				
			}
		},
		components:{
			Stacked,
			Dotted,
			ageAnalysis,
			regionalDistribution,
			flowerRanking
		}
	}
</script>
<style scoped>
.base-charts {
	width: 100%;
	overflow: scroll;
}
.list-item {
	padding-left: 20px;
	padding-right: 20px;
	overflow: scroll;
}
</style>
