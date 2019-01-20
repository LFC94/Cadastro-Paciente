package interfaces;

import java.util.ArrayList;

import objetos.Convenio;

public interface InterfaceConvenio {
	
	public void criarBanco() throws Exception;
	public void cadastra(Convenio convenio) throws Exception ;
	public void remove(Convenio convenio) throws Exception ;
	public ArrayList<Convenio> busca(String nome) throws Exception;
	public Convenio busca(int id) throws Exception ;
	

}
