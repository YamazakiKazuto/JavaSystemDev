//制作者　内田
package scoremanager.main;

import bean.Role;
import bean.Teacher;
import dao.RoleDao;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherRoleUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        String teacher_id = request.getParameter("id");
        String role_num = request.getParameter("role_num");
        
        RoleDao rDao = new RoleDao();
        Role role = rDao.get(role_num); 

        TeacherDao tDao = new TeacherDao();
        Teacher teacher =tDao.get(teacher_id);
        
        teacher.setRole(role);

        tDao.save(teacher,role);
        session.setAttribute("user", user);

        response.sendRedirect("teacher_update_done.jsp");
    }
}