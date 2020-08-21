<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>发布动态</title>
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
    <legend>发布动态</legend>
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
                        <textarea name="content" rows="7" class="layui-input layui-textarea"
                                  lay-verify="required">${pet.introduce}</textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">发布人:</label>
                    <div class="layui-input-inline" style="width: 480px;">
                        <select name="userType" style="width: 30px">
                            <option value="0">当前用户</option>
                            <option value="1">用户A</option>
                            <option value="2">用户B</option>
                            <option value="3">用户C</option>
                            <option value="4">用户D</option>
                            <option value="5">用户E</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-upload">
                <input type="hidden" name="images" id="images">
                <button type="button" class="layui-btn" id="test3">多图片上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                    预览图：
                    <div class="layui-upload-list" id="demo2"></div>
                </blockquote>
            </div>
            <div class="layui-inline1" style="text-align: center">
                <div class="layui-btn-container">
                    <button class="layui-btn" lay-event="search" lay-submit lay-filter="add_btn">发布</button>
                </div>
            </div>
        </form>
    </div>
    <div class="layui-col-md3">
        <div class="layui-upload-list">
        </div>
    </div>
</div>

<script src="../static/layui.js"></script>

<script type="text/javascript">
    layui.use(['form', 'upload'], function () {
        var form = layui.form, $ = layui.$, upload = layui.upload;

        //多图片上传
        upload.render({
            elem: '#test3'
            , url: '/upload/image' //改成您自己的上传接口
            , multiple: true
            , number: 2
            , accept: 'images'
            , acceptMime: 'image/*'
            , size: 5120
            , drag: true
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style="width: 100px">')
                });
            }
            , done: function (res) {
                //上传完毕
                var img = $('#images').val();
                if (img == '') {
                    $('#images').val(res.data);
                } else {
                    $('#images').val(img + "," + res.data);
                }
            }
        });

        // 添加
        form.on('submit(add_btn)', function (formData) {
            $.ajax({
                url: "/dynamic/publish",
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