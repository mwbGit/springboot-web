package com.mwb.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.mwb.web.model.common.PageQuery;
import com.mwb.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/7/31
 */

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        //说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        //说明：保存一个实体，null的属性也会保存，不会使用数据库默认值
        return mapper.insert(entity);
    }

    @Override
    public int saveNotNull(T entity) {
        //说明：保存一个实体，属性不为null的值
        return mapper.insertSelective(entity);
    }

    @Override
    public int delete(Object key) {
        //说明：根据主键字段进行删除，方法参数必须包含完整的主键属性
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int updateAll(T entity) {
        //说明：根据主键更新实体全部字段，null值会被更新
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        //根据主键更新属性不为null的值
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        //说明：根据Example条件进行查询
        //重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
        return mapper.selectByExample(example);
    }

    @Override
    public T selectOneByExample(Object example) {
        return mapper.selectOneByExample(example);
    }

    @Override
    public List<T> pageSearch(PageQuery query, Class<T> entityClass) {
        //分页
        if (query.isPaged()) {
            PageHelper.startPage(query.getPage(), query.getPageSize());
        }
        Example example = new Example(entityClass);
        //排序
        example.orderBy(query.getOrder()).desc();
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

}