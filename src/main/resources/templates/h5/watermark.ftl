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
    <link rel="stylesheet" href="../../static/css/layui.css" media="all" type="text/css">
    <link rel="canonical" href="http://www.mengweibo.com/">
    <title>水印助手</title>
    <link rel="stylesheet" href="../../static/css/layui.css" media="all">
    <link rel="shortcut icon" href="../../static/watermark/favicon.ico">

</head>
<body >

<div class="layui-body" style="left:0; margin: 50px;">
    <div class="layui-fluid">
        <div class="layui-col-md12 layui-col-xs12 layui-col-sm12">
            <div style="text-align: center">
                <div class="layui-col-md12 layui-col-xs12 layui-col-sm12">
                    <div class="layui-card">
                        <div class="layui-card-body" style="">
                            <h3>水印助手</h3><br>
                            <a href="https://apps.apple.com/cn/app/id1537228751" style="color: blue">IOS下载地址</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form" lay-filter="passForm" style="text-align: center">

                <div class="layui-input-inline">
                    <input type="text" name="code" required lay-verify="required" placeholder="提取码" autocomplete="off" value="${code}"
                           class="layui-input" style="width: 250px">

                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label" style="font-size: medium"> <a href="" style="color: red;display: none" id="down">点击下载</a> </label>
                        <div class="layui-input-inline">
                            <button class="layui-btn" lay-submit="" lay-filter="feedback" style="margin-top: 20px">
                                提取
                            </button>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>

</div>
<script src="../../static/layui.js"></script>

<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        //监听提交
        form.on('submit(feedback)', function (data) {
            $.ajax({
                url: '/activity/extract',
                type: 'post',
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 0 && data.data != null) {
                        $("#down").attr("href", "/activity/download?url=" + data.data.url);
                        $("#down").show();
                        layer.msg('提取成功,剩余提取次数：' + data.data.surplusTimes + "次");
                    } else {
                        $("#down").hide();
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