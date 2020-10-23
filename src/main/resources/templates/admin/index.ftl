<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 后台管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">后台管理</div>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="/index.html" target="_blank">前台</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${avatar}" class="layui-nav-img">
                    ${userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a lay-href="/html/pwd_update">修改密码</a></dd>
                    <dd><a href="/login/out">退出登录</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li data-name="home" class="layui-nav-item layui-nav-itemed">
                    <a href="/admin/index.html" lay-tips="主页" lay-direction="2">
                        <i class="layui-icon layui-icon-home"></i>
                        <cite>主页</cite>
                    </a>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">
                        <i class="layui-icon layui-icon-android"></i> 宠物管理</a>
<#--                        <i class="layui-icon layui-icon-picture"></i>宠物管理</a>-->
                    <dl class="layui-nav-child">
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/pet_list.html" lay-active="e1">宠物管理</a>
                        </dd>
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/pet/info" lay-active="e1">添加宠物</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">
                        <i class="layui-icon layui-icon-read"></i> 文章管理</a>
                    <dl class="layui-nav-child">
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/article_list.html" lay-active="e1">文章管理</a>
                        </dd>
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/article_info.html" lay-active="e1">添加文章</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">
                        <i class="layui-icon layui-icon-release"></i> 动态管理</a>
                    <dl class="layui-nav-child">
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/dynamic_list.html" lay-active="e1">动态管理</a>
                        </dd>
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/dynamic_add.html" lay-active="e1">发布动态</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">
                        <i class="layui-icon layui-icon-picture"></i> 图片管理</a>
                    <dl class="layui-nav-child">
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/pet_picture.html" lay-active="e1">图片管理</a>
                        </dd>
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/picture_add.html" lay-active="e1">添加图片</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">
                        <i class="layui-icon layui-icon-dialogue"></i> 消息管理</a>
                    <dl class="layui-nav-child">
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/message_list.html" lay-active="e1">消息审核</a>
                        </dd>
                        <dd data-name="console">
                            <a href="javascript:;" lay-href="/admin/message_add.html" lay-active="e1">群发消息</a>
                        </dd>
                    </dl>
                </li>
                <#--    图标            https://www.layui.com/doc/element/icon.html-->
                <li class="layui-nav-item"><a href="javascript:;" lay-href="/admin/user_list.html"
                                              lay-active="e1"><i class="layui-icon layui-icon-group"></i>用户管理</a></li>

                <li class="layui-nav-item"><a href="javascript:;" lay-href="/admin/banner_list.html"
                                              lay-active="e1"><i class="layui-icon layui-icon-chart-screen"></i> 轮播管理</a>
                </li>
                <li class="layui-nav-item"><a href="javascript:;" lay-href="/admin/comment_list.html"
                                              lay-active="e1"><i class="layui-icon layui-icon-reply-fill"></i> 评论管理</a></li>
                <li class="layui-nav-item"><a href="javascript:;" lay-href="/admin/feedback.html"
                                              lay-active="e1"><i class="layui-icon layui-icon-help"></i> 反馈管理</a></li>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="frameMain" style="padding: 15px;width: 100%; height: 100%">内容主体区域</iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../../static/layui.js"></script>
<script>
    layui.use(['element', 'util'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        var util = layui.util;

        //…
        $(document).ready(function () {
            $("dd>a").click(function (e) {
                if ($(this).attr("lay-href") != null) {
                    e.preventDefault();
                    $("#frameMain").attr("src", $(this).attr("lay-href"));
                }
            });
        });

        util.event('lay-active', {
            e1: function () {
                var url = this.getAttribute('lay-href');
                $("#frameMain").attr("src", url);
            }
        });
    });

</script>
</body>
</html>