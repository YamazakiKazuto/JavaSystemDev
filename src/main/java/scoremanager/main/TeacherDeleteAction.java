package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TeacherDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 削除対象のIDを取得
        String id = request.getParameter("id");
        
        TeacherDao tDao = new TeacherDao();
        Teacher teacher = tDao.get(id); // 削除する教員の情報を取得

        // 教員情報をリクエストセットして確認画面へ
        request.setAttribute("teacher", teacher);
        request.getRequestDispatcher("teacher_delete.jsp").forward(request, response);
    }
}