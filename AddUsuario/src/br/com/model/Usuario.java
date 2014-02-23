package br.com.model;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Usuario {

	
	private int id;

	private String user_nome;

	private String user_cpf;

	private String user_rg;

	private String id_telefone;

	private String id_email;

	private String user_login;

	private String user_senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_nome() {
		return user_nome;
	}

	public void setUser_nome(String user_nome) {
		this.user_nome = user_nome;
	}

	public String getUser_cpf() {
		return user_cpf;
	}

	public void setUser_cpf(String user_cpf) {
		this.user_cpf = user_cpf;
	}

	public String getUser_rg() {
		return user_rg;
	}

	public void setUser_rg(String user_rg) {
		this.user_rg = user_rg;
	}

	public String getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(String id_telefone) {
		this.id_telefone = id_telefone;
	}

	public String getId_email() {
		return id_email;
	}

	public void setId_email(String id_email) {
		this.id_email = id_email;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getUser_senha() {
		return user_senha;
	}

	public void setUser_senha(String user_senha) {
		this.user_senha = user_senha;
	}

		
}
