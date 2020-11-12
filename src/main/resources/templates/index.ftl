<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>猫咪之家</title>
    <#assign keyword = "">
    <#include "include/css.ftl">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 300px;
        max-height: 140px;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">

    <#--     主体-->
    <div class="layui-body" style="left:0; margin: 0 50px;">
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md8">
                    <div class="layui-row layui-col-space15">
                        <div class="layui-col-md12">
                            <div class="layui-card">
                                <div class="layui-card-body">
                                    <div class="layui-carousel" id="test1" lay-filter="test1">
                                        <div carousel-item="" id="banner_ul">
                                        </div>
                                    </div>
                                </div>
                                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                                    <legend>最新文章</legend>
                                </fieldset>

                                <div class="layui-card-body layui-text">
                                    <div class="layui-row layui-col-space15" id="article_ul">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            品种
                        </div>
                        <div class="layui-card-body layui-text">
                            <div class="layui-row layui-col-space15" id="pet_ul">
                            </div>
                        </div>
                    </div>

                    <div class="layui-card ">
                        <div class="layui-card-header">猫友动态</div>
                        <div class="layui-card-body layui-text">
                            <dl class="layuiadmin-card-status" id="msg_list">

                            </dl>
                            <a href="/html/forum.html">查看更多</a>
                        </div>
                    </div>

                    <div class="layui-card">
                        <div class="layui-card-header">
                            网站寄语
                        </div>
                        <div class="layui-card-body layui-text layadmin-text">
                            <p>
                                但经历整个人世冷暖，彼此命运交叠成无数曲折，它们说，或许生的序幕由他人打开，但舞台上的热泪盈眶却必须由自己奏响。要永远炙烈燃放如同烟火，即便幻灭而逝，也不要放弃能璀璨永存的那一刻。</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-card">
                    <div class="layui-card-header">
                        萌宠图片
                        <a href="/html/picture.html" style="float: right;color: #01AAED">查看更多</a>
                    </div>
                    <div class="layui-card-body layui-text" id="img_ul">
                    </div>
                </div>
            </div>
            <img alt="" style="display:none; width: 600px;" id="displayimg" src="" />

        </div>
    </div>

    <#include "include/footer.ftl">
</div>

<#include "include/js.ftl">

<script src="../static/js/home.js"></script>

</body>
</html>