//制作者　石川
package scoremanager.main;
 
import java.util.List;

import bean.Role;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class ModeChangeAction extends Action {
 
    @Override
 
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	HttpSession session = request.getSession();
        Teacher user =(Teacher)session.getAttribute("user");
        
        String role_num = request.getParameter("role_num");
        TeacherDao dao=new TeacherDao();
		Role role_mode=dao.modeget(role_num);
        user.setMode(role_mode);
        session.setAttribute("user",user);
        
        
		List<Role> Role_list=dao.modelistget(user.getRole().getRole());
		request.setAttribute("role_list", Role_list);
		
		if ("1".equals(role_num)) {
        	request.setAttribute("becareful","全能管理者モードに変更されました");
        }
		request.setAttribute("mode_changed","役職モードが変更されました");
        
		request.getRequestDispatcher("menu.jsp")
        .forward(request, response);
    }   
}
 