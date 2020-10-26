<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head><meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <Meta name="Copyright" Content="本页版权归水印专家所有。All Rights Reserved">
    <meta name="keywords" content="水印专家,去水印,视频去水印,水印大师,图片九宫格"/>
    <meta name="description" content="专业去视频水印，支持主流短视频平台去水印，图片九宫格、合成视频等功能。"/>
    <link rel="stylesheet" href="../static/css/layui.css" media="all" type="text/css">
    <link rel="canonical" href="http://www.mengweibo.com/">
    <title>水印专家</title>
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
    <link rel="shortcut icon" href="../static/watermark/favicon.ico">
<#--<style type="text/css" >-->
<#--    body {-->
<#--        /*加载背景图*/-->
<#--        background-image: url("static/watermark/favicon.ico");-->
<#--        background-size: 100% 100%;-->
<#--        background-attachment: fixed;-->
<#--    }-->
<#--</style>-->
</head>
<body >

<div class="layui-body" style="left:0; margin: 50px;">
    <div class="layui-fluid">

        <div class="layui-col-md3">
            </br>
        </div>

        <div class="layui-col-md6">
            <div style="text-align: center">
                <h1><a href="https://apps.apple.com/cn/app/id1537228751">水印专家</a></h1>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">功能介绍</div>
                        <div class="layui-card-body" style="color: red">
                            主流短视频平台去水印<br>
                            朋友圈图片九宫格、四宫格<br>
                            多张图片合成视频<br>
                            生成二维码<br>
                            下载地址：<a href="https://apps.apple.com/cn/app/id1537228751">https://apps.apple.com/cn/app/id1537228751</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form" lay-filter="passForm" style="text-align: center">
                       <textarea placeholder="反馈内容" name="content" class="layui-textarea"
                                 lay-verify="required" style="float: left; width: 100%"></textarea>
                <button class="layui-btn" lay-submit="" lay-filter="feedback" style="margin-top: 20px">
                    反馈
                </button>
            </div>


        </div>
    </div>

    <div class="layui-col-md3">
        </br>

    </div>
</div>
</div>
<script src="../static/layui.js"></script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?20c742aa750b3ba3e9eb9203b391174a";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        //监听提交
        form.on('submit(feedback)', function (data) {
            $.ajax({
                url: '/feedback/save',
                type: 'post',
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('感谢反馈，我们会及时跟进!');
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });

            return false;
        });
    });
</script>
</body>
</html>