<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户管理</title>
    <link rel="stylesheet" href="../static/css/layui.css">
</head>
<body>

<form class="layui-form" action="" lay-filter="search_form">
    <br>
    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="name" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">年龄:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="age_min" placeholder="0" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="age_max" placeholder="100" autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-block" style="width: 100px;">
                <select name="sex" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-btn-container">
                <button class="layui-btn" lay-event="search" lay-submit lay-filter="search_btn" >查询</button>
                <button class="layui-btn" lay-event="add" lay-submit lay-filter="add_btn" > <i class="layui-icon">&#xe608;</i> 添加</button>
            </div>
        </div>
    </div>
</form>
<form class="layui-form" action="" id="test2" style="display: none;" lay-filter="add_form">
    </br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="id" autocomplete="off" class="layui-input" value="0" style="display: none">
                <input type="text" name="name" autocomplete="off" class="layui-input" lay-verify="username">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-block" style="width: 200px;">
                <select name="sex" style="width: 30px">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">年龄:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="age" autocomplete="off" class="layui-input" lay-verify="required|number">
            </div>
        </div>
    </div>

</form>
<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>

<script src="../static/layui.js"></script>
<script src="../static/js/user_table.js"></script>
<script type="text/html" id="sexTpl">
    {{#  if(d.sex == 0){ }}
    男
    {{#  } else{ }}
    女
    {{#  }  }}
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

</script>

<script type="text/html" id="toolbarDemo">

</script>
</body>
</html>