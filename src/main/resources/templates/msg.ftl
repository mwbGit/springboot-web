<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>消息中心</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/forum.css" media="all">
</head>
<style type="text/css">

</style>

<body style="margin: 10px 60px 15px 60px;">

<div class="layui-fluid" id="LAY-app-message">
    <div class="layui-card">
        <div class="layui-tab layui-tab-brief" lay-filter="msg_tab">
            <ul class="layui-tab-title">
                <li class="layui-this">全部消息</li>
                <li>系统通知</li>
                <li>动态消息</li>
                <li>私信消息</li>
            </ul>
            <div class="layui-tab-content">

                <div class="layui-tab-item layui-show" style="">

                    <div class="LAY-app-message-btns" style="margin-bottom: 10px;">
                        <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="direct"
                                data-events="readyAll" lay-active="e1">全部已读
                        </button>
                    </div>

                    <table class="" id="test" lay-filter="table"></table>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="../static/layui.js"></script>
<script src="../static/js/msg.js"></script>
<script type="text/html" id="statusTpl">
    {{#  if(d.status == 0){ }}
    未读
    {{#  } else{ }}
    已读
    {{#  }  }}
</script>
<script type="text/html" id="statusTpl1">
    {{#  if(d.type == 2){ }}
        <a href="/user/info?id={{d.objectId}}" target="_blank" style="color: blue">{{d.title}}</a>
    {{#  } else{ }}
    {{ d.title }}
    {{#  }  }}
</script>
<style id="LAY_layadmin_theme">.layui-side-menu, .layadmin-pagetabs .layui-tab-title li:after, .layadmin-pagetabs .layui-tab-title li.layui-this:after, .layui-layer-admin .layui-layer-title, .layadmin-side-shrink .layui-side-menu .layui-nav > .layui-nav-item > .layui-nav-child {
        background-color: #20222A !important;
    }

    .layui-nav-tree .layui-this, .layui-nav-tree .layui-this > a, .layui-nav-tree .layui-nav-child dd.layui-this, .layui-nav-tree .layui-nav-child dd.layui-this a {
        background-color: #009688 !important;
    }

    .layui-layout-admin .layui-logo {
        background-color: #20222A !important;
    }</style>
</body>
</html>