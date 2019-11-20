<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019-11-20
  Time: 14:34
  To change this template use File | Settings | File Templates.
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

            <li><h4 class="page-title">修改学生信息</h4></li>
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
                <div class="layui-tab layui-tab-card" lay-filter="demo">
                    <ul class="layui-tab-title">
                        <li class="layui-this">学生基本信息修改</li>
                        <li>学生学籍信息修改</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 100%;">
                        <div class="layui-tab-item layui-show">
                            <form class="layui-form layui-form-pane" action="" id="update_form_base" style="margin-left: 20px">
                                <input type="hidden" value="${stuBaseMsg.id}" name="id" />
                                <input type="hidden" value="<fmt:formatDate value="${stuBaseMsg.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="updated" />
                                <div class="layui-form-item" style="margin-top: 10px">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">姓名</label>
                                        <div class="layui-input-inline">
                                            <input value="${stuBaseMsg.name}" type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入学生姓名" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">性别</label>
                                        <div class="layui-input-block">
                                            <input type="radio" name="sex" value="男" title="男" <c:if test="${stuBaseMsg.sex == '男'}">checked="checked"</c:if> />
                                            <input type="radio" name="sex" value="女" title="女" <c:if test="${stuBaseMsg.sex == '女'}">checked="checked"</c:if>/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">出生日期</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="birthday" value="<fmt:formatDate value="${stuBaseMsg.birthday}" pattern="yyyy-MM-dd" />" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
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
                                            <input type="text" value="${stuBaseMsg.certificateNumber}" name="certificateNumber" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">贯籍</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="${stuBaseMsg.ancestralHome}" name="ancestralHome" lay-verify="title" autocomplete="off" placeholder="例如：共青团团员" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">民族</label>
                                        <div class="layui-input-inline">
                                            <select name="nation" lay-filter="aihao">
                                                <option value="汉族" <c:if test="${stuBaseMsg.nation == '汉族'}">onselect="true"</c:if>>汉族</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">政治面貌</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="${stuBaseMsg.politicsStatus}" name="politicsStatus" lay-verify="title" autocomplete="off" placeholder="例如：共青团团员" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">户口所在地</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="${stuBaseMsg.regPermanentResidence}" name="regPermanentResidence" lay-verify="title" autocomplete="off" placeholder="例如：共青团团员" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">入学日期</label>
                                        <div class="layui-input-inline">
                                            <input type="text" value="<fmt:formatDate value="${stuBaseMsg.enrollmentDate}" pattern="yyyy-MM-dd"/>" name="enrollmentDate" id="enrollment_date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
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
                                            <input type="hidden" id="uploadImgUrl" name="image" value="${stuBaseMsg.image}">
                                        </div>
                                    </div>
                                    <div class="layui-inline" id="rotationChartDiv">
                                        <img layer-pid="rotationChartDiv" alt=""  src="${stuBaseMsg.image}" style="max-width: 100%;max-height: 100%;">'
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="submit" class="layui-btn" lay-submit="" lay-filter="submit_base_form">立即提交</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="layui-tab-item">
                            <form class="layui-form layui-form-pane" action="" id="update_form_study" style="margin-left: 20px">
                                <input type="hidden" name="id" value="${stuStudyMsg.id}" />
                                <input type="hidden" name="updated" value="<fmt:formatDate value="${stuStudyMsg.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                                <div class="layui-form-item" style="margin-top: 10px">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">学年</label>
                                        <div class="layui-input-inline">
                                            <select name="semester" lay-filter="semester">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="2020-2021" <c:if test="${stuStudyMsg.semester eq '2020-2021'}">selected="selected"</c:if>>2020-2021</option>
                                                <option value="2019-2020" <c:if test="${stuStudyMsg.semester eq '2019-2020'}">selected="selected"</c:if>>2019-2020</option>
                                                <option value="2018-2019" <c:if test="${stuStudyMsg.semester eq '2018-2019'}">selected="selected"</c:if>>2018-2019</option>
                                                <option value="2017-2018" <c:if test="${stuStudyMsg.semester eq '2017-2018'}">selected="selected"</c:if>>2017-2018</option>
                                                <option value="2016-2017" <c:if test="${stuStudyMsg.semester eq '2016-2017'}">selected="selected"</c:if>>2016-2017</option>
                                                <option value="2015-2016" <c:if test="${stuStudyMsg.semester eq '2015-2016'}">selected="selected"</c:if>>2015-2016</option>
                                                <option value="2014-2015" <c:if test="${stuStudyMsg.semester eq '2014-2015'}">selected="selected"</c:if>>2014-2015</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">学期</label>
                                        <div class="layui-input-inline">
                                            <select name="term" lay-filter="term">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="1" <c:if test="${stuStudyMsg.term eq '1'}">selected="selected"</c:if>>1</option>
                                                <option value="2" <c:if test="${stuStudyMsg.term eq '2'}">selected="selected"</c:if>>2</option>
                                            </select>
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
                                        <label class="layui-form-label">学籍状态</label>
                                        <div class="layui-input-inline">
                                            <select name="studyStatus" lay-filter="aihao">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="1" <c:if test="${stuStudyMsg.studyStatus==1}">selected="selected" </c:if>>在读</option>
                                                <option value="2" <c:if test="${stuStudyMsg.studyStatus==2}">selected="selected" </c:if>>毕业</option>
                                                <option value="3" <c:if test="${stuStudyMsg.studyStatus==3}">selected="selected" </c:if>>退学</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">是否在校</label>
                                        <div class="layui-input-inline">
                                            <select name="isInSchool" lay-filter="aihao">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="1" <c:if test="${stuStudyMsg.isInSchool==1}">selected="selected" </c:if>>在校</option>
                                                <option value="2" <c:if test="${stuStudyMsg.isInSchool==2}">selected="selected" </c:if>>离校</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">学历层次</label>
                                        <div class="layui-input-inline">
                                            <select name="educationLevel" lay-filter="aihao">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="专科" <c:if test="${stuStudyMsg.educationLevel == '专科'}">selected="selected"</c:if>>专科</option>
                                                <option value="本科" <c:if test="${stuStudyMsg.educationLevel == '本科'}">selected="selected"</c:if>>本科</option>
                                                <option value="研究生" <c:if test="${stuStudyMsg.educationLevel == '研究生'}">selected="selected"</c:if>>研究生</option>
                                                <option value="博士" <c:if test="${stuStudyMsg.educationLevel == '博士'}">selected="selected"</c:if>>博士</option>
                                                <option value="博士后" <c:if test="${stuStudyMsg.educationLevel == '博士后'}">selected="selected"</c:if>>博士后</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">培养层次</label>
                                        <div class="layui-input-inline">
                                            <select name="cultivateLevel" lay-filter="aihao">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="专科" <c:if test="${stuStudyMsg.cultivateLevel == '专科'}">selected="selected"</c:if>>专科</option>
                                                <option value="本科" <c:if test="${stuStudyMsg.cultivateLevel == '本科'}">selected="selected"</c:if>>本科</option>
                                                <option value="研究生" <c:if test="${stuStudyMsg.cultivateLevel == '研究生'}">selected="selected"</c:if>>研究生</option>
                                                <option value="博士" <c:if test="${stuStudyMsg.cultivateLevel == '博士'}">selected="selected"</c:if>>博士</option>
                                                <option value="博士后" <c:if test="${stuStudyMsg.cultivateLevel == '博士后'}">selected="selected"</c:if>>博士后</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">学生类别</label>
                                        <div class="layui-input-inline">
                                            <select name="category" lay-filter="aihao">
                                                <option value="" selected="selected">暂未设置</option>
                                                <option value="普通本科生" <c:if test="${stuStudyMsg.category == '普通本科生'}">selected="selected"</c:if>>普通本科生</option>
                                                <option value="专插本" <c:if test="${stuStudyMsg.category == '专插本'}">selected="selected"</c:if>>专插本</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button type="submit" class="layui-btn" lay-submit="" lay-filter="submit_study_form">立即提交</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
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

<%--页面元素初始化脚本--%>
<script>
    layui.use('element', function(){
        var element = layui.element;

        //一些事件监听
        element.on('tab(demo)', function(data){
            console.log(data);
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
        $("#academy_select").empty();
        //发送ajax请求获取院系，专业列表
        $.ajax({
            type: "get",
            async: true,
            url: "http://localhost:8083/getStuAcademies",
            dataType: "jsonp",
            jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
            success: function(json){
                var str = "";
                var academyId = ${stuStudyMsg.academyId};
                $.each( json, function(index, content)
                {
                    //为Select追加一个Option(下拉项)
                    if (academyId == content.id){
                        str = "<option value=" + content.id + " selected=\"selected\">" + content.name + "</option>";
                    } else {
                        str = "<option value=" + content.id + ">" + content.name + "</option>";
                    }
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
                var str = "";
                var majarId = ${stuStudyMsg.majorId};
                $.each( json, function(index, content)
                {
                    //为Select追加一个Option(下拉项)
                    if(majarId == content.id){
                        str = "<option value=" + content.id + " selected=\"selected\">" + content.name + "</option>";
                    }else{
                        str = "<option value=" + content.id + ">" + content.name + "</option>";
                    }
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
<%--表单提交事件--%>
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

        //自定义验证规则
        form.verify({
            name: function(value){
                if(value.length < 2){
                    return '姓名至少得2个字符啊';
                }
            }
        });

        //监听提交
        form.on('submit(submit_base_form)', function(data){
            $.ajax({
                url : "/update/stuBaseMsg",
                type : "POST",
                contentType: 'application/json',
                async : true,
                data : JSON.stringify(data.field),
                dataType : 'json',
                success : function(data) {
                    if (data.status == 200) {
                        layer.alert(data.msg, {
                            title: '修改成功'
                        });
                        //刷新当前页面
                        $(location).reload();
                    } else {
                        layer.alert(data.msg, {
                            title: '修改失败'
                        });
                    }
                }
            });
            return false;
        });

        //监听提交
        form.on('submit(submit_study_form)', function(data){
            $.ajax({
                url : "/update/stuStudyMsg",
                type : "POST",
                contentType: 'application/json',
                async : true,
                data : JSON.stringify(data.field),
                dataType : 'json',
                success : function(data) {
                    if (data.status == 200) {
                        layer.alert(data.msg, {
                            title: '修改成功'
                        });
                        //刷新当前页面
                        $(location).reload();
                    } else {
                        layer.alert(data.msg, {
                            title: '修改失败'
                        });
                    }
                }
            });
            return false;
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
            , url: "commonCtrl/receiptUpload" //必填项
            , method: ''  //可选项。HTTP类型，默认post
            , accept: 'images'
            , acceptMime: 'image/*'
            , size: 200
            ,done: function(res){
                //如果上传失败
                if(!res.success){
                    return layer.alert('上传失败');
                }
                //上传成功
                // var img = '<img layer-pid="rotationChartDiv" alt=""  layer-src="' + res.data.url + '" src="' +
                //     res.data.url + '" style="max-width: 100%;max-height: 100%;">';
                // $("#rotationChartDiv").html(img);
                // $("#url").val(res.data.url);
                //点击放大
                renderImg();
            }
            ,error: function(){
                layer.msg("上传失败");
            }
        });
    });
</script>

</html>
