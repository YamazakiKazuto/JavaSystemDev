package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        Teacher user = (Teacher) session.getAttribute("user");

        // セッションチェック
        if (user == null) {
            response.sendRedirect("Login.action");
            return;
        }

        String schoolCd = user.getSchool().getCd();
        School school = new School();
        school.setCd(schoolCd);

        // --- POST時の処理（検索） ---
        if ("POST".equals(request.getMethod())) {
            request.setCharacterEncoding("UTF-8");
            String entYearStr = request.getParameter("ent_year");
            String classNum = request.getParameter("class_num");
            String subjectCode = request.getParameter("subject_code");
            String noStr = request.getParameter("no");
            
            System.out.println("entYear=" + entYearStr);
            System.out.println("classNum=" + classNum);
            System.out.println("subjectCode=" + subjectCode);
            System.out.println("no=" + noStr);

            List<String> errors = new ArrayList<>();
            if (entYearStr == null || entYearStr.isEmpty() ||
            	    classNum == null || classNum.equals("0") ||
            	    subjectCode == null || subjectCode.isEmpty() ||
            	    noStr == null || noStr.isEmpty()) {

            	    errors.add("入学年度とクラスと科目と回数を選択してください");
            	}

            if (errors.isEmpty()) {
                try {
                    TestDao tDao = new TestDao();
                    Subject subject = new Subject();
                    subject.setCd(subjectCode);

                    List<Test> tests = tDao.filter(Integer.parseInt(entYearStr), classNum, subject, Integer.parseInt(noStr), school);
                    
                    request.setAttribute("tests", tests);
                    request.setAttribute("ent_year", entYearStr);
                    request.setAttribute("class_num", classNum);
                    request.setAttribute("subject_code", subjectCode);
                    request.setAttribute("no", noStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("errors", errors);
            }
        }

        // --- 共通のプルダウンデータ準備 ---
        try {
            // 入学年度
            List<Integer> entYears = new ArrayList<>();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = currentYear - 10; i <= currentYear; i++) {
                entYears.add(i);
            }
            request.setAttribute("ent_years", entYears);

            // クラス一覧 (DAOがSchoolオブジェクトを受け取る形式)
            ClassNumDao cDao = new ClassNumDao();
            List<String> classNums = cDao.filter(school); // Stringのリストとして取得
            request.setAttribute("class_nums", classNums);

            // 科目一覧
            SubjectDao sDao = new SubjectDao();
            List<Subject> subjects = sDao.filter(school); 
            request.setAttribute("subjects", subjects);
            System.out.println(classNums);
            System.out.println("schoolCd=" + schoolCd);
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}