<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>动态管理</title>
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
    <legend>动态管理</legend>
</fieldset>
<form class="layui-form" action="" lay-filter="search_form">
    <br>
    <div class="layui-form-item">
        <input type="hidden" name="showComment" value="false">
        <div class="layui-inline">
            <label class="layui-form-label">动态ID:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="id" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">用户ID:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="userId" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">标题:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="title" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">状态:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="status" style="width: 30px">
                    <option value="0">未审核</option>
                    <option value="1">审核通过</option>
                    <option value="2">审核驳回</option>
                    <option value="-1">全部</option>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <div class="layui-btn-container">
                <button class="layui-btn" lay-event="search" lay-submit lay-filter="search_btn">查询</button>
            </div>
        </div>
    </div>
</form>

<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>

<script src="../../static/layui.js"></script>
<script src="../../static/admin/dynamic.js"></script>
<script type="text/html" id="sexTpl">
    {{#  if(d.headImg != ""){ }}
     <div> <img src="{{ d.headImg }}" style="width: 100%" lay-active="e1" lay-data="{{ d.headImg }}"></div>
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
    <a class="layui-btn layui-btn-xs" href="/user/info?dynamicId={{ d.id }}" target="_blank">查看</a>
    {{#  if(d.status == 0){ }}
    <a class="layui-btn layui-btn-xs" lay-event="pass">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unPass">驳回</a>
    {{#  } else{ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="unPass">驳回</a>
    {{#  }  }}
</script>

<script type="text/html" id="toolbarDemo">

</script>
</body>
</html>