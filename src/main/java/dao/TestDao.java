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
        PreparedStatement ps = con.prepareStatement(
            baseSql+"student_no=?"
        );
        ps.setString(1, school.getCd());
        ps.setString(2, student.getNo());
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            test = new Test();
            test.setStudent(student);
            test.setClassNum(student.getClassNum());
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));
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
            "select t.* from test t join student s on t.student_no = s.no join subject su on t.subject_cd = su.school_cd  where s.school_cd=?"+
            "and s.ent_year=? and s.class_num=? and t.subject_cd=? order by s.no asc";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, school.getCd());
        ps.setInt(2, entYear);
        ps.setString(3, classNum);
        ps.setString(4, subject.getCd());

        ResultSet rs = ps.executeQuery();
        list = postFilter(rs, school);

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
                "insert into test (student_no, subject_cd, school_cd, no, point) " +
                "values (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, test.getStudent().getNo());
            ps.setString(2, test.getSubject().getCd());
            ps.setString(3, test.getSchool().getCd());
            ps.setInt(4, test.getNo());
            ps.setInt(5, test.getPoint());

            return ps.executeUpdate() == 1;

        } else {
            // UPDATE
            String sql =
                "update test set point=? " +
                "where student_no=? and subject_cd=? and school_cd=? and no=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, test.getPoint());
            ps.setString(2, test.getStudent().getNo());
            ps.setString(3, test.getSubject().getCd());
            ps.setString(4, test.getSchool().getCd());
            ps.setInt(5, test.getNo());

            return ps.executeUpdate() == 1;
        }
    }
}

