<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>文章详情</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 320px;
        max-height: 160px;
    }

    .cmdlist-container {
        margin: 5px 5px
    }

    .cmdlist-text .info {
        height: 40px;
        font-size: 14px;
        line-height: 20px;
        width: 100%;
        overflow: hidden;
        color: #666;
        margin-bottom: 10px;
        text-align: center;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">

<div class="layui-fluid">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>添加宠物</legend>
    </fieldset>
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md1">
        </div>
        <div class="layui-col-md10">
            <form class="layui-form" action="" id="test2" lay-filter="add_form">
                </br>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">标题:</label>
                        <div class="layui-input-inline" style="width: 500px;">
                            <#if pet ! >
                                <input type="hidden" name="id"  value="${pet.id?c}">
                            <#else >
                                <input type="hidden" name="id"  value="0">
                            </#if>
                            <input type="text" name="title" autocomplete="off" class="layui-input" lay-verify="required" value="${pet.title}">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">作者:</label>
                        <div class="layui-input-inline" >
                            <input type="text" name="viewNum" value="猫军" placeholder="100-1000元" autocomplete="off" lay-verify="required"
                                   class="layui-input" value="${pet.viewNum}">
                        </div>
                        <label class="layui-form-label">来源:</label>
                        <div class="layui-input-inline" >
                            <input type="text" name="praiseNum" value="猫咪之家" placeholder="猫咪之家" autocomplete="off" lay-verify="required"
                                   class="layui-input" value="${pet.praiseNum}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">品类:</label>
                        <div class="layui-input-inline">
                            <select name="petId" lay-verify="required" lay-search="" lay-filter="selectF" id ="selectF">
                            </select>
                        </div>
                        <label class="layui-form-label">类别:</label>
                        <div class="layui-input-inline">
                            <select name="type" style="width: 30px">
                                <option value="0">默认</option>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">内容:</label>
                        <div class="layui-input-inline" style="width: 800px">
                            <textarea id="demo" style="display: none;" name="body" lay-verify="required">${pet.body}</textarea>

                        </div>
                    </div>
                </div>

                <div class="layui-inline1" style="text-align: center">
                    <div class="layui-btn-container">
                        <button class="layui-btn" lay-event="search" lay-submit lay-filter="add_btn">保存</button>
                    </div>
                    <input type="hidden" value="" id="image" name ="image"/>
                </div>
            </form>
        </div>
        <div class="layui-col-md1">
            <div class="layui-upload-list">
                <img id="demo1" src="${pet.image}" style="width: 200px;margin: 0 2px 2px 0">
            </div>
        </div>
    </div>

</div>


<script src="../static/layui.js"></script>

<script>
    layui.use(['layedit', 'form'], function(){
        var layedit = layui.layedit;
        var $ = layui.jquery;
        var form = layui.form;

        $.get("/pet/search?paged=false", function (res) {
            var  str ='<option value="">直接选择或搜索选择</option>';
            if (res.code == 0) {
                layui.each(res.data, function (index, val) {
                    str += '<option value="'+val.id+'">'+ val.name+'</option>';
                });
                $("#selectF").html(str);
                form.render('select'); //刷新select选择框渲染
            }
        });

        var index =  layedit.build('demo', {
            uploadImage : {
                url : "/upload/layedit",
                type : 'post'
            },
            tool: [
                'strong' //加粗
                ,'italic' //斜体
                ,'underline' //下划线
                ,'del' //删除线

                ,'|' //分割线

                ,'left' //左对齐
                ,'center' //居中对齐
                ,'right' //右对齐
                ,'link' //超链接
                ,'unlink' //清除链接
                ,'face' //表情
                ,'image' //插入图片
            ]
        }); //建立编辑器

        // 添加
        form.on('submit(add_btn)', function (formData) {
            $.ajax({
                url: "/article/save",
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