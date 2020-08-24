layui.use(['form', 'layer', 'table', 'element', 'util'], function () {
    var table = layui.table
        , layer = layui.layer, $ = layui.$, element = layui.element, util = layui.util;

    // 表格
    table.render({
        elem: '#test'  //绑定table id
        , url: '/msg/list'  //数据请求路径
        , cellMinWidth: 80
        , where : {
            type: -1
        }
        , cols: [[
            {type: 'space', hide: true}
            , {
                field: 'typeDesc',
                title: '消息类型',
                width: '10%'
            }, {
                field: 'title',
                title: '标题',
                width: '20%',
                templet: '#statusTpl1'
            } , {field: 'body', title: '内容'}
            , {
                field: 'status',
                title: '状态',
                width: '10%',
                templet: '#statusTpl'
            }
            , {
                field: 'addTime',
                title: '时间',
                width: '15%',
                templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd HH:mm:ss") }}</div>'
            }
        ]]
        , loading: true
        , page: true   //开启分页
        , limit: 15   //默认十条数据一页
        , limits: [15, 20, 30, 50]  //数据分页条
        , id: 'userReload'
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        }, request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
    });

    element.on('tab(msg_tab)', function (data) {
        reloadTable(data.index)
    });

    util.event('lay-active', {
        e1: function () {
            $.get('/msg/readAll', function (data) {
                reloadTable(0);
            });
        }

    });

    //加载table
    function reloadTable(tab) {
        table.reload('userReload', {
            where: { //设定异步数据接口的额外参数，任意设
                type: tab-1
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    }
});
