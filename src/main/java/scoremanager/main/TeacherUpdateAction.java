package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションのuser情報をそのままjspで使うため、フォワードのみ
        request.getRequestDispatcher("teacher_update.jsp").forward(request, response);
    }
}