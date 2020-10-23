<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>留言反馈-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">

    <div class="layui-body" style="left:0; margin: 0 50px;">
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
                                    <button class="layui-btn" lay-submit="" lay-filter="feedback" style="float: right">
                                        反馈
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">
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