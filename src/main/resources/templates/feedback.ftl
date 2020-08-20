<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>留言反馈</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
</head>
<body style="margin: 10px 60px 15px 60px;">
<div class="layui-fluid">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>留言反馈</legend>
    </fieldset>
    <div class="layui-col-md8">
        <div class="layui-card">
            <div class="layui-card-body" pad15="">
                <div class="layui-form" lay-filter="passForm">
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                        <textarea placeholder="留言反馈内容" name="content" class="layui-textarea"
                                  lay-verify="required"></textarea>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="feedback" style="float: right">反馈
                            </button>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="../static/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'jquery', 'layer'], function () {
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