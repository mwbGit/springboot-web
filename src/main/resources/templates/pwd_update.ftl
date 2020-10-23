<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>密码修改-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;">
        <div class="layui-fluid">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>修改密码</legend>
            </fieldset>
            <div class="layui-row layui-col-space5">
                <div class="layui-col-md3">
                </div>
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-body" pad15="">
                            <div class="layui-form" lay-filter="passForm">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">当前密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="oldPassword" lay-verify="pass" lay-vertype="tips"
                                               class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">新密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" placeholder="6到16个字符" name="newPassword"
                                               lay-verify="pass"
                                               lay-vertype="tips" autocomplete="off" id="LAY_password"
                                               class="layui-input">
                                    </div>
                                    <!-- 对号 -->
                                    <div class="layui-inline">
                                        <i class="layui-icon" id="pri" style="color: green;font-weight: bolder;" hidden></i>
                                    </div>
                                    <!-- 错号 -->
                                    <div class="layui-inline">
                                        <i class="layui-icon" id="pwr" style="color: red; font-weight: bolder;"
                                           hidden>ဆ</i>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">确认新密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="rpass" lay-verify="rpass" lay-vertype="tips"
                                               autocomplete="off"
                                               class="layui-input">
                                    </div>
                                    <!-- 对号 -->
                                    <div class="layui-inline">
                                        <i class="layui-icon" id="rpri" style="color: green;font-weight: bolder;"
                                           hidden></i>
                                    </div>
                                    <!-- 错号 -->
                                    <div class="layui-inline">
                                        <i class="layui-icon" id="rpwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit="" lay-filter="setmypass">确认修改</button>
                                    </div>

                                </div>
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
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        //添加表单失焦事件
        //验证表单

        form.verify({
            rpass: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (!/^[\S]{6,12}$/.test(value)) {
                    return '密码必须6到12位，且不能出现空格';
                }
                if (value != form.val("passForm").newPassword) {
                    return '两次密码不一至';
                }
            },
            pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
        });

        //监听提交
        form.on('submit(setmypass)', function (data) {
            $.ajax({
                url: '/user/password/update',
                type: 'post',
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('修改成功');
                        ///location.href = "login.html";
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