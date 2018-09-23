package com.xmbcit.xj808.arms.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.xmbcit.xj808.arms.api.dao.IOrderInfoDao;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.base.IResultSet;
import com.xmbcit.xj808.arms.entity.OrderInfo;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

/**
 * 订单实现接口
 * 
 * @author ALEX
 *
 */
public class OrderInfoImpl extends BaseDao<OrderInfo> implements IOrderInfoDao<OrderInfo> {

	public OrderInfoImpl(IConnect iConnect) {
		super(iConnect);

	}

	/** 排序方式 */
	private String order = "o_num";

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

	/** 增加订单 */
	@Override
	public boolean insert(OrderInfo t) {
		String sql = "insert into orderinfo values(?,?,?,?,?,?,?,?,?)";
		int a = executeUpdate(sql, t.getId(), t.getOnum(), t.getCnum(), t.getOmoney(), t.getOcount(), t.getOdate(),
				t.getCreateTime(), t.getOrgId(), t.getuCreateUnum());
		return a>0;
	}

	/** 更新订单 */
	@Override
	public boolean update(OrderInfo t) {
		String sql = "update orderinfo set o_num=?,c_num=?,o_money=?,o_count=?,o_date=?,create_time=?,org_id=?,u_create_u_num=? where id =?";
		int a = executeUpdate(sql, t.getOnum(), t.getCnum(), t.getOmoney(), t.getOcount(), t.getOdate(), t.getCreateTime(),
				t.getOrgId(), t.getuCreateUnum(), t.getId());
		return a>0;
	}

	/** 查询订单列表 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<OrderInfo> select(OrderInfo orderinfo) {
		StringBuilder sql = new StringBuilder("select * from orderinfo ");
		List params = new ArrayList();// 参数集合
		sql.append(createTempSql(orderinfo, params));
		return executeQuery(sql.toString(), params.toArray());

	}

	private String createTempSql(OrderInfo orderinfo, List params) {
		StringBuilder sb = new StringBuilder();
		if (!CommonUtil.isNull(orderinfo)) {// 判断orderinfo是否为空
			if (!CommonUtil.isNull(orderinfo.getId())) {// 判断id是否为空
				sb.append(" ").append(join).append(" id=?");
				params.add(orderinfo.getId());
			}
			if (!CommonUtil.isNull(orderinfo.getOnum())) {// 判断商品是否为空
				sb.append(" ").append(join).append(" o_num =?");
				params.add(orderinfo.getOnum());
			}
			if (!CommonUtil.isNull(orderinfo.getCnum())) {// 判断顾客是否为空
				sb.append(" ").append(join).append(" c_num =?");
				params.add(orderinfo.getCnum());
			}
			if (orderinfo.getOmoney()!=null) {// 判断商品总价是否为空
				sb.append(" ").append(join).append(" o_money =?");
				params.add(orderinfo.getOmoney());
			}
			if (orderinfo.getOcount()!=0) {// 判断购买数量是否为空
				sb.append(" ").append(join).append(" o_count =?");
				params.add(orderinfo.getOcount());
			}
			if (!CommonUtil.isNull(orderinfo.getOdate())) {// 判断时间是否为空
				sb.append(" ").append(join).append(" o_date =?");
				params.add(orderinfo.getOdate());
			}
			if(!CommonUtil.isNull(orderinfo.getCreateTime())){
				sb.append(" ").append(join).append(" create_time=?");
				params.add(orderinfo.getCreateTime());
			}
			if(!CommonUtil.isNull(orderinfo.getOrgId())){
				sb.append(" ").append(join).append(" org_id=?");
				params.add(orderinfo.getOrgId());
			}
			if(!CommonUtil.isNull(orderinfo.getuCreateUnum())){
				sb.append(" ").append(join).append(" u_create_u_num=?");
				params.add(orderinfo.getuCreateUnum());
			}
			if (sb.length() > 1) {
				String wh = "where 1"+("or".equals(join)?"<>":"=")+"1 ";
				sb.insert(0,wh );
			}
		}
		sb.append(" order by ").append(order);
		return sb.toString();
	}

	/** 查询单个订单 */
	@Override
	public OrderInfo selectSimpleEntityByNum(String o_num) {
		String sql = "select * from orderinfo where o_num =?";
		List<OrderInfo> orderinfo = executeQuery(sql, o_num);
		if (orderinfo.isEmpty()) {
			return null;
		}
		return orderinfo.get(0);
	}

	/** 查询分页 */
	@Override
	public IPage<OrderInfo> selectPage(IPage<OrderInfo> page, OrderInfo orderinfo) {
		int i = page.getNowPage();// 获取当前页
		int size = page.getPageSize();// 获取页面显示大小
		int si = (i - 1) * size;// 计算查询其实位置
		if (si < 0) {
			si = 0;
		}
		final int[] maxDataSize = { 0 };// 数据总数量
		StringBuilder sb = new StringBuilder("select * from orderinfo  ");
		List p = new ArrayList();
		sb.append(createTempSql(orderinfo, p));

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

		DataPageImp<OrderInfo> u = new DataPageImp<>(executeQuery(sb.toString(), p.toArray()), maxDataSize[0], page);
		return u;

	}

	/** 创建OrderInfo实体类 */
	@Override
	public OrderInfo getEntity(ResultSet rs) {
		OrderInfo orderinfo = new OrderInfo();
		try {
			orderinfo.setId(rs.getString("id"));
			orderinfo.setOnum(rs.getString("o_num"));
			orderinfo.setCnum(rs.getString("c_num"));
			orderinfo.setOmoney(rs.getBigDecimal("o_money"));
			orderinfo.setOcount(rs.getInt("o_count"));
			orderinfo.setOdate(rs.getTimestamp("o_date"));
			orderinfo.setCreateTime(rs.getTimestamp("create_time"));
			orderinfo.setOrgId(rs.getString("org_id"));
			orderinfo.setuCreateUnum(rs.getString("u_create_u_num"));
		} catch (Exception e) {
			System.out.println("构造订单类类异常");
		}
		return orderinfo;
	}

}
