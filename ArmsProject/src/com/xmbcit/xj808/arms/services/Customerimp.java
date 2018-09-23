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
import com.xmbcit.xj808.arms.entity.Customer;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

public class Customerimp extends BaseDao<Customer> implements IBullitenDao<Customer> {

	public Customerimp(IConnect iConnect) {
		super(iConnect);
	}

	/** 排序方式ʽ */
	private String order = "c_num";

	private String join = "and";

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
	public Customer getEntity(ResultSet rs) {
		Customer cu = new Customer();
		try {
			cu.setId(rs.getString(1));
			cu.setCnum(rs.getString(2));
			cu.setCimg(rs.getString("c_img"));
			cu.setCname(rs.getString("c_name"));
			cu.setCaddress(rs.getString(5));
			cu.setCtelephone(rs.getString(6));
			cu.setCaccount(rs.getString(7));
			cu.setCpwd(rs.getString(8));
			cu.setCemail(rs.getString(9));
			cu.setCreateTime(rs.getDate(10));
			cu.setOrgId(rs.getString(11));
			cu.setuCreateUnum(rs.getString(12));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cu;
	}

	/**
	 * 新增
	 */
	@Override
	public boolean insert(Customer t) {
		String sql = "insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.executeUpdate(sql, t.getId(), t.getCnum(), t.getCname(), t.getCimg(), t.getCaddress(),
				t.getCtelephone(), t.getCaccount(), t.getCpwd(), t.getCemail(), t.getCreateTime(), t.getOrgId(),
				t.getuCreateUnum()) > 0;
	}

	/**
	 * 修改
	 */
	@Override
	public boolean update(Customer t) {
		String sql = "update customer set c_img=?,c_name=?,c_address=?,c_telephone=?,c_account=?,c_pwd=?,c_email=?,create_time=?,org_id=?,u_create_u_num=? where id=?";
		return this.executeUpdate(sql, t.getCimg(), t.getCname(), t.getCaddress(), t.getCtelephone(),
				t.getCaccount(), t.getCpwd(), t.getCemail(), t.getCreateTime(), t.getOrgId(), t.getuCreateUnum(),
				t.getId()) > 0;
	}

	/**
	 * 查询
	 */
	@Override
	public List<Customer> select(Customer t) {
		StringBuilder sb = new StringBuilder("select * from customer  ");
		List p = new ArrayList();
		sb.append(createTempSql(t, p));

		return this.executeQuery(sb.toString(), p.toArray());
	}

	/**
	 * 查询条件
	 * 
	 * @param t
	 * @param params
	 * @return
	 */
	private Object createTempSql(Customer t, List params) {
		StringBuilder sb = new StringBuilder();
		if (!CommonUtil.isNull(t)) {
			if (!CommonUtil.isNull(t.getId())) {
				sb.append(" ").append(join).append(" id=?");
				params.add(t.getId());
			}
			if (!CommonUtil.isNull(t.getCnum())) {
				sb.append(" ").append(join).append(" c_num=?");
				params.add(t.getCnum());
			}
			if (!CommonUtil.isNull(t.getCimg())) {
				sb.append(" ").append(join).append(" c_img=?");
				params.add(t.getCimg());
			}
			if (!CommonUtil.isNull(t.getCname())) {
				sb.append(" ").append(join).append(" c_name like ? ");
				params.add("%" + t.getCname() + "%");
			}
			if (!CommonUtil.isNull(t.getCaddress())) {
				sb.append(" ").append(join).append(" c_address=?");
				params.add("%" + t.getCaddress() + "%");
			}
			if (!CommonUtil.isNull(t.getCtelephone())) {
				sb.append(" ").append(join).append(" c_telephone =?");
				params.add( t.getCtelephone() );
			}
			if (!CommonUtil.isNull(t.getCaccount())) {
				sb.append(" ").append(join).append(" c_account=?");
				params.add( t.getCaccount() );
			}

			if (!CommonUtil.isNull(t.getCpwd())) {
				sb.append(" ").append(join).append(" c_pwd=?");
				params.add(t.getCpwd());
			}
			if (!CommonUtil.isNull(t.getCemail())) {
				sb.append(" ").append(join).append(" c_email=?");
				params.add(t.getCemail());
			}
			if (!CommonUtil.isNull(t.getCreateTime())) {
				sb.append(" ").append(join).append(" create_time=?");
				params.add( t.getCreateTime() );
			}
			if (!CommonUtil.isNull(t.getOrgId())) {
				sb.append(" ").append(join).append(" org_id=?");
				params.add( t.getOrgId() );
			}
			if (!CommonUtil.isNull(t.getuCreateUnum())) {
				sb.append(" ").append(join).append(" c_create_u_num=?");
				params.add(t.getuCreateUnum());
			}if(sb.length()>1){
				sb.insert(0,"where 1"+("and".equals(join)?"=":"<>")+"1 ");
			}
		}
		sb.append(" order by ").append(order);
		return sb.toString();
	}

	/**
	 * 查询单个
	 */
	@Override
	public Customer selectSimpleEntityByNum(String id) {
		String sql = "select * from customer where  id =?";
		List<Customer> customer = executeQuery(sql, id);
		if (customer.isEmpty()) {
			return null;
		}
		return customer.get(0);
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Customer> selectPage(IPage<Customer> page, Customer templetEntity) {
		int i = page.getNowPage();// 获取当前页
		int size = page.getPageSize();// 获取页面显示大小
		int si = (i - 1) * size;// 计算查询其实位置
		if (si < 0) {
			si = 0;
		}
		final int[] maxDataSize = { 0 };// 数据总数量
		StringBuilder sb = new StringBuilder("select * from Customer  ");
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

		DataPageImp<Customer> u = new DataPageImp<>(executeQuery(sb.toString(), p.toArray()), maxDataSize[0], page);
		return u;
	}

}
