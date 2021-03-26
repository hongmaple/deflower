<template>
	<view class="order-detail-footer">
	  <input v-model="danmuValue" class="uni-input" type="text" placeholder="输入内容" />
	  <view class="footer-box">
	     <text class="buy-again" @tap="sendMessage()">发送消息</text>
	  </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				socket: {},
				messagesList: [],
				sendMessages: {
					toUserId: 2
				},
				danmuValue: ''
			}
		},
		onLoad: function (options) {
			var user = JSON.parse(uni.getStorageSync('token'));
			this.openSocket(user.id);
		},
		methods: {
			    openSocket: function(id) {
			       if(typeof(WebSocket) == "undefined") {
			           console.log("您的浏览器不支持WebSocket");
			       }else{
			           console.log("您的浏览器支持WebSocket");
			           //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
			           //等同于socket = new WebSocket("ws://localhost:8888/xxxx/im/25");
			           //var socketUrl="${request.contextPath}/im/"+$("#userId").val();
			           var socketUrl="http://localhost:9001/ws/"+id;
			           socketUrl=socketUrl.replace("https","ws").replace("http","ws");
			           console.log(socketUrl);
			           this.socket = new WebSocket(socketUrl);
			           //打开事件
			           this.socket.onopen = function() {
			               console.log("websocket已打开");
			               //socket.send("这是来自客户端的消息" + location.href + new Date());
			           };
			           //获得消息事件
			           this.socket.onmessage = function(msg) {
			               //发现消息进入    开始处理前端触发逻辑
						   console.log(msg.data);
						   var message = JSON.parse(msg.data);
						   var messagesList = this.messagesList;
						   console.log(messagesList);
						   messagesList.push(message);
						   this.setData({
						     messagesList: messagesList
						   });
						   console.log(this.messagesList);
			           };
			           //关闭事件
			           this.socket.onclose = function() {
			               console.log("websocket已关闭");
			           };
			           //发生了错误事件
			           this.socket.onerror = function() {
			               console.log("websocket发生了错误");
			           }
			       }
			   },
			   sendMessage: function() {
			       if(typeof(WebSocket) == "undefined") {
			           console.log("您的浏览器不支持WebSocket");
			       }else {
			           console.log("您的浏览器支持WebSocket");
					   var sendMessages = this.sendMessages;
					   sendMessages.contentText=this.danmuValue;
					   console.log(JSON.stringify(sendMessages));
			           this.socket.send(JSON.stringify(sendMessages));
			       }
			   }
		}
	}
</script>

<style>
/* 底部栏 */
.order-detail-footer {
  position: fixed;
  bottom: 0;
  width: 100%;
  max-width: 750rpx;
  background: #fff;
  margin: auto;
  display: -webkit-flex;
  display: -webkit-box;
  display: -moz-box;
  display: -ms-flexbox;
  display: flex;
  padding: 22rpx 0;
  font-size: 26rpx;
  box-shadow: 0 -1px 3px rgba(0, 0, 0, 0.05);
}
.uni-input{
	float: left;
	width: 540rpx;
	height: 60rpx;
	font-size: 28rpx;
    border-radius: 50rpx;
	border: 2rpx solid #777;
	margin-left: 15rpx;
}
.buy-again {
  font-size: 26rpx;
  color: #fff;
  background: #eb2444;
  border-radius: 50rpx;
  padding: 10rpx 20rpx;
  margin-right: 20rpx;
  float: right;
  margin-left: 20rpx;
  margin-top: 3px;
}
</style>
