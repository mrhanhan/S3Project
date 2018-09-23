package com.xmbcit.xj808.arms.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xmbcit.xj808.arms.api.dao.IBullitenDao;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.base.IResultSet;
import com.xmbcit.xj808.arms.entity.Bulliten;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

 public  class BullitenImpl extends BaseDao<Bulliten> implements IBullitenDao<Bulliten> {

    public BullitenImpl(IConnect iConnect) {
        super(iConnect);
    }

    /** 排序方式 */
    private String order = " b_num ";

    private String join = " or ";

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
     * 添加公告
     */
    @Override
    public boolean insert(Bulliten t) {
        String sql = "insert into bulliten values(?,?,?,?,?,?,?,?,?,?)";
        return this.executeUpdate(sql, t.getId(), t.getBnum(), t.getBtitle(), t.getBcontent(), t.getUnum(),
                t.getBdate(), t.getbUpdateDate(), t.getCreateTime(), t.getOrgId(), t.getuCreateUnum()) > 0;
    }

    /**
     * 修改公告
     */
    @Override
    public boolean update(Bulliten t) {
        String sql = "update bulliten set b_title=?,b_content=?,b_update_date=? where id=?";
        return this.executeUpdate(sql, t.getBtitle(), t.getBcontent(), t.getbUpdateDate(), t.getId()) > 0;
    }

    /**
     * 查询公告
     */
    @Override
    public List<Bulliten> select(Bulliten t) {
        StringBuilder sql = new StringBuilder("select * from bulliten  ");
        List params = new ArrayList();// 参数集合
        sql.append(createTempSql(t, params));
        return this.executeQuery(sql.toString(), params.toArray());
    }

    /**
     * 查询单个公告
     */
    @Override
    public Bulliten selectSimpleEntityByNum(String id) {
        String sql = "select * from bulliten where  id =?";
        List<Bulliten> bulliten = executeQuery(sql, id);
        if (bulliten.isEmpty()) {
            return null;
        }
        return bulliten.get(0);
    }

    /**
     * 分页查询
     */
    @Override
    public IPage<Bulliten> selectPage(IPage<Bulliten> page, Bulliten templetEntity) {
        int i = page.getNowPage();// 获取当前页
        int size = page.getPageSize();// 获取页面显示大小
        int si = (i - 1) * size;// 计算查询其实位置
        if (si < 0) {
            si = 0;
        }
        final int[] maxDataSize = { 0 };// 数据总数量
        StringBuilder sb = new StringBuilder("select * from bulliten  ");
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

        DataPageImp<Bulliten> u = new DataPageImp<>(executeQuery(sb.toString(), p.toArray()), maxDataSize[0], page);
        return u;
    }

    /**
     * 查询条件
     *
     * @param \
     * @param b
     * @param list
     * @return
     */
    private String createTempSql(Bulliten b, List list) {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNull(b)) {
            if (!CommonUtil.isNull(b.getId())) {
                sb.append(" ").append(join).append(" id=? ");
                list.add(b.getId());
            }
            if (!CommonUtil.isNull(b.getBnum())) {
                sb.append(" ").append(join).append(" b_num=? ");
                list.add(b.getBnum());
            }
            if (!CommonUtil.isNull(b.getBtitle())) {
                sb.append(" ").append(join).append(" b_title like ? ");
                list.add("%" + b.getBtitle() + "%");

            }
            if (!CommonUtil.isNull(b.getBcontent())) {
                sb.append(" ").append(join).append(" b_content like ? ");
                list.add("%" + b.getBcontent() + "%");

            }
            if (!CommonUtil.isNull(b.getUnum())) {
                sb.append(" ").append(join).append(" u_num=? ");
                list.add(b.getUnum());

            }
            if (!CommonUtil.isNull(b.getBdate())) {
                sb.append(" ").append(join).append(" b_date=? ");
                list.add(b.getBdate());

            }
            if (!CommonUtil.isNull(b.getCreateTime())) {
                sb.append(" ").append(join).append(" b_update_date=? ");
                list.add(b.getCreateTime());

            }
            if (!CommonUtil.isNull(b.getOrgId())) {
                sb.append(" ").append(join).append(" org_id=? ");
                list.add(b.getOrgId());

            }
            if (!CommonUtil.isNull(b.getuCreateUnum())) {
                sb.append(" ").append(join).append(" u_create_u_num=? ");
                list.add(b.getuCreateUnum());

            }
            if (sb.length() > 1) {
                String wh = "where 1" + ("or".equals(join) ? "<>" : "=") + "1 ";
                sb.insert(0, wh);
            }
        }
        sb.append(" order by ").append(order);
        return sb.toString();
    }

    @Override
    public Bulliten getEntity(ResultSet rs) {
        Bulliten b = new Bulliten();
        try {
            b.setId(rs.getString(1));
            b.setBnum(rs.getString(2));
            b.setBtitle(rs.getString(3));
            b.setBcontent(rs.getString(4));
            b.setUnum(rs.getString(5));
            b.setBdate(rs.getDate(6));
            b.setbUpdateDate(rs.getDate(7));
            b.setCreateTime(rs.getDate(8));
            b.setOrgId(rs.getString(9));
            b.setuCreateUnum(rs.getString(10));
        } catch (SQLException e) {
            log.warn("构造实体类异常！");
        }
        return b;
    }
}
