<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            <li><h4 class="page-title">课程管理</h4></li>
        </ul>

        <ul class="am-nav am-navbar-nav am-navbar-right">
            <li class="inform"><i class="am-icon-bell-o" aria-hidden="true"></i></li>
            <li class="hidden-xs am-hide-sm-only">
                <form role="search" class="app-search">
                    <input type="text" placeholder="Search..." class="form-control">
                    <a href=""><img src="../../../../../assets/img/search.png"></a>
                </form>
            </li>
        </ul>
    </div>
</header>
<!-- end page -->


<div class="admin">
    <jsp:include page="/WEB-INF/jsp/common/sidebar.jsp"></jsp:include>
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <div class="card-box">
                <!-- Row start -->
                <div class="am-g">
                    <div class="am-u-sm-12 am-u-md-6">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button id="add" type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
                                <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
                                <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
                                <button type="button" id="del" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
                            </div>
                        </div>
                    </div>

                    <div class="am-u-sm-12 am-u-md-3">
                        <div class="am-input-group am-input-group-sm">
                            <input type="text" class="am-form-field">
                            <span class="am-input-group-btn">
				            <button class="am-btn am-btn-default" type="button">搜索</button>
				          </span>
                        </div>
                    </div>
                </div>
                <!-- Row end -->

                <!-- Row start -->
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-check"><input type="checkbox" /></th>
                                    <th class="table-id">编号</th>
                                    <th class="table-hover">课程名称</th>
                                    <th class="table-type">类别</th>
                                    <th class="table-type">二级学院</th>
                                    <th class="table-type">专业</th>
                                    <th class="table-type">最大容纳人数</th>
                                    <th class="table-type">已选人数</th>
                                    <th class="table-type">教师姓名</th>
                                    <th class="table-date am-hide-sm-only">上课日期</th>
                                    <th class="table-date am-hide-sm-only">创立日期</th>
                                    <th class="table-date am-hide-sm-only">更新日期</th>
                                    <th class="table-set">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><input type="checkbox" /></td>
                                    <td>1</td>
                                    <td><a href="#">Business management</a></td>
                                    <td>default</td>
                                    <td>default</td>
                                    <td>default</td>
                                    <td>default</td>
                                    <td>default</td>
                                    <td>default</td>
                                    <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
                                    <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
                                    <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary" type="button" id="edit"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                                                <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"><span class="am-icon-copy"></span> 复制</button>
                                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div id="page" ></div>
                            <hr />
                            <p>注：.....</p>
                        </form>
                    </div>

                </div>
                <!-- Row end -->

            </div>
        </div>
    </div>
</div>
<!-- end right Content here -->
<!--</div>-->

<!-- navbar -->
<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
<jsp:include page="/WEB-INF/jsp/common/jsscript.jsp"></jsp:include>

</body>
<%--表单--%>
<form class="layui-form layui-form-pane" action="" id="add_form" style="margin-left: 20px">
    <div class="layui-form-item" style="margin-top: 10px">
        <div class="layui-inline">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="course_name" autocomplete="off" placeholder="请输入增加的课程" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">课程类别</label>
            <div class="layui-input-block">
                <input type="radio" name="categoryId" value="1" title="必修" checked="" >
                <input type="radio" name="categoryId" value="2" title="专业选修">
                <input type="radio" name="categoryId" value="3" title="院公选">
                <input type="radio" name="categoryId" value="4" title="特殊课程">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">院系</label>
            <div class="layui-input-inline">
                <select name="academyId" lay-filter="academy" id="academy_select">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">专业</label>
            <div class="layui-input-inline">
                <select name="majorId" lay-filter="major" id="major_select">
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 150px">最大容纳人数</label>
                <div class="layui-input-inline">
                    <input type="text" name="maxNumber" lay-verify="required" autocomplete="off" placeholder="请输入可容纳人数" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">已选人数</label>
                <label class="layui-form-label">0人</label>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-inline">
                <label class="layui-form-label">授课教师</label>
                <div class="layui-input-inline">
                    <input type="text" name="teacherName" lay-verify="teacher" autocomplete="off" placeholder="请输入该门课程的教师" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">上课时间</label>
            <div class="layui-input-inline">
                <input type="text" name="time" lay-verify="course_time" autocomplete="off" placeholder="请输入上课时间" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<%--分页脚本--%>
