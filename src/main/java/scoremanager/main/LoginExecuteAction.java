package scoremanager.main;
 
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class LoginExecuteAction extends Action {
 
    @Override
 
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
   			HttpSession session=request.getSession();
   			String id=request.getParameter("id");
   			String password=request.getParameter("password");
   			TeacherDao dao=new TeacherDao();
  			Teacher user=dao.search(id, password);
   			
   			if (user!=null) {
   				//セッション属性に顧客Beanを保存
   				session.setAttribute("user",user);
   		        response.sendRedirect(request.getContextPath() + "/scoremanager/main/Menu.action");	
  			}
   			else if(user == null) {
   				request.setAttribute("returnid",id);
   				request.setAttribute("returnpassword",password);
   				request.setAttribute("error","ログインに失敗しました。IDまたはパスワードが正しくありません");
   				request.getRequestDispatcher("login.jsp")
                .forward(request, response);
   			}
    			


    }   
}
 