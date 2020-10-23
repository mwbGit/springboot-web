<div class="layui-header">
    <div class="layui-logo">猫咪之家</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left" lay-filter="test1">
        <li class="layui-nav-item"><a href="/html/index.html">主页</a></li>
        <li class="layui-nav-item"><a href="/html/pet.html">品种</a></li>
        <li class="layui-nav-item"><a href="/html/article_list.html">文章</a></li>
        <li class="layui-nav-item"><a href="/html/picture.html">萌图</a></li>
        <li class="layui-nav-item"><a href="/html/forum.html">动态</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right" lay-filter="test1">
        <#if avatar!>
            <li class="layui-nav-item" lay-unselect="">
                <#if admin == true >
                    <a href="/admin/index.html" target="_blank">
                        后台管理
                    </a>
                </#if>
            </li>
            <li class="layui-nav-item" lay-unselect="">
                <a href="/html/msg.html" lay-text="消息中心">
                    <i class="layui-icon layui-icon-notice"></i>
                    <#if unRead == true>
                        <span class="layui-badge-dot"></span>
                    <#else>
                    </#if>
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${avatar}" class="layui-nav-img">
                    ${userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/html/user_update.html">修改信息</a></dd>
                    <dd><a href="/html/pwd_update.html">修改密码</a></dd>
                    <dd><a href="/login/out">退出登录</a></dd>
                    <dd><a href="/html/feedback.html">留言反馈</a></dd>
                </dl>
            </li>
        <#else>
            <li class="layui-nav-item">
                <a href="/html/login.html">登录</a>
            </li>
        </#if>
    </ul>
</div>
