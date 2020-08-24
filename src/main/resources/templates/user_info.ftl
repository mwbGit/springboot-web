<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>文章详情</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/forum.css" media="all">

</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 320px;
        max-height: 160px;
    }

    .cmdlist-container {
        margin: 5px 5px
    }

    .cmdlist-text .info {
        height: 40px;
        font-size: 14px;
        line-height: 20px;
        width: 100%;
        overflow: hidden;
        color: #666;
        margin-bottom: 10px;
        text-align: center;
    }
</style>
<body style="margin: 50px 60px 15px 60px;">

<div class="layui-fluid">
    <div class="layui-row layui-col-space8">
        <div class="layui-col-md2">
            <div class="layadmin-homepage-panel layadmin-homepage-shadow">
                <div class="layui-card text-center">
                    <div class="layui-card-body">
                        <#if userInfo !>
                            <div class="layadmin-homepage-pad-ver">
                                <img class="layadmin-homepage-pad-img" src="${userInfo.avatar}"
                                     width="96" height="96">
                            </div>
                            <a href="/user/info?userId=${userInfo.id}"><h4 class="layadmin-homepage-font">${userInfo.name}</h4></a>
                            <br>
                            <button class="layui-btn layui-btn-fluid" lay-active ='e4' lay-id="${userId}">发私信</button>
                        <#else >
                        </#if>
                    </div>
                </div>
                <p class="layadmin-homepage-about">
                    关于我
                </p>
                <div class="layadmin-homepage-pad-hor">
                    <mdall>
                        <#if userInfo !>
                            ${userInfo.introduce}
                        <#else >
                            暂无介绍
                        </#if>
                    </mdall>
                </div>
            </div>
        </div>
        <div class="layui-col-md10">
            <div class="layui-fluid layadmin-homepage-content">
                <div class="layui-row layui-col-space20 layadmin-homepage-list-imgtxt">
                    <div class="layui-col-md9">
                        <div class="grid-demo">
                            <#if dynamicInfo !>

                                <div class="panel-body layadmin-homepage-shadow">
                                    <a href="javascript:;" class="media-left">
                                        <img src="${dynamicInfo.headImg}" height="46px"
                                             width="46px">
                                    </a>
                                    <div class="media-body">
                                        <div class="pad-btm">
                                            <p class="fontColor">
                                                <a href="javascript:;">${dynamicInfo.userName}</a> &nbsp; 发布动态 &nbsp;<span>${dynamicInfo.title}</span>
                                            </p>
                                            <p class="min-font"><span class="layui-breadcrumb" style="visibility: visible;">
                                            <a href="javascript:;">${dynamicInfo.timeDesc}</a></span></p>
                                        </div>
                                        <p>${dynamicInfo.content}
                                        <#if dynamicInfo.imageUrls !>
                                                <#list dynamicInfo.imageUrls as url>
                                                    <img class="h-img" src="${url}" style="width: 100%;">
                                                </#list>
                                        </#if>
                                        <#--                                    点赞评论-->
                                        </p>
                                        <div class="media">
                                            <div class="media-right">
                                                <ul class="list-inline">
                                                    <li><a href="javascript:;" lay-active="e3" lay-id="${dynamicInfo.id}" lay-data="${dynamicInfo.praised}">
                                                            <#if dynamicInfo.praised>
                                                                <i class="layui-icon layui-icon-heart-fill"></i>
                                                            <#else >
                                                                <i class="layui-icon layui-icon-heart"></i>
                                                            </#if>
                                                        </a>
                                                        <span>${dynamicInfo.praiseNum}</span>
                                                    </li>
                                                    <li><a href="javascript:;" lay-active="e2" lay-id="${dynamicInfo.id}">
                                                            <i class="layui-icon layui-icon-reply-fill"></i>
                                                        </a>
                                                        <span>${dynamicInfo.commentNum}</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <#--                                    评论-->
                                        <#if dynamicInfo.comments !>
                                            <div class="media-list">
                                                <#list dynamicInfo.comments as comment>
                                                    <#if comment_index == 2>
                                                        <div id="comment${dynamicInfo.id}" style="display: none">
                                                    </#if >
                                                    <div class="media-item">
                                                        <a href="javascript:;" class="media-item-left">
                                                            <img class="img-xs"
                                                                 src="${comment.headImg}">
                                                        </a>
                                                        <div class="media-text">
                                                            <div>
                                                                <a href="/user/info?userId=${comment.userId}">${comment.userName}</a>
                                                                <mdall> ${comment.timeDesc}</mdall>
                                                            </div>
                                                            <div>${comment.content}</div>
                                                        </div>
                                                    </div>
                                                    <#if comment_index gt 2 &&  comment_has_next != true>
                                                        </div>
                                                    </#if >
                                                </#list>
                                                <#if dynamicInfo.comments?size gt 2>
                                                    <a href="javascript:;" class="layui-btn" lay-data="true" lay-id="${dynamicInfo.id}" lay-active="e1">展示全部</a>
                                                </#if >
                                            </div>
                                        </#if>
                                    </div>
                                </div>

                            </#if>

                            <fieldset class="layui-elem-field layui-field-title">
                                <legend>全部动态</legend>
                            </fieldset>

                            <#if all !>
                                <#list all.list as dynamicItme>
                                <div class="panel-body layadmin-homepage-shadow">
                                    <a href="javascript:;" class="media-left">
                                        <img src="${dynamicItme.headImg}" height="46px"
                                             width="46px">
                                    </a>
                                    <div class="media-body">
                                        <div class="pad-btm">
                                            <p class="fontColor">
                                                <a href="javascript:;">${dynamicItme.userName}</a> &nbsp; 发布动态 &nbsp;<span>${dynamicItme.title}</span>
                                            </p>
                                            <p class="min-font"><span class="layui-breadcrumb" style="visibility: visible;">
                                            <a href="javascript:;">${dynamicItme.timeDesc}</a></span></p>
                                        </div>
                                        <p>${dynamicItme.content}
                                            <#if dynamicItme.imageUrls !>
                                                <#list dynamicItme.imageUrls as url>
                                                    <img class="h-img" src="${url}" style="width: 100%;">
                                                </#list>
                                            </#if>
                                        </p>
                                        <#--                                    点赞评论-->
                                        <div class="media">
                                            <div class="media-right">
                                                <ul class="list-inline">
                                                    <li><a href="javascript:;" lay-active="e3" lay-id="${dynamicItme.id}" lay-data="${dynamicItme.praised}">
                                                            <#if dynamicItme.praised>
                                                                <i class="layui-icon layui-icon-heart-fill"></i>
                                                            <#else >
                                                                <i class="layui-icon layui-icon-heart"></i>
                                                            </#if>
                                                        </a>
                                                        <span>${dynamicItme.praiseNum}</span>
                                                    </li>
                                                    <li><a href="javascript:;" lay-active="e2" lay-id="${dynamicItme.id}">
                                                            <i class="layui-icon layui-icon-reply-fill"></i>
                                                        </a>
                                                        <span>${dynamicItme.commentNum}</span>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <#--                                    评论-->
                                        <#if dynamicItme.comments !>
                                            <div class="media-list">
                                                <#list dynamicItme.comments as comment>
                                                    <#if comment_index == 2>
                                                        <div id="comment${dynamicItme.id}" style="display: none">
                                                    </#if >
                                                    <div class="media-item">
                                                        <a href="javascript:;" class="media-item-left">
                                                            <img class="img-xs"
                                                                 src="${comment.headImg}">
                                                        </a>
                                                        <div class="media-text">
                                                            <div>
                                                                <a href="/user/info?userId=${comment.userId}">${comment.userName}</a>
                                                                <mdall> ${comment.timeDesc}</mdall>
                                                            </div>
                                                            <div>${comment.content}</div>
                                                        </div>
                                                    </div>
                                                    <#if comment_index gt 1 &&  comment_has_next != true>
                                                        </div>
                                                    </#if >
                                                </#list>
                                                <#if dynamicItme.comments?size gt 2>
                                                    <a href="javascript:;" class="layui-btn" lay-data="true" lay-id="${dynamicItme.id}" lay-active="e1">展示全部</a>
                                                </#if >
                                            </div>
                                        </#if>
                                    </div>
                                </div>
                                </#list>
                            </#if>
                        </div>

                    </div>
                    <div class="layui-col-md3">
                        <div class="grid-demo">
                            <div class="layui-card homepage-bottom">
                                <div class="layui-card-header">
                                    <h3 class="panel-title">
                                        他的动态
                                    </h3>
                                </div>
                                <div class="layui-card-body layui-text">
                                    <div class="layui-card-body">
                                        <ul class="layuiadmin-card-status layuiadmin-home2-usernote" id="hot_ul">
                                            <#if hot! >
                                                <#list hot as dynamic>
                                                    <li>
                                                        <a href="/user/info?dynamicId=${dynamic.id}">${dynamic.title}</a>
                                                        <span><i class="layui-icon">&#xe6c6;</i>${dynamic.praiseNum}</span>
                                                    </li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md9" style="text-align: center">
                <#if all ! && all.total gt 0>
                    <#if all.isFirstPage == false >
                        <a href="/user/info?userId=${userId}&page=${all.prePage}">上一页</a>
                    </#if>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <#if all.isLastPage == false >
                        <a href="/user/info?userId=${userId}&page=${all.nextPage}">下一页</a>
                    </#if>
                    <#else >
                        暂无动态
                </#if>
            </div>
        </div>
    </div>
