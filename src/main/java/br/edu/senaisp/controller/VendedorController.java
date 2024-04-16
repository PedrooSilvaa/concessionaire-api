package br.edu.senaisp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.edu.senaisp.dao.VendedorDAO;
import br.edu.senaisp.model.Vendedor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/vendedor")
public class VendedorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		VendedorDAO dao = new VendedorDAO(); // chamando cliente dao

		List<Vendedor> vendedores = dao.lista(); // declarando a lista

		String texto = "";

		try {

			Gson gson = new Gson(); // declaracao do objeto gson

			texto = gson.toJson(vendedores); // transformando o objeto cliente para gson

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.getWriter().append(texto);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id")); // pegando parametro

		String json = "";
		String linha = "";

		BufferedReader br = req.getReader();

		while ((linha = br.readLine()) != null) {
			json += linha;
		}

		Gson gson = new Gson();

		Vendedor vendedor = gson.fromJson(json, Vendedor.class);

		vendedor.setId(id); // mostrando qual id eu quero

		VendedorDAO dao = new VendedorDAO();

		dao.Update(vendedor);

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id")); // pegando parametro

		VendedorDAO dao = new VendedorDAO();

		dao.Delete(id);

	}

}
