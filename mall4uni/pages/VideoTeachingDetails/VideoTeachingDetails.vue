<template>
    <view>
        <view class="uni-padding-wrap uni-common-mt">
            <view>
                <video style="width: 100%; " id="myVideo" :src="serverUrl + item.url"
                    @error="videoErrorCallback" :danmu-list="danmuList" enable-danmu danmu-btn controls></video>
            </view>
			<view class="prod-text-right">
			  <view class="prod-text more">{{item.title}}</view>
			  <view class="col" @tap="addOrCannelCollection">
			    <image v-if="!isCollection" src="/static/images/icon/prod-col.png"></image>
			    <image v-if="isCollection" src="/static/images/icon/prod-col-red.png"></image>
			    收藏
			  </view>
			  <view class="prod-price more" v-if="item.isCharge">
			    <text class="symbol">￥</text> <text class="big-num">{{wxs.parsePrice(item.price)[0]}}</text><text class="small-num">.{{wxs.parsePrice(item.price)[1]}}</text> 
			  </view>
			  <view class="prod-price more" v-else>
			    <text class="symbol">￥</text> <text class="big-num">免费</text> 
			  </view>
			</view>
			<!-- #ifndef MP-ALIPAY -->
			<view class="uni-list">
			    <view class="uni-list-cell">
			        <view class="uni-list-cell-db">
			            <input v-model="danmuValue" class="uni-input" type="text" placeholder="  在此处输入弹幕内容" />
						<view class="footer-box" @tap="sendDanmu">
						  发送弹幕
						</view>
			        </view>
			    </view>
			</view>
            <!-- <view class="uni-btn-v">
                <button @click="sendDanmu" class="page-body-button">发送弹幕</button>
            </view> -->
            <!-- #endif -->
        </view>
    </view>
</template>
<script module="wxs" lang="wxs" src="../../wxs/number.wxs"></script>
<script>
	var config = require("../../utils/config.js");
    var http = require("../../utils/http.js");
	export default {
		 data() {
		        return {
		            src: '',
                    danmuList: [],
		    //         danmuList: [{
		    //                 text: '找工作就上银领人才网',
		    //                 color: '#ff0000',
		    //                 time: 1
		    //             },
		    //             {
		    //                 text: 'http://www.yinlinkrc.com/',
		    //                 color: '#ff00ff',
		    //                 time: 2
		    //             },
						// {
						//     text: '想学编程加qq2496155694',
						//     color: '#ff00ff',
						//     time: 3
						// },
						// {
						//     text: '鸿枫真优秀',
						//     color: '#ff00ff',
						//     time: 4
						// }
		    //         ],
		            danmuValue: '',
					item: {
						
					},
					serverUrl: config.domain,
                    id: 0,
					isCollection: false
		        }
		    },
		    onReady: function(res) {
		        // #ifndef MP-ALIPAY
		        this.videoContext = uni.createVideoContext('myVideo')
		        // #endif
		    },
			onLoad: function (options) {
                console.log(options.id)
				this.setData({
				  id: options.id
                });
				var params = {
				  url: "/VideoTeaching/"+options.id,
				  method: "GET",
				  callBack: res => {
				    this.setData({
				      item: res.data
				    });
				  }
				};
				http.request(params);
				this.getCollection();
			},
		    methods: {
		        sendDanmu: function() {
		            this.videoContext.sendDanmu({
		                text: this.danmuValue,
		                color: this.getRandomColor()
		            });
		            this.danmuValue = '';
		        },
		        videoErrorCallback: function(e) {
		            // uni.showModal({
		            //     content: "视频不存在",
		            //     showCancel: false
		            // })
		        },
		        getRandomColor: function() {
		            const rgb = []
		            for (let i = 0; i < 3; ++i) {
		                let color = Math.floor(Math.random() * 256).toString(16)
		                color = color.length == 1 ? '0' + color : color
		                rgb.push(color)
		            }
		            return '#' + rgb.join('')
		        },
				addOrCannelCollection() {
				  uni.showLoading();
				  var favoriteId =this.id;
				  var params;
				  if(this.isCollection) {
				     params = {
				        url: `/flower/favorite/${favoriteId}`,
				      	needToken: true,
				        method: "delete",
				        callBack: res => {
				          this.setData({
				            isCollection: !this.isCollection
				          });
				          uni.hideLoading();
				        }
				       };
				  }else {
				      params = {
				        url: "/flower/favorite",
				      	needToken: true,
				        method: "POST",
				        data: {
				         id: 0,
				         createTime: null,
				         favoriteId: favoriteId,
						 type: 1
				        },
				        callBack: res => {
				          this.setData({
				            isCollection: !this.isCollection
				          });
				          uni.hideLoading();
				        }
				      };
				  }
				  http.request(params);
				},
				getCollection() {
					var params = {
					  url: `/flower/favorite/video/${this.id}`,
					  method: "GET",
					  needToken: true,
					  callBack: res => {
						  if(res) {
							  this.setData({
							    isCollection: res.data,
							  }); 
						  }
					    uni.hideLoading();
					  }
					};
					http.request(params);
				},
		    }
	}
