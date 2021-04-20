<template>
	<div>
		<div id="listChart" ref="myChart" :style="{width: '100%', height: '400px'}"></div>
	</div>
</template>

<script>
	import echarts from 'echarts'
	export default {
		data() {
			return {

			};
		},
		mounted() {
			this.$nextTick(()=>{
				this.drawLine();
			})
		},
		methods: {
			drawLine() {
				let listChart = this.$echarts.init(this.$refs.myChart)
				this.$axios
                    .get("/api/statistics/regionalDistribution",null,{headers: {"token": localStorage.getItem("eleToken")}})
                    .then(res => {
                    // 指定图表的配置项和数据
                    var option = {
                        legend: {},
                        tooltip: {},
                        dataset: {
                            // 提供一份数据。
                            source: res.data.source
                        },
                        // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
                        xAxis: {type: 'category'},
                        // 声明一个 Y 轴，数值轴。
                        yAxis: {},
                        // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
                        series: [
                            {type: 'bar'}
                        ]
                    };
				    listChart.setOption(option);
                }).catch(err => console.log(err));
			}
		}
	}
</script>

<style>

</style>
