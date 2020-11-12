<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]><html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]><html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]><html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="" lang="zh-cn"><!--<![endif]--><html>
<head>
    <title>萌宠图片-猫咪之家</title>
    <#assign keyword = ",猫咪图片">
    <#include "include/css.ftl">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 320px;
        max-height: 160px;
        height: 100%;
    }

    .cmdlist-container {
        margin: 2px
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;" id="scrollElem">
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
            <img alt="" style="display:none; width: 600px;" id="displayimg" src="" />
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">

<script>
    layui.use(['layer', 'util', 'flow'], function () {
        var $ = layui.jquery, util = layui.util;
        var flow = layui.flow;
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
                    content: "<img style='width: 600px;'  alt=" + name + " title=" + name + " src=" + url + " />"
                });
            }
        });

        flow.load({
            isAuto: true,
            elem: '#t_body' //指定列表容器
            ,scrollElem: '#scrollElem' //滚动条所在元素，一般不用填，此处只是演示需要。
            , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
                var lis = [];
                var pageSize = 50;
                //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                $.get('/pet/picture/search?pageSize=' + pageSize + '&page=' + page, function (res) {
                    //假设你的列表返回在data集合中
                    layui.each(res.data, function (index, val) {
                        var str = '' +
                            '<div class="layui-input-inline cmdlist-container">' +
                            '<div class="">' +
                            '<a href="javascript:;" lay-active="e1" lay-data="' + val.waterImage + '">' +
                            '<img  src="' + val.waterImage + '" alt="' + val.title + '" title="' + val.title + '"  > ' +
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