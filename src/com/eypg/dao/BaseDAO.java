package com.eypg.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * 统一数据访问接口
 */
public interface BaseDAO {

    public Session getSessions();

    /**
     * 加载指定ID的持久化对象
     */
    @SuppressWarnings("rawtypes")
    public Object loadById(Class clazz, Serializable id);

    /**
     * 加载满足条件的持久化对象
     */
    public Object loadObject(String hql);

    /**
     * 删除指定ID的持久化对象
     */
    public void delById(Class<?> clazz, Serializable id);

    /**
     * 保存或更新指定的持久化对象
     */
    public void saveOrUpdate(Object obj);

    /**
     * 根据SQL保存或更新指定的持久化对象
     */
    public Object saveOrUpdateSQL(String sql);

    /**
     * 装载指定类的所有持久化对象
     */
    public List<?> listAll(String clazz);

    /**
     * 分页装载指定类的所有持久化对象
     */
    public List<?> listAll(String clazz, int pageNo, int pageSize);

    /**
     * 统计指定类的所有持久化对象
     */
    public int countAll(String clazz);

    /**
     * 查询指定类的满足条件的持久化对象
     */
    public List<?> query(String hql);

    /**
     * 查询指定类的满足条件的持久化对象
     */
    public List<?> querySQL(String hql);

    /**
     * 分页查询指定类的满足条件的持久化对象
     */
    public List<?> query(String hql, int pageNo, int pageSize);

    /**
     * 统计指定类的查询结果
     */
    public int countQuery(String hql);

    /**
     * 条件更新数据
     */
    public int update(String hql);

    /***分页查询***/
    public Pagination pageQuery(Pagination pageCommon, StringBuffer hql, StringBuffer sql);

    /***执行存储过程***/
    public List<?> callProc(String proName, List<String> paramList);

    /**
     * 执行sql查询，支持分页
     **/
    public List sqlQuery(StringBuffer sql, Map<String, Class> entityMap, Pagination page);

    public List sqlQueryBean(StringBuffer sql, Map<String, Class> entityMap, Pagination page);

    public int getAllNum(StringBuffer sql);

    public BigDecimal getSelectSum(StringBuffer sql);

    /**
     * 连表查询不带分页
     **/
    public List sqlQueryEntity(StringBuffer sql, Map<String, Class> entityMap);

    public List sqlQueryEntityBean(StringBuffer sql, Map<String, Class> entityMap);

    /**
     * loadALL
     **/
    public List find(String hql);
}

