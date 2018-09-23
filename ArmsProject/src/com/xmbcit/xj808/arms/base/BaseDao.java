package com.xmbcit.xj808.arms.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 基础操作类，所有操作实体操作类的基类<br/>
 * 提供操作sql语句执行的操作
 *
 * @param <T>
 * @author MrHanHao
 */

public abstract class BaseDao<T> extends BaseObject implements IEntity<T> {

    /*连接对象*/
    private IConnect conn;
    /**
     * 无参构造函数*
     *
     * @param iConnect 连接对象
     */
    public BaseDao(IConnect iConnect) {
        conn = iConnect;
    }
    public IConnect getConn(){
        return conn;
    }
    /**
     * 执行增删改的sql语句，并返回受影响的行数
     *
     * @param sql    sql语句
     * @param params 参数值
     * @return 受影响的行数
     */
    protected int executeUpdate(String sql, Object... params) {
        int a = 0;
        PreparedStatement ps = null;
        try {
            /* 创建语句并执行 */
            ps = createStatement(sql, params);
      //      System.out.println(ps.toString());
            a = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            close(null,ps);
        }
        return a;
    }
    /**
     * 根据携带参数的SQL语句和提供的值，创建预编译操作对象
     *
     * @param sql    携带参数的sql语句
     * @param params 参数值
     * @return 预编译操作对象
     * @throws SQLException
     */
    private PreparedStatement createStatement(String sql, Object... params) throws SQLException {
        /* 创建对象，并设置参数值 */
      //  System.out.println(sql+" size:"+ Arrays.toString(params));
        PreparedStatement ps = conn.getConn().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
       // System.out.println(ps);
        return ps;
    }
    /**
     * 执行查询的sql语句，并返回实体结果集
     *
     * @param sql    sql语句
     * @param params 参数值
     * @return 返回List结果集合，空集合代表无结果
     */
    protected List<T> executeQuery(String sql, Object... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector<T> entitys = new Vector<>();
        try {
            /*打开链接创建语句并执行*/
            ps = createStatement(sql, params);
          //  System.out.println(ps);
            rs = ps.executeQuery();
            /*根据结果集创建实体集合*/
            while (rs.next()) {
                entitys.add(getEntity(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs,ps);
        }

        return entitys;
    }

    private void close(ResultSet rs, PreparedStatement ps) {
        try {
            if (rs != null)
                rs.close();
            if (ps != null) {

                ps.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方法，查询制定表，通过，创建实体类接口，创建出制定结果集合
     * @param sql sql查询语句
     * @param entity 创建实体类的对象
     * @param params 参数
     * @param <E> int a = execQuery(...)  E = int
     * @return 返回查询结果集合 List<E>
     */
    protected <E> List<E> execQuery(String sql, IEntity<E> entity, Object... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vector<E> entitys = new Vector<>();
        try {
            /*打开链接创建语句并执行*/
            ps = createStatement(sql, params);
            rs = ps.executeQuery();
            /*根据结果集创建实体集合*/
            while(rs.next()){
                E e =entity.getEntity(rs);
                entitys.add(e);
            }

        } catch (Exception e) {

        } finally {
            close(rs,ps);
        }
        return entitys;
    }


    /**
     * 执行查询方法，将结果集交给制定对象 entityl来进行处理
     * @param sql
     * @param entity
     * @param params
     */
    protected void execQuery(String sql, IResultSet entity, Object... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            /*打开链接创建语句并执行*/
            ps = createStatement(sql, params);
            rs = ps.executeQuery();
            /*根据结果集创建实体集合*/
           entity.result(rs);

        } catch (Exception e) {

        } finally {
            close(rs,ps);
        }
    }
}
