package objetos;

import java.util.ArrayList;

public class Convenio {
	private String nome;
	private int id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static String[] arrayConvenio(ArrayList<Convenio> convenios) {
		String[] ret = new String[convenios.size()];

		for (int x = 0; x < convenios.size(); x++)
			ret[x] = convenios.get(x).getNome();

		return ret;

	}

	public static int buscarConvenio(ArrayList<Convenio> convenios, String nome) {
		for (int x = 0; x < convenios.size(); x++) {
			if (convenios.get(x).getNome().equals(nome))
				return x;
		}
		return -1;
	}

	public static int buscarConvenio(ArrayList<Convenio> convenios, int id) {
		for (int x = 0; x < convenios.size(); x++) {
			if (convenios.get(x).getId() == id)
				return x;
		}
		return -1;
	}
}
