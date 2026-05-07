//制作者　内田
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    /**
     * IDとパスワードで教員を検索する（ログイン認証用）
     */
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection con = getConnection();
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
                    
                    School school = new School();
                    school.setCd(rs.getString("school_cd"));
                    teacher.setSchool(school);
                }
            }
        } finally {
            if (con != null) con.close();
        }
        return teacher;
    }

    /**
     * IDから教員情報を1件取得する（重複チェック・個別取得用）
     */
    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        Connection con = getConnection();
        String sql = "SELECT * FROM teacher WHERE id = ?";
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
                    teacher.setName(rs.getString("name"));
                    teacher.setPassword(rs.getString("password"));
                    
                    School school = new School();
                    school.setCd(rs.getString("school_cd"));
                    teacher.setSchool(school);
                }
            }
        } finally {
            if (con != null) con.close();
        }
        return teacher;
    }

    /**
     * 所属学校の教員一覧を取得する（ログイン管理画面用）
     */
    public List<Teacher> filter(School school) throws Exception {
        List<Teacher> list = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT * FROM teacher WHERE school_cd = ? ORDER BY id ASC";
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, school.getCd());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Teacher t = new Teacher();
                    t.setId(rs.getString("id"));
                    t.setName(rs.getString("name"));
                    t.setPassword(rs.getString("password"));
                    t.setSchool(school);
                    list.add(t);
                }
            }
        } finally {
            if (con != null) con.close();
        }
        return list;
    }

    /**
     * 教員情報を保存する（新規登録・更新の両方に対応）
     */
    public boolean save(Teacher teacher) throws Exception {
        Connection con = getConnection();
        // H2 DatabaseのMERGE文を使用（IDが重複していればUPDATE、なければINSERT）
        String sql = "MERGE INTO teacher KEY(id) VALUES (?, ?, ?, ?)";
        int count = 0;
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, teacher.getId());
            st.setString(2, teacher.getPassword());
            st.setString(3, teacher.getName());
            st.setString(4, teacher.getSchool().getCd());
            
            count = st.executeUpdate();
        } finally {
            if (con != null) con.close();
        }
        return count > 0;
    }

    /**
     * 教員情報を削除する
     */
    public boolean delete(String id) throws Exception {
        Connection con = getConnection();
        String sql = "DELETE FROM teacher WHERE id = ?";
        int count = 0;
        
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            count = st.executeUpdate();
        } finally {
            if (con != null) con.close();
        }
        return count > 0;
    }
}