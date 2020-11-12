<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>轮播管理</title>
    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/css/layui.css" media="all">
</head>
<style type="text/css">
    .layui-form-label {
        padding: 9px 10px;
        width: 50px;
    }
</style>
<body style="margin: 10px 60px 15px 60px;">
<fieldset class="layui-elem-field layui-field-title">
    <legend>轮播管理</legend>
</fieldset>
<div class="layui-inline">
    <a class="layui-btn" lay-active="e2" href="javascript:;"> 添加 </a>
</div>
<form class="layui-form" action="" id="test2" style="display: none;" lay-filter="add_form">
    </br>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">标题:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="title" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
            <label class="layui-form-label">优先级:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <select name="level" style="width: 30px">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">类型:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="type" style="width: 30px">
                    <option value="0">图片</option>
                    <option value="1">文章</option>
                    <option value="2">品种</option>
                    <option value="3">动态</option>
                    <#--                    <option value="3">动态</option>-->
                </select>
            </div>
            <label class="layui-form-label">关联Id:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="objectId" autocomplete="off" class="layui-input" value="0">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">链接</label>
            <div class="layui-input-inline"  style="width: 380px;">
                <input type="text" id="url" name="url" autocomplete="off" class="layui-input" placeholder="选填">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline" style="text-align: center">
                <label class="layui-form-label">图片:</label>
                <img id="demo1" src="" style="width: 100px;margin: 0 2px 2px 0">
                <input type="hidden" value="0" id="id"/>
                <input type="hidden" value="" id="image" name="image"/>
            </div>
        </div>
    </div>

    <div class="layui-form-item" style="float: bottom">
        <div class="layui-inline">
            <label class="layui-form-label"></label>
            <div class="layui-input-inline" style="">
                <button type="button" class="layui-btn" id="test11">上传图片</button>
            </div>
        </div>
    </div>

</form>
<!-- 你的HTML代码 -->
<table class="layui-hide" id="test" lay-filter="table"></table>
<img alt="" style="display:none; width: 600px;" id="displayimg" src="" />

<script src="../../static/layui.js"></script>
<script src="../../static/admin/banner.js"></script>

<script type="text/html" id="sexTp2">
    {{#  if(d.status == 1){ }}
    <span style="color: #00FF00">上线</span>
    {{#  } else{ }}
    <span style="color: red">下线</span>
    {{#  }  }}
</script>

<script type="text/html" id="sexTpl">
    {{#  if(d.image != ""){ }}
    <div><img src="{{ d.image }}" style="width: 100%" lay-active="e1" lay-data="{{ d.image }}"></div>
    {{#  }  }}
</script>

<script type="text/html" id="sexTp3">
    {{#  if(d.type == 1){ }}
    <a target="_blank" href="/article/{{ d.objectId }}.html">{{ d.title }}</a>
    {{#  } else if(d.type == 2) { }}
    <a target="_blank" href="/pet/{{ d.objectId }}.html">{{ d.title }}</a>
    {{#  } else { }}
    {{ d.title }}
    {{#  }  }}
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn  layui-btn-xs" lay-event="edit">修改</a>
    {{#  if(d.status == 0){ }}
    <a class="layui-btn layui-btn-xs" lay-event="online">上线</a>
    {{#  } else{ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="offline">下线</a>
    {{#  }  }}
</script>


<script type="text/html" id="toolbarDemo">

</script>
</body>
</html>