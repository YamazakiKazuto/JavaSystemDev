//制作者　勝見
package scoremanager.main;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
    	Teacher user = (Teacher) session.getAttribute("user");
    	Test test = (Test)session.getAttribute("test");
        
        TestDao tDao = new TestDao();
        tDao.delete(test,user.getSchool()); // データベースから削除
        
        session.removeAttribute("test");
        // 削除完了画面へ（前回の回答で作ったJSP）
        request.getRequestDispatcher("test_delete_done.jsp").forward(request, response);
    }
}