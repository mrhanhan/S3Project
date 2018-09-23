package com.xmbcit.xj808.arms.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.xmbcit.xj808.arms.api.dao.IImagesDao;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.base.BaseDao;
import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.entity.Images;

public class ImagesImpl extends BaseDao<Images> implements IImagesDao<Images> {

	public ImagesImpl(IConnect iConnect) {
		super(iConnect);
	}

	@Override
	public Images getEntity(ResultSet rs) {
		Images images = new Images();
		try {
			images.setId(rs.getString(1));
			images.setIpic(rs.getString(2));
			images.setImessage(rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return images;
	}

	@Override
	public boolean insert(Images t) {
		String sql = "insert into images values(?,?,?)";
		return this.executeUpdate(sql, t.getId(), t.getIpic(), t.getImessage()) > 0;
	}

	@Override
	public boolean update(Images t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Images> select(Images t) {
		if(t==null){
			return  executeQuery("select * from images");
		}
		String sql = "select * from images where i_pic=?";
		return this.executeQuery(sql,t.getIpic());
	}

	@Override
	public Images selectSimpleEntityByNum(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPage<Images> selectPage(IPage<Images> page, Images templetEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
