// 石川煌生
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap; // HashMapからTreeMapに変更

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    /**
     * クラス番号と学校からクラス情報を取得する（存在チェック用）
     */
    public ClassNum get(String classNum, School school) throws Exception {
        ClassNum cn = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(
                "select * from class_num where class_num=? and school_cd=?"
            );
            statement.setString(1, classNum);
            statement.setString(2, school.getCd());
            rSet = statement.executeQuery();

            if (rSet.next()) {
                cn = new ClassNum();
                cn.setClassNum(rSet.getString("class_num"));
                cn.setSchool(school);
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return cn;
    }

    /**
     * すべてのクラスと所属人数を【昇順】で取得
     */
    public Map<String, Integer> countStudentsByClass(School school) throws Exception {
        // TreeMapを使うことで、キー（クラス番号）の小さい順に自動で並びます
        Map<String, Integer> map = new TreeMap<>(); 
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = "SELECT c.class_num, COUNT(s.no) as cnt " +
                         "FROM class_num c " +
                         "LEFT JOIN student s ON c.class_num = s.class_num AND c.school_cd = s.school_cd " +
                         "WHERE c.school_cd = ? " +
                         "GROUP BY c.class_num " +
                         "ORDER BY c.class_num ASC"; // SQLでも昇順を指定
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();

            while (rSet.next()) {
                map.put(rSet.getString("class_num"), rSet.getInt("cnt"));
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return map;
    }

    /**
     * クラスを新規登録
     */
    public boolean save(ClassNum classNum) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(
                "insert into class_num (class_num, school_cd) values (?, ?)"
            );
            statement.setString(1, classNum.getClassNum());
            statement.setString(2, classNum.getSchool().getCd());
            count = statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return count > 0;
    }

    /**
     * クラスを削除
     */
    public boolean delete(ClassNum classNum) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(
                "delete from class_num where class_num=? and school_cd=?"
            );
            statement.setString(1, classNum.getClassNum());
            statement.setString(2, classNum.getSchool().getCd());
            count = statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return count > 0;
    }

    /**
     * 重複チェック用の一覧取得
     */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                "select class_num from class_num where school_cd=? order by class_num ASC"
            );
            statement.setString(1, school.getCd());
            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                list.add(rSet.getString("class_num"));
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }
}
