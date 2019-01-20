package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqLite {
	public static Connection connectar() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:banco_de_dados/banco_sqlite.db");
	}
	public static String criarBancoConvenio() {
		String ret = "CREATE TABLE IF NOT EXISTS convenio (";
		ret += "id INTEGER PRIMARY KEY, ";
		ret += "nome TEXT";
		return ret + ")";
	}
}
