<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>猫咪品种-猫咪之家</title>
    <#assign keyword = ",猫品种">
    <#include "include/css.ftl">
</head>
<body class="layui-layout-body" >
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md8">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend>热门品种</legend>
                    </fieldset>

                    <div class="layui-row layui-col-space15" id="t_body">
                    </div>
                </div>

                <div class="layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            品种分类
                        </div>
                        <div class="layui-card-body layui-row layui-col-space15">
                            <div class="layui-col-md12 card-link" id="characters">

                                <a href="javascript:;">操作二</a>
                                <a href="javascript:;">操作三</a>
                                <a href="javascript:;">操作四</a>
                                <a href="javascript:;">操作五</a>
                                <a href="javascript:;">操作六</a>
                            </div>
                        </div>
                    </div>

                    <div class="layui-card">
                        <div class="layui-card-header">
                            作者心语
                        </div>
                        <div class="layui-card-body layui-text layadmin-text">
                            <p>我是迷路的小猫咪，在等待有个最适合的人抱起我，给予真心的温暖，宠爱如珍宝，安静，美好的生活在一起，我期盼着。</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">
<script src="../static/js/pet.js"></script>


</body>
</html>