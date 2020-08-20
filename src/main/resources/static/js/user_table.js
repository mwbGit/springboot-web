layui.use(['form', 'layer', 'table'], function () {
    var table = layui.table
        , form = layui.form, $ = layui.$;

    // 表格
    table.render({
        elem: '#test'  //绑定table id
        , url: '/user/search'  //数据请求路径
        , cellMinWidth: 80
        , cols: [[
            {type: 'space', hide: true}
            , {field: 'id', title: 'ID'}
            , {field: 'name', title: '名称'}
            , {
                field: 'age',
                title: '年龄',
                edit: 'text',
                templet: '<div>{{d.age}}岁</div>'
            }
            , {
                field: 'sex',
                title: '性别',
                templet: '#sexTpl'
            }
            , {
                field: 'addTime',
                title: '添加时间',
                templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd HH:mm:ss") }}</div>'
            }
            , {fixed: 'right', title: '操作', width: 250, align: 'center', toolbar: "#barDemo"}
        ]]
        , loading: true
        , page: true   //开启分页
        , limit: 5   //默认十条数据一页
        , limits: [5, 20, 30, 50]  //数据分页条
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

        if (layEvent === 'detail') { //查看

            //do somehing
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除用户"' + data.name + '"?', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                $.ajax({
                    url: "/user/delete?id=" + data.id,
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            layer.close(index);
                            layer.msg(data.msg);
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                });
            });
        } else if (layEvent === 'edit') { //编辑
            form.val("add_form", {
                "id": data.id,
                "name": data.name,
                "age": data.age,
                "sex": data.sex
            });

            layer.open({
                type: 1,
                title: '编辑用户',
                area: ['500px', '300px'],
                content: $('#test2'),
                btn: '添加'
                , yes: function (index, layero) {
                    // debugger
                    addOrUpdate(index);

                    //同步更新缓存对应的值
                    obj.update({
                        username: '123'
                        , title: 'xxx'
                    });
                }, end: function () {
                    form.val("add_form", {
                        "id": '0',
                        "age": '',
                        "name": ''
                    });
                }
            });
        } else if (layEvent === 'LAYTABLE_TIPS') {
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
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
            area: ['500px', '300px'],
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
            where: { //设定异步数据接口的额外参数，任意设
                name: formData.name
                , ageMin: formData.age_min
                , ageMax: formData.age_max
                , sex: formData.sex
                //…
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    }

    //添加或者保存
    function addOrUpdate(index) {
        var formData = form.val("add_form");
        if (formData.name == '') {
            layer.msg('名称不可以为空', {icon: 2});
            return;
        }
        if (formData.age == '') {
            layer.msg('年龄不可以为空', {icon: 2});
            return;
        }
        $.ajax({
            url: "/user/saveOrUpdate",
            data: {
                'id': formData.id,
                'name': formData.name,
                'age': formData.age,
                'sex': formData.sex
            },
            type: "Post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    layer.close(index);
                    layer.msg(data.msg);
                    reloadTable();
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            },
            error: function (data) {
                // layer.msg('你确定删除么1111？' + index);
            }
        });
    }
});
