package com.xmbcit.xj808.arms.api.dao;

/**
 * 分页查询数据查询！
 */
public interface IPageSelect<T> {
    /**
     * 根据模板查询分页数据！
     * @param page 分页对象，包含当前所在的页面信息 例如（当前页面索引，当前页面显示大小）
     * @param templetEntity 查询
     * @return 返回分页对象
     */
    IPage<T> selectPage(IPage<T> page, T templetEntity);
}
