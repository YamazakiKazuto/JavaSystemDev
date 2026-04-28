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
        String entYear = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num");
        String subjectCode = request.getParameter("subject_code");
        String noStr = request.getParameter("no");

        List<Test> testList = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        School school = new School();
        school.setCd(user.getSchool().getCd());

        if (points != null) {
            for (int i = 0; i < points.length; i++) { // 配列なので .length を使用
                int point = Integer.parseInt(points[i]);
                

                Test test = new Test();
                Student student = new Student();
                student.setNo(studentNos[i]);
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

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            // エラー時は検索画面のロジックを再実行
            new TestRegistAction().execute(request, response);
            return;
        }

        tDao.save(testList);
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}