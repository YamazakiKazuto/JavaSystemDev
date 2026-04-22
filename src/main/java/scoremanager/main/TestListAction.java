package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	HttpSession session = request.getSession();
    	Teacher user = (Teacher) session.getAttribute("user");
    	
        School school =user.getSchool();
        // クラス一覧取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classList = classNumDao.filter(school);

        // 科目一覧取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(school);
        
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }
        session.setAttribute("entyearset", entYearSet);
        session.setAttribute("classlist", classList);
        session.setAttribute("subjectlist", subjectList);

        request.getRequestDispatcher("test_list.jsp")
        .forward(request, response);
    }
}