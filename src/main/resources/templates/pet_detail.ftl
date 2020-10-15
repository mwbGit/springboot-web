<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>${pet.name}</title>
    <link rel="shortcut icon" href="../static/favicon.ico">
    <link rel="stylesheet" href="../static/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/forum.css" media="all">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 200px;
        max-height: 200px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<div class="layui-fluid">
    <div class="layui-row layui-col-space20 layadmin-homepage-list-imgtxt" style="margin-top: 50px">
        <div class="layui-col-md1">
        </div>
        <div class="layui-col-md10">
            <div class="layui-row layui-col-space20">
                <div class="layui-col-md4 layui-col-sm6">
                    <div class="layui-col-md12">
                        <a href="javascript:;">
                            <div class="layadmin-text-center">
                                <img src="${pet.image}"
                                     style="width: 100%">
                            </div>
                        </a>
                    </div>


                </div>

                <div class="layui-col-md8 layadmin-padding-left20 layui-col-sm6">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                            <h2 style="font-weight: bold">${pet.name}</h2>
                        </div>

                        <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                            <h3>均价：<span style="color: brown">${pet.price}</span></h3>
                        </div>

                        <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                            <h3>评分：
                                <div id="test9"></div>
                            </h3>
                        </div>
                        <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                            <h3>祖籍：<span >${pet.nation}</span></h3>
                        </div>
                        <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                            <h3>寿命：<span >${pet.life}</span></h3>
                        </div>
                        <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                            <h3>特点：
                                <#list pet.traits as tag>
                                    <span class="layui-badge-rim" style="color: blue">${tag}</span>
                                </#list>
                            </h3>
                            <h3>缺点：
                                <#list pet.defects  as tag>
                                    <span class="layui-badge-rim" style="color: yellowgreen">${tag}</span>
                                </#list>
                            </h3>
                            <h3>易患病：
                                <#list pet.easyOfDiseases as tag>
                                    <span class="layui-badge-rim" style="color: red">${tag}</span>
                                </#list>
                            </h3>
                        </div>

                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">介绍:</div>
                        <div class="layui-card-body">
                            <span>${pet.introduce}</span>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">特征:</div>
                        <div class="layui-card-body">
                            <span>${pet.feature}</span>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">喂养要点:</div>
                        <div class="layui-card-body">
                            <span>${pet.feedPoints}</span>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">养护知识:</div>
                        <div class="layui-card-body">
                            <span>${pet.careKnowledge}</span>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">性格特征:</div>
                        <div class="layui-card-body">
                            <span>${pet.characterFeature}</span>
                        </div>
                    </div>
                </div>
                <#if pictures ! >
                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <div class="layui-card-header">图片:</div>
                            <div class="layui-card-body">
                                <#list pictures as tag>
                                    <div class="layui-input-inline cmdlist-container">
                                        <img src="${tag}" lay-data="${tag}" lay-active="e1">
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </#if>
                <div class="layui-col-md12" style="text-align: center; margin-top: 5px">
                    <h7>
                        <span ><a href="http://www.maomihome.com/" target="_blank">猫咪之家</a></span><br>
                        <span ><a href="http://www.maomihome.com/" style="color: #00C0F7" target="_blank">www.maomihome.com</a></span>
                    </h7>
                </div>
            </div>
        </div>
        <div class="layui-col-md1">
        </div>
        <img alt="" style="display:none" id="displayimg" src=""/>
    </div>
</div>
<script src="../static/layui.js"></script>

<script>
    layui.use(['layer', 'util', 'jquery', 'carousel', 'rate'], function () {
        var $ = layui.jquery, util = layui.util, carousel = layui.carousel;
        var rate = layui.rate;

        //建造实例
        var ins = carousel.render({
            elem: '#test1'
            , autoplay: 'true' //设置容器宽度
            , interval: '4000' //设置容器宽度
            , width: '100%' //设置容器宽度
            , arrow: 'always' //始终显示箭头
            , indicator: 'none' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

        //只读
        rate.render({
            elem: '#test9'
            , value: ${pet.score}
            , readonly: true
        });

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
    });
</script>

</body>
</html>