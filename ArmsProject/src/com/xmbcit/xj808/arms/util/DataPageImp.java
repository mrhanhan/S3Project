package com.xmbcit.xj808.arms.util;

import com.xmbcit.xj808.arms.api.dao.IPage;

import java.util.List;

/**
 * 分页接口实现类<br/>
 * 首页1
 *
 * @param <T>
 */
public class DataPageImp<T> implements IPage<T> {
    /*当前页面索引*/
    private int nowPage;
    /*上一页*/
    private int lastIndex;
    /*下一页*/
    private int nextIndex;
    /*总页面*/
    private int maxPage;

    /*数据集合*/
    private List<T> datas;

    /*总数据大小*/
    private int maxDataSize;
    /*页面显示数据量大小*/
    private int pageSize;
    /*实际数据大小*/
    private int dataSize;

    /**
     * 构造方法！
     *
     * @param data        当前页面的数据
     * @param maxDataSize 总数据大小
     */
    public DataPageImp(List<T> data, int maxDataSize) {
        datas = data;
        this.maxDataSize = maxDataSize;

        nowPage = 1;
        pageSize = 10;
        init();
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1)
            pageSize = 1;
        this.pageSize = pageSize;
    }

    /**
     * 构造方法！
     *
     * @param data        当前页面的数据
     * @param maxDataSize 总数据大小
     * @param page        参考分页对象，吧参考的分页对象中的，显示数据大小，页面索引，拷贝过来
     */
    public DataPageImp(List<T> data, int maxDataSize, IPage<T> page) {

        datas = data;

        this.maxDataSize = maxDataSize;
        nowPage = page.getNowPage();
        pageSize = page.getPageSize();

        init();
    }

    /**
     * 计算方法
     */
    private void init() {
        if (datas != null)
            dataSize = datas.size();
        int max = 1;
        if (maxDataSize > 0) {
            max = maxDataSize / pageSize;//总书记除去页面数据，得到页数
            if (maxDataSize % pageSize != 0) {//如果不能被
                max++;
            }
        }
        maxPage = max;
        nextIndex = getNextPageIndex();
        lastIndex = getLastPageIndex();
    }

    /**
     * 设置当前当前页面的索引
     *
     * @param nowPage
     */
    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
        if (this.nowPage < 1) {
            nowPage = 1;
        } else if (nowPage > getMaxPage()) {
            this.nowPage = getMaxPage();
        }
    }

    /**
     * 设置最大数据量
     *
     * @param maxDataSize
     */
    public void setMaxDataSize(int maxDataSize) {
        this.maxDataSize = maxDataSize;
    }

    @Override
    public int getNowPage() {
        if (this.nowPage < 1) {
            nowPage = 1;
        } else if (nowPage > getMaxPage()) {
            this.nowPage = getMaxPage();
        }
        return nowPage;
    }

    @Override
    public int getMaxPage() {
        return maxPage;
    }

    @Override
    public int getDateSize() {
        return maxDataSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getNextPageIndex() {
        int n = getNowPage() + 1;
        if (n > getMaxPage()) {
            n = getMaxPage();
        }
        return n;
    }

    @Override
    public int getLastPageIndex() {
        int n = getNowPage() - 1;
        if (n < 1) {
            n = 1;
        }
        return n;

    }

    @Override
    public List<T> getNowPageData() {
        return datas;
    }


    @Override
    public String toString() {
        return "分页对象{" +
                "当前页面= " + nowPage +
                ", 上一页= " + lastIndex +
                ", 下一页= " + nextIndex +
                ", 最大页数=" + maxPage +
                ", 数据= " + datas +
                ", 最大数据量= " + maxDataSize +
                ", 页面大小= " + pageSize +
                '}';
    }
}
