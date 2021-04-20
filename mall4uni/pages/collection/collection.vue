<template>
<!--pages/category/category.wxml-->

<view class="container">
<!-- 滚动内容区 -->
  <view class="main">
	  <!-- 左侧菜单start -->
	  <scroll-view scroll-y="true" class="leftmenu">
	    <block v-for="(item, index) in categoryList" :key="index">
	      <view :class="'menu-item ' + (selIndex==index?'active':'') + ' '" :data-index="index" :data-id="item.id" @tap="onMenuTab">
	        {{item.name}}
	      </view>
	    </block>
	  </scroll-view>
	  <!-- 左侧菜单end -->
	  
    <!-- 右侧内容start -->
    <scroll-view scroll-y="true" class="rightcontent">
    <!-- <block wx:for='{{ productList}}' wx:key=''> -->
      <view class="cont-item" v-if="type==0">
        <block v-for="(item, index) in productList" :key="index">
          <view class="show-item" @tap="toProdPage" :data-prodid="item.id">
            <view class="more-prod-pic">
			  <image :src="serverUrl + item.images" class="more-pic" mode="widthFix"></image>
            </view>
            <view class="prod-text-right">
              <view class="prod-text more">{{item.title}}</view>
			  <view class="prod-price more">
			    <text class="symbol">￥</text> <text class="big-num">{{wxs.parsePrice(item.price)[0]}}</text><text class="small-num">.{{wxs.parsePrice(item.price)[1]}}</text> 
			  </view>
            </view>
          </view>
         
        </block>
      </view>
	  <view class="cont-item" v-if="type==1">
	    <block v-for="(item, index) in productList" :key="index">
	      <view class="show-item" @tap="toVideoDetailPage" :data-id="item.id">
	        <view class="more-prod-pic">
	  			  <image :src="serverUrl + item.pic" class="more-pic" mode="widthFix"></image>
	        </view>
	        <view class="prod-text-right">
	          <view class="prod-text more">{{item.title}}</view>
	  			  <view class="prod-price more" v-if="item.isCharge">
	  			    <text class="symbol">￥</text> <text class="big-num">{{wxs.parsePrice(item.price)[0]}}</text><text class="small-num">.{{wxs.parsePrice(item.price)[1]}}</text> 
	  			  </view>
	  			  <view class="prod-price more" v-else>
	  			    <text class="symbol">￥</text> <text class="big-num">免费</text> 
	  			  </view>
	        </view>
	      </view>
	     
	    </block>
	  </view>
     <!-- </block> -->
    </scroll-view>
    <!-- 右侧内容end -->
    <!-- </block> -->
  </view>

</view>
</template>

<script module="wxs" lang="wxs" src="../../wxs/number.wxs"></script>

<script>
// pages/category/category.js
var http = require("../../utils/http.js");
var config = require("../../utils/config.js");

export default {
  data() {
    return {
      selIndex: 0,
      productList: [],
	  serverUrl: config.domain,
	  categoryList: [
		  {
			  name: '花',
			  id: 0
		  },
		  {
			  name: '视频',
			  id: 1
		  }
	  ],
	  type: 0,
	  pages: 1,
	  pageNum: 1,
	  pageSize: 5
    };
  },
  components: {},
  props: {},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   this.getCollectionProd();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {},

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
	  if (this.pageNum < this.pages) {
	    this.setData({
	      pageNum: this.current + 1
	    });
	    this.loadProdData();
	  }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {},
  methods: {
	//跳转详情页
	toVideoDetailPage: function (e) {
	  var id = e.currentTarget.dataset.id;
      console.log(id);
	  uni.navigateTo({
	    url: '/pages/VideoTeachingDetails/VideoTeachingDetails?id=' + id
	  });
	},
	/**
	 * 分类点击事件
	 */
	onMenuTab: function (e) {
	  var id = e.currentTarget.dataset.id;
	  var index = e.currentTarget.dataset.index;
      
	  this.setData({
	    selIndex: index,
		type: id,
		productList: [],
		pages: 1,
		pageNum: 1
	  });
	  this.getCollectionProd();
	},    /**
     * 获取我的收藏商品
     */
    getCollectionProd: function () {
      var ths = this;
      uni.showLoading();
      var params = {
        url: "/flower/favorite/list",
        method: "POST",
		needToken: true,
        data: {
           pageNum: ths.pageNum,
           pageSize: ths.pageSize,
		   type: ths.type
        },
        callBack: function (res) {
			console.log(res.data.list)
          let list = [];

          if (res.pageNum == 1) {
            list = res.data.list;
          } else {
            list = ths.productList;
            list = list.concat(res.data.list);
          }

          ths.setData({
            productList: list,
            pages: res.pages
          });
          uni.hideLoading();
        }
      };
      http.request(params);
    },
	//跳转商品详情页
	toProdPage: function (e) {
	  var prodid = e.currentTarget.dataset.prodid;
	  uni.navigateTo({
	    url: '/pages/prod/prod?prodid=' + prodid
	  });
	}
  }
};
</script>
<style>
@import "./collection.css";
</style>