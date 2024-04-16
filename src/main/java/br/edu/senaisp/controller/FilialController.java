package br.edu.senaisp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

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

		FilialDAO dao = new FilialDAO(); // chamando cliente dao

		List<Filial> filials = dao.listaCompleto(); // declarando a lista

		String texto = "";

		try {

			Gson gson = new Gson(); // declaracao do objeto gson

			texto = gson.toJson(filials); // transformando o objeto cliente para gson

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.getWriter().append(texto);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		BufferedReader br = req.getReader(); // usando o br para leitura doq sera passado
//
//		String json = "";
//		String linha = "";
//
//		while ((linha = br.readLine()) != null) { // guarando a leitura na linha e vendo se nao e nulo
//			json += linha; // guardando e concatenando os dados
//		}
//
//		Gson gson = new Gson();
//
//		Filial filial = gson.fromJson(json, Filial.class); // a partir do json para o objeto
//
//		FilialDAO dao = new FilialDAO();
//
//		dao.novo(filial);

		String nome = "Pedro Cars";
		String rua = "Alamenda";
		String bairro = "Parque";
		int nr = 78;

		Filial filial = new Filial(nome, rua, bairro, nr);
		filial.setVendedor(new ArrayList<Vendedor>());

		Vendedor c1 = new Vendedor();
		c1.setNome("Pedro");
		c1.setCpf("222.222.222-22");

		Vendedor c2 = new Vendedor();
		c2.setNome("Jose");
		c2.setCpf("111.111.111-11");

		filial.getVendedores().add(c1);
		filial.getVendedores().add(c2);

		FilialDAO dao = new FilialDAO();
		int id = dao.novo(filial);

		resp.getWriter().append(String.valueOf(id));
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

		Filial filial = gson.fromJson(json, Filial.class);

		filial.setId(id); // mostrando qual id eu quero

		FilialDAO dao = new FilialDAO();

		dao.Update(filial);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id")); // pegando parametro

		FilialDAO dao = new FilialDAO();

		dao.Delete(id);

	}

}
