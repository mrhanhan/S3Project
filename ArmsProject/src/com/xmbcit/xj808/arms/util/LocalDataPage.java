package com.xmbcit.xj808.arms.util;

import com.xmbcit.xj808.arms.api.dao.IPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地数据分页
 * 假分页
 */
public class LocalDataPage extends DataPageImp {


    public static  LocalDataPage getPage(List data,IPage page){
        int showSize = page.getPageSize();//获取当前数据显示大小
        int dataMax  = data.size();//获取数据大小
        int nowPage = page.getNowPage();//获取当前显示页面
        int maxPage = dataMax/showSize;
        if(dataMax%showSize!=0)
            maxPage++;
        if(nowPage>maxPage){
            nowPage=maxPage;
        }
        //数据其实位置
        int dataStart = (nowPage-1)*showSize;
        if(nowPage==maxPage){
            showSize = dataMax-dataStart;
        }

        if(data.size()<1){
            showSize=0;
        }

        if(dataStart<0)
            dataStart=0;
        List datas = new ArrayList<>(showSize);

        for(int i=dataStart;i<dataStart+showSize;i++)
        {
            datas.add(data.get(i));
        }

        return new LocalDataPage(datas,dataMax,page);
    }


    /*当前页面索引*/
    private int nowPage;
    /*上一页*/
    private int lastIndex;
    /*下一页*/
    private int nextIndex;
    /*总页面*/
    private int maxPage;

    /*数据集合*/
    private List datas;

    /*总数据大小*/
    private int maxDataSize;
    /*页面显示数据量大小*/
    private int pageSize;
    /*实际数据大小*/
    private int dataSize;

    /**
     * 构造函数
     * @param data
     * @param
     */
    private LocalDataPage(List data,int dataMax, IPage page) {
        super(data, dataMax, page);
        datas = data;

        maxDataSize = dataMax;
        nowPage = page.getNowPage();
        pageSize = page.getPageSize();

        init();
    }


    public LocalDataPage(List data, int maxDataSize) {
        super(data, maxDataSize);
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
    public List getNowPageData() {
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
