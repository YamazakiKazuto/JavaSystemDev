package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // ベースSQL（学校コードで絞る前提）
    private String baseSql = "select * from subject where school_cd=? ";

    // 主キーで1件取得
    public Subject get(String cd, String schoolCd) throws Exception {
    	 
        Subject subject = null;
 
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
 
        String sql = baseSql + " and cd=?";
 
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, schoolCd);
            statement.setString(2, cd);
 
            rSet = statement.executeQuery();
 
            if (rSet.next()) {
                subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchoolCd(rSet.getString("school_cd"));
            }
 
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
 
        return subject;
    }

    // ResultSet → List<Subject> 変換
    private List<Subject> postFilter(ResultSet rSet) throws Exception {

        List<Subject> list = new ArrayList<>();

        try {
            while (rSet.next()) {

                Subject subject = new Subject();

                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchoolCd(rSet.getString("school_cd"));

                list.add(subject);
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 学校コードで絞り込み
    public List<Subject> filter(String schoolCd) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        String order = " order by cd asc";

        try {
            statement = connection.prepareStatement(baseSql + order);
            statement.setString(1, schoolCd);

            rSet = statement.executeQuery();

            list = postFilter(rSet);

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }

        return list;
    }

    // 保存（insert or update）
    public boolean save(Subject subject) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;


        // 既存チェック
        Subject old = get(subject.getCd(),subject.getSchoolCd());

        String sql;

        if (old == null) {
            // INSERT
            sql = "insert into subject(school_cd,cd, name) values(?, ?, ?)";
        } else {
            // UPDATE
            sql = "update subject set name=?, school_cd=? where cd=?";
        }

        try {
            statement = connection.prepareStatement(sql);

            if (old == null) {
                statement.setString(1, subject.getSchoolCd());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getName());
            } else {
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getSchoolCd());
                statement.setString(3, subject.getCd());
            }

            int result = statement.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
    }
    public boolean delete(String cd, String schoolCd) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String sql = "delete from subject where cd=? and school_cd=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, cd);
            statement.setString(2, schoolCd);
            int result = statement.executeUpdate();
            return result > 0;
 
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }
    }
}
