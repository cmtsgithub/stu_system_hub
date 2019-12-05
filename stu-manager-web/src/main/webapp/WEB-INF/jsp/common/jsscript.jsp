<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019-11-04
  Time: 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/assets/js/jquery-2.1.0.js" ></script>
<script type="text/javascript" src="/assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="/assets/js/app.js" ></script>
<script type="text/javascript" src="/assets/js/blockUI.js" ></script>
<script type="text/javascript" src="/assets/js/charts/echarts.min.js" ></script>
<script type="text/javascript" src="/assets/js/charts/indexChart.js" ></script>
<script type="text/javascript" src="/layui.js"></script>

<%--获取登陆信息--%>
<script>
    let _ticket = getCookie("STU_TOKEN");
    if(_ticket !== null || _ticket !== undefined || _ticket !== ''){
        $.ajax({
            url : "http://localhost:8086/user/token/" + _ticket,
            dataType : "jsonp",
            async: true,
            type : "GET",
            jsonp: "callback",
            success : function(data){
                if(data.status == 200){
                    let username = data.data.name;
                    $("#username_a").text(username);
                }
            }
        });
    }else{
        //如果没有token， 则没有登陆，则跳转到登陆页面
        window.location.href = "http://localhost:8086/user/login/page";
    }
    //读取cookies
    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
    //用户登出
    function logout() {
        let loginUrl = "http://localhost:8086/user/login/page";
        let _ticket = getCookie("STU_TOKEN");
        if(!_ticket){
            //如果没有token， 则没有登陆，则跳转到登陆页面
            window.location.href = loginUrl;
        }else{
            $.ajax({
                url : "http://localhost:8086/user/logout/" + _ticket,
                dataType : "jsonp",
                async: true,
                type : "GET",
                jsonp: "callback",
                success : function(data){
                    if(data.status == 200){
                        layer.alert(data.msg, {
                            title: '已安全退出',
                            yes: function(index, layero){
                                window.location.href = loginUrl;
                            },
                            cancel: function(index, layero){
                                window.location.href = loginUrl;
                            }
                        });
                    }else {
                        layer.alert(data.msg, {
                            title: '登出失败',
                            yes: function(index, layero){
                                window.location.href = loginUrl;
                            },
                            cancel: function(index, layero){
                                window.location.href = loginUrl;
                            }

                        });
                    }
                }
            });
        }
    }
</script>

