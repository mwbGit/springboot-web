<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>用户管理</title>
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
                            <div class="layui-carousel" id="test1">
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
                    网站公告
                    <i class="layui-icon layui-icon-tips" lay-tips="要支持的噢" lay-offset="5"></i>
                </div>
                <div class="layui-card-body layui-text layadmin-text">
                    <p>一直以来，layui 秉承无偿开源的初心，虔诚致力于服务各层次前后端 Web
                        开发者，在商业横飞的当今时代，这一信念从未动摇。即便身单力薄，仍然重拾决心，埋头造轮，以尽可能地填补产品本身的缺口。</p>
                    <p>在过去的一段的时间，我一直在寻求持久之道，已维持你眼前所见的一切。而 layuiAdmin 是我们尝试解决的手段之一。我相信真正有爱于 layui 生态的你，定然不会错过这一拥抱吧。</p>
                    <p>子曰：君子不用防，小人防不住。请务必通过官网正规渠道，获得 <a href="http://www.layui.com/admin/"
                                                        target="_blank">layuiAdmin</a>！</p>
                    <p>—— 贤心（<a href="http://www.layui.com/" target="_blank">layui.com</a>）</p>
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
</div>
<script src="../static/layui.js"></script>
<script src="../static/js/home.js"></script>


</body>
</html>