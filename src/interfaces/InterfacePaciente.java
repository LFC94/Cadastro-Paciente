package interfaces;

import java.util.ArrayList;

import objetos.Paciente;

public interface InterfacePaciente {
	
	public void criarBanco() throws Exception ;
	public void cadastra(Paciente paciente) throws Exception ;
	public void remove(Paciente paciente) throws Exception ;
	public ArrayList<Paciente> busca(String nome) throws Exception;
	public Paciente busca(int id) throws Exception ;
	

}
