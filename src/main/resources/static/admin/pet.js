layui.use(['form', 'layer', 'table', 'upload', 'util'], function () {
    var table = layui.table, util = layui.util
        , form = layui.form, $ = layui.$, upload = layui.upload;

    // 表格
    table.render({
        elem: '#test'  //绑定table id
        , url: '/pet/search'  //数据请求路径
        , cellMinWidth: 50
        , cols: [[
            {type: 'space', hide: true}
            , {field: 'id', title: 'ID', width: 50}
            , {
                field: 'image',
                title: '头像',
                width: 100,
                templet: '#sexTpl'
            }
            , {
                field: 'name',
                title: '名称',
                width: 200,
                templet: '<div><a href="/pet/{{ d.id }}.html" >{{ d.name }}</div>'
            }
            , {field: 'level', title: '优先级', width: 80}
            , {field: 'score', title: '星级', width: 80}
            , {field: 'trait', title: '特点', width: 100}
            , {field: 'advantage', title: '优点', width: 100}
            , {field: 'defect', title: '缺点', width: 100}
            , {field: 'price', title: '价格', width: 120}
            , {field: 'introduce', title: '介绍'}
            , {
                field: 'addTime',
                title: '添加时间',
                width: 120,
                templet: '<div>{{ layui.util.toDateString(d.addTime, "yyyy-MM-dd") }}</div>'
            }
            , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: "#barDemo"}
        ]]
        , loading: true
        , page: true   //开启分页
        , limit: 10   //默认十条数据一页
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
        if (layEvent === 'delete') { //查看
            layer.confirm('确定删除"' + data.name + '"?', function (index) {
                layer.close(index);
                ajaxGet('/pet/delete?id=' + data.id);
            });
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

    // 添加
    form.on('submit(add_btn)', function (formData) {
        addOrUpdate();
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
    function addOrUpdate() {
        var formData = form.val("add_form");
        var checkboxValue = "";
        $("input:checkbox[name='types']:checked").each(function () {
            if (checkboxValue == "") {
                checkboxValue = $(this).val();
            } else {
                checkboxValue += ',' + $(this).val();
            }
        });
        formData.types = checkboxValue;

        $.ajax({
            url: "/pet/saveOrUpdate",
            data: formData,
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    layer.msg(data.msg);
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

    //大图查看
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


    //文件-发布动态
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
});
