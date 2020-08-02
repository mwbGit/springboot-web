<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>开始使用layui</title>
    <link rel="stylesheet" href="../static/css/layui.css">
</head>
<body>

<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>

<script src="../static/layui.js"></script>
<script>
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
                , {field: 'name', title: '名称', width: 150}
                , {field: 'name', title: '菜单路径', edit: 'text'}
                , {
                    field: 'name',
                    title: '菜单权限',
                    templet: '<div><a href="/detail/{{d.id}}" class="layui-table-link">{{d.name}}哈哈哈</a></div>'
                }
                , {field: 'addTime', title: '时间'}
                , {field: 'updateTime', title: '事件'}
                , {field: 'name', title: '排序', templet: '#titleTpl'}
                , {fixed: 'right', title: '操作', width: 180, align: 'center', toolbar: "#barDemo"}
            ]]
            , loading: true
            , page: true   //开启分页
            , limit: 2   //默认十条数据一页
            , limits: [2, 20, 30, 50]  //数据分页条
            , id: 'testReload'
            // , toolbar : 'default'
            , toolbar : '#toolbarDemo'
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
            }, done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                console.log(count);
            }, defaultToolbar: ['filter', 'exports', {
                title: '提示' //标题
                , layEvent: 'LAYTABLE_TIPS' //事件名，用于 toolbar 事件中使用
                , icon: 'layui-icon-tips' //图标类名
            }]
        });
        // 行监听工具条
        table.on('tool(table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'detail') { //查看
                //do somehing
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
                //do something

                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    , title: 'xxx'
                });
            } else if (layEvent === 'LAYTABLE_TIPS') {
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

        // 工具栏时间监听
        table.on('toolbar(table)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    break;
                case 'delete':
                    layer.msg('删除');
                    break;
                case 'update':
                    layer.msg('编辑');
                    break;
                case 'LAYTABLE_TIPS':
                    layer.msg('LAYTABLE_TIPS');
                    table.reload('testReload', {
                        where: { //设定异步数据接口的额外参数，任意设
                            aaaaaa: 'xxx'
                            ,bbb: 'yyy'
                            //…
                        }
                        ,page: {
                            // curr: 1 //重新从第 1 页开始
                        }
                    }); //只重载数据
                    break;
            };
        });
    });
</script>
<script type="text/html" id="titleTpl">
    <a href="/detail/{{d.id}}" class="layui-table-link">{{d.name}}</a>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.id > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
    </div>
</script>
</body>
</html>