package br.edu.senaisp.controller;

import java.io.IOException;
import java.util.ArrayList;

import br.edu.senaisp.dao.FilialDAO;
import br.edu.senaisp.model.Filial;
import br.edu.senaisp.model.Vendedor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/filial")
public class FilialController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nome = req.getParameter("nome");
		String rua = req.getParameter("rua");
		String bairro = req.getParameter("bairro");
		int nr = Integer.parseInt(req.getParameter("nr"));
		
		Filial filial = new Filial(nome, rua, bairro, nr);
		filial.vendedor = new ArrayList<Vendedor>();
		
		Vendedor c1 = new Vendedor();
		c1.nome  = "Pedro";
		c1.cpf  = "222.222.222-22";
		
		Vendedor c2 = new Vendedor();
		c2.nome  = "Jose";
		c2.cpf  = "111.111.111-11";
		
		filial.vendedor.add(c1);
		filial.vendedor.add(c2);
		
		FilialDAO dao = new FilialDAO();
		int id = dao.novoCompleto(filial);
		
		
		
		resp.getWriter().append(String.valueOf(id));
		
		
	}

}
