<%--
  Created by IntelliJ IDEA.
  User: chenmingtao
  Date: 2019/12/5
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>

    <title>吉林大学珠海学院教学综合信息服务平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="Copyright" content="zfsoft" />
    <link rel="icon" href="/logo/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="/logo/favicon.ico" type="image/x-icon" />
    <script type="text/javascript">
        var _path 				= "";
        var _systemPath 		= "";
        var _stylePath 			= "http://jw.jluzh.com/zftal-ui-v5-1.0.2";
    </script>
    <!--jQuery核心框架库 -->
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/js/other_jquery/jquery.min.js?ver=20170713"></script>
    <!--jQuery常用工具扩展库：基础工具,资源加载工具,元素尺寸相关工具 -->
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/js/zftal/jquery.utils.contact-min.js?ver=20170713" charset="utf-8"></script>
    <!--jQuery浏览器检测 -->
    <script type="text/javascript" src="/js/browse/browse-judge.js"></script>
    <script type="text/javascript">
        //\u6d4f\u89c8\u5668\u7248\u672c\u9a8c\u8bc1
        /*var broswer = broswer();
        if(broswer.msie==true||broswer.safari==true||broswer.mozilla==true||broswer.chrome==true){
            if(broswer.msie==true&&broswer.version<9){
               window.location.href = _path+"/xtgl/init_cxBrowser.html";
            }
        }else{
             window.location.href = _path+"/xtgl/init_cxBrowser.html";
        }*/
    </script>
    <!--Bootstrap布局框架-->
    <link rel="stylesheet" type="text/css" href="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/bootstrap/css/bootstrap.min.css?ver=20170713" />
    <link rel="stylesheet" type="text/css" href="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/css/zftal-ui.css?ver=20170713" />
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/bootstrap/js/bootstrap.min.js?ver=20170713" charset="utf-8"></script>
    <!--jQuery.chosen美化插件-->
    <link rel="stylesheet" type="text/css" href="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/chosen/css/chosen-min.css?ver=20170713" />
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/chosen/jquery.choosen.concat-min.js?ver=20170713" charset="utf-8"></script>
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/chosen/lang/zh_CN-min.js?ver=20170713" charset="utf-8"></script>
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/js/utils/jquery.utils.pinyin.min.js?ver=20170713" charset="utf-8"></script>
    <!--业务框架jQuery全局设置和通用函数库-->
    <script type="text/javascript" src="/js/jquery.zftal.contact-min.js?ver=20170713"></script>
    <!--业务框架前端脚本国际化库-->
    <script type="text/javascript" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/js/zftal/lang/jquery.zftal_zh_CN-min.js?ver=20170713"></script>
    <!--[if lt IE 9]>
    <script src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/js/html5shiv.min.js?ver=20170713" type="text/javascript" charset="utf-8"></script>
    <script src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/js/respond.min.js?ver=20170713" type="text/javascript" charset="utf-8"></script>
    <![endif]-->

    <meta http-equiv="Content-Security-Policy" content="script-src 'self' http://jw.jluzh.com 'unsafe-inline'">

    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <style type="text/css">
        .btn-lang{
            width: 76px;
            border-color: #357ebd;
            font-weight: bold;
            height: 30px;
        }
        .btn-lang-enabled {
            background-color: #428bca;
            color:#fff;
        }
        .btn-lang-disabled {
            background-color: #fff;
            color: #333;
        }
        .btn-lang-enabled:hover,.btn-lang-enabled:focus {
            color:#fff;
        }
        .btn-group>.btn-lang:last-child{
            border-top-right-radius: 3px;
            border-bottom-right-radius: 3px;
        }

        .slideShow{
            position: relative;
            overflow: hidden;
        }
        .slideShow ul{
            list-style: none;
            margin: 0px;
            padding: 0px;
            width: 1000px;
            position: relative;
        }
        .slideShow ul li{
            margin: 0px;
            padding: 0px;
            float: left;
        }
        .slideShow .showNav{
            position: absolute;
            right: 10px;
            bottom: 5px;
            text-align:center;
            font-size: 12px;
            line-height: 20px;
        }
        .slideShow .showNav span{
            cursor: pointer;
            display: block;
            float: left;
            width: 20px;
            height: 20px;
            background: #ff5a28;
            margin-left: 2px;
            color: #fff;
        }
        .slideShow .showNav .active{
            background: #b63e1a;
        }
    </style>
</head>
<body style="background:#fafafa;">
<input type = "hidden" name = "yzcskz" id = "yzcskz" value = 3>
<input type = "hidden" name = "mmsfjm" id = "mmsfjm" value = 0>
<input type = "hidden" name = "mmsrddcshkzfs" id = "mmsrddcshkzfs" value = 0>
<input type = "hidden" name = "dlsbsdsj" id = "dlsbsdsj" value = 3>

