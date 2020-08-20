<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>猫咪之家</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">猫咪之家</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left" lay-filter="test1">
            <li class="layui-nav-item"><a href="javascript:;" lay-href="/html/home">主页</a></li>
            <li class="layui-nav-item"><a href="javascript:;" lay-href="/html/pet">品种</a></li>
            <li class="layui-nav-item"><a href="javascript:;" lay-href="/html/article_list">文章</a></li>
            <li class="layui-nav-item"><a href="javascript:;" lay-href="/html/picture">图片</a></li>
            <li class="layui-nav-item"><a href="javascript:;" lay-href="/html/forum">论坛</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="test1">
            <#if avatar!>
                <li class="layui-nav-item" lay-unselect="">
                    <a lay-href="/html/msg" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>
                        <#if unRead == true>
                            <span class="layui-badge-dot"></span>
                        <#else>
                        </#if>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="${avatar}" class="layui-nav-img">
                        ${userName}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="/html/user_update">修改信息</a></dd>
                        <dd><a lay-href="/html/pwd_update">修改密码</a></dd>
                        <dd><a href="/login/out">退出登录</a></dd>
                        <dd><a lay-href="/html/feedback">留言反馈</a></dd>
                    </dl>
                </li>
            <#else>
                <li class="layui-nav-item">
                    <a href="/html/login.html">登录</a>
                </li>
            </#if>
        </ul>
    </div>

    <div class="layui-body" style="left:1px;">
        <!-- 内容主体区域 -->
        <iframe id="frameMain" src="/html/home" style="padding: 15px;width: 100%; height: 100%">内容主体区域</iframe>
    </div>

    <div class="layui-footer" style="left:1px;">
        <!-- 底部固定区域 -->
        © maomizhijia.com
    </div>
</div>
<script src="../static/layui.js"></script>
<script>
    layui.use(['element', 'jquery', 'layer'], function () {
        var element = layui.element;
        var $ = layui.jquery;

        element.on('tab(idx_tab)', function (data) {
            layer.msg(data.index);
        });
        //…
        $(document).ready(function () {
            $("dd>a").click(function (e) {
                if ($(this).attr("lay-href") != null) {
                    e.preventDefault();
                    $("#frameMain").attr("src", $(this).attr("lay-href"));
                }
            });
            element.on('nav(test1)', function (e) {
                if ($(this).attr("lay-href") != null) {
                    $("#frameMain").attr("src", $(this).attr("lay-href"));
                }
            });
        });
    });

</script>
</body>
</html>