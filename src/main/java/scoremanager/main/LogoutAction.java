package scoremanager.main; // パッケージ名はご自身の環境に合わせてください

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();

	    // セッションがある場合のみ処理
	    if (session != null) {
	        // デバッグ用：もしログアウト前に名前を出したいなら、invalidate前に取得しておく
	        // Object user = session.getAttribute("user"); 
	        
	        // セッションを完全に破棄
	        session.invalidate();
	    }

	    // 設計書通り「logout.jsp」へ遷移
	    request.getRequestDispatcher("logout.jsp").forward(request, response);
	}
}