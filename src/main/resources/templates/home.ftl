<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>猫咪之家</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css">
    <link rel="stylesheet" href="../static/css/admin.css">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 300px;
        max-height: 140px;
    }

    .cmdlist-container {
        margin: 5px 5px
    }
</style>
<body style="margin: 10px 60px 15px 60px;">

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
                    <a href="/html/forum">查看更多</a>
                </div>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">
                    网站寄语
                </div>
                <div class="layui-card-body layui-text layadmin-text">
                    <p>但经历整个人世冷暖，彼此命运交叠成无数曲折，它们说，或许生的序幕由他人打开，但舞台上的热泪盈眶却必须由自己奏响。要永远炙烈燃放如同烟火，即便幻灭而逝，也不要放弃能璀璨永存的那一刻。</p>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row layui-col-space15">
        <div class="layui-card">
            <div class="layui-card-header">
                萌宠图片
                <a href="/html/picture" style="float: right;color: #01AAED">查看更多</a>
            </div>
            <div class="layui-card-body layui-text" id="img_ul">
            </div>
        </div>
    </div>
    <img alt="" style="display:none" id="displayimg" src=""/>

</div>
<script src="../static/layui.js"></script>
<script src="../static/js/home.js"></script>


</body>
</html>