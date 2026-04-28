package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
 
public class StudentDao extends Dao {
 
	private String baseSql = "select * from student where school_cd=? ";
 
    public Student get(String no) throws Exception {

    	no = no.trim(); 
		
		
		Student student=null;

	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT st.no,st.name,st.ent_year,st.class_num,st.is_attend,st.school_cd,sc.name as school_name FROM student as st LEFT JOIN school as sc ON st.school_cd = sc.cd WHERE no = ?");
	    st.setString(1, no);

	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {
            // 学生インスタンスを初期化
            student = new Student();
            School school = new School();
            // 学生インスタンスに検索結果をセット
            student.setNo(rs.getString("no"));
            student.setName(rs.getString("name"));
            student.setEntYear(rs.getInt("ent_year"));
            student.setClassNum(rs.getString("class_num"));
            student.setAttend(rs.getBoolean("is_attend"));
            
            school.setCd(rs.getString("school_cd"));
            school.setName(rs.getString("school_name"));
            student.setSchool(school);
        }


	    rs.close();
	    st.close();
	    con.close();
	 // ヒットしたデータを返す、見つからなければ null
	    return student;
	}
    
 
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        try {
            // リザルトセットを全件走査
            // 引数の名前に合わせて「rSet」に統一
            while (rSet.next()) {
                // 学生インスタンスを初期化
                Student student = new Student();
                // 学生インスタンスに検索結果をセット
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                // リストに追加
                list.add(student);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
 
        return list;
    }
 
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and class_num=?";
        // SQL文のソート
        String order = " order by no asc";
 
        // SQL文の在学フラグ条件
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }
 
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
            // プライベートステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
 
        return list;
    }
 
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        // リストを初期化
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        
        // 【修正ポイント1】class_numを含まない条件にする
        String condition = " and ent_year=? ";
        // SQL文のソート
        String order = " order by no asc";
 
        // SQL文の在学フラグ条件
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = " and is_attend=true";
        }
 
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            
            // 【修正ポイント2】ここにあった statement.setString(3, classNum) を削除
 
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
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
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String order = " order by no asc";
 
        // SQL文の在学フラグ
        String conditionIsAttend = "";
        // 在学フラグがtrueの場合
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
 
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
 
        return list;
    }
    
    
    public boolean save(Student student) throws Exception {

        // すでに存在するか確認
        Student existing = get(student.getNo());

        if (existing == null) {
            // ===== 新規登録 =====
            String sql = """
                INSERT INTO student
                (no, name,ent_year, class_num, is_attend, school_cd)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.isAttend());
            School school =student.getSchool();
            String schoolcd =school.getCd();
            ps.setString(6,schoolcd);

            return ps.executeUpdate() == 1;

        } else {
            // ===== 更新 =====
            String sql = """
                UPDATE student
                SET name = ?, class_num = ?, is_attend = ?
                WHERE no = ?
            """;
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getClassNum());
            ps.setBoolean(3, student.isAttend());
            ps.setString(4, student.getNo());

            return ps.executeUpdate() == 1;
        }
    }
}