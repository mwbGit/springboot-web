<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title> 后台管理</title>
    <link rel="stylesheet" href="../static/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
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
                    <a href="/index.html" lay-tips="主页" lay-direction="2">
                        <i class="layui-icon layui-icon-home"></i>
                        <cite>主页</cite>
                    </a>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">
                        <i class="layui-icon layui-icon-group"></i>管理列表</a>
                    <dl class="layui-nav-child">
                        <dd data-name="console">
                            <a lay-href="/html/table">用户管理</a>
                        </dd>
                        <dd data-name="console">
                            <a lay-href="/html/toTest">主页一</a>
                        </dd>
                        <dd data-name="console">
                            <a lay-href="">主页二</a>
                        </dd>
                    </dl>
                </li>
                <#--    图标            https://www.layui.com/doc/element/icon.html-->
                <li class="layui-nav-item"><a href="">菜单1</a></li>
                <li class="layui-nav-item"><a href="">菜单2</a></li>
                <li class="layui-nav-item"><a href="">菜单3</a></li>
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
<script src="../static/layui.js"></script>
<script>
    layui.use(['element', 'jquery'], function () {
        var element = layui.element;
        var $ = layui.jquery;

        //…
        $(document).ready(function () {
            $("dd>a").click(function (e) {
                if ($(this).attr("lay-href") !=null) {
                    e.preventDefault();
                    $("#frameMain").attr("src", $(this).attr("lay-href"));
                }
            });
        });
    });

</script>
</body>
</html>