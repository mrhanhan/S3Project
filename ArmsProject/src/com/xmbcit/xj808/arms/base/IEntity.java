package com.xmbcit.xj808.arms.base;

import java.sql.ResultSet;

/**
 * 根据单行结果集合，来创建实体
 */
public interface IEntity<T> {
    /**
     * 当前方法，在有结果集合时候，会被调用
     *
     * @param rs
     * @return
     */
    T getEntity(ResultSet rs);
}
