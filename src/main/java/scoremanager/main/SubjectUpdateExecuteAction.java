package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {

        // リクエストパラメータ取得
    	HttpSession session = request.getSession();
    	Teacher user = (Teacher) session.getAttribute("user");
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd,user.getSchool());
        if (subject == null) {
        	Subject sub = new Subject();
        	sub.setCd(cd);
        	sub.setName(name);
        	request.setAttribute("error","科目が存在していません");
        	request.setAttribute("subject",sub);
        	request.getRequestDispatcher("subject_update.jsp")
            .forward(request, response);
        }
        

        // 更新対象のみセット
        subject.setName(name);

        // 更新実行
        dao.save(subject);
        
        request.setAttribute("subject",subject);
        request.getRequestDispatcher("subject_update_done.jsp")
        .forward(request, response);
    }
}