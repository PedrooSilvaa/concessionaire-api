package br.edu.senaisp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.senaisp.model.Filial;
import br.edu.senaisp.model.Vendedor;

public class FilialDAO {
	
	private final String SQLINSERT = "INSERT INTO filial (nome, rua, nr, bairro) VALUES(?, ?, ?, ?)";

	private final String SQLSELECT = 
			"SELECT id, nome, rua, nr, bairro FROM filial";
	
	private final String SQLDELETE = 
			"DELETE FROM filial WHERE id = ?";	
	
	private final String SQLSELECT_ID = 
			"SELECT id, nome, rua, nr, bairro FROM filial WHERE id = ?";
	
	private String SQLUPDATE = 
			"UPDATE filial SET nome = ?, rua = ?, nr = ?, bairro = ? WHERE id = ?";
	
	private final String SQLJOINIDFILIAL = "SELECT f.id, f.nome, f.rua, f.nr, f.bairro FROM vendedor v join filial f on v.id_filial = f.id WHERE v.id = ?";
	
	public int novo(Filial filial) {
		int id = 0;
		Connection con = null;
		try {
			con = dao.conexao();

			
			con.setAutoCommit(false);
			if (!con.isClosed()) {
				PreparedStatement ps = con.prepareStatement(SQLINSERT, Statement.RETURN_GENERATED_KEYS);

				ps.setString(1, filial.getNome());
				ps.setString(2, filial.getRua());
				ps.setInt(3, filial.getNr());
				ps.setString(4, filial.getBairro());
				ps.setString(5, filial.getVendedor().getNome());
				

				ps.execute();

				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1); // variavel onde vai guardar o retorno do generated key
				
				filial.setId(id);; // gravando no id do estado com a variavel do generated key
				
				VendedorDAO vDAO = new VendedorDAO();
				
				for (Vendedor vendedor : filial.getVendedores()) {
					
					vendedor.setFilial(filial);
					
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
	
	public List<Filial> listaCompleto() {
		
		List<Filial> filial = new ArrayList<Filial>();
		
		VendedorDAO VendDao = new VendedorDAO();
		try {
			Connection con = dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            	con.prepareStatement(SQLSELECT);
            	ResultSet rs = ps.executeQuery();
            	
            	Filial tmp = null;
            	while (rs.next()) {
            		
            		tmp = new Filial();
            		tmp.setId(rs.getInt("id"));
            		tmp.setNome(rs.getString("nome") );
            		tmp.setRua(rs.getString("rua"));
            		tmp.setNr(rs.getInt("nr") );
            		tmp.setBairro(rs.getString("bairro"));
            		tmp.setVendedor(VendDao.buscaPorIdFilial(tmp.getId()));
            		
            		
            		//tmp.setVendedor(new Vendedor(null, "Não Existe", null, null));
            		
            		filial.add(tmp);
            	}
            	
            	con.close();
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return filial;
	}
	
	public void Delete(int numero) {
		
		try {
			Connection con = dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            	con.prepareStatement(SQLDELETE);
            		
            		ps.setInt(1, numero);
            		ps.execute();
            		
            	}
            	
            	con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Filial buscaPorId(int id) {
		Filial filial = null; 
		
		try {
			Connection con = dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            	con.prepareStatement(SQLSELECT_ID);
            	ps.setInt(1, id);
            	
            	ResultSet rs = ps.executeQuery();
            	
            	
            	if (rs.next()) {
            		
            		filial = new Filial();
            		
            		filial.setId(rs.getInt("id") );
            		filial.setNome(rs.getString("nome") );
            		filial.setRua(rs.getString("rua"));
            		filial.setNr(rs.getInt("nr") );
            		filial.setBairro(rs.getString("bairro"));
               	}
            	
            	con.close();
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return filial;
	}
	
	public void Update(Filial filial) {

		try {
			Connection con = dao.conexao();
		 if (!con.isClosed()) {
            	PreparedStatement ps = 
            			con.prepareStatement(SQLUPDATE);
            	
            	ps.setString(1, filial.getNome());
            	ps.setString(2, filial.getRua());
            	ps.setInt(3, filial.getNr());
            	ps.setString(4, filial.getBairro());
            	ps.setInt(5, filial.getId());
            	
            	ps.execute();
            }
            	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public Filial buscarFilalPorVendedor(int id) {
		Filial filial = new Filial();
		
		try {
			Connection con = dao.conexao();
			
			if (!con.isClosed()) {
				PreparedStatement ps = 
						con.prepareStatement(SQLJOINIDFILIAL);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					
					
            		filial.setId(rs.getInt("id"));
            		filial.setNome(rs.getString("nome"));
            		filial.setRua(rs.getString("rua"));
            		filial.setNr(rs.getInt("nr"));
            		filial.setBairro(rs.getString("bairro"));
					
            		
					
				}
				
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return filial;
	}
	
	
}
