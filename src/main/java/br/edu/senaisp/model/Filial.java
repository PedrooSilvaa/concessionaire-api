package br.edu.senaisp.model;

import java.util.List;

public class Filial {

	public Integer id;
	public String nome;
	public String rua;
	public Integer nr;
	public String bairro;
	public List<Vendedor> vendedor;
	
	
	public Filial(String nome, String rua, String bairro, int nr) {
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.nr = nr;
	}	
	
	public Filial(int id, String nome, String rua, String bairro, int nr) {
		this.id = id;
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.nr = nr;
	}
	
	
	
}