<script>
    //时间格式化函数，此处仅针对yyyy-MM-dd hh:mm:ss 的格式进行格式化
    var dateFormat = function(time) {
        var date=new Date(time);
        var year=date.getFullYear();
        /* 在日期格式中，月份是从0开始的，因此要加0
         * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
         * */
        var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
        var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
        var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
        var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
        var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
        // 拼接
        return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    }

    layui.use('laypage', function(){
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page'
            ,count: ${pageInfo.total}
            ,limit: 8
            ,layout: ['count', 'first', 'prev', 'page', 'next', 'last', 'limit', 'refresh']
            ,first:'首页'
            ,last:'尾页'
            ,jump: function(obj, first){
                //首次不执行
                if(!first){
                    renderTable(obj.curr, obj.limit);
                }
            }
        });
    });

    // 表格渲染函数
    function renderTable(pageNum, pageSize) {
        $.ajax({
            type: "get",
            async: true,
            contentType: 'application/json',
            url: "/getStuBaseMsg",
            data:{
                "pageNum":pageNum, "pageSize":pageSize
            },
            dataType:"json",
            success: function(data){
                //清空表格数据
                $('tbody').empty();
                //遍历json
                var stuList = data.list;
                $.each( stuList, function(index, stu)
                {
                    $("#stu_table").append(
                        "<tr><td><input type='checkbox' /></td><td >" + stu.id + "</td><td >" + stu.name + "</td><td >" + stu.sex + "</td><td >" + dateFormat(stu.updated) + "</td><td>"
                        + "<div class='am-btn-toolbar'>\n" +
                        "    <div class='am-btn-group am-btn-group-xs'>\n" +
                        "\t<button class='am-btn am-btn-default am-btn-xs am-text-secondary edit' type='button' onclick='updateStu(this)'><span class='am-icon-pencil-square-o'></span> 编辑</button>\n" +
                        "\t<button class='am-btn am-btn-default am-btn-xs am-hide-sm-only' type='button'><span class='am-icon-copy'></span> 复制</button>\n" +
                        "\t<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only' type='button' onclick='deleteStu(this)'><span class='am-icon-trash-o'></span> 删除</button>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "</td>\n" +
                        "</tr>"
                    );
                });
                renderForm();
            },
            error: function(){
                alert('分页数据请求失败');
            }
        });
    }
</script>
<%--学生删除，编辑脚本--%>
<script>
    //删除学生脚本
    function deleteStu(e) {
        var id = $(e).parent().parent().parent().parent().children()[1].innerHTML;
        var children = $(e).parent().parent().parent().parent().children();
        //配置一个透明的询问框
        layer.msg('确定要删除吗？请三思哦！', {
            time: 20000, //20s后自动关闭
            btn: ['确认删除', '我去三思', '不删了'],
            yes:function () {
                $.ajax({
                    url: '/stuStatus/' + id + "/" + 3,
                    async : true,
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        if(data.status == 200){
                            layer.msg('删除学号为:' + id + '成功', {
                                time: 20000, //20s后自动关闭
                                btn: ['好的']
                            });
                            //增加样式
                            for (var i=0; i<5; i++){
                                $(children[i]).css('color', 'red').css('text-decoration', 'line-through');
                            }
                        }else{
                            layer.msg('删除学号为:' + id + '失败', {
                                time: 20000, //20s后自动关闭
                                btn: ['好的']
                            });
                        }
                    }
                });
            }
        });
    }

    //更新学生脚本
    function updateStu(e) {
        var id = $(e).parent().parent().parent().parent().children()[1].innerHTML;
        $(location).attr('href', "/update/" + id);
    }
</script>
<%--表单渲染脚本--%>
<script>
    //自动渲染一次表单
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

        //……

        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来进行渲染
        form.render();
    });
    //表单渲染函数
    function renderForm(){
        layui.use('form', function(){
            var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功

            //……

            //但是，如果你的HTML是动态生成的，自动渲染就会失效
            //因此你需要在相应的地方，执行下述方法来进行渲染
            form.render();
        });
    }
