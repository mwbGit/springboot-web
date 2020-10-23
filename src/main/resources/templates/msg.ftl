<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>消息中心-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
</head>
<style type="text/css">

</style>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;">
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
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">
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
    <a href="/user/{{d.objectId}}.html" target="_blank" style="color: blue">{{d.title}}</a>
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