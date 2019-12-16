
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019-11-03
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统</title>
    <jsp:include page="/WEB-INF/jsp/common/csslink.jsp"></jsp:include>
</head>
<body>
<!-- Begin page -->
<header class="am-topbar am-topbar-fixed-top">
    <div class="am-topbar-left am-hide-sm-only">
        <a href="demo.html" class="logo"><span>Admin<span>to</span></span><i class="zmdi zmdi-layers"></i></a>
    </div>

    <div class="contain">
        <ul class="am-nav am-navbar-nav am-navbar-left">

            <li><h4 class="page-title">首页</h4></li>
        </ul>

        <ul class="am-nav am-navbar-nav am-navbar-right">
            <li class="inform"><i class="am-icon-bell-o" aria-hidden="true"></i></li>
            <li class="hidden-xs am-hide-sm-only">
                <form role="search" class="app-search">
                    <input type="text" placeholder="Search..." class="form-control">
                    <a href=""><img src="/assets/img/search.png"></a>
                </form>
            </li>
        </ul>
    </div>
</header>
<!-- end page -->


<div class="admin">
    <jsp:include page="/WEB-INF/jsp/common/sidebar.jsp"></jsp:include>
    <div class="content-page">
        <div class="layui-carousel" id="test1" style="margin: auto">
            <div carousel-item>
                <div><img src="/images/image/index/c493616f-6f9e-423f-bfdd-fd4f0c115901%5B1%5D.jpg"></div>
                <div><img src="/images/image/index/a148a164-9ed4-41e9-90a5-1f42fbb67bb4%5B1%5D.png"></div>
            </div>
        </div>
        <!--        <div id="footer">-->
        <!--            <div style="margin-left: auto;margin-right: auto"><img src="../image/index/icon.png" /></div>-->
        <!--        </div>-->
    </div>
</div>
<!-- end right Content here -->
<!--</div>-->

<!-- navbar -->
<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
<jsp:include page="/WEB-INF/jsp/common/jsscript.jsp"></jsp:include>

<script>
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '80%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            ,height: '450px'
            //,anim: 'updown' //切换动画方式
        });
    });
</script>
</body>

</html>