</script>
<%--获取院系列表--%>
<script>
    layui.use("layer", function () {
        var layer = layui.layer;
        $("#add").click(function () {
            $("#academy_select").empty();
            //发送ajax请求获取院系，专业列表
            $.ajax({
                type: "get",
                async: true,
                url: "http://localhost:8083/getStuAcademies",
                dataType: "jsonp",
                jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
                success: function(json){
                    $.each( json, function(index, content)
                    {
                        //为Select追加一个Option(下拉项)
                        var str = "<option value=" + content.id + ">" + content.name + "</option>";
                        $("#academy_select").append(str);
                    });
                    //请求专业列表
                    getMajors($('#academy_select option:selected') .val());
                    //渲染表单
                    renderForm();
                },
                error: function(){
                    alert('院系列表请求失败');
                }
            });
            layer.open({
                type: 1,
                title: '新增课程',
                area: ['1000px', '600px'],
                content: $('#add_form') //这里content是一个普通的String
            });
        });
    });
</script>
<%--院系联动脚本--%>
<script>
    //监听院系变化select框
    layui.use('form', function () {
        var form = layui.form;
        form.on('select(academy)', function(data){
            getMajors(data.value);
            form.render('select');
        });
    });
    /**
     * 获取专业的函数
     * @param data 院系id
     */
    function getMajors(data) {
        //发送ajax请求获取院系，专业列表
        $("#major_select").empty();
        $.ajax({
            type: "get",
            async: true,
            url: "http://localhost:8083/getMajors?academyId=" + data,
            dataType: "jsonp",
            jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
            success: function(json){
                $.each( json, function(index, content)
                {
                    //为Select追加一个Option(下拉项)
                    var str = "<option value=" + content.id + ">" + content.name + "</option>";
                    $("#major_select").append(str);
                });
                //渲染表单
                renderForm();
            },
            error: function(){
                alert('专业列表请求失败');
            }
        });
    }
</script>
<%--表单提交脚本--%>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            course_name: function(value){
                if(value.length < 1){
                    return '课程名称不能为空';
                }
            }
            ,number: function(value){
                if(value.length < 1){
                    return '教师姓名不能为空';
                }
            }
            ,teacher: function(value){
                if(value.length < 1){
                    return '教师姓名不能为空';
                }
            }
            ,course_time: function(value){
                if(value.length < 3){
                    return '请输入正确的上课时间';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            //发送ajax请求
            $.ajax({
                url : "/course/add",
                type : "POST",
                contentType: 'application/json',
                async : true,
                data : JSON.stringify(data.field),
                dataType : 'json',
                success : function(data) {
                    if (data.status == 200) {
                        layer.alert(data.msg, {
                            title: '新增成功'
                        });
                        // var stu = data.data;
                        // //异步插入表格中
                        // $("#stu_table").prepend(
                        //     "<tr><td><input type='checkbox' /></td><td >" + stu.id + "</td><td >" + stu.name + "</td><td >" + stu.sex + "</td><td >" + dateFormat(stu.updated) + "</td><td>"
                        //     + "<div class='am-btn-toolbar'>\n" +
                        //     "    <div class='am-btn-group am-btn-group-xs'>\n" +
                        //     "\t<button class='am-btn am-btn-default am-btn-xs am-text-secondary edit' type='button' onclick='updateStu(this)'><span class='am-icon-pencil-square-o'></span> 编辑</button>\n" +
                        //     "\t<button class='am-btn am-btn-default am-btn-xs am-hide-sm-only' type='button'><span class='am-icon-copy'></span> 复制</button>\n" +
                        //     "\t<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only' type='button' onclick='deleteStu(this)'><span class='am-icon-trash-o'></span> 删除</button>\n" +
                        //     "    </div>\n" +
                        //     "</div>\n" +
                        //     "</td>\n" +
                        //     "</tr>"
                        // );
                        // //增加样式
                        // var children = $("tr")[1].children;
                        // for (var i=0; i<5; i++){
                        //     $(children[i]).css('color', 'green');
                        // }
                    } else {
                        layer.alert(data.msg, {
                            title: '新增失败'
                        });
                    }
                }
            });
            return false;
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function(){
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

    });
</script>

</html>
