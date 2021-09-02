package com.mwb.web.es;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author mengweibo@kanzhun.com
 * @create 2021/3/4 9:33 下午
 */
@Document(indexName = OpinionEsConstants.OPINION_INDEX_NAME, type = OpinionEsConstants.OPINION_INDEX_TYPE, shards = 3)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticleEO {

    @Id
    private Long id;

    @Field(type = FieldType.Date)
    private Date addTime;

    @Field(type = FieldType.Date)
    private Date updateTime;

    @CompletionField(analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Keyword)
    private String company;

    @Field(type = FieldType.Long)
    private Long uid;

    @Field(type = FieldType.Keyword)
    private String phone;

    @Field(type = FieldType.Integer)
    private Integer level;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Long)
    private Long parentTypeId;

    @Field(type = FieldType.Long)
    private Long typeId;

    @Field(type = FieldType.Text)
    private String typeRemark;

    @Field(type = FieldType.Keyword)
    private String updateUserName;

    @Field(type = FieldType.Integer)
    private Integer effect;

    @Field(type = FieldType.Integer)
    private Integer emotion;

    @Field(type = FieldType.Long)
    private Long channelId;

    @Field(type = FieldType.Keyword)
    private List<Long> labelIds;

    @Field(type = FieldType.Integer)
    private Integer addTimeDate8;

}
