package com.xmbcit.xj808.arms.util;

import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * 计数器表实现
 */
public  class TableCountUtil extends BaseDao {
    /*编号长度*/
    private static final int NUM_LENGTH = 4;
    private SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");//日期转换工具

    private static TableCountUtil util = new TableCountUtil();
    private TableCountUtil(){
         super(new MyDBConn());
     }

    public static String getGoodsNum() {
        return util.getNum("G","goods");
    }

    /**
     * 根据前缀和表明生产编号
     * @param prefix
     * @param tabname
     * @return
     */
     public String getNum(String prefix,String tabname) {
        execQuery("select  DATEDIFF(now(),s_date) from sys_count where s_table=?", new IResultSet() {
            @Override
            public void result(ResultSet resultSet) {
                try {
                    if(resultSet.next()){
                        int diff = resultSet.getInt(1);
                        if(diff>0){
                            executeUpdate("update sys_count set s_count=0,s_date = now() where s_table=?",tabname);
                        }
                    }
                } catch (SQLException e) {

                }
            }
        },tabname);
         executeUpdate("update sys_count set s_count=s_count+1 where s_table = ?",tabname);
         StringBuilder count  = new StringBuilder();
         StringBuilder date  = new StringBuilder();
         execQuery("select  s_count,s_date from sys_count where s_table = ?", new IResultSet() {
             @Override
             public void result(ResultSet resultSet) {
                 try {
                     if(resultSet.next()){
                         count.append(resultSet.getInt(1));
                         date.append(s.format(resultSet.getDate(2)));
                     }
                 } catch (SQLException e) {

                 }
             }
         },tabname);
         int tc = NUM_LENGTH-count.length();//计算需要填充字符个数
         for(int i=0;i<tc;i++){
             count.insert(0,"0");
         }

         return date.insert(0,prefix).append(count).toString();
     }

    /**
     * 获取用户编号
     * @return
     */
     public static String getUserNum(){
         return util.getNum("U","user");
     }
    /**
     * 获取客户编号
     * @return
     */
    public static String getCustomerNum(){
        return util.getNum("C","customer");
    }
    /**
     * 获取商品类型编号
     * @return
     */
    public static String getGoodsTypeNum(){
        return util.getNum("T","goods_type");
    }
    /**
     * 获取订单编号
     * @return
     */
    public static String getOrderInfoNum(){
        return util.getNum("G","goods");
    }
    /**
     * 获取订单详细编号
     * @return
     */
    public static String getOrderDetailNum(){
        return util.getNum("D","order_details");
    }
    /**
     * 获取公告编号
     * @return
     */
    public static String getBullitenNum(){
        return util.getNum("U","bulliten");
    }

    @Override
    public Object getEntity(ResultSet rs) {
        return null;
    }

    /**
     * 关闭此工具类的连接
     */
    public static void close(){
        util.getConn().close();;
    }
}

