package com.dao;

import java.util.List;

import com.metier.entities.Produit;

public class TestDao {

	public static void main(String[] args) {
		ProduitDaoImpl dao=  new ProduitDaoImpl();
		Produit p1 = dao.save(new Produit("hp",900,45));
		Produit p2 = dao.save(new Produit("scanner",900.2,45));
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		System.out.println(" chercher des produits");
		List<Produit> prods = dao.produitsParMC("%h%");
		for(Produit p : prods) {
			System.out.println(p.toString());
		}
	}

}
