layui.use(['form', 'upload', 'layer', 'util', 'flow', 'element'], function () {
    var $ = layui.jquery, util = layui.util, element = layui.element,
        flow = layui.flow, layer = layui.layer, upload = layui.upload, form = layui.form;
    var comId = 1;
    reloadMsg();

    flow.load({
        elem: '#t_body' //指定列表容器
        ,scrollElem: '#scrollElem' //滚动条所在元素，一般不用填，此处只是演示需要。
        , done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get('/dynamic/search?pageSize=10&page=' + page, function (res) {
                //假设你的列表返回在data集合中
                layui.each(res.data, function (index, val) {
                    var str = getListHtml(val);

                    lis.push(str);
                });
                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < res.pages);
            });
        }
    });

    //文件-发布动态
    upload.render({
        elem: '#test11'
        , url: '/dynamic/save'
        , accept: 'images'
        , acceptMime: 'image/*'
        , size: 10240
        // , number: 4
        // , multiple: true
        , drag: true
        , bindAction: '#formDemo'
        , auto: false
        , data: {
            "content": function () {
                return form.val("form1").content;
            },
            "title": function () {
                return form.val("form1").title;
            }
        }
        , choose: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').html('<img src="' + result + '" alt="' + file.name + '" style="width: 100px;height: 100px;margin: 0 2px 2px 0">')
            });
        }
        , done: function (res, index, upload) { //上传后的回调
            if (res.code == 0) {
                layer.msg('发布成功');
                $('#dynamicA').text('发布动态');
                $('#form1')[0].reset();
                $('#form1').hide();
            } else {
                layer.msg(res.msg, {icon: 2});
            }
        }, error: function (index, upload) {
            layer.msg('发布失败', {icon: 2});
        }
    });


    //发布动态
    form.on('submit(save)', function (data) {
        var a = $('#demo1').html().trim();
        if (a == '') {
            $.ajax({
                url: '/dynamic/save',
                data: data.field,
                dataType: 'json',
                type: 'post',
                success: function (res) {
                    if (res.code == 0) {
                        layer.msg('发布成功');
                        $('#dynamicA').text('发布动态');
                        $('#form1')[0].reset();
                        $('#form1').hide();
                    } else {
                        layer.msg(res.msg, {icon: 2});
                    }
                }
            })
        }
        return false;
    });

    // 事件监听
    util.event('lay-active', {
        e1: function () {
            var show = this.getAttribute("lay-data");
            var curComId = this.getAttribute("lay-id");
            if (show == "true") {
                $(this).text("收起");
                this.setAttribute("lay-data", "false");
                $("#" + curComId).show();
            } else {
                $(this).text("更多");
                $("#" + curComId).hide();
                this.setAttribute("lay-data", "true");
            }
        }
        , e2: function () {
            var id = this.getAttribute("lay-id");
            layer.open({
                title: '评论规则(审核通过后展示)'
                , btn: ['评论']
                , area: ['500px', '300px']
                , content: '<textarea  placeholder="请输入内容（最多100个字）"style="width: 100%;height: 100%"></textarea>' //这里content是一个普通的String
                , yes: function (index, layero) {
                    //按钮【按钮一】的回调
                    var comment = '';
                    $(layero).find("textarea").each(function (i, v) {
                        comment = $(v).val().trim();
                    });
                    layer.close(index);

                    if (comment != null && comment != '') {
                        saveComment(id, comment);
                    }
                }
                , cancel: function () {
                    //右上角关闭回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
            // alert('触发了事件2');
        }
        , e3: function () {
            var id = this.getAttribute("lay-id");
            var data = this.getAttribute("lay-data");
            if (data == 'true') {
                this.setAttribute("lay-data", "false");
                $(this).html('<i class="layui-icon layui-icon-heart"></i>');
            } else {
                this.setAttribute("lay-data", "true");
                $(this).html('<i class="layui-icon layui-icon-heart-fill"></i>');
            }
            savePraise(id);
        }, e4: function () {
            var data = this.getAttribute("lay-data");
            if (data == 'true') {
                this.setAttribute("lay-data", "false");
                $(this).text('发布动态');
                $('#form1').hide();
            } else {
                this.setAttribute("lay-data", "true");
                $(this).text('取消发布');
                $('#form1').show();
            }
        } , e5: function () {
            var data = this.getAttribute("lay-data");
            if (data == 'true') {
                this.setAttribute("lay-data", "false");
                $(this).text('发布动态');
                $('#form1').hide();
            } else {
                this.setAttribute("lay-data", "true");
                $(this).text('取消发布');
                $('#form1').show();
            }
        }
    });


    function getCommentHtml(commentList) {
        var str = '';
        comId++;
        if (commentList != null) {
            str += '<div class="media-list">';
            layui.each(commentList, function (index, val) {
                str += '' +
                    '<div class="media-item" style="margin-left: 10px">' +
                    '<a href="javascript:;"><img class="img-xs" style="display:block; float: left" src="' + val.headImg + '"></a>' +
                    '<div class="media-text">' +
                    '<div><a href="javascript:;">' + val.userName + '</a><mdall> ' + val.timeDesc + '</mdall></div>' +
                    '<div>' + val.content + '</div>' +
                    '</div>' +
                    '</div>';
                if (index == 1) {
                    str += '<div style="display: none" id="' + comId + '">';
                } else if (commentList.length - 1 == index) {
                    str += '</div>';
                }
            });
            if (commentList.length > 3) {
                str += '<a href="javascript:;" class="layui-btn" lay-active="e1" lay-id="' + comId + '" lay-data="true">更多</a>';
            }
            str += '</div>';
        }
        return str;
    }

    function getImageHtml(images) {
        var str = '';
        layui.each(images, function (index, val) {
            str += '<img src="' + val + '" style="width: 100%">';
        });
        return str;
    }

    function getListHtml(data) {
        var str = '<div class="panel-body layadmin-homepage-shadow">' +
            '    <a href="javascript:;" class="media-left">' +
            '        <img src="' + data.headImg + '"' +
            '             height="46px" width="46px">' +
            '    </a>' +
            '    <div class="media-body">' +
            '        <div class="pad-btm">' +
            '            <p class="fontColor"><a href="/user/'+data.userId+'.html">' + data.userName + '</a> &nbsp 发布动态 &nbsp<a href="/user/'+data.userId+'.html?dynamicId='+data.id+'"><span>' + data.title + '</span></a>' +
            '            </p>' +
            '            <p class="min-font">' +
            '               <span class="layui-breadcrumb" lay-separator="-" style="visibility: visible;">' +
            '                <a href="javascript:;">' + data.timeDesc + '</a>' +
            '               </span>' +
            '            </p>' +
            '        </div>' +
            '        <p style="font-size: 18px;">' + data.content + '</p><div align="center">' + getImageHtml(data.imageUrls) + '</div>' +
            '        <div class="media">' +
            '            <div class="media-right">' +
            '                <ul class="list-inline">' +
            '                    <li>' +
            '                        <a href="javascript:;" lay-active="e3" lay-id="' + data.id + '" lay-data="' + data.praised + '">';
        if (data.praised) {
            str += ' <i class="layui-icon layui-icon-heart-fill"></i>';
        } else {
            str += ' <i class="layui-icon layui-icon-heart"></i>';
        }
        str += '                          </a>' +
            '                        <span>' + data.praiseNum + '</span>' +
            '                    </li>' +
            '                    <li>' +
            '                       <a href="javascript:;" lay-active="e2" lay-id="' + data.id + '"><i class="layui-icon layui-icon-reply-fill"></i></a>';
        if (data.commentNum > 0) {
            str += '                        <span>' + data.commentNum + '</span>';
        }
        str += '                    </li>' +
            '                </ul>' +
            '            </div>' +
            '        </div>' + getCommentHtml(data.comments) +
            '    </div>' +
            '</div>';
        return str;
    }

    function saveComment(id, comment) {
        $.ajax({
            url: '/dynamic/comment',
            data: {
                "dynamicId": id,
                "comment": comment
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('成功');
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            }
        });
    }

    function savePraise(id) {
        $.get('/dynamic/praise?dynamicId=' + id, function (data) {
        });
    }

    function reloadMsg() {

        $.get('/msg/new', function (data) {
            if (data.code == 0) {
                if (data.data.length == 0) {
                    $("#msg_list").hide();
                    return;
                }

                var str = '<div class="layui-collapse" id="">';
                $.each(data.data, function (i, val) {
                    str += '<div class="layui-colla-item">' +
                        '<h2 class="layui-colla-title">' + val.title + '</h2>';

                    if (i == 0) {
                        str += '<div class="layui-colla-content layui-show">';
                    } else {
                        str += '<div class="layui-colla-content">';
                    }
                    str += '<p>' + val.body + '</p></div></div>';
                });
                $('#msg_ul').html(str);
                element.render('collapse', 'msg_list');
            }
        });
    }
});
