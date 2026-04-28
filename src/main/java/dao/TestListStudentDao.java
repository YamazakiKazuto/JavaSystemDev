// 山崎壱人
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private String baseSql =
        "select su.name as subject_name, t.subject_cd, t.no, t.point " +
        "from test t " +
        "right join subject su on t.subject_cd = su.cd ";

    /**
     * ResultSet → List<TestListStudent>
     */
    private List<TestListStudent> postFilter(ResultSet rs) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        while (rs.next()) {
        	System.out.println(" swswswswswsws");
            TestListStudent tls = new TestListStudent();
            tls.setSubjectName(rs.getString("subject_name"));
            tls.setSubjectCd(rs.getString("subject_cd"));
            tls.setNum(rs.getInt("no"));
            tls.setPoint(rs.getInt("point"));

            list.add(tls);
        }
        
        return list;
    }

    /**
     * 学生別成績一覧
     */
    public List<TestListStudent> filter(Student student) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        System.out.println("student_no = "+student.getNo());
        System.out.println("school_cd = "+student.getSchool().getCd());
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(
            baseSql +
            "where t.student_no=? and t.school_cd = ? "
        );

        ps.setString(1, student.getNo());
        ps.setString(2, student.getSchool().getCd());

        ResultSet rs = ps.executeQuery();        
        list = postFilter(rs);

        rs.close();
        ps.close();
        con.close();

        return list;
    }
    
    
}
