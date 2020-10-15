<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>宠物信息修改</title>
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

<div class="layui-fluid">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>添加宠物</legend>
    </fieldset>
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md2">
        </div>
        <div class="layui-col-md7">
            <form class="layui-form" action="" id="test2" lay-filter="add_form">
                </br>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">名称:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <#if pet ! >
                                <input type="hidden" name="id"  value="${pet.id}">
                            <#else >
                                <input type="hidden" name="id"  value="0">
                            </#if>
                            <input type="text" name="name" autocomplete="off" class="layui-input" lay-verify="required" value="${pet.name}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">价格:</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" name="price" value="100-1000元" placeholder="100-1000元" autocomplete="off" lay-verify="required"
                                   class="layui-input" value="${pet.price}">
                        </div>
                        <label class="layui-form-label">优先级:</label>
                        <div class="layui-input-inline" style="width: 100px;">
                            <select name="level" style="width: 30px">
                                <#if pet ! >
                                    <option value="${pet.level}" selected>${pet.level}</option>
                                </#if>
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
                        <div class="layui-input-inline" style="width: 100px;">
                            <select name="score" style="width: 30px">
                                <#if pet ! >
                                    <option value="${pet.score}" selected>${pet.score}星</option>
                                </#if>
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
                        <label class="layui-form-label">祖籍:</label>
                        <div class="layui-input-inline" style="width: 190px;">
                            <input type="text" name="nation" autocomplete="off" class="layui-input" value="${pet.nation}">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">寿命:</label>
                        <div class="layui-input-inline" style="width: 200px;">
                            <input type="text" name="life" autocomplete="off" class="layui-input" value="${pet.life}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">特点:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <input type="text" name="trait" placeholder=",英文分割" autocomplete="off" class="layui-input" value="${pet.trait}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">优点:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <input type="text" name="advantage" placeholder=",英文分割" autocomplete="off" value="${pet.advantage}"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">缺点:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <input type="text" name="defect" placeholder=",英文分割" autocomplete="off" class="layui-input" value="${pet.defect}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">介绍:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <textarea name="introduce" rows="7" class="layui-input layui-textarea" lay-verify="required">${pet.introduce}</textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">养护知识:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <textarea name="careKnowledge" rows="7" class="layui-input layui-textarea"  >${pet.careKnowledge}</textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">特征:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <textarea name="feature" rows="7" class="layui-input layui-textarea"  >${pet.feature}</textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">喂养要点:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <textarea name="feedPoints" rows="7" class="layui-input layui-textarea" >${pet.feedPoints}</textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">性格特征:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <textarea name="characterFeature" rows="7" class="layui-input layui-textarea" >${pet.characterFeature}</textarea>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">性格:</label>
                        <div class="layui-input-inline" style="width: 480px;">
                            <#list types  as type>
                                <#if type.checked >
                                    <input type="checkbox" name="types" value="${type.id}" title="${type.name}" lay-skin="primary" checked>
                                <#else >
                                    <input type="checkbox" name="types" value="${type.id}" title="${type.name}" lay-skin="primary">
                                </#if>
                            </#list>
                        </div>
                    </div>
                </div>
                <div class="layui-inline1" style="text-align: center">
                    <div class="layui-btn-container">
                        <button type="button" class="layui-btn" id="test11">上传主图</button>
                        <button class="layui-btn" lay-event="search" lay-submit lay-filter="add_btn">保存</button>
                    </div>
                    <input type="hidden" value="" id="image" name ="image"/>
                </div>
            </form>
        </div>
        <div class="layui-col-md3">
            <div class="layui-upload-list">
                <img id="demo1" src="${pet.image}" style="width: 200px;margin: 0 2px 2px 0">
            </div>
        </div>
    </div>

</div>


<script src="../../static/layui.js"></script>
<script src="../../static/admin/pet.js"></script>

</body>
</html>