package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // --- 1. 入学年度のプルダウン用リスト作成 ---
        // 現在の年を取得（例: 2026年）
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        
        // 前後10年分程度の選択肢を作成（画像 c33fe0.png のような形式）
        for (int i = year - 10; i <= year + 1; i++) {
            entYearSet.add(i);
        }
        
        // JSPの c:forEach で使うためにセット
        request.setAttribute("ent_year_set", entYearSet);

        // --- 2. クラス番号のプルダウン用リスト取得 ---
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumSet = cNumDao.filter(teacher.getSchool());
        
        // JSPの c:forEach で使うためにセット
        request.setAttribute("class_num_set", classNumSet);

        // --- 3. 画面遷移 ---
        // 学生新規登録画面を表示
        request.getRequestDispatcher("class_create.jsp").forward(request, response);
    }
}