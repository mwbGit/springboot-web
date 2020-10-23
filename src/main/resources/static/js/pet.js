layui.use(['layer', 'jquery', 'util'], function () {
    var $ = layui.jquery;
    var util = layui.util;


    reload(0);

    $.get('/pet/characters', function (data) {
        if (data.code == 0) {
            var str = '<a class="petType"  href="javascript:;" lay-active="e1" lay-id="0">全部</a>';
            $.each(data.data, function (i, val) {
                str += '<a class="petType"  href="javascript:;" lay-active="e1" lay-id="' + val.id + '">' + val.name + '</a>';
            });
            $('#characters').html(str);
        }
    });

    util.event('lay-active', {
        e1: function () {
            var typeId = this.getAttribute("lay-id");
            reload(typeId);
            $(".petType").attr("style", "");
            this.setAttribute("style", "color: blue");
        }
    });

    function reload(type) {
        $.ajax({
            url: '/pet/hot/search?pageSize=50',
            data: {
                characterId: type
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.code == 0) {
                    var str = '';
                    $.each(data.data, function (i, val) {
                        str += '<div class="layui-col-md4 layui-col-sm4">' +
                            '<a href="/pet/' + val.id + '.html" ><img src="' + val.image + '" style="max-width: 250px;height: 190px">' +
                            '<div class="cmdlist-text" style="text-align:center">' +
                            ' <span class="inf">' + val.name + '</span><' +
                            '/div></a>' +
                            '</div>';
                    });
                    if (str == '') {
                        str = '无数据';
                    }
                    $('#t_body').html(str);
                }
            }
        });
    }

});
