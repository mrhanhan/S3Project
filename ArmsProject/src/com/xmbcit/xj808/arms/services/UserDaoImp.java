package com.xmbcit.xj808.arms.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.api.dao.IUserDao;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.base.IResultSet;
import com.xmbcit.xj808.arms.entity.User;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

/**
 * 用户实体Dao
 *
 * @author Administrator
 */
public class UserDaoImp extends BaseDao<User> implements IUserDao<User> {
    private String order = "u_num";
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

    public UserDaoImp(IConnect iConnect) {

        super(iConnect);

    }

    /**
     * 增加用户
     */
    @Override
    public boolean insert(User u) {
        String sql = " insert into user values(?,?,?,?,?,?,?,?,?,?)";
        int a = executeUpdate(sql, u.getId(), u.getUnum(), u.getUname(),u.getUimg(), u.getUaccout(), u.getUpwd(), u.getUauto(), u.getCreateTime(), u.getOrgId(), u.getuCreateUnum());

        return a > 0;
    }

    /**
     * 更新用户
     */
    @Override
    public boolean update(User u) {
        String sql = "update user set u_num=?,u_name=?,u_img=?,u_account=?,u_pwd=?,u_auto=?,create_time=?,org_id=?,u_create_u_num=? where id =?";
        int a = executeUpdate(sql, u.getUnum(), u.getUname(),u.getUimg(),u.getUaccout(), u.getUpwd(), u.getUauto(), u.getCreateTime(), u.getOrgId(), u.getuCreateUnum(),u.getId());

        return a > 0;
    }

    /**
     * 查询全部用户
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<User> select(User user) {
        StringBuilder sb = new StringBuilder("select * from user  ");
        List params = new ArrayList();//参数集合
        sb.append(createTempSql(user, params));
        return executeQuery(sb.toString(), params.toArray());
    }

    /**
     * 查询单个用户
     */
    @Override
    public User selectSimpleEntityByNum(String id) {

        String sql = " select * from User where id=? ";
        List<User> user = executeQuery(sql, id);
        if (user.isEmpty()) {
            return null;
        }

        return user.get(0);
    }

    /**
     * 分页查询用户
     */
    @Override
    public IPage<User> selectPage(IPage<User> page, User user) {
        int i = page.getNowPage();//获取当前页
        int size = page.getPageSize();//获取页面显示大小
        int si = i - 1 * size;//计算查询其实位置
        final int[] maxDataSize = {0};//数据总数量
        if(si<0){
            si = 0;
        }
        StringBuilder sb = new StringBuilder("select * from user ");
        List params = new ArrayList();//参数集合
        sb.append(createTempSql(user, params));

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
        }, params.toArray());
        sb.append(" limit ?,?");
        params.add(si);
        params.add(size);
        DataPageImp<User> u = new DataPageImp<>(executeQuery(sb.toString(), params.toArray()), maxDataSize[0], page);
        return u;
    }

    /**
     * 查询条件
     *
     * @param list
     * @return
     */
    private String createTempSql(User user, List list) {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNull(user)) {// 判断用户是否为空
            if (!CommonUtil.isNull(user.getId())) {// 判断id是否为空
                sb.append(" ").append(join).append(" id=?");
                list.add(user.getId());
            }
            if (!CommonUtil.isNull(user.getUnum())) {
                sb.append(" ").append(join).append(" u_num =?");
                list.add(user.getUnum());
            }
            if (!CommonUtil.isNull(user.getUaccout())) {// 判断账号是否为空
                sb.append(" ").append(join).append(" u_account =?");
                list.add(user.getUaccout());
            }
            if (!CommonUtil.isNull(user.getUname())) {// 判断名称是否为空
                sb.append(" ").append(join).append(" u_name like ?");
                list.add("%" + user.getUname() + "%");
            }
            if (!CommonUtil.isNull(user.getUauto())) {// 判断权限是否为空
                sb.append(" ").append(join).append(" u_auto =?");
                list.add(user.getUauto());
            }
            if (!CommonUtil.isNull(user.getUpwd())) {// 判断密码是否为空
                sb.append(" ").append(join).append(" u_pwd =?");
                list.add(user.getUpwd());
            }
            if (!CommonUtil.isNull(user.getCreateTime())) {// 判断时间是否为空
                sb.append(" ").append(join).append(" create_time =?");
                list.add(user.getCreateTime());
            }
            if (!CommonUtil.isNull(user.getOrgId())) {
                sb.append(" ").append(join).append(" org_id=?");
                list.add(user.getOrgId());
            }
            if (!CommonUtil.isNull(user.getuCreateUnum())) {
                sb.append(" ").append(join).append(" u_create_u_num=?");
                list.add(user.getuCreateUnum());
            }
            if (sb.length() > 1) {
                String wh = "where 1"+("or".equals(join)?"<>":"=")+"1 ";
                sb.insert(0,wh );
            }
        }
        sb.append(" order by ").append(order);
        return sb.toString();
    }

    @Override
    public User getEntity(ResultSet rs) {
        User user = new User();

        try {
            user.setId(rs.getString("id"));
            user.setUnum(rs.getString("u_num"));
            user.setUname(rs.getString("u_name"));
            user.setUimg(rs.getString("u_img"));
            user.setUaccout(rs.getString("u_account"));
            user.setUpwd(rs.getString("u_pwd"));
            user.setUauto(rs.getString("u_auto"));
            user.setCreateTime(rs.getDate("create_time"));
            user.setOrgId(rs.getString("org_id"));
            user.setuCreateUnum(rs.getString("u_create_u_num"));
        } catch (Exception e) {
            System.out.println("构造用户类异常");

        }
        return user;
    }

}
