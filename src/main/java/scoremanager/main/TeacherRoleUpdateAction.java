//制作者　内田
package scoremanager.main;

import java.util.List;

import bean.Role;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherRoleUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 登録画面へ転送
    	HttpSession session = request.getSession();
        Teacher user =(Teacher)session.getAttribute("user");
        
        String teacher_id = request.getParameter("teacher_id");
        
        TeacherDao dao=new TeacherDao();
        Teacher teacher =dao.get(teacher_id);
		List<Role> Role_list=dao.modelistget(user.getRole().getRole());
		
		request.setAttribute("teacher",teacher);		
		request.setAttribute("role_list", Role_list);
		
        request.getRequestDispatcher("teacher_role_update.jsp").forward(request, response);
    }
}