package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dao.IProduitDao;
import com.dao.ProduitDaoImpl;
import com.metier.entities.Produit;
import com.web.model.ProduitModel;


@WebServlet(name="cs", urlPatterns= {"*.php"})
public class ControleurServlet extends HttpServlet {
	private IProduitDao metier;
	
	@Override
	public void init() throws ServletException {
		//metier = new ProduitDaoImpl();
		ApplicationContext springContext = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						this.getServletContext());
		metier = (IProduitDao) springContext.getBean("da");
		  //metier = springContext.getBean(IProduitDao.class);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		if(path.equals("/index.php")) {

			request.getRequestDispatcher("produits.jsp").forward(request, response);
		}
		else if(path.equals("/chercher.php")) {
			String motCle = request.getParameter("motCle");
			ProduitModel model = new ProduitModel();
			model.setMotCle(motCle);
			List<Produit> produits = metier.produitsParMC("%"+motCle+"%");
			model.setProduits(produits);
			request.setAttribute("model", model);
			request.getRequestDispatcher("produits.jsp").forward(request, response);
			
		}
		else if(path.equals("/Saisie.php")) {
			request.setAttribute("produit", new Produit());
			request.getRequestDispatcher("SaisieProduit.jsp").forward(request,response);
		}
		else if(path.equals("/SaveProduit.php") && (request.getMethod().equals("POST"))) {
			String des=request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			Produit p = metier.save(new Produit(des,prix,quantite));
			
			request.setAttribute("produit",p);
			request.getRequestDispatcher("Confirmation.jsp").forward(request,response);
		}
		else if(path.equals("/Supprime.php")) {
			Long id= Long.parseLong(request.getParameter("id"));
			metier.deleteProduit(id);
			response.sendRedirect("chercher.php?motCle=");
			//request.getRequestDispatcher("/produits.jsp").forward(request,response);
		}
		else if(path.equals("/Edit.php")) {
			Long id= Long.parseLong(request.getParameter("id"));
			Produit p=metier.getProduit(id);
			request.setAttribute("produit", p);
			request.getRequestDispatcher("EditProduit.jsp").forward(request,response);
		}
		else if(path.equals("/UpdateProduit.php") && (request.getMethod().equals("POST"))) {
			Long id = Long.parseLong(request.getParameter("id"));
			String des=request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite = Integer.parseInt(request.getParameter("quantite"));
			Produit p = new Produit(des,prix,quantite);
			p.setId(id);
			 p = metier.update(p);
			
			request.setAttribute("produit",p);
			request.getRequestDispatcher("Confirmation.jsp").forward(request,response);}
		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}
