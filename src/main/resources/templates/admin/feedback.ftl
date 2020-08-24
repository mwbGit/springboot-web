<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>反馈管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
</head>
<style type="text/css">
    .layui-form-label {
        padding: 9px 10px;
        width: 40px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<fieldset class="layui-elem-field layui-field-title">
    <legend>反馈管理</legend>
</fieldset>
<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>

<script src="../../static/layui.js"></script>

<script type="text/javascript">
    layui.use(['table'], function () {
        var table = layui.table;

        // 表格
        table.render({
            elem: '#test'  //绑定table id
            , url: '/feedback/search'  //数据请求路径
            , cellMinWidth: 80
            , cols: [[
                {type: 'space', hide: true}
                , {field: 'id', title: 'ID', width : 100}
                , {field: 'content', title: '反馈内容'}
                , {field: 'userId', title: '用户ID', width : 100}
                , {
                    field: 'addTime',
                    title: '反馈时间',
                    width: 200,
                    templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd HH:mm:ss") }}</div>'
                }
            ]]
            , loading: true
            , page: true   //开启分页
            , limit: 20   //默认十条数据一页
            , limits: [10, 20, 30, 50]  //数据分页条
            , id: 'userReload'
            , request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        });
    });
</script>
</body>
</html>