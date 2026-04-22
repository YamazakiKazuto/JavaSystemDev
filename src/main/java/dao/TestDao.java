
package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
 
public class TestDao extends Dao {
 
    private String baseSql ="select * from test where school_cd=? ";
 
 
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
 
        Test test = null;
 
        Connection con = getConnection();
 
        String sql =
            "select * from test " +
            "where school_cd=? AND student_no=? AND subject_cd=? AND no=?";
 
        PreparedStatement ps = con.prepareStatement(sql);
 
        ps.setString(1, school.getCd());
        ps.setString(2, student.getNo());
        ps.setString(3, subject.getCd());
        ps.setInt(4, no);
 
        ResultSet rs = ps.executeQuery();
 
        if (rs.next()) {
            test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));
            test.setClassNum(rs.getString("class_num"));
        }
 
        rs.close();
        ps.close();
        con.close();
        return test;
    }
 
 
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
 
        List<Test> list = new ArrayList<>();
 
        while (rSet.next()) {
            Test test = new Test();
 
            Student student = new Student();
            student.setNo(rSet.getString("student_no"));
            test.setStudent(student);
            test.setClassNum(rSet.getString("class_num"));
            Subject subject = new Subject();
            subject.setCd(rSet.getString("subject_cd"));
            test.setSubject(subject);
 
            test.setSchool(school);
            test.setNo(rSet.getInt("no"));
            test.setPoint(rSet.getInt("point"));
 
            list.add(test);
        }
 
        return list;
    }
 
 
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school)
            throws Exception {
 
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();
 
        String sql =
            "select s.no as student_no, s.name, s.class_num, " +
            "t.no as test_no, t.point, t.subject_cd " +
            "from student s " +
            "left join test t on s.no = t.student_no " +
            "and t.subject_cd = ? " +
            "and t.no = ? " +
            "and t.school_cd = ? " +
            "where s.school_cd = ? " +
            "and s.ent_year = ? " +
            "and s.class_num = ? " +
            "order by s.no asc";
 
        PreparedStatement ps = con.prepareStatement(sql);
 
        ps.setString(1, subject.getCd());
        ps.setInt(2, num);
        ps.setString(3, school.getCd());
        ps.setString(4, school.getCd());
        ps.setInt(5, entYear);
        ps.setString(6, classNum);
 
        ResultSet rs = ps.executeQuery();
 
        while (rs.next()) {
            Test test = new Test();
 
            // 学生
            Student student = new Student();
            student.setNo(rs.getString("student_no"));
            student.setName(rs.getString("name")); // ← 重要
            test.setStudent(student);
 
            test.setClassNum(rs.getString("class_num"));
 
            // 科目
            test.setSubject(subject);
 
            test.setSchool(school);
 
            // 回数
            test.setNo(num);
 
            // 点数（null対策）
            int point = rs.getInt("point");
            if (rs.wasNull()) {
                test.setPoint(0); // 空扱い
            } else {
                test.setPoint(point);
            }
 
            list.add(test);
        }
 
        rs.close();
        ps.close();
        con.close();
 
        return list;
    }
 
    /**
     * 一括保存
     */
    public boolean save(List<Test> list) throws Exception {
 
        Connection con = getConnection();
        con.setAutoCommit(false);
 
        try {
            for (Test test : list) {
                save(test, con);
            }
            con.commit();
            return true;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }

 
 
    /**
     * 単体保存（INSERT / UPDATE）
     */
    public boolean save(Test test, Connection con) throws Exception {
 
        Test existing =
            get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
        
        if (existing == null) {
            // INSERT
        	String sql =
        		    "insert into test (student_no, subject_cd, school_cd, no, point, class_num) " +
        		    "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, test.getStudent().getNo());
            ps.setString(2, test.getSubject().getCd());
            ps.setString(3, test.getSchool().getCd());
            ps.setInt(4, test.getNo());
            ps.setInt(5, test.getPoint());
            ps.setString(6, test.getClassNum());
 
            
            return ps.executeUpdate() == 1;
 
        } else {
            // UPDATE
        	String sql =
        		    "update test set point=?, class_num=? " +
        		    "where student_no=? and subject_cd=? and school_cd=? and no=?";
        	PreparedStatement ps = con.prepareStatement(sql);
 
        	ps.setInt(1, test.getPoint());
        	ps.setString(2, test.getClassNum()); // ← 追加
 
        	ps.setString(3, test.getStudent().getNo());
        	ps.setString(4, test.getSubject().getCd());
        	ps.setString(5, test.getSchool().getCd());
        	ps.setInt(6, test.getNo());
 
            return ps.executeUpdate() == 1;
        }
    }
}