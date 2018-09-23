package com.xmbcit.xj808.arms.services;

import com.xmbcit.xj808.arms.api.dao.IOrderDetailsDao;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.base.IResultSet;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.entity.OrderDetails;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDatialDaoImp extends BaseDao<OrderDetails> implements IOrderDetailsDao<OrderDetails> {
    /**
     * 无参构造函数*
     *
     * @param iConnect 连接对象
     */
    public OrderDatialDaoImp(IConnect iConnect) {
        super(iConnect);
    }

    private String order="g_num";
    private String join="or";

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    @Override
    public boolean insert(OrderDetails orderDetails) {
        String sql = "insert into order_details values(?,?,?,?,?,?,?)";
        return executeUpdate(sql, orderDetails.getOnum(), orderDetails.getGnum(), orderDetails.getDcount(), orderDetails.getDmoney(),
                orderDetails.getCreateTime(), orderDetails.getOrgId(), orderDetails.getuCreateUnum()) > 0;
    }

    @Override
    public boolean update(OrderDetails orderDetails) {
        return false;
    }

    @Override
    public List<OrderDetails> select(OrderDetails orderDetails) {
        StringBuilder sb  = new StringBuilder("select * from order_details ");
        List params = new ArrayList();
        sb.append(createTempSql(orderDetails,params));
        return executeQuery(sb.toString(),params.toArray());
    }

    /**
     * 创建模板语句
     * @param t
     * @param params
     * @return
     */
    private String createTempSql(OrderDetails t ,List params){
        StringBuilder sb = new StringBuilder();
        if(!CommonUtil.isNull(t)){

            if(!CommonUtil.isNull(t.getGnum())){
                sb.append(" ").append(join).append(" g_num=?");
                params.add(t.getGnum());
            }
            if(!CommonUtil.isNull(t.getOnum())){
                sb.append(" ").append(join).append(" o_num=?");
                params.add(t.getOnum());
            }
            if(t.getDcount()!=0){
                sb.append(" ").append(join).append(" d_count=?");
                params.add(t.getDcount());
            }
            if(CommonUtil.isNull(t.getDmoney())){
                sb.append(" ").append(join).append(" d_money=?");
                params.add(t.getDmoney());
            }
            if(!CommonUtil.isNull(t.getCreateTime())){
                sb.append(" ").append(join).append(" create_time=?");
                params.add(t.getCreateTime());
            }
            if(!CommonUtil.isNull(t.getOrgId())){
                sb.append(" ").append(join).append(" org_id=?");
                params.add(t.getOrgId());
            }
            if(!CommonUtil.isNull(t.getuCreateUnum())){
                sb.append(" ").append(join).append(" u_create_u_num=?");
                params.add(t.getuCreateUnum());
            }
            if(sb.length()>1){
                String wh = "where 1"+("or".equals(join)?"<>":"=")+"1 ";
                sb.insert(0,wh );
            }

        }
        sb.append(" order by ").append(order);
        return sb.toString();
    }

    @Override
    public OrderDetails selectSimpleEntityByNum(String id) {
        StringBuilder sb  = new StringBuilder("select * from order_details id=?");
        List<OrderDetails> orderDetails = executeQuery(sb.toString(),id);
        if(orderDetails.isEmpty())
            return null;
        return orderDetails.get(0);
    }

    @Override
    public IPage<OrderDetails> selectPage(IPage<OrderDetails> page, OrderDetails templetEntity) {
        int i = page.getNowPage();// 获取当前页
        int size = page.getPageSize();// 获取页面显示大小
        int si = (i - 1) * size;// 计算查询其实位置
        if(si<0){
            si = 0;
        }
        final int[] maxDataSize = { 0 };// 数据总数量
        StringBuilder sb = new StringBuilder("select * from order_details  ");
        List p = new ArrayList();
        sb.append(createTempSql(templetEntity,p));

        execQuery(sb.toString().replaceAll("\\*","count(*)"), new IResultSet() {
            @Override
            public void result(ResultSet resultSet) {
                try {
                    if (resultSet.next()) {
                        maxDataSize[0] = resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },p.toArray());
        sb.append(" limit ?,? ");
        p.add(si);
        p.add(size);

        DataPageImp<OrderDetails> u = new DataPageImp<>(executeQuery(sb.toString(), p.toArray()), maxDataSize[0], page);
        return u;

    }

    @Override
    public OrderDetails getEntity(ResultSet rs) {
        OrderDetails od = new OrderDetails();
        try {
            od.setOnum(rs.getString("o_num"));
            od.setGnum(rs.getString("g_num"));
            od.setDcount(rs.getInt("d_count"));
            od.setDmoney(rs.getBigDecimal("d_money"));

        } catch (Exception e) {

        }
        return od;
    }
}
