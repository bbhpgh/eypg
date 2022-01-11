package com.eypg.generics.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 泛型Hibernate DAO类
 *
 * @author songchong <mailto:song316@gmail.com/>
 */
@SuppressWarnings("unchecked")
public class HibernateGenericsDao<T, ID extends Serializable> extends HibernateDaoSupport {
    private Class<T> pojoClass;

    /**
     * 初始化DAO，获取POJO类型
     */
    public HibernateGenericsDao() {
        this.pojoClass = GenericsUtils.getSuperClassGenricType(getClass());
    }

    /** */
    /**
     * 获得该DAO对应的POJO类型
     */
    public Class<T> getPojoClass() {
        return this.pojoClass;
    }

    /** */
    /**
     * 获得该DAO对应的POJO类型名
     */
    public String getPojoClassName() {
        return getPojoClass().getName();
    }

    //加载对象

    /** */
    /**
     * 加载所有的对象
     */
    public List<T> loadAll() {
        return (List<T>) getHibernateTemplate().loadAll(getPojoClass());
    }

    /** */
    /**
     * 根据hql查询
     *
     * @param values 可变参数
     */
    public List find(String hql, Object values) {
        return getHibernateTemplate().find(hql, values);
    }

    /** */
    /**
     * 根据条件加载对象
     *
     * @param criteria Criteria实例
     */
    public List<T> findByCriteria(final Criteria criteria) {
        List list = criteria.list();
        return transformResults(list);
    }

