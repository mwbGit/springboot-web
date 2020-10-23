<#assign keyword = "">
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <#include "include/css.ftl">
    <title>${article.title}-猫咪之家</title>
    <link rel="stylesheet" href="../static/css/forum.css" media="all">
    <style type="text/css">
        p {
            margin-bottom: 10px;
            margin-top: 10px;
        }

        .media-body {
            font-size: 18px;
        }

        .media-body b {
            font-size: 20px;
        }

        .media-body ul {
            margin-left: 20px;
        }

        li {
            list-style-type: square;
        }

    </style>
</head>
<style type="text/css">

</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">

    <#--     主体-->
    <div class="layui-body" style="left:0; margin: 0 50px;">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space20 layadmin-homepage-list-imgtxt">
                <div class="layui-col-md2">
                </div>
                <div class="layui-col-md8">
                    <div class="grid-demo">
                        <div class="panel-body layadmin-homepage-shadow">
                            <div class="media-body">
                                <div class="pad-btm">
                                    <h1 style="text-align: center; margin-top: 10px;">${article.title}</h1>
                                    <p class="min-font">
                      <span class="layui-breadcrumb" lay-separator="-"
                            style="visibility: visible; float: right;margin-bottom: 20px">
                        <a href="http://www.maomihome.com"
                           target="_blank">猫咪之家-${article.author}-${article.dateTime}</a>
                      </span>
                                    </p>
                                </div>
                                <p style="font-size: medium">${article.body}</p>
                                <div class="media">
                                    <div class="media-right">
                                    </div>
                                    <div class="media-left">
                                    </div>
                                </div>
                                <div class="layui-col-md12" style="text-align: center; margin-top: 100px">
                                    <a href="javascript:;" class="layui-btn" lay-active="e1" lay-data="false"
                                       lay-id="${article.id?c}">点赞</a>

                                    <h6>
                                        <#if article.source != "猫咪之家" >
                                            <span style="font-size: xx-small">来源：${article.source}，如有侵权请联系我们删除。</span>
                                        <#else >
                                            <span style="font-size: xx-small">来源：<a href="http://www.maomihome.com/"><b>猫咪之家网</b></a>，内容受到版权保护，如若复制将会追究法律责任。</span>
                                        </#if>
                                    </h6>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md2">
                </div>
            </div>
        </div>

        <#include "include/footer.ftl">
    </div>
</div>
<#include "include/js.ftl">

<script>
    layui.use(['layer', 'util'], function () {
        var $ = layui.jquery, util = layui.util;
        util.event('lay-active', {
            e1: function () {
                var show = this.getAttribute("lay-data");
                var curId = this.getAttribute("lay-id");
                if (show == "false") {
                    this.setAttribute("lay-data", "true");
                    $.get('/article/praise?id=' + curId, function (data) {
                        if (data.data) {
                            layer.msg('点赞+1');
                        }
                    });
                }
            }
        });
    });
</script>

</body>
</html>