<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>修改信息-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;">
        <div class="layui-fluid">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>修改信息</legend>
            </fieldset>
            <div class="layui-row layui-col-space5">
                <div class="layui-col-md3">
                </div>
                <div class="layui-col-md6">
                    <div class="layui-form" lay-filter="form1">
                        <div class="layui-col-md8">
                            <div class="layui-card">
                                <div class="layui-card-body" pad15="">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">账号</label>
                                        <div class="layui-input-inline">
                                            <label class="layui-form-label" id="account">账号</label>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">昵称</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="name" lay-verify="required" lay-vertype="tips"
                                                   placeholder="3-16字昵称"
                                                   class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">微信</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="wechat" class="layui-input" placeholder="微信号">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">介绍</label>
                                        <div class="layui-input-inline" style="width: 400px">
                                            <textarea name="introduce" class="layui-textarea" placeholder="最多1000字"
                                                      lay-verify="required"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label"></label>
                                        <div class="layui-input-inline">
                                            <div class="layui-upload">
                                                <button type="button" class="layui-btn" id="test11"
                                                        style="font-size: small">
                                                    选择头像
                                                </button>
                                                <button class="layui-btn" type="button" lay-submit lay-filter="save"
                                                        id="formDemo">
                                                    保存
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="layui-col-md4">
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" id="demo1" style="width: 100px" lay-data="">
                            </div>
                        </div>

                    </div>
                </div>
                <div class="layui-col-md3">
                </div>
            </div>
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">
<script type="text/javascript">
    layui.use(['form', 'upload', 'layer', 'util'], function () {
        var $ = layui.jquery, layer = layui.layer, upload = layui.upload, form = layui.form;
        $.get('/user/detail', function (res) {
            form.val("form1", {
                "wechat": res.data.wechat
                , "name": res.data.name
                , "introduce": res.data.introduce
            });
            $('#demo1').attr("src", res.data.avatar)
            $('#account').text(res.data.account)
        });

        //文件-发布动态
        upload.render({
            elem: '#test11'
            , url: '/user/info/update'
            , accept: 'images'
            , acceptMime: 'image/*'
            , size: 5120
            , drag: true
            , bindAction: '#formDemo'
            , auto: false
            , data: {
                "wechat": function () {
                    return form.val("form1").wechat;
                },
                "name": function () {
                    return form.val("form1").name;
                }
            }, before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。

            }
            , choose: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr("src", result)
                    $('#demo1').attr("lay-data", '1')
                });
            }
            , done: function (res, index, upload) { //上传后的回调
                if (res.code == 0) {
                    layer.msg('成功');
                } else {
                    layer.msg(res.msg, {icon: 2});
                }
            }, error: function (index, upload) {
                layer.msg('失败', {icon: 2});
            }
        });

        //发布动态
        form.on('submit(save)', function (data) {
            var a = $('#demo1').attr("lay-data");
            if (a == '') {
                $.ajax({
                    url: '/user/info/update',
                    data: data.field,
                    dataType: 'json',
                    type: 'post',
                    success: function (res) {
                        if (res.code == 0) {
                            layer.msg('成功');
                        } else {
                            layer.msg(res.msg, {icon: 2});
                        }
                    }
                })
            }
            return false;
        })

    });
</script>


</body>
</html>