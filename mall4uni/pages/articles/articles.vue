<template>
    <view class="container">
       <view class="title-content">
            <view  @tap="topage">
                <image src="../../static/images/icon/fanhui.png" class="topage">
            </view>
            <view class="title">
                <view>
                    <text>送花的常识与技巧</text>
                </view>
            </view>
        </view>
        <view class="articles">
            <text class="title1">新春佳节：</text>
            <text class="title2">可选送大丽花、牡丹花、水仙花、桃花、吉庆果、金桔、状元红、吉祥果等表示吉祥。<br/></text>
            <text class="title1">祝贺开业：</text>
            <text class="title2">可选红月季、牡丹、一品红等，表示开业大吉，生意兴隆。<br/></text>
            <text class="title1">看望父母：</text>
            <text class="title2">可选剑兰花、康乃馨、百合花、菊花满天星、后插成花蓝或花束、祝父母百年好和，幸福美满。<br/></text>
            <text class="title1">探望病人：</text>
            <text class="title2">可选素静淡雅的马蹄莲、素色苍兰、剑兰、康乃馨表示问候，并祝愿早日康复。<br/></text>
            <text class="title1">送别朋友：</text>
            <text class="title2">赠一束芍药花，表示依依惜别之情。<br/></text>
        </view>
      
    </view>
</template>

<script>
    var http = require("../../utils/http.js");
    var config = require("../../utils/config.js");
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			topage: function (){
			    uni.navigateBack({
			        delta: 1
			    });
			},
		}
	}
</script>

<style>
    /* pages/prod-classify/prod-classify.wxss */
    
    page {
      background: #fff;
    }
    
    .line-fix {
      width: 100%;
      position: fixed;
      top: 0;
      margin-top: 30rpx;
    }
    
    .tit-background {
      margin-top: 100rpx;
      width: 100%;
      height: 20rpx;
      background: #f4f4f4;
    }
    .title-content{
       height:100rpx;
       background-color: #fff;
       position: fixed;
       top: 0;
       display: flex;
       z-index: 999;
       width: 100%;
       border-bottom: 2rpx solid #f4f4f4;
      /* background-color: #00AAFF; */
    }
    .topage{
        z-index: 999;
        margin-top: 20rpx;
        margin-left:20rpx;
        margin-right:170rpx;
        width:60rpx;
        height:60rpx;
    }
    .title {
      font-family: STKaiti;
      font-size: 30px;
      margin-top:20rpx;
      text-align:center;
      margin-left:-80rpx;
    } 
    .articles{
        margin-top:100rpx;
    }
    .title1{
        font-family: STKaiti;
        font-size: 20px;
        margin-top:20rpx; 
    }
    .title2{
        font-size: 17px;
        margin-top:20rpx; 
    }
</style>
