<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>宠物管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
</head>
<style type="text/css">
    .layui-form-label {
        padding: 9px 10px;
        width: 50px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<fieldset class="layui-elem-field layui-field-title">
    <legend>宠物管理</legend>
</fieldset>
<form class="layui-form" action="" lay-filter="search_form">
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">名称:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="name" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">优先级:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="level" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">星级:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="score" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="0">0星</option>
                    <option value="1">1星</option>
                    <option value="2">2星</option>
                    <option value="3">3星</option>
                    <option value="4">4星</option>
                    <option value="5">5星</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">排序:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="order" style="width: 30px">
                    <option value="id">时间</option>
                    <option value="level">优先级</option>
                    <option value="score">星级</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-btn-container">
                <button class="layui-btn" lay-event="search" lay-submit lay-filter="search_btn">查询</button>
                <a href="/pet/info" class="layui-btn"> 添加 </a>
            </div>
        </div>
    </div>
</form>
<form class="layui-form" action="" id="test2" style="display: none;" lay-filter="add_form">
    </br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">名称:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="name" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
            <label class="layui-form-label">价格:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="price" value="100-1000元" placeholder="100-1000元" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">优先级:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="level" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                </select>
            </div>
            <label class="layui-form-label">星级:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="score" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="0">0星</option>
                    <option value="1">1星</option>
                    <option value="2">2星</option>
                    <option value="3">3星</option>
                    <option value="4">4星</option>
                    <option value="5">5星</option>
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">特点:</label>
            <div class="layui-input-inline" style="width: 400px;">
                <input type="text" name="trait" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">优点:</label>
            <div class="layui-input-inline" style="width: 400px;">
                <input type="text" name="advantage" placeholder=",英文分割" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">缺点:</label>
            <div class="layui-input-inline" style="width: 400px;">
                <input type="text" name="defect" placeholder=",英文分割" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">介绍:</label>
            <div class="layui-input-inline" style="width: 400px;">
                <textarea name="introduce" rows="3" class="layui-input"></textarea>
            </div>
        </div>
    </div>

</form>

<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>


<script src="../../static/layui.js"></script>
<script src="../../static/admin/pet.js"></script>
<script type="text/html" id="sexTpl">
    {{#  if(d.headImg != ""){ }}
    <div><img src="{{ d.image }}" style="width: 100%" lay-active="e1" lay-data="{{ d.image }}"></div>
    {{#  }  }}
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" href="/pet/info?id={{ d.id }}" target="_blank">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>

</script>

</body>
</html>