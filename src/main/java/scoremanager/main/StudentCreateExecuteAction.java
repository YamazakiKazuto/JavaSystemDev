package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
    	
        HttpSession session = request.getSession();
        // パラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("ent_year");
        String classNum = request.getParameter("classnumm");
        Teacher user =(Teacher)session.getAttribute("user");
        
        // 入学年度未入力チェック
        if (entYearStr == null || entYearStr.isEmpty()) {
            request.setAttribute("ent_year_error", "入学年度を選択してください。");
            request.setAttribute("no",no);
            request.setAttribute("classnu", classNum);
            request.setAttribute("name",name);
            request.getRequestDispatcher("student_create.jsp")
                   .forward(request, response);
            return;
        }
        
        StudentDao dao = new StudentDao();

        //学生番号重複チェック

        Student existing = dao.get(no);

        if (existing != null) {
            request.setAttribute("no_error", "学生番号が重複しています。");
            request.setAttribute("no",no);
            request.setAttribute("name",name);
            request.setAttribute("classnu", classNum);
            int entYear=Integer.parseInt(entYearStr);
            request.setAttribute("f1", entYear);
            request.getRequestDispatcher("student_create.jsp")
                   .forward(request, response);
            return;
        }
        session.removeAttribute("classnum");
        int ent_year=Integer.parseInt(entYearStr);
        
        // 登録処理

        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(classNum);
        student.setAttend(true);      // 新規登録は在学中
        student.setSchool(user.getSchool()); 

        dao.save(student);

        // 完了画面へ
        request.getRequestDispatcher("student_create_done.jsp")
               .forward(request, response);
    }
}