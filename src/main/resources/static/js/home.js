layui.use(['carousel', 'layer', 'util'], function () {
    var $ = layui.jquery, util = layui.util;
    var carousel = layui.carousel;

    //建造实例
    var ins = carousel.render({
        elem: '#test1'
        , autoplay: 'true'
        , interval: '5000'
        , width: '100%' //设置容器宽度
        , height: '350px' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
        ,trigger : 'click'
    });

    //最近动态
    $.get('/dynamic/new', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<dd> <div>' +
                    '<p>' + val.userName + ' 发布新动态 <a href="/user/'+val.userId +'.html?dynamicId='+val.id+'"> 【' + val.title + '】</a></p>' +
                    '<span>' + val.timeDesc + '</span>' +
                    '</div></dd>';
            });
            $('#msg_list').html(str);
        }
    });

    //轮播
    $.get('/banner/list', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<div><a target="_blank" href="' + val.jumpUrl + '"  ><img src="' + val.image + '" ' +
                    'style="width: 100%;box-sizing: border-box;position: absolute; left: 50%;top: 50%;transform: translate(-50%, -50%);" ></a></div>';
            });
            $('#banner_ul').html(str);
            ins.reload();
        }
    });


    //图片
    $.get('/pet/picture/hot', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<div class="layui-input-inline cmdlist-container">' +
                    '<a href="javascript:;" lay-active="e1" lay-data="' + val.image + '"> ' +

                    '<img src="' + val.image + '" alt="' + val.title + '"' +
                    ' title="' + val.title + '"></a></div>';
            });
            $('#img_ul').html(str);
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

    //品种
    $.get('/pet/hot/search?pageSize=8', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<div class="layui-col-md3">' +
                    '<a href="/pet/' + val.id + '.html" target="_blank"><img src="' + val.image + '" class="layui-circle" style="width: 80px;height: 80px"></a>' +
                    '<div style="text-align:center">' +
                    '<span class="inf">' + val.name + '</span>' +
                    '</div></div>';
            });
            str += '<div class="layui-col-md12"> <a href="/html/pet.html">查看更多</a></div>';
            $('#pet_ul').html(str);
        }
    });

    //文章
    $.get('/article/search?pageSize=3', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<li>' +
                    '<div class="layui-row layui-col-space5" >' +
                    '<div class="layui-col-md4" ><img src="' + val.image + '" style="max-width: 250px" ></div>' +
                    '<div class="layui-col-md8" >' +
                    '<div class="layui-row layui-col-space5" >' +
                    '<div class="layui-col-md12">' +
                    ' <h2><a href="/article/' + val.id + '.html"> ' + val.title + '</a></h2>' +
                    '</div>' +
                    '<div class="layui-col-md12" style="text-overflow: -o-ellipsis-lastline;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 5;-webkit-box-orient: vertical;">' +
                    '<span>' + val.bodyDesc + '</span>' +
                    '</div>' +
                    '<div class="layui-col-md3">' +
                    '<span><i class="layui-icon">&#xe6c6;</i>' + val.praiseNum + '</span>' +
                    '</div>' +
                    ' <div class="layui-col-md5 layui-col-md-offset4">' +
                    '<span>' + _util(val.addTime) + '</span>' +
                    '</div>' +
                    '</div></div><div>' +
                    '</li>';
            });
            str += '<div class="layui-col-md12"> <a href="/html/article_list.html">查看更多</a></div>';
            $('#article_ul').html(str);
        }
    });

    function _util(date) {
        if (date && date !== '' && date !== null) {
            return util.toDateString(date, 'yyyy-MM-dd HH:mm:ss')
        } else {
            return '';
        }
    }


});