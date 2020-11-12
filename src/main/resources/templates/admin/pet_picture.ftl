<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>猫咪图片</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
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
<body style="margin: 10px 60px 15px 60px;">
<div class="layui-body" style="left:0; margin: 0 50px;" id="scrollElem">

    <div class="layui-row layui-col-space15">

        <div class="layui-col-md12">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>宠物图片</legend>
            </fieldset>
            <div class="layui-row layui-col-space15">

                <form class="layui-form" action="" id="test1" >
                    <div class="layui-form-item">
                        <label class="layui-form-label">搜索选择框</label>
                        <div class="layui-input-inline">
                            <select name="petId" lay-verify="required" lay-search="" lay-filter="selectF" id ="selectF">

                            </select>
                        </div>
                    </div>
                </form>
                <div id="tab-active">
                    <div id="t_body">
                    </div>
                </div>
            </div>
        </div>

    </div>
    <img alt="" style="display:none; width: 600px;" id="displayimg" src="" />
</div>
<script src="../../static/layui.js"></script>

<script>
    layui.use(['layer', 'util', 'flow', 'form'], function () {
        var $ = layui.jquery, util = layui.util;
        var flow = layui.flow;
        var form = layui.form;
        var petId = 0;
        reload();
        load();

        util.event('lay-active', {
            e1: function () {
                var url = this.getAttribute('lay-data');
                var id = this.getAttribute('lay-id');
                $("#displayimg").attr("src", url);
                var height = $("#displayimg").height();
                var width = $("#displayimg").width();
                layer.open({
                    type: 1,
                    title: false,
                    btn: ['删除', '取消'],
                    // closeBtn: 1,
                    shadeClose: true,
                    area: [width + 'px', height + 'px'], //宽高
                    content: "<img style='width: 600px;'  alt=" + name + " title=" + name + " src=" + url + " />"
                    , yes: function (index, layero) {
                        //按钮【按钮一】的回调
                        ajaxGet("/pet/picture/delete?id=" + id);
                        layer.close(index)
                    }
                    , btn2: function (index, layero) {
                        layer.close(index)
                    }
                });
            }
        });

        form.on('select(selectF)', function(data){
            petId = data.value;
            $("#t_body").remove();
            // $(document).unbind();
            $('#tab-active').append('<ul id="t_body"></ul>');
            load();
        });

        function load() {
            flow.load({
                elem: '#t_body' //指定列表容器
                ,scrollElem: '#scrollElem' //滚动条所在元素，一般不用填，此处只是演示需要。
                , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
                    var lis = [];
                    //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                    $.get('/pet/picture/search?pageSize=50&page=' + page + '&petId=' + petId, function (res) {
                        //假设你的列表返回在data集合中
                        layui.each(res.data, function (index, val) {
                            var str = '' +
                                '<div class="layui-input-inline">' +
                                '<div class="cmdlist-container">' +
                                '<a href="javascript:;" lay-active="e1" lay-data="' + val.waterImage + '" lay-id="' + val.id + '">' +
                                '<img  src="' + val.waterImage + '" alt="' + val.title + '"> ' +
                                '<div class="cmdlist-text">' +
                                // '<p class="info">' + val.title + '</p>' +
                                '</div>' +
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
        }

        function ajaxGet(url) {
            $.get(url, function (data) {
                if (data.code == 0) {
                    layer.msg("成功");
                }
            });
        }

        function reload() {
            $.get("/pet/search?paged=false", function (res) {
                var  str ='<option value="">直接选择或搜索选择</option>';
                if (res.code == 0) {
                    layui.each(res.data, function (index, val) {
                        str += '<option value="'+val.id+'">'+ val.name+'</option>';
                    });
                    $("#selectF").html(str);
                    form.render('select'); //刷新select选择框渲染
                }
            });
        }
    });
</script>

</body>
</html>