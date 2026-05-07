//制作者　石川
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action; // 重要：Actionをインポート

public class TestRegistExecuteAction extends Action { // 重要：Actionを継承

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("UTF-8");
        TestDao tDao = new TestDao();
        
        
        String[] studentNos = request.getParameterValues("student_no");
        String[] points = request.getParameterValues("point");
        String classNum = request.getParameter("class_num");
        String subjectCode = request.getParameter("subject_code");
        String noStr = request.getParameter("no");

        List<Test> testList = new ArrayList<>();
        
        HttpSession session = request.getSession();
        List<Test> tests = (List<Test>) session.getAttribute("tests");
        List<String> studentNames = new ArrayList<>();

        for (Test test : tests) {
            studentNames.add(test.getStudent().getName());
        }

        
        Teacher user = (Teacher) session.getAttribute("user");
        School school = new School();
        school.setCd(user.getSchool().getCd());

        int count=0;
        if (points != null) {
            for (int i = 0; i < points.length; i++) { // 配列なので .length を使用
                int point = Integer.parseInt(points[i]);                

                Test test = new Test();
                
                if (point < 0 || point > 100) {
            		test.setJudgePoint(true);
            		request.setAttribute("error","0〜100の範囲で入力してください");
            		count++;
            		System.out.println(count);
            	}
                
                Student student = new Student();
                student.setNo(studentNos[i]);
                student.setName(studentNames.get(i));
                test.setStudent(student);
                
                Subject subject = new Subject();
                subject.setCd(subjectCode);
                test.setSubject(subject); // setSubject(Subject) を使用
                
                test.setSchool(school);
                test.setPoint(point);
                test.setClassNum(classNum);
                test.setNo(Integer.parseInt(noStr));
                
                testList.add(test);
            }
        }
        if (count != 0) {
        	session.setAttribute("tests",testList);
        	request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        	return;
        }
        tDao.save(testList);
        session.removeAttribute("tests");
        session.removeAttribute("ent_year");
        session.removeAttribute("class_num");
        session.removeAttribute("no");
        session.removeAttribute("subject");
        session.removeAttribute("subject_code");
        session.removeAttribute("ent_years");
        session.removeAttribute("class_nums");
        session.removeAttribute("subjects");
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}