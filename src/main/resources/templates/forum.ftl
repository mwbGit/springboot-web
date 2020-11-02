<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>猫友动态-猫咪之家</title>
    <#assign keyword = ",猫友动态">
    <#include "include/css.ftl">
    <link rel="stylesheet" href="../static/css/forum.css" media="all">
</head>
<style type="text/css">
    p img {
        /*clear: both;*/
        display: block;
        margin: 0 auto;
    }

</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;" id="scrollElem">

        <div class="layui-fluid">
            <div class="layui-layout layui-layout-admin" style="margin-top: 20px">
                <div class="layui-fluid layadmin-homepage-fluid">
                    <#--            <div class="layui-fluid layadmin-homepage-content">-->
                    <div class="layui-row layui-col-space20 layadmin-homepage-list-imgtxt">
                        <div class="layui-col-md9">
                            <div class="layui-col-md12">
                                <form class="layui-form" lay-filter="form1" id="form1" style="display: none">
                                    <div class="layui-form-item layui-form-text">
                                        <div class="layui-input-inline" style="width: 100%;">
                                            <input type="text" name="title" autocomplete="off" class="layui-input"
                                                   lay-verify="required"
                                                   placeholder="请输入标题（最多100个字）">
                                        </div>
                                        <div class="layui-input-inline" style="width: 100%;">
                                    <textarea name="content" placeholder="请输入内容（最多1000个字）" lay-verify="required"
                                              class="layui-textarea" rows="10"></textarea>
                                        </div>
                                    </div>

                                    <div class="layui-form-item" style="overflow: hidden;">
                                        <div class="layui-input-block " style="float: right">
                                            <button class="layui-btn" type="button" lay-submit lay-filter="save"
                                                    id="formDemo">
                                                发表
                                            </button>
                                        </div>

                                        <div class="layui-upload">
                                            <button type="button" class="layui-btn" id="test11">上传插图</button>
                                            <div class="layui-upload-list">
                                                <div id="demo1"></div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="layui-col-md12">
                                <fieldset class="layui-elem-field layui-field-title">
                                    <legend>最新动态</legend>
                                </fieldset>
                            </div>
                            <div class="grid-demo">
                                <ul id="t_body">
                                </ul>
                            </div>
                        </div>
                        <div class="layui-col-md3">
                            <div class="grid-demo">
                                <div class="layui-card homepage-bottom">
                                    <div class="layui-card-header">
                                        <a class="panel-title">

                                        </a>
                                        <a id="dynamicA" href="javascript:;" lay-active="e4" lay-data="false" class=""
                                           style="color: darkslategray">发布动态</a>
                                    </div>
                                </div>
                            </div>
                            <div class="grid-demo">
                                <div class="layui-card homepage-bottom" id="msg_list">
                                    <div class="layui-card-header">
                                        <h3 class="panel-title">
                                            <i class="layui-icon layui-icon-user"></i>
                                            最新消息
                                        </h3>
                                    </div>
                                    <div class="layui-card-body">
                                        <div class="layui-collapse" id="msg_ul" lay-filter="msg_list">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <#--            </div>-->
                </div>
            </div>
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">
<script src="../static/js/forum.js"></script>
</body>
</html>
