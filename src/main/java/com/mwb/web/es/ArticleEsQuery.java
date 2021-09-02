package com.mwb.web.es;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2021/3/5 10:52 上午
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEsQuery {

    public static final String SORT_KEY_ADD_TIME = "addTime";

    public static final String SORT_KEY_UPDATE_TIME = "updateTime";

    public static final String GROUP_KEY_ADD_TIME_DATE8 = "addTimeDate8";

    public static final String GROUP_KEY_CHANNEL_ID = "channelId";

    public static final String GROUP_KEY_LEVEL = "level";

    public static final String GROUP_KEY_PARENT_TYPE_ID = "parentTypeId";

    public static final String GROUP_KEY_TYPE_ID = "typeId";

    public static final String GROUP_KEY_LABEL_ID = "labelIds";

    public static final String GROUP_KEY_STATUS = "status";

    private List<Integer> status;//状态

    private List<Long> labelIds;//标签id

    private long parentTypeId;//一级分类id

    private long typeId;//二级分类id

    private Date addBeginTime;//起始添加时间

    private Date addEndTime;//结束添加时间

    private long channelId;//渠道id

    private String groupKey;//分组key

    private String sortKey;//排序key

    private boolean sortDesc;//是否降序

    private String parentGroupKey;//一级分组key

    private String childGroupKey;//二级分组key

    private int page;

    private int pageSize;

    private String title;

    private String company;

    private String phone;

    private long uid;

    private int level;

    private int effect;

    private int emotion;

    private String userName;

    private boolean uidAndPhoneMatchOne;//控制uid和phone作为一个查询条件

    public int getPage() {
        return Math.max(page, 1);
    }

    public int getPageSize() {
        return Math.min(pageSize, 200);
    }

}
