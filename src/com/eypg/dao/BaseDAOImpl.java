package com.eypg.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eypg.pojo.UserBuy;
import com.eypg.util.StringUtil;

/**
 * 统一数据访问接口实现
 */
@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory1(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 统计指定类的所有持久化对象
     */
    public int countAll(String clazz) {
        final String hql = "select count(*) from " + clazz + " as a";
        @SuppressWarnings("rawtypes")
        Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        });
        return count.intValue();
    }

    /**
     * 统计指定类的查询结果
     */
    public int countQuery(String hql) {
        final String counthql = hql;
        Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(counthql);
                query.setMaxResults(1);
                return query.uniqueResult();
            }
        });
        return count.intValue();
    }

    /**
     * 查询指定类的满足条件的持久化对象
     */
    public List querySQL(String hql) {
        final String hql1 = hql;
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(hql1);
                return query.list();
            }
        });
    }


    /**
     * 删除指定ID的持久化对象
     */
    public void delById(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
    }

    /**
     * 装载指定类的所有持久化对象
     */
    public List listAll(String clazz) {
        return getHibernateTemplate().find("from " + clazz + " as a order by a.id desc");
    }

    /**
     * 分页装载指定类的所有持久化对象
     */
    public List listAll(String clazz, int pageNo, int pageSize) {
        final int pNo = pageNo;
        final int pSize = pageSize;
        final String hql = "from " + clazz + " as a order by a.id desc";
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setMaxResults(pSize);
                query.setFirstResult((pNo - 1) * pSize);
                List<Object> result = query.list();
                if (!Hibernate.isInitialized(result)) Hibernate.initialize(result);
                return result;
            }
        });
        return list;
    }

    /**
     * 加载指定ID的持久化对象
     */
    public Object loadById(Class clazz, Serializable id) {
        return getHibernateTemplate().get(clazz, id);
    }

    /**
     * 加载满足条件的持久化对象
     */
    public Object loadObject(String hql) {
        final String hql1 = hql;
        Object obj = null;
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                return query.list();
            }
        });
        if (list.size() > 0) obj = list.get(0);
        return obj;
    }

    /**
     * 查询指定类的满足条件的持久化对象
     */
    public List query(String hql) {
        final String hql1 = hql;
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                return query.list();
            }
        });
    }

    /**
     * 分页查询指定类的满足条件的持久化对象
     */
    public List<Object> query(String hql, int pageNo, int pageSize) {
        final int pNo = pageNo;
        final int pSize = pageSize;
        final String hql1 = hql;
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                query.setMaxResults(pSize);
                query.setFirstResult((pNo - 1) * pSize);
                List<Object> result = query.list();
                if (!Hibernate.isInitialized(result)) Hibernate.initialize(result);
                return result;
            }
        });
    }

    /**
     * 保存或更新指定的持久化对象
     */
    @Transactional
    public void saveOrUpdate(Object obj) {
        this.getHibernateTemplate().saveOrUpdate(obj);
    }

    /**
     * 根据SQL保存或更新指定的持久化对象
     */
    public Object saveOrUpdateSQL(String hql) {
        final String hql1 = hql;
        Object obj = null;
        List<Object> list = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                return query.list();
            }
        });
        if (list.size() > 0) obj = list.get(0);
        return obj;
    }

    /**
     * 条件更新数据
     */
    public int update(String hql) {
        final String hql1 = hql;
        return ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql1);
                return query.executeUpdate();
            }
        })).intValue();
    }

    /*分页查询*/
    public Pagination pageQuery(Pagination pagination, StringBuffer hql, StringBuffer sql) {
        List<Object> list = null;
        Session session = getSession();
        try {
            pagination.setResultCount(this.getAllNum(sql));// 给工具类PageCommon赋予记录总数
            Query query = session.createQuery(hql.toString());
            query.setFirstResult(pagination.getFirstResult()).setMaxResults(pagination.pageSize);            // 设置开始标记和结束标记
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        pagination.setList(list);

        return pagination;
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public int getAllNum(StringBuffer sql) {
        Session session = getSession();
        try {
            BigInteger t = (BigInteger) session.createSQLQuery(sql.toString()).uniqueResult();
            if (t != null)
                return t.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 0;
    }

    public Session getSessions() {
        return this.getSession();
    }

    public List<?> callProc(String proName, List<String> paramList) {
        if (StringUtil.isBlank(proName)) {
            return null;
        }
        Query query = this.getSession().getNamedQuery(proName);
        if (paramList != null && paramList.size() > 0) {
            int i = 0;
            for (String str : paramList) {
                query.setString(i, str);
                i++;
            }
        }
        return query.list();
    }

    public List sqlQuery(StringBuffer sql, Map<String, Class> entityMap, Pagination page) {
        if (StringUtil.isBlank(sql.toString())) {
            return null;
        }
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sql.toString());
            if (entityMap != null && entityMap.size() > 0) {
                for (String key : entityMap.keySet()) {
                    Class value = entityMap.get(key);
                    query.addEntity(key, value);
                }
            }
            query.setFirstResult(page.getFirstResult()).setMaxResults(page.pageSize);            // 设置开始标记和结束标记
            return query.list();
        } catch (DataAccessResourceFailureException e) {
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List sqlQueryBean(StringBuffer sql, Map<String, Class> entityMap, Pagination page) {
        if (StringUtil.isBlank(sql.toString())) {
            return null;
        }
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sql.toString());
            if (entityMap != null && entityMap.size() > 0) {
                for (String key : entityMap.keySet()) {
                    Class value = entityMap.get(key);
//					query.addEntity(key,value);
                    query.setResultTransformer(Transformers.aliasToBean(value));
                }
            }
            query.setFirstResult(page.getFirstResult()).setMaxResults(page.pageSize);            // 设置开始标记和结束标记
            return query.list();
        } catch (DataAccessResourceFailureException e) {
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * 获取sum数
     */
    public BigDecimal getSelectSum(StringBuffer sql) {
        Session session = getSession();
        try {
            BigDecimal t = (BigDecimal) session.createSQLQuery(sql.toString()).uniqueResult();
            if (t != null)
                return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List sqlQueryEntity(StringBuffer sql, Map<String, Class> entityMap) {
        if (StringUtil.isBlank(sql.toString())) {
            return null;
        }
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sql.toString());
            if (entityMap != null && entityMap.size() > 0) {
                for (String key : entityMap.keySet()) {
                    Class value = entityMap.get(key);
                    query.addEntity(key, value);
                }
            }
            return query.list();
        } catch (DataAccessResourceFailureException e) {
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List sqlQueryEntityBean(StringBuffer sql, Map<String, Class> entityMap) {
        if (StringUtil.isBlank(sql.toString())) {
            return null;
        }
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sql.toString());
            if (entityMap != null && entityMap.size() > 0) {
                for (String key : entityMap.keySet()) {
                    Class value = entityMap.get(key);
//					query.addEntity(key,value);
                    query.setResultTransformer(Transformers.aliasToBean(value));
                }
            }
            return query.list();
        } catch (DataAccessResourceFailureException e) {
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }


    public List find(String hql) {
        try {
            return getHibernateTemplate().find(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}