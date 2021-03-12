//第三方插件layer的函数方法封装
document.write('<script type="text/javascript" src="/layer/2.4/layer.js"></script>');
//这样写的好处，不必所有页面都引入第三方插件，避免报错，有利于对插件的样式统一

	//layer弹出层的使用方法
	//title标题，url,资源路径，w,宽，h,高
	function Popup(title,url,w,h){
	   layer.open({
         type: 2, 
         title: title,
         content: url,
         shade:[0.35, '#ffffff'],
         maxmin:true,
         area: screen() < 2 ? ['100%', '100%'] : [w, h], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
         skin: 'layui-layer-lan',
         resize:true
       });
      /*  var index = parent.layer.getFrameIndex(url); //先得到当前iframe层的索引
       layer.iframeAuto(index);   */  
	}
	    //判断浏览器大小方法
    function screen() {
        //获取当前窗口的宽度
	    var width = $(window).width();
	    if (width > 1200) {
	        return 3;   //大屏幕
	    } else if (width > 992) {
	        return 2;   //中屏幕
	    } else if (width > 620) {
	        return 1;   //小屏幕
	    } else{
	        return 0;   //超小屏幕
	    }
    }
    function Popup2(type,title,url,w,h){
 	   layer.open({
          type: type, 
          title: title,
          content: url,
          shade:0.4,
          maxmin:true,
          area: screen() < 2 ? ['100%', '100%'] : [w, h], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
          extend: 'myskin/fgdf.css', //加载您的扩展样式
          skin: 'layui-layer-lan'
        });
       /*  var index = parent.layer.getFrameIndex(url); //先得到当前iframe层的索引
        layer.iframeAuto(index);   */  
 	}