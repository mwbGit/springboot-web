layui.use(['carousel', 'layer', 'util'], function () {
    var $ = layui.jquery, util = layui.util;
    var carousel = layui.carousel;

    //建造实例
    var ins = carousel.render({
        elem: '#test1'
        , autoplay: 'true'
        , interval: '5000'
        , width: '100%' //设置容器宽度
        , arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });

    //最近动态
    $.get('/dynamic/new', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<dd> <div>' +
                    '<p>' + val.userName + ' 发布新动态 <a href="/user/info?dynamicId='+val.id+'"> 【' + val.title + '】</a></p>' +
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
                str += '<div><img src="' + val.image + '" style="width: 100%"></div>';
            });
            $('#banner_ul').html(str);
            ins.reload();
        }
    });

    //轮播
    $.get('/pet/picture/hot', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<div class="layui-input-inline cmdlist-container">' +
                    '<img src="' + val.image + '" alt="' + val.title + '"' +
                    'title="' + val.title + '"></div>';
            });
            $('#img_ul').html(str);
        }
    });

    //品种
    $.get('/pet/search?order=level&pageSize=8', function (data) {
        if (data.code == 0) {
            var str = '';
            $.each(data.data, function (i, val) {
                str += '<div class="layui-col-md3">' +
                    '<a href="/pet/detail?id=' + val.id + '" target="_blank"><img src="' + val.image + '" class="layui-circle" style="width: 80px;height: 80px"></a>' +
                    '<div style="text-align:center">' +
                    '<span class="inf">' + val.name + '</span>' +
                    '</div></div>';
            });
            str += '<div class="layui-col-md12"> <a href="/html/pet">查看更多</a></div>';
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
                    ' <h2><a href="/article/detail?id=' + val.id + '"> ' + val.title + '</a></h2>' +
                    '</div>' +
                    '<div class="layui-col-md12" style="text-overflow: -o-ellipsis-lastline;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 5;-webkit-box-orient: vertical;">' +
                    '<span>' + val.body + '</span>' +
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
            str += '<div class="layui-col-md12"> <a href="/html/article_list">查看更多</a></div>';
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