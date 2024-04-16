package br.edu.senaisp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.senaisp.model.Filial;
import br.edu.senaisp.model.Vendedor;

public class VendedorDAO {

	private final String SQLINSERT = "INSERT INTO vendedor (nome, cpf, id_filial) VALUES(?, ?, ?)";

	private final String SQLSELECT = "SELECT id, nome, cpf, id_filial FROM vendedor";

	private final String SQLDELETE = "DELETE FROM filial WHERE id = ?";

	private final String SQLSELECT_ID = "SELECT id, nome, cpf, id_filial FROM vendedor WHERE id = ?";

	private final String SQLSELECT_IDFILIAL = "SELECT id, nome, cpf FROM vendedor WHERE id_filial = ?";

	private final String SQLUPDATE = "UPDATE vendedor SET nome = ?, cpf = ? WHERE id = ?";
	
	
	public int novo(Vendedor vendedor) {
		int id = 0;
		try {
			Connection con = dao.conexao();

			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLINSERT, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, vendedor.getNome());
				ps.setString(2, vendedor.getCpf());
				ps.setInt(3, vendedor.getFilial().getId());

				ps.execute();

				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return id;

	}

	public List<Vendedor> lista() {

		List<Vendedor> vendedores = new ArrayList<Vendedor>();

		try {
			Connection con = dao.conexao();

			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLSELECT);
				ResultSet rs = ps.executeQuery();

				Vendedor tmp = null;
				while (rs.next()) {

					tmp = new Vendedor();
					tmp.setId(rs.getInt("id"));
					tmp.setNome(rs.getString("nome"));
					tmp.setCpf(rs.getString("cpf"));
					FilialDAO fiDAO = new FilialDAO();
					tmp.setFilial(fiDAO.buscarFilalPorVendedor(rs.getInt("id")));
					vendedores.add(tmp);
				}

				con.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vendedores;
	}

	public void Delete(int numero) {

		try {
			Connection con = dao.conexao();

			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLDELETE);

				ps.setInt(1, numero);
				ps.execute();

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Vendedor buscaPorId(int id) {
		Vendedor vendedor = null; 
		
		try {
			Connection con = dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            	con.prepareStatement(SQLSELECT_ID);
            	ps.setInt(1, id);
            	
            	ResultSet rs = ps.executeQuery();
            	
            	
            	if (rs.next()) {
            		
            		vendedor = new Vendedor();
            		vendedor.setId(rs.getInt("id"));
					vendedor.setNome(rs.getString("nome"));
					vendedor.setCpf(rs.getString("cpf"));
					vendedor.setFilial(new Filial(rs.getInt("id")));
            		
               	}
            	
            	con.close();
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vendedor;
	}
	
	
	public List<Vendedor> buscaPorIdFilial(int id) {
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		
		try {
			Connection con = dao.conexao();
			
			if (!con.isClosed()) {
				PreparedStatement ps = 
						con.prepareStatement(SQLSELECT_IDFILIAL);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				Vendedor tmp = null;
				while(rs.next()) {
					
					tmp = new Vendedor();
					tmp.setId(rs.getInt("id"));
					tmp.setNome(rs.getString("nome"));
					tmp.setCpf(rs.getString("cpf"));
					vendedores.add(tmp);
					
				}
				
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vendedores;
	}
	
	public void Update(Vendedor vendedor) {

		try {
			Connection con = dao.conexao();
		 if (!con.isClosed()) {
            	PreparedStatement ps = 
            			con.prepareStatement(SQLUPDATE);
            	
            	ps.setString(1, vendedor.getNome());
            	ps.setString(2, vendedor.getCpf());
            	ps.setInt(3, vendedor.getId());
            	
            	ps.execute();
            }
            	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
