package scoremanager.main;

import java.util.Map;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNumStr = request.getParameter("class_num");
        ClassNumDao cDao = new ClassNumDao();

        // 学生がいるかチェック
        Map<String, Integer> counts = cDao.countStudentsByClass(teacher.getSchool());
        Integer studentCount = counts.get(classNumStr);

        if (studentCount != null && studentCount > 0) {
            // 学生がいる場合は一覧に戻ってエラーを表示
            request.setAttribute("error", "学生が所属しているため削除できません。");
            request.getRequestDispatcher("ClassCounts.action").forward(request, response);
        } else {
            // 学生が0人なら削除実行
            ClassNum cn = new ClassNum();
            cn.setClassNum(classNumStr);
            cn.setSchool(teacher.getSchool());
            cDao.delete(cn);
            
            // 【変更点】削除完了画面へ移動
            request.getRequestDispatcher("class_delete_done.jsp").forward(request, response);
        }
    }
}