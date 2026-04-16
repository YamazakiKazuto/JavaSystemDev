package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        // リクエストパラメータ取得
        String no = request.getParameter("no");
        no = no.trim();
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        boolean isAttend =
                Boolean.parseBoolean(request.getParameter("is_enrolled"));

        StudentDao studao = new StudentDao();

        // 既存学生データ取得
        Student student = studao.get(no);

        // 更新対象のみセット
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(isAttend);

        // 更新実行
        studao.save(student);

        request.getRequestDispatcher("student_update_done.jsp")
        .forward(request, response);
    }
}