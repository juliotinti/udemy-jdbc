package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Ex5 {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null; 
		try {
			conn = DB.getConnection();
			//para proteger o banco de dados de uma falha no meio do processo
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			//fabricando um erro
			int x = 1;
			if(x == 1)
				throw new SQLException("Fake error");
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			conn.commit(); //somente fala que a transação se terminou se n ocorreu nenhum erro entre o setAutoCommit(false) e o commit()
			
			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);

		} catch (SQLException e) {
			//se ocorrer um erro, ele desfaz o que foi feito até a hr do erro
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) { // se ocorrer um erro no rollback
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}		
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
