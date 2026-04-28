// 内田桔梗
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School; // Schoolクラスも使用するためインポート
import bean.Teacher;

public class TeacherDao extends Dao {
    /**
     * IDとパスワードで教員を検索する
     */
    public Teacher search(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection con = getConnection();

        // テーブル名とカラム名は一般的な設計（teacher, school_cd）を想定
        String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, password);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setPassword(rs.getString("password"));
                    
                    // --- Schoolオブジェクトのセット ---
                    School school = new School();
                    school.setCd(rs.getString("school_cd")); 

                    teacher.setSchool(school);
                }
            }
        } finally {
            // Dao.java の設計に合わせて適切に close
            if (con != null) con.close();
        }
        
        return teacher;
    }
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection con = getConnection();

        // IDとパスワードで教員テーブルを検索
        String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, password);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    // データが見つかった場合、Teacherインスタンスを生成して値をセット
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setPassword(rs.getString("password"));
                    
                    // 学校情報のセット（必要に応じて）
                    School school = new School();
                    school.setCd(rs.getString("school_cd"));
                    teacher.setSchool(school);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        
        return teacher;
    }
}
