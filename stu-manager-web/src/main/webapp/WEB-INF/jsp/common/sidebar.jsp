<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <!-- User -->
        <div class="user-box am-hide-sm-only">
            <div class="user-img">
                <img src="/assets/img/avatar-1.jpg" alt="user-img" title="Mat Helme" class="img-circle img-thumbnail img-responsive">
                <div class="user-status offline"><i class="am-icon-dot-circle-o" aria-hidden="true"></i></div>
            </div>
            <h5><a href="#" id="username_a">Mat Helme</a> </h5>
            <h5><a href="javascript:void(0);" onclick="logout()" id="logout_a" style="color: hotpink">退出登陆</a> </h5>
            <ul class="list-inline">
                <li>
                    <a href="#">
                        <i class="fa fa-cog" aria-hidden="true"></i>
                    </a>
                </li>

                <li>
                    <a href="#" class="text-custom">
                        <i class="fa fa-cog" aria-hidden="true"></i>
                    </a>
                </li>
            </ul>
        </div>
        <!-- End User -->

        <ul class="am-list admin-sidebar-list">
            <li><a href="/index"><span class="am-icon-home"></span> 首页</a></li>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-table"></span> 学生管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                    <li><a href="/stuBaseMsgPage" class="am-cf"> <span>学生列表</span></a></li>
                    <li><a href="">查询学生</a></li>
                </ul>
            </li>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><i class="am-icon-line-chart" aria-hidden="true"></i> 课程管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav2">
                    <li><a href="/coursePage" class="am-cf"> <span>课程列表</span></a></li>
                    <li><a href="" class="am-cf"> <span>查询课程</span></a></li>
                    <li><a href="" class="am-cf"> <span>考试情况</span></a></li>
                </ul>
            </li>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav5'}"><span class="am-icon-file"></span> 院系管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav5">
                    <li><a  class="am-cf"> 院系列表</a></li>
                    <li><a href="">查询院系</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

