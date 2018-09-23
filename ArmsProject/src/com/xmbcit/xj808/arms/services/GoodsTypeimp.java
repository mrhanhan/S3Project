package com.xmbcit.xj808.arms.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xmbcit.xj808.arms.api.dao.IGoodsTypeDao;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.base.IResultSet;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.entity.User;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;

public class GoodsTypeimp extends BaseDao<GoodsType> implements IGoodsTypeDao<GoodsType> {

	public GoodsTypeimp(IConnect iConnect) {
		super(iConnect);		
	}
	/**排序方式*/
	private String order="t_num";

	private String join="and";



	/**
	 * 新增商品类型
	 */
	@Override
	public boolean insert(GoodsType t) {
		String sql = "insert into goods_type values(?,?,?,?,?,?,?,?)";
		return this.executeUpdate(sql, t.getId(), t.getTnum(), t.getTcode(), t.getTname(), t.getTremark(),
				t.getCreateTime(), t.getOrgId(), t.getuCreateUnum()) > 0;
	}

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
	 * 修改商品类型
	 */
	@Override
	public boolean update(GoodsType t) {
		String sql = "update goods_type set t_num=?,t_code=?,t_name=?,t_remark=?,create_time=?,org_id=?,u_create_u_num=? where id=?";
		return this.executeUpdate(sql, t.getTnum(), t.getTcode(), t.getTname(), t.getTremark(), t.getCreateTime(),
				t.getOrgId(), t.getuCreateUnum(), t.getId()) > 0;
	}

	/**
	 * 查询商品类型
	 */
	@Override
	public List<GoodsType> select(GoodsType t) {
		StringBuilder sb = new StringBuilder("select * from goods_type  ");
		List p = new ArrayList();
		sb.append(createTempSql(t,p));

		return this.executeQuery(sb.toString(),p.toArray());
	}

	/**
	 * 创建模板语句
	 * @param t
	 * @param params
	 * @return
	 */
	private String createTempSql(GoodsType t ,List params){
		StringBuilder sb = new StringBuilder();
		if(!CommonUtil.isNull(t)){

			if(!CommonUtil.isNull(t.getId())){
				sb.append(" ").append(join).append(" id=?");
				params.add(t.getId());
			}
			if(!CommonUtil.isNull(t.getTcode())){
				sb.append(" ").append(join).append(" t_code=?");
				params.add(t.getTcode());
			}
			if(!CommonUtil.isNull(t.getTname())){
				sb.append(" ").append(join).append(" t_name like ?");
				params.add("%"+t.getTname()+"%");
			}
			if(!CommonUtil.isNull(t.getTnum())){
				sb.append(" ").append(join).append(" t_num=?");
				params.add(t.getTnum());
			}
			if(!CommonUtil.isNull(t.getTremark())){
				sb.append(" ").append(join).append(" t_remark like ?");
				params.add("%"+t.getTremark()+"%");
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

	/**
	 * 查询单个商品类型
	 */
	@Override
	public GoodsType selectSimpleEntityByNum(String id) {
		String sql = "select * from goods_type where  id =?";
		List<GoodsType> goodsType = executeQuery(sql, id);
		if (goodsType.isEmpty()) {
			return null;
		}
		return goodsType.get(0);
	}

	/**
	 * 分页查询
	 */
	@Override
	public IPage<GoodsType> selectPage(IPage<GoodsType> page, GoodsType templetEntity) {
		int i = page.getNowPage();// 获取当前页
		int size = page.getPageSize();// 获取页面显示大小
		int si = (i - 1) * size;// 计算查询其实位置
		if(si<0){
			si = 0;
		}
		final int[] maxDataSize = { 0 };// 数据总数量
		StringBuilder sb = new StringBuilder("select * from goods_type  ");
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

		DataPageImp<GoodsType> u = new DataPageImp<>(executeQuery(sb.toString(), p.toArray()), maxDataSize[0], page);
		return u;
	}

	@Override
	public GoodsType getEntity(ResultSet rs) {
		GoodsType t = new GoodsType();
		try {
			t.setId(rs.getString(1));
			t.setTnum(rs.getString(2));
			t.setTcode(rs.getString(3));
			t.setTname(rs.getString(4));
			t.setTremark(rs.getString(5));
			t.setCreateTime(rs.getDate(6));
			t.setOrgId(rs.getString(7));
			t.setuCreateUnum(rs.getString(8));

		} catch (Exception e) {

		}

		return t;
	}

}