</script>

<style>
.prod-text-right {
  padding: 30rpx 30rpx 0 30rpx;
  position: relative;
  background: #fff;
}

.prod-text-right .cate-prod-info {
  font-size: 22rpx;
  color: #999;
  margin: 10rpx 0 20rpx 0;
}

.prod-text-right .prod-text.more {
  margin: 0;
  font-size: 28rpx;
  display: -webkit-box;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  color: #000;
}

.prod-text-right .prod-price.more {
  padding-top: 10rpx;
  font-size: 28rpx;
  color: #eb2444;
  font-family: arial;
}
.uni-input{
    margin-left:10rpx;
	float: left;
	width: 500rpx;
    height:50rpx;
	font-size: 28rpx;
    border-radius: 50rpx;
	border: 2rpx solid #777;
	padding-left: 15rpx;
}
.footer-box {
  margin-right:10rpx;
  font-size: 22rpx;
  padding-left: 5rpx;
  width: 150rpx;
  height:30px;
  background: #eb2444;
  text-align: center;
  line-height: 50rpx;
  color: #fff;
  float: right;
  border-radius: 30rpx;
}
.search-bar {
  width: 100%;
  position: fixed;
  top: 0;
  left: 0;
  color: #777;
  background: #fff;
  box-shadow: 0 2rpx 6rpx rgba(0, 0, 0, 0.07);
  z-index: 3;
  padding: 20rpx 0;
}

.search-bar .arrow {
  width: 20rpx;
  height: 20rpx;
  border-bottom: 2rpx solid #777;
  border-left: 2rpx solid #777;
  transform: rotate(45deg);
  position: absolute;
  left: 30rpx;
  top: 41rpx;
}

.search-bar .search-box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60rpx;
  background: #f7f7f7;
  z-index: 999;
  width: 92%;
  border-radius: 50rpx;
  text-align: center;
  margin: auto;
}

.sear-input {
  font-size: 28rpx;
}

.search-bar .search-hint {
  font-size: 28rpx;
  position: absolute;
  right: 30rpx;
  top: 32rpx;
}
.uni-list {
	padding-top: 30rpx;
}
.prod-text-right .col {
  position: absolute;
  top: 30rpx;
  right: 0;
  width: 80rpx;
  color: #666;
  font-size: 20rpx;
  padding-left: 20rpx;
  text-align: center;
}

.prod-text-right .col image {
  display: block;
  margin: auto;
  width: 40rpx;
  height: 40rpx;
}

.prod-text-right .col::after {
  content: "";
  display: block;
  width: 1px;
  height: auto;
  background: #f1f1f1;
  position: absolute;
  top: 0;
  bottom: 5px;
  left: 0;
}
</style>
