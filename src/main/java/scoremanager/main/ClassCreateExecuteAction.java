package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        
        // 1. パラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num"); // JSPのname属性がclass_numの場合
        Teacher user = (Teacher) session.getAttribute("user");
        
        StudentDao dao = new StudentDao();

        // --- バリデーション開始 ---

        // 入力値をリクエストにセット（エラーで戻った時にフォームが空にならないようにする）
        request.setAttribute("no", no);
        request.setAttribute("name", name);
        request.setAttribute("ent_year", entYearStr);
        request.setAttribute("class_num", classNum);

        // 【チェック】全項目入力必須 ＆ 学生番号の数字3桁チェック
        if (entYearStr == null || entYearStr.isEmpty() || 
            no == null || !no.matches("^[0-9]{3}$") || 
            name == null || name.isEmpty() || 
            classNum == null || classNum.equals("0")) {
            
            // エラーメッセージをセットして作成画面に戻す
            request.setAttribute("error", "学生番号は数字3桁で入力してください。また、全項目入力必須です。");
            request.getRequestDispatcher("class_create.jsp").forward(request, response);
            return;
        }

        // 【チェック】学生番号重複チェック
        Student existing = dao.get(no);
        if (existing != null) {
            request.setAttribute("error", "学生番号が重複しています。");
            request.getRequestDispatcher("class_create.jsp").forward(request, response);
            return;
        }

        // --- 登録処理 ---
        try {
            int ent_year = Integer.parseInt(entYearStr);
            
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(ent_year);
            student.setClassNum(classNum);
            student.setAttend(true);      // 新規登録は在学中
            student.setSchool(user.getSchool()); 

            dao.save(student);

            // 完了画面へ
            request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "入学年度の形式が正しくありません。");
            request.getRequestDispatcher("student_create.jsp").forward(request, response);
        }
    }
}