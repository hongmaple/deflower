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
                    .get("/api/statistics/ageAnalysis",null,{headers: {"token": localStorage.getItem("eleToken")}})
                    .then(res => {
                         var option = {
                            series : [
                                {
                                    name: '用户年龄分析',
                                    type: 'pie',
                                    radius: '55%',
                                    roseType: 'angle',
                                    data: res.data
                                }
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
