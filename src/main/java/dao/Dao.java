package dao;
 
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;
 
public class Dao {
 
    static DataSource ds;
 
    static {
        try {
            InitialContext context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/javasystemdev");   
        } catch (Exception e) {
            // スタックトレースを必ず出力させて原因を特定する
            e.printStackTrace(); 

        }
    }
    
 
    public Connection getConnection() throws Exception {
        // データベースへのコネクションを返却
        return ds.getConnection();
    }
}