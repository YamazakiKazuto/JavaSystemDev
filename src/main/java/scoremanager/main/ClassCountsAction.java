package scoremanager.main;

import java.util.Map;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCountsAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNumDao cDao = new ClassNumDao();
        Map<String, Integer> counts = cDao.countStudentsByClass(teacher.getSchool());

        request.setAttribute("classCounts", counts);
        request.getRequestDispatcher("class_counts.jsp").forward(request, response);
    }
}