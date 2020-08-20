<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>萌宠图片</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 320px;
        max-height: 160px;
    }

    .cmdlist-container {
        margin: 5px 5px
    }
</style>
<body style="margin: 10px 60px 15px 60px;">

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>最新图片</legend>
            </fieldset>
            <div class="layui-row layui-col-space15">
                <div id="t_body">

                </div>
            </div>
        </div>

    </div>
    <img alt="" style="display:none" id="displayimg" src=""/>
</div>
<script src="../static/layui.js"></script>

<script>
    layui.use(['layer', 'util', 'flow'], function () {
        var $ = layui.jquery, util = layui.util;
        var flow = layui.flow;
        util.event('lay-active', {
            e1: function () {
            }
        });
        util.event('lay-active', {
            e1: function () {
                var url = this.getAttribute('lay-data');
                $("#displayimg").attr("src", url);
                var height = $("#displayimg").height();
                var width = $("#displayimg").width();
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: true,
                    area: [width + 'px', height + 'px'], //宽高
                    content: "<img alt=" + name + " title=" + name + " src=" + url + " />"
                });
            }
        });

        flow.load({
            elem: '#t_body' //指定列表容器
            , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
                var lis = [];
                //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                $.get('/pet/picture/search?page=' + page, function (res) {
                    //假设你的列表返回在data集合中
                    layui.each(res.data, function (index, val) {
                        var str = '' +
                            '<div class="layui-input-inline">' +
                            '<div class="cmdlist-container">' +
                            '<a href="javascript:;" lay-active="e1" lay-data="' + val.image + '">' +
                            '<img  src="' + val.image + '" alt="' + val.title + '" title="' + val.title + '"> ' +
                            ' </a>' +
                            '</div>' +
                            '</div>';
                        lis.push(str);
                    });
                    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                    next(lis.join(''), page < res.pages);
                });
            }
        });
    });
</script>


</body>
</html>