package com.xmbcit.xj808.arms.api.dao;

import java.util.List;

/**
 * 分页接口<br/>
 * 处理数据分页
 * @param <T>
 */
public interface IPage<T> {
    /**
     * 获取当前页面索引
     *  第几页
     * @return
     */
    int getNowPage();

    /**
     * 获取当前数据总页数
     * @return
     */
    int getMaxPage();

    /**
     * 获取查询数据总条数
     * @return
     */
    int getDateSize();

    /**
     * 获取页面显示数据个数
     * @return
     */
    int getPageSize();

    /**
     * 获取下一页索引<br/>
     * 如果当前页，就是最后一页，那么在调用此方法时，返回最后一页索引
     * @return
     */
    int getNextPageIndex();

    /**
     * 获取上一页索引，如果当前页是第一页，则上一页返回首页索引
     * @return
     */
    int getLastPageIndex();
    /**
     * 获取当前页的数据
     * @return
     */
    List<T> getNowPageData();
}
