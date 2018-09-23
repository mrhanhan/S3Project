package com.xmbcit.xj808.arms.base;

import java.sql.ResultSet;

/**
 * 处理结果集合对象，接口
 * 配合basedao处理一些，查询内容
 *
 */
public interface IResultSet {
    void result(ResultSet resultSet);
}
