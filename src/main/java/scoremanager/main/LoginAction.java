package scoremanager.main;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
public class LoginAction extends Action {
 
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // このActionは認証を行わず、単にログイン画面を表示させるだけです。
        // シーケンス図における「login.jsp」への遷移（起動）を担当します。
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
 