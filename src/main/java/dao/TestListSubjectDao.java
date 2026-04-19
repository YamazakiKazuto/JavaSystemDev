package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private String baseSql =
        "select s.no as student_no, s.name as student_name, " +
        "s.class_num, t.no, t.point " +
        "from test t " +
        "join student s on t.student_no = s.no ";

    /**
     * ResultSet → List<TestListSubject>
     */
    private List<TestListSubject> postFilter(
            ResultSet rs, Subject subject, School school)
            throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        while (rs.next()) {
            TestListSubject tls = new TestListSubject();

            // Student
            Student student = new Student();
            student.setNo(rs.getString("student_no"));
            student.setName(rs.getString("student_name"));
            tls.setStudent(student);

            // class
            tls.setClassNum(rs.getString("class_num"));

            // subject / school
            tls.setSubject(subject);
            tls.setSchool(school);

            // test
            tls.setNo(rs.getInt("no"));
            tls.setPoint(rs.getInt("point"));

            list.add(tls);
        }

        return list;
    }

    /**
     * 科目別成績一覧取得
     */
    public List<TestListSubject> filter(
            int entYear, String classNum, Subject subject, School school)
            throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            baseSql +
            "where s.school_cd=? and s.ent_year=? and s.class_num=? " +
            "and t.subject_cd=? " +
            "order by s.no"
        );

        ps.setString(1, school.getCd());
        ps.setInt(2, entYear);
        ps.setString(3, classNum);
        ps.setString(4, subject.getCd());

        ResultSet rs = ps.executeQuery();
        list = postFilter(rs, subject, school);

        rs.close();
        ps.close();
        con.close();

        return list;
    }
}