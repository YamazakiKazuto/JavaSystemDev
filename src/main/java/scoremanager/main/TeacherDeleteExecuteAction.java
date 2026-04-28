package scoremanager.main;

import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        
        TeacherDao tDao = new TeacherDao();
        tDao.delete(id); // データベースから削除
        
        // 削除完了画面へ（前回の回答で作ったJSP）
        request.getRequestDispatcher("teacher_delete_finished.jsp").forward(request, response);
    }
}