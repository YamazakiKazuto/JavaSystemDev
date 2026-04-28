package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 削除したいクラス番号を受け取る
        String classNum = request.getParameter("class_num");
        
        // 2. JSPに渡す
        request.setAttribute("class_num", classNum);
        
        // 3. 確認画面（JSP）へ移動
        // パスをファイル名だけにすることで、同じフォルダ内のJSPを探しに行きます
        request.getRequestDispatcher("class_delete.jsp").forward(request, response);
    }
}