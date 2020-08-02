<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layDate快速使用</title>
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
</head>
<body>

<div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
    <input type="text" class="layui-input" id="test1">
</div>

<script src="../static/layui.js"></script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1' //指定元素
            ,type :"time"
        });
    });
</script>
</body>
</html>