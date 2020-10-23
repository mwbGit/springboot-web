<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>猫咪文章-猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <#--     主体-->
    <div class="layui-body" style="left:0; margin: 0 50px;">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md8">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>最新文章</legend>
                    </fieldset>
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-md12">
                            <div class="layui-card">
                                <ul id="t_body">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md4">
                    <div class="layui-card-body layui-text">
                        <div class="layui-card-header">热门文章</div>
                        <div class="layui-card-body">
                            <ul class="layuiadmin-card-status layuiadmin-home2-usernote" id="hot_ul">
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">
<script src="../static/js/article.js"></script>
<script type="text/html" id="sexTpl">
    <div class="layui-row layui-col-space5">
        <div class="layui-col-md12">
            <h2><a href=""> {{d.title }}</a></h2>
        </div>
        <div class="layui-col-md12">
            <span>{{= d.body }}</span>
        </div>
        <div class="layui-col-md3">
            <span><i class="layui-icon">&#xe6c6;</i>{{# d.praiseNum }}</span>
        </div>
        <div class="layui-col-md5 layui-col-md-offset4">
            <span>{{layui.util.toDateString(d.addTime, "yyyy-MM-dd HH:mm:ss") }}</span>
        </div>
    </div>
</script>

</body>
</html>