    /** */
    /**
     * 根据条件加载对象
     *
     * @param detachedCriteria DetachedCriteria实例
     */
    public List<T> findByCriteria(final DetachedCriteria detachedCriteria) {
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                List list = criteria.list();
                return transformResults(list);
            }
        });
    }

    /** */
    /**
     * 根据给定的实例查找对象
     */
    public List<T> findByExample(T instance) {
        List<T> results = (List<T>) getHibernateTemplate().findByExample(instance);
        return results;
    }

    /** */
    /**
     * 根据ID查找对象
     */
    public T findById(ID id) {
        return (T) getHibernateTemplate().get(getPojoClassName(), id);
    }

    /**
     * 根据某个具体属性进行查找
     */
    public List<T> findByProperty(String propertyName, Object value) {
        String queryString = "from " + getPojoClassName() + " as model where model."
                + propertyName + "= ?";
        return (List<T>) getHibernateTemplate().find(queryString, value);
    }

    //新建、修改、删除

    /** */
    /**
     * 新建对象实例化
     */
    public ID save(T transientInstance) {
        return (ID) getHibernateTemplate().save(transientInstance);
    }

    /** */
    /**
     * 更新已存在的对象
     */
    public void update(T transientInstance) {
        getHibernateTemplate().update(transientInstance);
    }

    /** */
    /**
     * 删除指定ID的对象
     */
    public void delete(ID id) {
        T instance = findById(id);
        if (instance != null)
            getHibernateTemplate().delete(instance);
    }

    /** */
    /**
     * 删除指定对象
     */
    public void delete(T persistentInstance) {
        getHibernateTemplate().delete(persistentInstance);
    }

    //分页
    /** */
    /**
     * 根据Criteria加载分页，指定页大小和起始位置
     */
    public PaginationSupport findPageByCriteria(final Criteria criteria, final int pageSize, final int startIndex) {
        int totalCount = getCountByCriteria(criteria);
        criteria.setProjection(null);
        List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        items = transformResults(items);
        PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
        return ps;
    }

    /** */
    /**
     * 根据Criteria加载分页，默认页大小，从第0条开始
     */
    public PaginationSupport findPageByCriteria(final Criteria criteria) {
        return findPageByCriteria(criteria, PaginationSupport.PAGESIZE, 0);
    }

    /** */
    /**
     * 根据Criteria加载分页，默认页大小，从第startIndex条开始
     */
    public PaginationSupport findPageByCriteria(final Criteria criteria, final int startIndex) {
        return findPageByCriteria(criteria, PaginationSupport.PAGESIZE, startIndex);
    }

    /** */
    /**
     * 根据Criteria统计总数
     */
    public int getCountByCriteria(final Criteria criteria) {
        Integer count = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return count.intValue();
    }

    /** */
    /**
     * 根据DetachedCriteria加载分页，指定页大小和起始位置
     */
    public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex) {
        return (PaginationSupport) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                criteria.setProjection(null);
                List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
                items = transformResults(items);
                PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
                return ps;
            }
        });
    }

    /** */
    /**
     * 根据DetachedCriteria加载分页，默认页大小，从第0条开始
     */
    public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria) {
        return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, 0);
    }

    /** */
    /**
     * 根据DetachedCriteria加载分页，默认页大小，从第startIndex条开始
     */
    public PaginationSupport findPageByCriteria(final DetachedCriteria detachedCriteria, final int startIndex) {
        return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, startIndex);
    }

    /** */
    /**
     * 根据DetachedCriteria统计总数
     */
    public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
        Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(session);
                return criteria.setProjection(Projections.rowCount()).uniqueResult();
            }
        });
        return count.intValue();
    }

    /** */
    /**
     * 根据hql加载分页，指定页大小和起始位置
     */
    public PaginationSupport findPageByQuery(final String hql, final int pageSize, final int startIndex, Object values) {
        int totalCount = getCountByQuery(hql, values);

        if (totalCount < 1)
            return new PaginationSupport(new ArrayList(0), 0);

        Query query = createQuery(hql, values);
        List items = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
        return ps;
    }

    /** */
    /**
     * 根据hql加载分页，默认页大小，从第0条开始
     */
    public PaginationSupport findPageByQuery(final String hql, Object values) {
        return findPageByQuery(hql, PaginationSupport.PAGESIZE, 0, values);
    }

    /** */
    /**
     * 根据hql加载分页，默认页大小，从第startIndex条开始
     */
    public PaginationSupport findPageByQuery(final String hql, final int startIndex, Object values) {
        return findPageByQuery(hql, PaginationSupport.PAGESIZE, startIndex, values);
    }

    /** */
    /**
     * 根据hql统计总数
     */
    public int getCountByQuery(final String hql, Object values) {
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        return (Integer) countlist.get(0);
    }

    //创建Criteria和Query

    /** */
    /**
     * 创建Criteria对象
     *
     * @param criterions 可变的Restrictions条件列表
     */
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(getPojoClass());
        for (Criterion c : criterions)
            criteria.add(c);
        return criteria;
    }

    /** */
    /**
     * 创建Criteria对象，带排序字段与升降序字段
     */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion criterions) {
        Criteria criteria = createCriteria(criterions);
        if (isAsc)
            criteria.addOrder(Order.asc(orderBy));
        else
            criteria.addOrder(Order.desc(orderBy));
        return criteria;
    }

    /** */
    /**
     * 方法取自SpringSide.
     * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
     * 留意可以连续设置,如下：
     * <pre>
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
     * </pre>
     * 调用方式如下：
     * <pre>
     *        dao.createQuery(hql)
     *        dao.createQuery(hql,arg0);
     *        dao.createQuery(hql,arg0,arg1);
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
     * </pre>
     *
     * @param values 可变参数.
     */
    public Query createQuery(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    /** */
    /**
     * 方法取自SpringSide.
     * 去除hql的select子句，未考虑union的情况
     */
    private static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);
    }

    /** */
    /**
     * 方法取自SpringSide.
     * 去除hql的orderby子句
     */
    private static String removeOrders(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /** */
    /**
     * 将联合查询的结果内容从Map或者Object[]转换为实体类型，如果没有转换必要则直接返回
     */
    private List transformResults(List items) {
        if (items.size() > 0) {
            if (items.get(0) instanceof Map) {
                ArrayList list = new ArrayList(items.size());
                for (int i = 0; i < items.size(); i++) {
                    Map map = (Map) items.get(i);
                    list.add(map.get(CriteriaSpecification.ROOT_ALIAS));
                }
                return list;
            } else if (items.get(0) instanceof Object[]) {
                ArrayList list = new ArrayList(items.size());
                int pos = 0;
                for (int i = 0; i < ((Object[]) items.get(0)).length; i++) {
                    if (((Object[]) items.get(0))[i].getClass() == getPojoClass()) {
                        pos = i;
                        break;
                    }
                }
                for (int i = 0; i < items.size(); i++) {
                    list.add(((Object[]) items.get(i))[pos]);
                }
                return list;
            } else
                return items;
        } else
            return items;
    }
}
