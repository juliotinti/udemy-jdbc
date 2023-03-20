package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class Ex4 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null; 
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("DELETE FROM department "  //SEMPRE USE DELETE COM WHERE, SEMPRE
									 + "WHERE "
									 + "Id = ?");
			st.setDouble(1, 5);
			
			int rowsAffected = st.executeUpdate();
			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());		
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
