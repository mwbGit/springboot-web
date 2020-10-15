layui.use(['form', 'layer', 'table', 'util'], function () {
    var table = layui.table, util = layui.util
        , form = layui.form, $ = layui.$;

    // 表格
    table.render({
        elem: '#test'  //绑定table id
        , url: '/article/search'  //数据请求路径
        , cellMinWidth: 80
        , cols: [[
            {type: 'space', hide: true}
            , {
                field: 'id', title: 'ID', width: 50,
                templet: '<div><a href="/dynamic/detail?id={{ d.id }}" target="_blank">{{ d.id }}</div>'
            }
            , {
                field: 'image',
                title: '封面',
                width: 100,
                templet: '#sexTpl'
            }
            , {field: 'author', title: '作者', width: 100}
            , {field: 'petId', title: '宠物ID', width: 80}
            , {
                field: 'title',
                title: '标题',
            }, {field: 'body', title: '内容'}
            , {
                field: 'addTime',
                title: '发布时间',
                width: 180,
                templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd HH:mm:ss") }}</div>'
            }
            , {field: 'viewNum', title: '观看数', width: 80}
            , {field: 'praiseNum', title: '点赞数', width: 80}
            , {fixed: 'right', title: '操作', width: 220, align: 'center', toolbar: "#barDemo"}
        ]]
        , loading: true
        , page: true   //开启分页
        , limit: 20   //默认十条数据一页
        , limits: [10, 20, 30, 50]  //数据分页条
        , id: 'userReload'
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
    });

    // 行监听工具条
    table.on('tool(table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if (layEvent === 'praise') {
            ajaxGet('/article/praise/add?id=' + data.id);
        } else if (layEvent === 'delete') {
            ajaxGet('/article/delete?id=' + data.id);
        }
        reloadTable();
    });

    // 查询
    form.on('submit(search_btn)', function (formData) {
        // layer.msg(JSON.stringify(formData.field))
        // var formData = form.val("search_form");
        reloadTable();
        return false;

    });

    //加载table
    function reloadTable() {
        var formData = form.val("search_form");
        table.reload('userReload', {
            where: formData
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    }

    function ajaxGet(url) {
        $.get(url, function (data) {
            if (data.code == 0) {
                layer.msg("成功");
            }
        });
    }

    //时间
    util.event('lay-active', {
        e1: function () {
            var url = this.getAttribute('lay-data');
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: true,
                content: "<img alt=" + name + " title=" + name + " src=" + url + " />"
            });
        }
    });
});
