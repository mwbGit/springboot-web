<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html lang="en">
<head>
    <title>登录-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
    <link rel="stylesheet" href="../static/css/style.css">
</head>
<body style="margin: 10px 60px 15px 60px;">

<div class="login-main">
    <header class="layui-elip">登录</header>
    <form class="layui-form">
        <div class="layui-input-inline">
            <input type="text" name="account" required lay-verify="required" placeholder="账号" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" name="code" required lay-verify="required" placeholder="验证码"
                   style="width: 40%;float: left;margin-right: 10px" class="layui-input">
            <div style="float: left">
                <img src="/captcha" id="captchaId"> <a href="javascript:;" lay-active="e1"><i
                            class="layui-icon layui-icon-refresh" style="margin: 0px 10px;"></i></a>
            </div>
        </div>
        <div class="layui-input-inline login-btn">
            <button lay-submit lay-filter="login" class="layui-btn">登录</button>
        </div>
        <hr/>
        <!--<div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-primary">QQ登录</button>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-normal">微信登录</button>
        </div>-->
        <p><a href="/html/register.html" class="fl">立即注册</a></p>
    </form>
</div>


<#include "include/js.ftl">
<script type="text/javascript">
    layui.use(['form', 'layer', 'util'], function () {

        // 操作对象
        var form = layui.form;
        var $ = layui.jquery, util = layui.util;
        form.on('submit(login)', function (data) {
            // console.log(data.field);
            $.ajax({
                url: '/login/to',
                data: data.field,
                dataType: 'json',
                type: 'post',
                success: function (data1) {
                    if (data1.code == 0) {
                        location.href = "/index.html";
                    } else {
                        $("#captchaId").attr("src", "/captcha?" + new Date().getTime());
                        layer.msg(data1.msg);
                    }
                }
            });
            return false;
        });

        util.event('lay-active', {
            e1: function () {
                $("#captchaId").attr("src", "/captcha?" + new Date().getTime());
            }
        });
    });
</script>
</body>
</html>