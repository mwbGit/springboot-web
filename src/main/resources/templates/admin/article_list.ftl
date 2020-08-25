<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>文章管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css"  media="all">
</head>
<style type="text/css">
    .layui-form-label {
        padding: 9px 10px;
        width: 60px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<fieldset class="layui-elem-field layui-field-title">
    <legend>文章管理</legend>
</fieldset>
<form class="layui-form" action="" lay-filter="search_form">
    <br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">文章ID:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="id" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">宠物ID:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="petId" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">标题:</label>
            <div class="layui-input-inline" style="width: 400px;">
                <input type="text" name="title" autocomplete="off" class="layui-input">
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
            <label class="layui-form-label">动态ID:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="dynamicId" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
            <label class="layui-form-label" style="width: 100px">评论的用户:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <select name="userType" style="width: 30px">
                    <option value="0">当前用户</option>
                    <option value="1">用户A</option>
                    <option value="2">用户B</option>
                    <option value="3">用户C</option>
                    <option value="4">用户D</option>
                    <option value="5">用户E</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">内容:</label>
            <div class="layui-input-inline" style="width: 500px;">
                <textarea type="text" name="content" rows="5" class="layui-textarea" lay-verify="required"></textarea>
            </div>
        </div>
    </div>
</form>
<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>

<script src="../../static/layui.js"></script>
<script src="../../static/admin/article.js"></script>
<script type="text/html" id="sexTpl">
    {{#  if(d.image != ""){ }}
     <div> <img src="{{ d.image }}" style="width: 100%" lay-active="e1" lay-data="{{ d.image }}"></div>
    {{#  }  }}
</script>

<script type="text/html" id="sexTp1">
    {{#  if(d.oneImageUrl != ""){ }}
    <div> <img src="{{ d.oneImageUrl }}" style="width: 100%" lay-active="e1" lay-data="{{ d.oneImageUrl }}"></div>
    {{#  }  }}
</script>

<script type="text/html" id="sexTp3">
    {{#  if(d.status == 2){ }}
    <span style="color: red">驳回</span>
    {{#  } else if(d.status == 1) { }}
    <span style="color: green">通过</span>
    {{#  } else { }}
    <span style="">未审核</span>
    {{#  }  }}
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" href="/article/detail?id={{ d.id }}" target="_blank">查看</a>
    <a class="layui-btn layui-btn-xs" href="/article/info?id={{ d.id }}" >编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="praise">+赞</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
</script>

<script type="text/html" id="toolbarDemo">

</script>
</body>
</html>