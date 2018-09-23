package com.xmbcit.xj808.arms.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xmbcit.xj808.arms.api.dao.IGoodsDao;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.base.IResultSet;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

public class GoodsImp extends BaseDao<Goods> implements IGoodsDao<Goods> {

    public GoodsImp(IConnect iConnect) {
        super(iConnect);

    }

    /**
     * 排序方式
     */
    private String order = "g_num";

    private String join = "or";

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

    /**
     * 新增商品
     */
    @Override
    public boolean insert(Goods t) {
        String sql = "insert into goods values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return this.executeUpdate(sql, t.getId(), t.getGnum(), t.getTnum(), t.getGname(), t.getGprice(),
                t.getGdiscount(), t.getGstate(), t.getGisdiscount(), t.getGimg(), t.getGisnew(), t.getGisrecom(),
                t.getGremark(), t.getCreateTime(), t.getOrgId(), t.getuCreateUnum()) > 0;
    }

    /**
     * 修改商品
     */
    @Override
    public boolean update(Goods t) {
        String sql = "update goods set g_num=?,t_num=?,g_name=?,g_price=?,g_discount=?,g_state=?,g_isdiscount=?,g_img=?,g_isnew =?,g_isrecom=?,g_remark=?,create_time=?,org_id=?,u_create_u_num=?where id=?";
        return this.executeUpdate(sql, t.getGnum(), t.getTnum(), t.getGname(), t.getGprice(), t.getGdiscount(),
                t.getGstate(), t.getGisdiscount(), t.getGimg(), t.getGisnew(), t.getGisrecom(), t.getGremark(),
                t.getCreateTime(), t.getOrgId(), t.getuCreateUnum(), t.getId()) > 0;
    }

    /**
     * 查询商品
     */
    @Override
    public List<Goods> select(Goods t) {
        StringBuilder sql = new StringBuilder("select * from goods ");
        List list = new ArrayList(); // 参数集合
        sql.append(createTempSql(t, list));
        return this.executeQuery(sql.toString(), list.toArray());
    }

    /**
     * 查询条件
     *
     * @param list
     * @return
     */
    private String createTempSql(Goods t, List list) {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNull(t)) { // 判断goods对象是否空
            if (!CommonUtil.isNull(t.getId())) {
                sb.append(" ").append(join).append(" id= ?");
                list.add(t.getId());
            }
            if (!CommonUtil.isNull(t.getGnum())) {
                sb.append(" ").append(join).append(" g_num= ?");
                list.add(t.getGnum());
            }
            if (!CommonUtil.isNull(t.getTnum())) {
                sb.append(" ").append(join).append(" t_num= ?");
                list.add(t.getTnum());
            }
            if (!CommonUtil.isNull(t.getGname())) {
                sb.append(" ").append(join).append(" g_name like ?");
                list.add("%" + t.getGname() + "%");
            }

            if (t.getGprice() != 0) {
                sb.append(" ").append(join).append(" g_price= ?");
                list.add(t.getGprice());
            }

            if (t.getGdiscount() != 0) {
                sb.append(" ").append(join).append(" g_discount=?");
                list.add(t.getGdiscount());
            }
            if (!CommonUtil.isNull(t.getGstate())) {
                sb.append(" ").append(join).append(" g_state=?");
                list.add(t.getGstate());
            }
            if (!CommonUtil.isNull(t.getGisdiscount())) {
                sb.append(" ").append(join).append(" g_isdiscount=?");
                list.add(t.getGisdiscount());
            }
            if (!CommonUtil.isNull(t.getGimg())) {
                sb.append(" ").append(join).append(" g_img = ?");
                list.add(t.getGimg());
            }
            if (!CommonUtil.isNull(t.getGisnew())) {
                sb.append(" ").append(join).append(" g_isnew=?");
                list.add(t.getGisnew());
            }
            if (!CommonUtil.isNull(t.getGisrecom())) {
                sb.append(" ").append(join).append(" g_isrecom=?");
                list.add(t.getGisrecom());
            }
            if (!CommonUtil.isNull(t.getGremark())) {
                sb.append(" ").append(join).append(" g_remark like ?");
                list.add("%" + t.getGremark() + "%");
            }
            if (!CommonUtil.isNull(t.getCreateTime())) {
                sb.append(" ").append(join).append(" create_time=?");
                list.add(t.getCreateTime());
            }
            if (!CommonUtil.isNull(t.getOrgId())) {
                sb.append(" ").append(join).append(" org_id=?");
                list.add(t.getOrgId());
            }
            if (!CommonUtil.isNull(t.getuCreateUnum())) {
                sb.append(" ").append(join).append(" u_create_u_num=?");
                list.add(t.getuCreateUnum());
            }
            if (sb.length() > 1) {
                String wh = "where 1"+("or".equals(join)?"<>":"=")+"1 ";
                sb.insert(0,wh );
            }

        }
        sb.append(" order by ").append(order);
        return sb.toString();
    }

    /**
     * 查询单个商品
     */
    @Override
    public Goods selectSimpleEntityByNum(String id) {
        String sql = "select * from goods where  id =?";
        List<Goods> goodss = executeQuery(sql, id);
        if (goodss.isEmpty()) {
            return null;
        }
        return goodss.get(0);
    }

    /**
     * 分页查询
     */
    @Override
    public IPage<Goods> selectPage(IPage<Goods> page, Goods templetEntity) {
        int i = page.getNowPage();// 获取当前页
        int size = page.getPageSize();// 获取页面显示大小
        int si = (i - 1) * size;// 计算查询其实位置
        if (si < 0) {
            si = 0;
        }
        final int[] maxDataSize = {0};// 数据总数量
        StringBuilder sb = new StringBuilder("select * from goods  ");
        List p = new ArrayList();
        sb.append(createTempSql(templetEntity, p));

        execQuery(sb.toString().replaceAll("\\*", "count(*)"), new IResultSet() {
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

        DataPageImp<Goods> u = new DataPageImp<>(executeQuery(sb.toString(), p.toArray()), maxDataSize[0], page);
        return u;
    }

    @Override
    public Goods getEntity(ResultSet rs) {
        Goods d = new Goods();
        try {
            d.setId(rs.getString(1));
            d.setGnum(rs.getString(2));
            d.setTnum(rs.getString(3));
            d.setGname(rs.getString(4));
            d.setGprice(rs.getDouble(5));
            d.setGdiscount(rs.getInt(6));
            d.setGstate(rs.getString(7));
            d.setGisdiscount(rs.getString(8));
            d.setGimg(rs.getString(9));
            d.setGisnew(rs.getString(10));
            d.setGisrecom(rs.getString(11));
            d.setGremark(rs.getString(12));
            d.setCreateTime(rs.getDate(13));
            d.setOrgId(rs.getString(14));
            d.setuCreateUnum(rs.getString(15));

        } catch (Exception e) {
        }
        return d;
    }

}
