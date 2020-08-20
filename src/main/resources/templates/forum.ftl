<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>萌宠动态</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css">
    <link rel="stylesheet" href="../static/css/forum.css" media="all">
</head>
<body style="margin: 10px 60px 15px 60px;">

<style>
    .media-text {
        margin-left: 10px;
    }
</style>
<div id="LAY_app">
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
                                    <button class="layui-btn" type="button" lay-submit lay-filter="save" id="formDemo">
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
<script src="../static/layui.js"></script>
<script src="../static/js/forum.js"></script>
</body>
</html>
