<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>猫猫文章</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css">
</head>
<body style="margin: 10px 60px 15px 60px;">

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
<script src="../static/layui.js"></script>
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