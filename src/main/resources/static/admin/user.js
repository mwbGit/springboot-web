layui.use(['form', 'layer', 'table', 'util'], function () {
    var table = layui.table, util= layui.util
        , form = layui.form, $ = layui.$, layer=layui.layer;

    // 表格
    table.render({
        elem: '#test'  //绑定table id
        , url: '/user/search'  //数据请求路径
        , cellMinWidth: 80
        , cols: [[
            {type: 'space', hide: true}
            , {field: 'id', title: 'ID',width : 50}
            , {
                field: 'headImg',
                title: '头像',
                width : 100,
                templet: '#sexTpl'
            }
            , {field: 'account', title: '账号',width : 100,
                templet: '<div><a href="/user/{{ d.id }}.html" target="_blank">{{ d.account }}</div>'
            }
            , {field: 'name', title: '昵称',width : 100}
            , {field: 'sexDesc', title: '性别',width : 80}
            , {field: 'introduce', title: '介绍'}
            , {
                field: 'addTime',
                title: '注册时间',
                width: 180,
                templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd HH:mm:ss") }}</div>'
            }
            , {field: 'statusDesc', title: '状态', width : 100, templet : "#sexTp3"}
            , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: "#barDemo"}
        ]]
        , loading: true
        , page: true   //开启分页
        , limit: 20   //默认十条数据一页
        , limits: [10, 20, 30, 50]  //数据分页条
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

    // 行监听工具条
    table.on('tool(table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if (layEvent === 'pass') { //查看
            ajaxGet('/user/audit?status=1&id=' + data.id);
            reloadTable();
        } else if (layEvent === 'unPass') { //查看
            layer.open({
                type: 1,
                title: '驳回原因',
                btn: ['介绍违规',"昵称违规", "头像违规"]
                , yes: function (index, layero) {
                    // debugger
                    ajaxGet('/user/audit?status=2&id=' + data.id + "&reason=介绍内容包含敏感信息，使用功能将被限制，请尽快修改个人信息。");
                    layer.close(index);
                    reloadTable();
                }, btn2: function (index, layero) {
                    ajaxGet('/user/audit?status=2&id=' + data.id + "&reason=昵称违规，使用功能将被限制，请尽快修改个人信息。");
                    reloadTable();
                }, btn3: function (index, layero) {
                    ajaxGet('/user/audit?status=2&id=' + data.id + "&reason=头像违规，使用功能将被限制，请尽快修改个人信息。");
                    reloadTable();
                }
            });
        } else if (layEvent === 'frozen') { //删除
            layer.confirm('确定冻结用户"' + data.name + '"?', function (index) {
                layer.close(index);
                ajaxGet('/user/audit?status=3&id=' + data.id + "&reason=账号违规，使用功能已被限制。");
            });
            reloadTable();
        }
    });

    // 查询
    form.on('submit(search_btn)', function (formData) {
        // layer.msg(JSON.stringify(formData.field))
        // var formData = form.val("search_form");
        reloadTable();
        return false;

    });

    // 添加
    form.on('submit(add_btn)', function (formData) {
        layer.open({
            type: 1,
            title: '添加用户',
            area: ['600px', '300px'],
            content: $('#test2'),
            btn: '添加'
            , yes: function (index, layero) {
                // debugger
                addOrUpdate(index);
            }
        });
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

    //添加或者保存
    function addOrUpdate(index) {
        var formData = form.val("add_form");
        if (formData.account == '') {
            layer.msg('账号不可以为空', {icon: 2});
            return;
        }
        if (formData.name == '') {
            layer.msg('昵称不可以为空', {icon: 2});
            return;
        }
        if (formData.password == '') {
            layer.msg('密码不可以为空', {icon: 2});
            return;
        }
        $.ajax({
            url: "/user/add",
            data: formData,
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    layer.close(index);
                    layer.msg(data.msg);
                    reloadTable();
                    $('#test2')[0].reset()
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            },
            error: function (data) {
                // layer.msg('你确定删除么1111？' + index);
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
