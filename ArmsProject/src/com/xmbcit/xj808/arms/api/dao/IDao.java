package com.xmbcit.xj808.arms.api.dao;

import java.util.List;

/**
 * 操作类接口
 */
public interface IDao<T> {
    /**
     * 添加实体
     * @param t
     * @return
     */
    boolean insert(T t);

    /**
     *修改指定的实体，根据id来修改！如果不存在id，无法返回false
     * @param t
     * @return
     */
    boolean update(T t);

    /**
     * 根据模板实体查询
     * @param t 参考实体模板，如果t=null 则无需参考，直接查询所有
     * @return
     */
    List<T> select(T t);

    /**
     * 根据编号查询
     * @param id 编号
     * @return 返回单个实体，如果不存在返回null
     */
    T selectSimpleEntityByNum(String id);
}

