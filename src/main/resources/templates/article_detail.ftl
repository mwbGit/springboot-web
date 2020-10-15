<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>${article.title}</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/forum.css" media="all">
 <style type="text/css" >
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
<body style="margin: 10px 60px 15px 60px;">
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
                        <a href="javascript:;">${article.author}</a><span lay-separator="">-</span>
                        <a href="javascript:;">${article.dateTime}</a>
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
                               lay-id="${article.id}">点赞</a>

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
<script src="../static/layui.js"></script>

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