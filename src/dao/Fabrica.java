package dao;

import interfaces.InterfaceConvenio;
import interfaces.InterfacePaciente;

public class Fabrica {
	public static InterfacePaciente criaDAOPaciente() {
		return new PacienteDAOSqLite();
	}
	
	public static InterfaceConvenio criaDAOConvenio() {
		return new ConvenioDAOSqLite();
	}

}