<div class="container container_1170">
    <div class="row sl_log_top">
        <div class="col-sm-8 logo_1"><img src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/images/logo/logo_jw_d.png" style="margin-top:-3px" />
            <span id="xtmc">吉林大学珠海学院教学综合信息服务平台</span>
        </div>
        <div class="col-sm-4 text-right hidden-xs">


        </div>
    </div>
    <div class="row sl_log_bor4">

        <div class="col-sm-8 hidden-xs sl_log_lf slideShow">
            <!-- 0不轮换图片-->
            <img class="img-responsive" src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/images/login_bg_pic.jpg" />



        </div>
        <div class="col-sm-4 sl_log_rt">
            <form class="form-horizontal" role="form" method="post" id="login_form">
                <input type="hidden" id="csrftoken" name="csrftoken" value="8ec5487c-f416-42dc-b61c-fca176aa6935,8ec5487cf41642dcb61cfca176aa6935"/>
                <input type="hidden" name="redirect" value="${redirect}">
                <h5>用户登录</h5>
                <!-- 防止浏览器自动填充密码 -->
                <input type="text" style="display: none;" autocomplete="off"/>
                <input type="password" style="display: none;" autocomplete="off"/>
                <!-- 防止浏览器自动填充密码 end -->
                <p style="display: none;" id="tips" class="bg_danger sl_danger">
                </p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><img src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/images/log_ic01.png" width="16" height="16" /></div>
                        <input type="text" class="form-control" name="name" id="yhm" value="" placeholder="用户名" onblur="" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><img src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/images/log_ic02.png" width="16" height="16" /></div>
                        <input type="password" name="mm" id="hidMm" style="display:none" >
                        <input type="text" class="form-control" name="password" id="mm" value="" placeholder="密码" autocomplete="new-password"  onfocus="this.type='password'">
                        <input type="password" style="display:none;">
                    </div>
                </div>

                <!-- 0-弹出验证码-->



                <div class="form-group">
                    <!--<a href="/xtgl/init_fkIndex.html" class="checkbox pull-left" target="_blank">访客登录</a> -->
                    <!-- <a href="#" class="checkbox pull-right" target="_blank">忘记密码了？</a> -->
                </div>
                <div class="form-group" id = "myDiv">
                    <!--<a href="/xtgl/init_fkIndex.html" class="checkbox pull-left" target="_blank">访客登录</a> -->

                    <a href="/pwdmgr/retake/index.zf" id="wjmm" class="checkbox pull-right " target="_blank">忘记密码了？</a>

                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary btn-block" id="dl">登 录</button>
                </div>





                <!-- 登录口提示信息 -->

                <div class="form-group">
                    <div class="alert alert-danger" role="alert" id="dlktsxx">
                    </div>
                </div>


            </form>
            <div class="sl_log_ewm hidden-xs"><img src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/images/login_ewm.gif" width="90" height="90" />&nbsp;&nbsp;<span>用手机扫一扫,<br />安全、便捷登录</span></div>
        </div>
    </div>
</div>


<!-- footer -->
<div id="footerID" class="footer">
    <p>版权所有&copy; Copyright 1999-2017  正方软件股份有限公司　　中国·杭州西湖区紫霞街176号 互联网创新创业园2号301</p>
</div>
<script type="text/javascript">
    //系统中页面底部的时间随系统时间来定
    var date=new Date;
    var year=date.getFullYear();
    var s = $("#footerID").text().indexOf('-');
    var textNr =$("#footerID").text().substr(0,s+1)+year+$("#footerID").text().substr(s+5);
    $("#footerID").text("");
    $("#footerID").text(textNr);
</script>
<!-- footer-end -->
</body>
<script type="text/javascript">

    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
        if(r!=null)return  unescape(r[2]); return null;
    }

    var error_message=GetQueryString("errorCode");
    if(error_message !=null && error_message.toString().length>1)
    {
        if(error_message.toString() == "12138"){
            let text = '<span class="glyphicon glyphicon-minus-sign"></span>';
            $("#tips").empty().append(text + "请登录");
            $("#tips").show();
        }
    }

    var dlktsxx="1.学生账号为学号，教职工账号为职工号，初始密码均为证件号码后六位。2.请登陆先维护个人邮箱，方便个人密码找回。3.如密码丢失且未设置找回邮箱，请联系所在学院教学秘书";
    var arr=[];
    var html="";
    if(dlktsxx.indexOf("。")>-1){
        arr=dlktsxx.split("。");
        for(var s=0;s<arr.length;s++){
            if(arr[s]!=''){
                html+=arr[s]+"<br>";
            }
        }
    }else{
        html=dlktsxx;
    }
    $("#dlktsxx").html(html);


    //ip


    jQuery(function($){

        function changeLanguage(lang) {
            $.ajaxSetup({async : false});
            jQuery.post(_path + "/xtgl/init_changeLocal.html", {//config-shiro.xml里要加这个
                language : lang != "en_US" ? "zh_CN" : "en_US"
            }, function(b) {
                if (1 == parseInt(b)) {
                    if ($("#topButton").size() > 0) {
                        $("#topButton").click();
                    } else {
                        window.location.reload();
                    }
                }
            }, "json");
            $.ajaxSetup({async : true});
        }
        $("button.btn-lang").click(function() {
            if (!$(this).hasClass("btn-lang-enabled")) {
                changeLanguage($(this).attr("value"));
            }
        })

        //切换英文
        if($(".btn-lang-enabled").val()=="en_US"){
            $("#xtmc").text("");
            $("title").text("")
            $("#wpjssq").text("New Teacher Account Application");
            $("#wjmm").text("Forgot your password?");
            $("#jzfw").text("Parents visit");
            $("#dlktsxx").hide();
        }

    });

</script>
<script type='text/javascript' src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/crypto/rsa/jsbn.js?ver=20170713"></script>
<script type='text/javascript' src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/crypto/rsa/prng4.js?ver=20170713"></script>
<script type='text/javascript' src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/crypto/rsa/rng.js?ver=20170713"></script>
<script type='text/javascript' src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/crypto/rsa/rsa.js?ver=20170713"></script>
<script type='text/javascript' src="http://jw.jluzh.com/zftal-ui-v5-1.0.2/assets/plugins/crypto/rsa/base64.js?ver=20170713"></script>
<!--<script type="text/javascript" src="/js/login/login.js"></script>-->
<script type="text/javascript" src="/js/login/login.js"></script>
</html>
