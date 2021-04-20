<template>
	<view class="container">
		<!--input列表 -->
		<view class="input-box">
		  <view class="section">
		    <text>标题</text>
		    <input placeholder="请输入标题" type="text" maxlength="11" :value="VideoTeaching.title" @input="onTitleInput"></input>
		  </view>
		  <view class="section">
			  <text>封面</text>
               <image style="width: 130rpx;height: 130rpx;padding: 1%;" @tap="upload" :src="VideoTeaching.pic?serverUrl+VideoTeaching.pic :'../../static/images/icon/upload.png'"></image>
		  </view> 
		  <view class="section">
		  	   <text>视频</text>
			   <image v-if="!VideoTeaching.url" style="width: 130rpx;height: 130rpx;padding: 1%;" @tap="uploadVideo" src="../../static/images/icon/upload.png"></image>
			   <video v-else style="width: 100%; " id="myVideo" :src="serverUrl + VideoTeaching.url"
			       @error="videoErrorCallback" enable-danmu danmu-btn controls></video>
		  </view> 
		  <view class="section">
		  	   <text>是否收费</text>
		  	   <switch checked @change="switch1Change" />
		  </view>
		  <view class="section"v-if="VideoTeaching.isCharge">
		    <text>价格</text>
		    <input placeholder="请输入价格" type="number" :value="VideoTeaching.price" @input="onPriceInput"></input>
		  </view>
		</view>
		<!-- end input列表 -->
		<!-- 功能按钮 -->
		<view class="btn-box">
		  <view class="keep btn" @tap="onSubmiti">
		    <text>发布</text>
		  </view>
		</view>
		<!-- end 功能按钮 -->
	</view>
</template>

<script>
	var http = require("../../utils/http.js");
	var config = require("../../utils/config.js");
	var index = [18, 0, 0];
	var t = 0;
	var show = false;
	var moveY = 200;
	export default {
		data() {
			return {
				serverUrl: config.domain,
				VideoTeaching: {
					isCharge: true
				}
			}
		},
		/**
		 * 生命周期函数--监听页面显示
		 */
		onShow: function() {
		},
		methods: {
			upload: function() {
				uni.chooseImage({
				    success: (chooseImageRes) => {
				        const tempFilePaths = chooseImageRes.tempFilePaths;
				        const uploadTask = uni.uploadFile({
				            url: config.domain+"/common/upload", //仅为示例，非真实的接口地址
				            filePath: tempFilePaths[0],
				            name: 'file',
				            formData: {
				                
				            },
				            success: (uploadFileRes) => {
								var data = JSON.parse(uploadFileRes.data);
								console.log(data.fileName);
								this.setData({
								  'VideoTeaching.pic': data.fileName
								});
				            }
				        });
				
				        uploadTask.onProgressUpdate((res) => {
				           
				        });
				    }
				});
		    },
			uploadVideo: function() {
				uni.chooseImage({
				    success: (chooseImageRes) => {
				        const tempFilePaths = chooseImageRes.tempFilePaths;
				        const uploadTask = uni.uploadFile({
				            url: config.domain+"/common/upload", //仅为示例，非真实的接口地址
				            filePath: tempFilePaths[0],
				            name: 'file',
				            formData: {
				                
				            },
				            success: (uploadFileRes) => {
								var data = JSON.parse(uploadFileRes.data);
								console.log(data.fileName);
								this.setData({
								  'VideoTeaching.url': data.fileName
								});
				            }
				        });
				
				        uploadTask.onProgressUpdate((res) => {
				            
				        });
				    }
				});
		    },
			onPriceInput: function (e) {
				this.setData({
				  'VideoTeaching.price': e.detail.value
				});
			},
			onTitleInput: function (e) {
				this.setData({
				  'VideoTeaching.title': e.detail.value
				});
			},
			onSubmiti: function () {
				if(!this.VideoTeaching.title||typeof(this.VideoTeaching.title) == "undefined") {
					uni.showToast({
						title: "标题不能为空",
						icon: 'none',
					});
					return;
				}

				if(!this.VideoTeaching.pic||typeof(this.VideoTeaching.pic) == "undefined") {
					uni.showToast({
						title: "封面不能为空",
						icon: 'none',
					});
					return;
				}
				if(!this.VideoTeaching.url||typeof(this.VideoTeaching.url) == "undefined") {
					uni.showToast({
						title: "视频不能为空",
						icon: 'none',
					});
					return;
				}
				if(this.VideoTeaching.isCharge) {
					if(!this.VideoTeaching.price||typeof(this.VideoTeaching.price) == "undefined") {
						uni.showToast({
							title: "价格不能为空",
							icon: 'none',
						});
						return;
					}
				}
				if(this.VideoTeaching.price==0) {
					uni.showToast({
						title: "价格需大于0",
						icon: 'none',
					});
					return;
				}
				
				var params = {
				  url: "/VideoTeaching/create",
				  method: "POST",
				  needToken: true,
				  data: this.VideoTeaching,
				  callBack: function (res) {
				    uni.hideLoading();
				    uni.showToast({
				    	title: "发布成功",
				    	icon: 'none',
				    });
					setTimeout(() => {
						uni.navigateBack({
							delta: 2
						});
					},1000)
				  }
				};
				http.request(params);
			},
			switch1Change: function (e) {
				this.setData({
				  'VideoTeaching.isCharge': e.target.value
				});
			},
	    }
	}
</script>

<style>
@import "./addVideo.css";
	 .picker-view {
			width: 750rpx;
			height: 600rpx;
			margin-top: 20rpx;
	 }
	.item {
			height: 50px;
			align-items: center;
			justify-content: center;
			text-align: center;
	}
    #editor {
        width: 100%;
        height: 300px;
        background-color: #CCCCCC;
    }
</style>
