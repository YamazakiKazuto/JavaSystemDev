package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        String oldId = user.getId();
        String newId = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        TeacherDao tDao = new TeacherDao();

        if (!oldId.equals(newId)) {
            if (tDao.get(newId) != null) {
                request.setAttribute("error", "その教員IDは既に使用されています。");
                request.getRequestDispatcher("teacher_update.jsp").forward(request, response);
                return;
            }
            tDao.delete(oldId);
        }

        user.setId(newId);
        user.setName(name);
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }

        tDao.save(user);
        session.setAttribute("user", user);

        response.sendRedirect("LoginManage.action");
    }
}