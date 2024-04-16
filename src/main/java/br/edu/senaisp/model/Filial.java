package br.edu.senaisp.model;

import java.util.ArrayList;
import java.util.List;

public class Filial {

	private Integer id;
	private String nome;
	private String rua;
	private Integer nr;
	private String bairro;
	private Vendedor vendedor;
	private List<Vendedor> vendedores = new ArrayList<>();
	
	public Filial() {
	}
	
	public Filial(String nome, String rua, String bairro, int nr) {
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.nr = nr;
	}	
	
	public Filial(int id) {
		this.id = id;
		
	}
	
	public Filial(int id, String nome, String rua, String bairro, int nr) {
		this.id = id;
		this.nome = nome;
		this.rua = rua;
		this.bairro = bairro;
		this.nr = nr;
	}

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

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedor(List<Vendedor> vendedor) {
		this.vendedores = vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
	
	
	
	
	
}
