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

            <li><h4 class="page-title">新增学生</h4></li>
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
                            <table id="stu_table" class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-check"><input type="checkbox" /></th><th class="table-id">学号</th><th class="table-hover">姓名</th><th class="table-type">性别</th><th class="table-date am-hide-sm-only">修改日期</th><th class="table-set">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="stuBaseMsg" items="${stuBaseMsgList}">
                                <tr>
                                    <td><input type="checkbox" /></td>
                                    <td >${stuBaseMsg.id}</td>
                                    <td >${stuBaseMsg.name}</td>
                                    <td >${stuBaseMsg.sex}</td>
                                    <td ><fmt:formatDate value="${stuBaseMsg.updated}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
                                </c:forEach>
                                </tbody>
                            </table>
                            <div id="page" ></div>
                            <hr />
                            <p>注：学生基本信息列表</p>
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
</div>
</div>

<!-- navbar -->
<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
<jsp:include page="/WEB-INF/jsp/common/jsscript.jsp"></jsp:include>

<%--分页脚本--%>
<script>
    layui.use('laypage', function(){
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'page'
            ,count: 100
            ,layout: ['count', 'first', 'prev', 'page', 'next', 'last', 'limit', 'refresh']
            ,first:'首页'
            ,last:'尾页'
            ,jump: function(obj){
                console.log(obj)
            }
        });
    });
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
        $("#add, #edit").click(function () {
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
                title: '新增学生',
                area: ['1000px', '600px'],
                content: $('#add_form') //这里content是一个普通的String
            });
        });
        //删除监听函数
        $("#del").click(function(){
            //配置一个透明的询问框
            layer.msg('确定要删除吗？请三思哦！', {
                time: 20000, //20s后自动关闭
                btn: ['确认删除', '我去三思', '不删了']
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
</body>
<%--表单--%>
<form class="layui-form layui-form-pane" action="" id="add_form" style="margin-left: 20px">
    <div class="layui-form-item" style="margin-top: 10px">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入学生姓名" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked="">
                <input type="radio" name="sex" value="女" title="女">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="text" name="birthday" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">证件类型</label>
            <div class="layui-input-inline">
                <select name="certificateType" lay-filter="aihao">
                    <option value="身份证">身份证</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证号码</label>
            <div class="layui-input-inline">
                <input type="text" name="certificateNumber" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input" value="441827199803127214">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">贯籍</label>
            <div class="layui-input-inline">
                <input type="text" name="ancestralHome" autocomplete="off" placeholder="例如：共青团团员" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">民族</label>
            <div class="layui-input-inline">
                <select name="nation" lay-filter="aihao">
                    <option value="汉族">汉族</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">政治面貌</label>
            <div class="layui-input-inline">
                <input type="text" name="politicsStatus" autocomplete="off" placeholder="例如：共青团团员" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">户口所在地</label>
            <div class="layui-input-inline">
                <input type="text" name="regPermanentResidence" autocomplete="off" placeholder="例如：共青团团员" class="layui-input">
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
        <div class="layui-inline">
            <label class="layui-form-label">入学日期</label>
            <div class="layui-input-inline">
                <input type="text" name="enrollmentDate" id="enrollment_date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">头像照片</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="uploadImg">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
                <input type="hidden" id="uploadImgUrl" name="image" value="">
            </div>
        </div>
        <div class="layui-inline" id="rotationChartDiv"></div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
<%--            <button type="submit" id="form_submit_btn" class="layui-btn layui-btn-disabled" lay-submit="" lay-filter="demo1" disabled="disabled">立即提交</button>--%>
    <button type="submit" id="form_submit_btn" class="layui-btn " lay-submit="" lay-filter="demo1" >立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<%--表单提交脚本--%>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        //同时绑定多个
        lay('#date, #enrollment_date').each(function(){
            laydate.render({
                elem: this
                ,trigger: 'click'
            });
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            title: function(value){
                if(value.length < 2){
                    return '姓名至少得2个字符啊';
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
                url : "/stu_add",
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
                        var stu = data.data;
                        alert(stu.updated);
                        //异步插入表格中
                        $("#stu_table").prepend(
                            "<tr><td><input type='checkbox' /></td><td >" + stu.id + "</td><td >" + stu.name + "</td><td >" + stu.sex + "</td><td >" + dateFormat(stu.updated) + "</td><td>"
                             + "<div class='am-btn-toolbar'>\n" +
                            "    <div class='am-btn-group am-btn-group-xs'>\n" +
                            "\t<button class='am-btn am-btn-default am-btn-xs am-text-secondary' type='button' id='edit'><span class='am-icon-pencil-square-o'></span> 编辑</button>\n" +
                            "\t<button class='am-btn am-btn-default am-btn-xs am-hide-sm-only'><span class='am-icon-copy'></span> 复制</button>\n" +
                            "\t<button class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'><span class='am-icon-trash-o'></span> 删除</button>\n" +
                            "    </div>\n" +
                            "</div>\n" +
                            "</td>\n" +
                            "</tr>"

                        );
                    } else {
                        layer.alert(data.msg, {
                            title: '新增失败'
                        });
                    }
                }
            });
            return false;
        });

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

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function(){
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

    });
</script>
<%--图片上传脚本--%>
<script>
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        //图片上传
        upload.render({
            elem: '#uploadImg'
            , url: "/image/uploadImage" //必填项
            , method: 'post'  //可选项。HTTP类型，默认post
            , accept: 'images'
            , acceptMime: 'image/*'
            , size: 200
            ,done: function(data){
                //如果上传失败
                if(data.status != 200){
                    $("#form_submit_btn").addClass("layui-btn-disabled").attr('disabled', 'disabled');
                    return layer.alert('上传失败');
                }
                //上传成功
                var img = '<img layer-pid="rotationChartDiv" alt=""  layer-src="' + data.data.url + '" src="' +
                    data.data.url + '" style="max-width: 100%;max-height: 100%;">';
                $("#form_submit_btn").removeClass("layui-btn-disabled").removeAttr('disabled');
                $("#rotationChartDiv").html(img);
                $("#uploadImgUrl").val(data.data.url);
            }
            ,error: function(){
                layer.msg("上传失败");
            }
        });
    });
</script>
<%--图片放大脚本--%>
<script>
    var renderImg = function () {
        var form = layui.form;
        form.render();
        layer.photos({
            photos: '.theImg'
            , anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
        });
    }
</script>

</html>
