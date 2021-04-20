<template>
  <div class="home-top">
    <div class="home-wrapper">
		<el-row :gutter="20">
		  <el-col :span="3">
			  <div class="grid-content bg-purple pay">
				<p class="list_number">{{statisticsTop.saleroom}}</p>
				<p>销售额</p>
			  </div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple income">
		  	<p class="list_number">{{statisticsTop.orderQuantity}}</p>
		  	<p>订单数</p>
		  	</div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple hidden_investors">
		  	<p class="list_number">{{statisticsTop.numberGoods}}</p>
		  	<p>商品数</p>
		  	</div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple intention_investors">
		  	<p class="list_number">{{statisticsTop.numberVideo}}</p>
		  	<p>视频数</p>
		  	</div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple waitpending_investors">
		  	<p class="list_number">{{statisticsTop.totalNumberRegistrants}}</p>
		  	<p>总注册人数</p>
		  	</div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple pending_investors">
		  	<p class="list_number">%{{statisticsTop.menThan}}</p>
		  	<p>男性占比</p>
		  	</div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple pass_investors">
		  	<p class="list_number">%{{statisticsTop.womenThan}}</p>
		  	<p>女性占比</p>
		  	</div>
		  </el-col>
		  <el-col :span="3">
		  	<div class="grid-content bg-purple pay">
		  	<p class="list_number">+{{statisticsTop.newRegistrations}}</p>
		  	<p>新增注册人数</p>
		  	</div>
		  </el-col>
		</el-row>
	</div>
  </div>
</template>

<script>
// @ is an alias to /src
export default {
  name: 'Nofind',
   data() {
    return {
      statisticsTop: {}
	}
  },
  methods: {
        statistics_top() {
			this.$axios
				.get("/api/statistics/top",null,{headers: {"token": localStorage.getItem("eleToken")}})
				.then(res => {
				this.statisticsTop = res.data;
				}).catch(err => console.log(err));
       },
  },
  created() {
    this.statistics_top();
  }
}
</script>
<style scoped>
  .home-top {
	width: 100%;
	height: 100%;
	overflow: hidden;
  }
  .home-wrapper {
	padding:15px;
  }
  .grid-content {
    min-height: 36px;
	text-align: center;
    font-size: 14px;
    border-radius: 6px;
    padding: 15px 0;
    color: #fff;
  }
  .list_number{
	font-size: 16px;
	font-weight: 700;
	padding-bottom: 5px;
  }
  .pay {
	background: #18a689;
  }
  .income{
	background-color: #9c0;
  }
  .hidden_investors{
	background-color: #3c9;
  }
  .intention_investors  {
	background-color: #3b5999;
  }
  .waitpending_investors {
	background-color: #6c9;
  }
  .pending_investors{
	background-color: #099;
  }
  .pass_investors {
	background-color: #f90;
  }
  .newadd_investors {
	background-color: #09c;
  }
</style>
