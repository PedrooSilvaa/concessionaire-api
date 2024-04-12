package br.edu.senaisp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.edu.senaisp.model.Vendedor;

public class VendedorDAO {


	private final String SQLINSERT = "INSERT INTO vendedor (nome, cpf, id_filial) VALUES(?, ?, ?)";
	
	public int novo(Vendedor vendedor) {
		int id = 0;
		try {
			Connection con = dao.conexao();

			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLINSERT, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, vendedor.nome);
				ps.setString(2, vendedor.cpf);
				ps.setInt(3, vendedor.filial.id);

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
	
}
