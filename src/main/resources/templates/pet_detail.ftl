<!DOCTYPE html>
<!--[if lt IE 7 ]>
<html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7 ]>
<html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8 ]>
<html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9 ]>
<html class="ie9" lang="zh-cn"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html class="" lang="zh-cn"><!--<![endif]-->
<html>
<head>
    <title>${pet.name}-猫咪之家</title>
    <#assign keyword = ",${pet.name},${pet.name}图片,${pet.name}价格,${pet.name}介绍">
    <#include "include/css.ftl">
</head>
<style type="text/css">
    .cmdlist-container img {
        max-width: 200px;
        max-height: 200px;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "include/header.ftl">
    <div class="layui-body" style="left:0; margin: 0 50px;">
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
                                        <img src="${pet.waterImage}"
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
                                    <h3>祖籍：<span>${pet.nation}</span></h3>
                                </div>
                                <div class="layui-col-md12" style="border: 1px solid #e7ecf3">
                                    <h3>寿命：<span>${pet.life}</span></h3>
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
                                                <img src="${tag}" lay-data="${tag}" lay-active="e1" style="max-height: 200px; height: 100%">
                                            </div>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                        </#if>

                    </div>
                </div>
                <div class="layui-col-md1">
                </div>
                <img alt="" style="display:none; max-width: 80%" id="displayimg" src="" />
            </div>
        </div>
    </div>
    <#include "include/footer.ftl">
</div>
<#include "include/js.ftl">

<script>
    layui.use(['layer', 'util', 'carousel', 'rate'], function () {
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