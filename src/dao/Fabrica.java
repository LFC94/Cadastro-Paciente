package dao;

import interfaces.InterfacePaciente;

public class Fabrica {
	public static InterfacePaciente criaDAO() {
		return new PacienteDAOSqLite();
	}

}
