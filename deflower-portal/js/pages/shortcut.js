const shortcut = {
    template: "\
    <div class='py-container'> \
        <div class='shortcut'> \
            <ul class='fl'> \
               <li class='f-item'>青青欢迎您！</li> \
               <li class='f-item' v-if='user && user.username'>\
               尊敬的会员，<span style='color: red;'>{{user.username}}</span>\
               </li>\
               <li v-else class='f-item'> \
                   请<a href='javascript:void(0)' @click='gotoLogin'>登录</a>　 \
                   <span><a href='register.html' target='_blank'>免费注册</a></span> \
               </li> \
           </ul> \
           <ul class='fr'> \
               <li class='f-item'><a href='home.html' target='_blank'>个人中心</a></li> \
               <li class='f-item space'></li> \
               <li class='f-item' id='service'> \
                   <span @click='tanchuan'>客户服务</span> \
               </li> \
           </ul> \
       </div> \
    </div>\
    ",
    name: "shortcut",
    data() {
        return {
            user: null
        }
    },
    created() {
        ly.http("/auth/verify")
            .then(resp => {
                this.user = resp.data;
            })
    },
    methods: {
        gotoLogin() {
            window.location = "login.html?returnUrl=" + window.location;
        },
        tanchuan() {
            layer.alert('提示', {
                 skin: 'layui-layer-lan'
                ,content: "客服电话：1812373778383"
                ,closeBtn: 0
                ,anim: 4 //动画类型
            });
        }
    }
}
export default shortcut;