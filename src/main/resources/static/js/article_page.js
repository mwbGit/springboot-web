layui.use(['form', 'layer', 'table', 'jquery', 'laypage', 'util'], function () {
    var table = layui.table
        , form = layui.form, $ = layui.jquery, laypage = layui.laypage;
    var util = layui.util;

    reload(1, 1);
    reloadHot();

    function reloadHot() {
        $.ajax({
            url: '/article/hot',
            data: {},
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.code == 0) {
                    var str = '';
                    $.each(data.data, function (i, val) {
                        str += '<li><a>' + val.title + '</a> <span><i class="layui-icon">&#xe6c6;</i>' + val.praiseNum + '</span></li>';
                    });

                    $('#hot_ul').html(str);
                }
            }
        });
    }

    var limit = 10;

    var flow = layui.flow;
    flow.load({
        elem: '#t_body' //指定列表容器
        , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get('/article/search?page=' + page, function (res) {
                //假设你的列表返回在data集合中
                layui.each(res.data, function (index, val) {
                    var str = '<li>' +
                        '<div class="layui-row layui-col-space5" >' +
                        '<div class="layui-col-md4" ><img src="' + val.image + '" style="max-width: 250px" ></div>' +
                        '<div class="layui-col-md8" >' +
                        '<div class="layui-row layui-col-space5" >' +
                        '<div class="layui-col-md12">' +
                        ' <h2><a href=""> ' + val.title + '</a></h2>' +
                        '</div>' +
                        '<div class="layui-col-md12" style="text-overflow: -o-ellipsis-lastline;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 5;-webkit-box-orient: vertical;">' +
                        '<span>' + val.body + '</span>' +
                        '</div>' +
                        '<div class="layui-col-md3">' +
                        '<span><i class="layui-icon">&#xe6c6;</i>' + val.praiseNum + '</span>' +
                        '</div>' +
                        ' <div class="layui-col-md5 layui-col-md-offset4">' +
                        '<span>' + val.addTime + '</span>' +
                        '</div>' +
                        '</div></div><div>' +
                        '</li>';


                    lis.push(str);
                });
                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < 11);
            });
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
