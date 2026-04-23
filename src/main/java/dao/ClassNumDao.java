package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

	public ClassNum get(String class_num, School school) throws Exception {

	    // クラス番号インスタンスを初期化
	    ClassNum classNum = new ClassNum();

	    // データベースへのコネクションを確立
	    Connection connection = getConnection();

	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(
	                "select * from class_num where class_num = ? and school_cd = ?"
	        );

	        // プレースホルダに値をバインド
	        statement.setString(1, class_num);
	        statement.setString(2, school.getCd());

	        // リザルトセットを実行
	        ResultSet rSet = statement.executeQuery();

	        // 学校Daoを初期化
	        SchoolDao sDao = new SchoolDao();

	        if (rSet.next()) {
	            // リザルトセットが存在する場合
	            // クラス番号インスタンスに検索結果をセット
	            classNum.setClassNum(rSet.getString("class_num"));
	            classNum.setSchool(sDao.get(rSet.getString("school_cd")));
	        } else {
	            // リザルトセットが存在しない場合
	            // クラス番号インスタンスにnullをセット
	            classNum = null;
	        }

	    } catch (Exception e) {
	        throw e;

	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return classNum;
	}

	public List<String> filter(School school) throws Exception {

	    // リストを初期化
	    List<String> list = new ArrayList<>();

	    // データベースへのコネクションを確立
	    Connection connection = getConnection();

	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(
	            "select class_num from class_num where school_cd=? order by class_num"
	        );

	        // プレースホルダに学校コードをバインド
	        statement.setString(1, school.getCd());

	        // リザルトセットを実行
	        ResultSet rSet = statement.executeQuery();

	        // リザルトセットを全件走査
	        while (rSet.next()) {
	            // リストにクラス番号を追加
	            list.add(rSet.getString("class_num"));
	        }

	    } catch (Exception e) {
	        throw e;

	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return list;
	}


   public Map<String, Integer> countStudentsByClass(School school) throws Exception {
       Map<String, Integer> map = new HashMap<>();
       Connection connection = getConnection();
       PreparedStatement statement = null;
       ResultSet rSet = null;

       try {
           // SQL: 学校コードで絞り込み、クラスごとに集計
           String sql = "SELECT class_num, COUNT(*) as cnt FROM student WHERE school_cd=? GROUP BY class_num";
           
           statement = connection.prepareStatement(sql);
           statement.setString(1, school.getCd());
           
           rSet = statement.executeQuery();

           while (rSet.next()) {
               // クラス番号をキー、人数(cnt)を値として保存
               map.put(rSet.getString("class_num"), rSet.getInt("cnt"));
           }
       } catch (Exception e) {
           throw e;
       } finally {
           // リソースの解放
           if (rSet != null) rSet.close();
           if (statement != null) statement.close();
           if (connection != null) connection.close();
       }
       return map;
   }
	
    public boolean save(ClassNum classNum) throws Exception {

    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

    }
}