package com.eypg.service;

import java.util.List;

public interface TService<T, Serializable> {
    public void add(T t);

    public void delete(String id);

    public void update(String hql);

    public T findById(String id);

    public List<T> query(String hql);
}