</div>
</div>


<script src="../static/layui.js"></script>

<script>
    layui.use(['layer', 'util'], function () {
        var $ = layui.jquery, util = layui.util;

        util.event('lay-active', {
            e1: function () {
                var show = this.getAttribute("lay-data");
                var curComId = this.getAttribute("lay-id");
                if (show == "true") {
                    $(this).text("收起");
                    this.setAttribute("lay-data", "false");
                    $("#comment" + curComId).show();
                } else {
                    $(this).text("展示全部");
                    $("#comment" + curComId).hide();
                    this.setAttribute("lay-data", "true");
                }
            }
            , e2: function () {
                var id = this.getAttribute("lay-id");
                layer.open({
                    title: '评论规则(审核通过后展示)'
                    , btn: ['评论']
                    , area: ['500px', '300px']
                    , content: '<textarea  placeholder="请输入消息内容（最多100个字）"style="width: 100%;height: 100%"></textarea>' //这里content是一个普通的String
                    , yes: function (index, layero) {
                        //按钮【按钮一】的回调
                        var comment = '';
                        $(layero).find("textarea").each(function (i, v) {
                            comment = $(v).val().trim();
                        });
                        layer.close(index);

                        if (comment != null && comment != '') {
                            saveComment(id, comment);
                        }
                    }
                });
            }
            , e3: function () {
                var id = this.getAttribute("lay-id");
                var data = this.getAttribute("lay-data");
                if (data === 'true') {
                    this.setAttribute("lay-data", "false");
                    $(this).html('<i class="layui-icon layui-icon-heart"></i>');
                } else {
                    this.setAttribute("lay-data", "true");
                    $(this).html('<i class="layui-icon layui-icon-heart-fill"></i>');
                }
                savePraise(id);
            }, e4: function () {
                var id = this.getAttribute("lay-id");
                layer.open({
                    title: '私信'
                    , btn: ['发送']
                    , area: ['500px', '300px']
                    , content: '<textarea  placeholder="请输入内容（最多100个字）"style="width: 100%;height: 100%"></textarea>' //这里content是一个普通的String
                    , yes: function (index, layero) {
                        //按钮【按钮一】的回调
                        var comment = '';
                        $(layero).find("textarea").each(function (i, v) {
                            comment = $(v).val().trim();
                        });
                        layer.close(index);

                        if (comment != null && comment != '') {
                            sendMsg(id, comment);
                        }
                    }
                });
            }
        });

        function saveComment(id, comment) {
            $.ajax({
                url: '/dynamic/comment',
                data: {
                    "dynamicId": id,
                    "comment": comment
                },
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('成功');
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
        }

        function sendMsg(userId, content) {
            $.ajax({
                url: '/msg/alone/send',
                data: {
                    "userId": userId,
                    "content": content
                },
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg(data.msg);
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                }
            });
        }

        function savePraise(id) {
            $.get('/dynamic/praise?dynamicId=' + id, function (data) {
            });
        }
    });
</script>


</body>
</html>