<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html lang="en">
<head>
    <title>注册-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
    <link rel="stylesheet" href="../static/css/style.css">
</head>
<body style="margin: 10px 60px 15px 60px;">

<div class="login-main">
    <header class="layui-elip" style="width: 82%">注册</header>

    <!-- 表单选项 -->
    <form class="layui-form">
        <div class="layui-input-inline">
            <!-- 用户名 -->
            <div class="layui-inline">
                <input type="radio" name="sex" value="1" title="男">
                <input type="radio" name="sex" value="0" title="女" checked>
            </div>
            <div class="layui-inline" style="width: 85%">
                <input type="text" id="user" name="account" required lay-verify="required" placeholder="请输入账号"
                       autocomplete="off" class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="ri" style="color: green;font-weight: bolder;" hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="wr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
            </div>
        </div>
        <!-- 密码 -->
        <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
                <input type="password" id="pwd" name="password" required lay-verify="required"
                       placeholder="请输入密码(6-12位)" autocomplete="off" class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="pri" style="color: green;font-weight: bolder;" hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="pwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
            </div>
        </div>
        <!-- 确认密码 -->
        <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
                <input type="password" id="rpwd" name="repassword" required lay-verify="required"
                       placeholder="请确认密码(6-12位)" autocomplete="off" class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rpri" style="color: green;font-weight: bolder;" hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rpwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
            </div>
        </div>

        <div class="layui-input-inline">
            <input type="text" name="code" required lay-verify="required" placeholder="验证码"
                   style="width: 30%;float: left; margin-right: 5px" class="layui-input">
            <div style="float: left">
                <img src="/captcha" id="captchaId"> <a href="javascript:;" lay-active="e1"><i
                            class="layui-icon layui-icon-refresh"></i></a>
            </div>
        </div>
        <div class="layui-input-inline login-btn" style="width: 85%">
            <button type="submit" lay-submit lay-filter="sub" class="layui-btn">注册</button>
        </div>
        <hr style="width: 85%"/>
        <p style="width: 85%"><a href="/html/login.html" class="fl">已有账号？立即登录</a></p>
    </form>
</div>


<#include "include/js.ftl">
<script type="text/javascript">
    layui.use(['form', 'jquery', 'layer', 'util'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var util = layui.util;
        util.event('lay-active', {
            e1: function () {
                $("#captchaId").attr("src", "/captcha?" + new Date().getTime());
            }
        });

        // you code ...
        // 为密码添加正则验证
        $('#pwd').blur(function () {
            check();
        });

        //验证两次密码是否一致
        $('#rpwd').blur(function () {
            check();
        });

        //添加表单监听事件,提交注册信息
        form.on('submit(sub)', function (data) {
            if (!check()) {
                return false;
            }
            $.ajax({
                url: '/register',
                type: 'post',
                dataType: 'json',
                data: data.field,
                // data: {
                //     account: $('#user').val(),
                //     password: $('#pwd').val(),
                // },
                success: function (data1) {
                    if (data1.code == 0) {
                        layer.msg('注册成功');
                        location.href = "/index.html";
                    } else {
                        $("#captchaId").attr("src", "/captcha?" + new Date().getTime());
                        layer.msg(data1.msg);
                    }
                }
            });
            //防止页面跳转
            return false;
        });

        function check() {
            var reg = /^[\w]{6,12}$/;
            if (!($('#pwd').val().match(reg))) {
                //layer.msg('请输入合法密码');
                $('#pwr').removeAttr('hidden');
                $('#pri').attr('hidden', 'hidden');
                layer.msg('请输入合法密码');
                return false;
            } else {
                $('#pri').removeAttr('hidden');
                $('#pwr').attr('hidden', 'hidden');
            }
            if ($('#pwd').val() != $('#rpwd').val()) {
                $('#rpwr').removeAttr('hidden');
                $('#rpri').attr('hidden', 'hidden');
                layer.msg('两次输入密码不一致!');
                return false;
            } else {
                $('#rpri').removeAttr('hidden');
                $('#rpwr').attr('hidden', 'hidden');
            }
            ;
            return true;
        }
    });
</script>
</body>
</html>