package com.xmbcit.xj808.arms.util;

import com.xmbcit.xj808.arms.base.IConnect;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接类
 */
public class MyDBConn implements IConnect {

    /**
     * 数据库连接对象
     */
    private Connection conn;

    @Override
    public Connection getConn() throws SQLException {
        if(conn == null || conn.isClosed()){
            conn =DbUtil.getConnection();
        }
        return conn;
    }

    @Override
    public boolean close(Connection conn) {
        try {
             conn.close();
             return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean isConn() {
        try {
            return !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean beginTrasn() {
        if(isConn()){
            try {
                conn.setAutoCommit(true);
                return true;
            } catch (SQLException e) {
            }

        }
        return false;
    }

    @Override
    public boolean commitTrans() {
        if(isConn()){
            try {
                if(!conn.getAutoCommit()){
                    conn.commit();
                    conn.setAutoCommit(true);
                    return true;
                }

            } catch (SQLException e) {

            }
        }
        return false;
    }

    @Override
    public boolean rollbackTrans() {
        if(isConn()){
            try {
                if(!conn.getAutoCommit()){
                    conn.rollback();
                    conn.setAutoCommit(true);
                    return true;
                }

            } catch (SQLException e) {

            }
        }
        return false;
    }

    @Override
    public IConnect newConn(boolean isConn) {
        MyDBConn myDBConn = new MyDBConn();
        if(isConn){
            try {
                myDBConn.getConn();
            } catch (SQLException e) {


            }
        }
        return myDBConn;
    }

    /**
     * 关闭连接方法
     */
    @Override
    public void close(){
        try {
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
