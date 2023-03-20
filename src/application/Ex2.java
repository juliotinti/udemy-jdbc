package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;

public class Ex2 {

	public static void main(String[] args) {
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null; 
		try {
			conn = DB.getConnection();
//			st = conn.prepareStatement(
//					"INSERT INTO seller "
//					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
//					+ "VALUES"
//					+ "(?, ?, ?, ?, ?)");     //? é o placeholder (so dps voce vai colocar o valor que o dado terá)
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES"
					+ "(?, ?, ?, ?, ?)",                //? é o placeholder (so dps voce vai colocar o valor que o dado terá)
					Statement.RETURN_GENERATED_KEYS);   //esse final retorna qual é a primaryKey do objeto adicionado
			//agora precisamos fazer o comando que irá trocar o placeholder pelo valor da variável
			st.setString(1, "Carl Purple"); //trocar o primeiro ? pela string
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(fmt.parse("22/04/1985").getTime())); //para fazer uma data em sql, PRECISA desse java.sql.Date()
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			int rowsAffected = st.executeUpdate(); //o int é so para saber quantas linhas foram alteradas, mas o comando
												   //executeUpdate faz o query chegar no MySQL
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1); //getGeneratedKeys, esse comando gera uma tabela com uma única coluna, que é o primaryKey do sql 
					System.out.println("Done! Id = " + id);
				}
			}else
				System.out.println("No rows affected");

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();			
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
