layui.use(['form', 'layer', 'table', 'upload','util'], function () {
    var table = layui.table, util = layui.util
        , form = layui.form, $ = layui.$, upload =layui.upload;

    // 表格
    table.render({
        elem: '#test'  //绑定table id
        , url: '/banner/search'  //数据请求路径
        , cellMinWidth: 80
        , cols: [[
            {type: 'space', hide: true}
            , {field: 'id', title: 'ID', width: 80}
            , {field: 'typeDesc', title: '类型', width: 100}
            , {
                field: 'image',
                title: '图片',
                width: 100,
                templet: '#sexTpl'
            }
            , {field: 'title', title: '标题', templet: '#sexTp3'}
            , {field: 'level', title: '优先级', width: 100}
            , {field: 'objectId', title: '关联Id', width: 100}
            , {field: 'status', title: '状态', width: 100, templet: '#sexTp2'}
            , {
                field: 'addTime',
                title: '添加时间',
                width: 150,
                templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd") }}</div>'
            }
            , {fixed: 'right', title: '操作', width: 250, align: 'center', toolbar: "#barDemo"}
        ]]
        , loading: true
        , page: true   //开启分页
        , limit: 10   //默认十条数据一页
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
        if (layEvent === 'offline') { //查看
            ajaxGet('/banner/online?status=0&id=' + data.id);
        } else if (layEvent === 'online') { //查看
            ajaxGet('/banner/online?status=1&id=' + data.id);
        } if (layEvent === 'edit') {
            form.val("add_form", {
                "id": data.id,
                "title": data.title,
                "image": data.image,
                "type[option]": data.type,
                "objectId": data.objectId
            });
            $('#demo1').attr("src", data.image)
            layer.open({
                type: 1,
                title: '编辑轮播',
                area: ['500px', '450px'],
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
                        "title": '',
                        "image": '',
                        "objectId": '0'
                    });
                }
            });
        }
        reloadTable();
    });

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
        },
        e2: function () {
            layer.open({
                type: 1,
                title: '添加轮播',
                area: ['500px', '450px'],
                content: $('#test2'),
                btn: ['保存']
                , yes: function (index, layero) {
                    addOrUpdate(index);
                }
            });
            return false;
        }
    });

    //文件-上传
    upload.render({
        elem: '#test11'
        , url: '/upload/image'
        , accept: 'images'
        , acceptMime: 'image/*'
        , size: 10240
        , drag: true
        , choose: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr("src", result)
            });
        }
        , done: function (res, index, upload) { //上传后的回调
            if (res.code == 0) {
                $('#image').val(res.data);
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        }, error: function (index, upload) {
            layer.msg('失败', {icon: 2});
        }
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
        if (formData.title == '') {
            layer.msg('请填写标题', {icon: 2});
            return;
        }

        if (formData.image == '') {
            layer.msg('请上传图片', {icon: 2});
            return;
        }

        $.ajax({
            url: "/banner/save",
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

});
