package objetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente {
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private String nome, mae, pai, profissao, cep, endereco, bairro, cidade, uf = "MG", telefone, numero;
	private Date dataNasc;
	private int id, convenio = -1;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae.toUpperCase().trim();
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai.toUpperCase().trim();
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao.toUpperCase().trim();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco.toUpperCase().trim();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase().trim();
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase().trim();
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf.toUpperCase().trim();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public String getDataNascimento() {
		return dataNasc == null ? "" : df.format(dataNasc);
	}

	public void setDataNasc(Date dataNasc) throws Exception {
		if (dataNasc.getTime() > (new Date()).getTime()) {
			throw new Exception("Data de nacimento maior que a data atual");
		}
		this.dataNasc = dataNasc;
	}

	public void setDataNasc(String dataNasc) throws Exception {
		try {
			setDataNasc(df.parse(dataNasc));
		} catch (ParseException e) {
			this.dataNasc = null;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConvenio() {
		return convenio;
	}

	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
