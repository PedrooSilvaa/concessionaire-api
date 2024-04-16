package br.edu.senaisp.model;

public class Vendedor {

	private Integer id;
	private String nome;
	private String cpf;
	private Filial filial;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	public Vendedor() {
		
	}
	
	public Vendedor(Integer id, String nome, String cpf, Filial filial) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.filial = filial;
	}
	
	
	

	
	
}