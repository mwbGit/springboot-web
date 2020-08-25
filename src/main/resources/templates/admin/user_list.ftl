<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>用户管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
</head>
<style type="text/css">
    .layui-form-label {
        padding: 9px 10px;
        width: 40px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<fieldset class="layui-elem-field layui-field-title">
    <legend>用户管理</legend>
</fieldset>
<form class="layui-form" action="" lay-filter="search_form">
    <br>
    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">ID:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="id" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">账号:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="account" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">昵称:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="name" autocomplete="off" class="layui-input">
            </div>
        </div>

<#--        <div class="layui-inline">-->
<#--            <label class="layui-form-label">年龄:</label>-->
<#--            <div class="layui-input-inline" style="width: 100px;">-->
<#--                <input type="text" name="age_min" placeholder="0" autocomplete="off" class="layui-input">-->
<#--            </div>-->
<#--            <div class="layui-form-mid">-</div>-->
<#--            <div class="layui-input-inline" style="width: 100px;">-->
<#--                <input type="text" name="age_max" placeholder="100" autocomplete="off" class="layui-input">-->
<#--            </div>-->
<#--        </div>-->


        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="sex" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="status" style="width: 30px">
                    <option value="">请选择</option>
                    <option value="0">待审核</option>
                    <option value="1">审核通过</option>
                    <option value="2">审核驳回</option>
                    <option value="3">冻结</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-btn-container">
                <button class="layui-btn" lay-event="search" lay-submit lay-filter="search_btn">查询</button>
                <button class="layui-btn" lay-event="add" lay-submit lay-filter="add_btn"> 添加
                </button>
            </div>
        </div>
    </div>
</form>
<form class="layui-form" action="" id="test2" style="display: none;" lay-filter="add_form">
    </br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账号:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="account" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
            <label class="layui-form-label">密码:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="password" autocomplete="off" value="123456" class="layui-input" lay-verify="required">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">昵称:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="name" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="sex" style="width: 30px">
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">身份:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="identity" style="width: 30px">
                    <option value="0">用户</option>
                    <option value="1">管理员</option>
                </select>
            </div>
            <label class="layui-form-label">状态:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="status" style="width: 30px">
                    <option value="1">审核通过</option>
                    <option value="0">待审核</option>
                </select>
            </div>
        </div>
    </div>
</form>
<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>

<script src="../../static/layui.js"></script>
<script src="../../static/admin/user.js"></script>
<script type="text/html" id="sexTpl">
    {{#  if(d.headImg != ""){ }}
     <div> <img src="{{ d.headImg }}" style="width: 100%" lay-active="e1" lay-data="{{ d.headImg }}"></div>
    {{#  }  }}
</script>

<script type="text/html" id="sexTp3">
    {{#  if(d.status == 2){ }}
    <span style="color: red">驳回</span>
    {{#  } else if(d.status == 3) { }}
    <span style="color: #ff2a48">冻结</span>
    {{#  } else if(d.status == 1) { }}
    <span style="color: green">通过</span>
    {{#  } else { }}
    <span style="">未审核</span>
    {{#  }  }}
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="pass">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unPass">驳回</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="frozen">冻结</a>

</script>

<script type="text/html" id="toolbarDemo">

</script>
</body>
</html>