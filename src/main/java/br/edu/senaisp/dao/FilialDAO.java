package br.edu.senaisp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.senaisp.model.Filial;
import br.edu.senaisp.model.Vendedor;

public class FilialDAO {
	
	private final String SQLINSERT = "INSERT INTO filial (nome, rua, nr, bairro) VALUES(?, ?, ?, ?)";

	public int novo(Filial filial) {
		int id = 0;
		
		try {
			Connection con = dao.conexao();
			
			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLINSERT, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, filial.nome);
				ps.setString(2, filial.rua);
				ps.setInt(3, filial.nr);
				ps.setString(4, filial.bairro);

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

	public int novoCompleto(Filial filial) {
		int id = 0;
		Connection con = null;
		try {
			con = dao.conexao();

			
			con.setAutoCommit(false);
			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLINSERT, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, filial.nome);
				ps.setString(2, filial.rua);
				ps.setInt(3, filial.nr);
				ps.setString(4, filial.bairro);

				ps.execute();

				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1); // variavel onde vai guardar o retorno do generated key
				
				filial.id = id; // gravando no id do estado com a variavel do generated key
				
				VendedorDAO vDAO = new VendedorDAO();
				
				for (Vendedor vendedor : filial.vendedor) {
					
					vendedor.filial = filial;
					
					vDAO.novo(vendedor);
					
				}
				con.commit();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
				
			} catch (SQLException e1) {
 
			}
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
		
	}
}
