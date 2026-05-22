//このRoleDaoはどこにも使われていない
//Roleは使われている
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Role;

public class RoleDao extends Dao {

    // getメソッド 学校コードを指定して学校インスタンス1件取得する
    // @param cd 学校コード
    // @return 学校クラスのインスタンス 存在しない場合はnull
    // @throws Exception
    public Role get(String roleid) throws Exception {

        // 学校インスタンスを初期化
    	Role role = new Role();

        // データベースへのコネクションを確立
        Connection connection = getConnection();

        // プリペアードステートメント
        PreparedStatement statement = null;

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from role where role = ?");

            // プレースホルダに学校コードをバインド
            statement.setString(1, roleid);

            // リザルトセットを実行
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                // リザルトセットが存在する場合
                // 学校インスタンスに学校名をセット
                role.setRole(rSet.getString("role"));
                role.setName(rSet.getString("role_name"));
            } else {
                // 存在しない場合
                // 学校インスタンスにnullをセット
                role = null;
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

        return role;
    }
}