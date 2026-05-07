package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginManageAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        
        TeacherDao tDao = new TeacherDao();
        // 所属学校の教員一覧を取得（TeacherDaoにメソッドを追加します）
        List<Teacher> teachers = tDao.filter(user.getSchool());
        
        // JSPにデータを渡す
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("login_manage.jsp").forward(request, response);
    }
}