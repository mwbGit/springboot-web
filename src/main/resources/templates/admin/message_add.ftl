<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>消息管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
</head>
<style type="text/css">
    .layui-form-label {
        padding: 9px 10px;
        width: 60px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<fieldset class="layui-elem-field layui-field-title">
    <legend>发消息</legend>
</fieldset>
<!-- 你的HTML代码 -->
<div class="layui-row layui-col-space15">
    <div class="layui-col-md2">
    </div>
    <div class="layui-col-md7">
        <form class="layui-form" action="" id="test2" lay-filter="add_form">
            </br>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">标题:</label>
                    <div class="layui-input-inline" style="width: 480px;">
                        <input type="text" name="title" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">内容:</label>
                    <div class="layui-input-inline" style="width: 480px;">
                        <textarea name="body" rows="7" class="layui-input layui-textarea" lay-verify="required">${pet.introduce}</textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户ID:</label>
                    <div class="layui-input-inline" style="width: 480px;">
                        <input type="text" name="userId" autocomplete="off" class="layui-input" lay-verify="required" placeholder="0表示全部">
                    </div>
                </div>
            </div>
            <div class="layui-inline1" style="text-align: center">
                <div class="layui-btn-container">
                    <button class="layui-btn" lay-event="search" lay-submit lay-filter="add_btn">发送</button>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-col-md3">
        <div class="layui-upload-list">
        </div>
    </div>
</div>

<script src="../../static/layui.js"></script>

<script type="text/javascript">
    layui.use(['form'], function () {
        var form = layui.form, $ = layui.$;

        // 添加
        form.on('submit(add_btn)', function (formData) {
            $.ajax({
                url: "/msg/send",
                data: formData.field,
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg(data.msg);
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