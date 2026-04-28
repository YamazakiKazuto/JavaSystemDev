package scoremanager.main;

import java.util.List;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");
        
        String classNum = request.getParameter("class_num");
        ClassNumDao cDao = new ClassNumDao();

        if (classNum == null || classNum.isEmpty() || !classNum.matches("^[0-9]{3}$")) {
            request.setAttribute("error", "クラス番号は数字3桁で入力してください。");
            request.setAttribute("class_num", classNum);
            request.getRequestDispatcher("class_create.jsp").forward(request, response);
            return;
        }

        List<String> existingClasses = cDao.filter(user.getSchool());
        if (existingClasses.contains(classNum)) {
            request.setAttribute("error", "そのクラス番号は既に登録されています。");
            request.getRequestDispatcher("class_create.jsp").forward(request, response);
            return;
        }

        ClassNum newClass = new ClassNum();
        newClass.setClassNum(classNum);
        newClass.setSchool(user.getSchool());
        cDao.save(newClass);

        request.getRequestDispatcher("class_create_done.jsp").forward(request, response);
    }
}