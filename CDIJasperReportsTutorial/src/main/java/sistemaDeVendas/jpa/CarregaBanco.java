package sistemaDeVendas.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import sistemaDeVendas.model.Categoria;

public class CarregaBanco {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mySQLConfig");
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Categoria catPai = new Categoria();
		Categoria catFilho = new Categoria(catPai);
		Categoria catFilho2 = new Categoria(catPai);
		Categoria catFilho3 = new Categoria(catPai);
		Categoria catFilho4 = new Categoria(catPai);
		Categoria catFilho5 = new Categoria(catPai);
		catFilho.setDescricao("Mouse");
		catFilho2.setDescricao("Keyboard");
		catFilho3.setDescricao("HD");
		catFilho4.setDescricao("Monitor");
		catFilho5.setDescricao("Flat cable");
		List<Categoria> subCategoria = new ArrayList<>();
		subCategoria.add(catFilho);
		subCategoria.add(catFilho2);
		subCategoria.add(catFilho3);
		subCategoria.add(catFilho4);
		subCategoria.add(catFilho5);
		catPai.setDescricao("Informática");
		catPai.setSubcategorias(subCategoria);
		em.persist(catPai);
		
		Categoria catPai2 = new Categoria();
		Categoria catFilho6 = new Categoria(catPai2);
		Categoria catFilho7 = new Categoria(catPai2);
		Categoria catFilho8 = new Categoria(catPai2);
		Categoria catFilho9 = new Categoria(catPai2);
		Categoria catFilho10 = new Categoria(catPai2);
		catFilho6.setDescricao("Panela de pressão");
		catFilho7.setDescricao("jogo de cozinha");
		catFilho8.setDescricao("prato");
		catFilho9.setDescricao("Escorredor");
		catFilho10.setDescricao("talheres");
		List<Categoria> subCategoria2 = new ArrayList<>();
		subCategoria2.add(catFilho6);
		subCategoria2.add(catFilho7);
		subCategoria2.add(catFilho8);
		subCategoria2.add(catFilho9);
		subCategoria2.add(catFilho10);
		catPai2.setDescricao("Artigos domésticos");
		catPai2.setSubcategorias(subCategoria2);
		em.persist(catPai2);

		Categoria catPai3 = new Categoria();
		Categoria catFilho11 = new Categoria(catPai3);
		Categoria catFilho12 = new Categoria(catPai3);
		Categoria catFilho13 = new Categoria(catPai3);
		Categoria catFilho14 = new Categoria(catPai3);
		Categoria catFilho15 = new Categoria(catPai3);
		catFilho11.setDescricao("Espremedor de laranjas");
		catFilho12.setDescricao("Cafeteira Nespresso");
		catFilho13.setDescricao("Forno elétrico");
		catFilho14.setDescricao("Refrigerador");
		catFilho15.setDescricao("Fogão");
		List<Categoria> subCategoria3 = new ArrayList<>();
		subCategoria3.add(catFilho11);
		subCategoria3.add(catFilho12);
		subCategoria3.add(catFilho13);
		subCategoria3.add(catFilho14);
		subCategoria3.add(catFilho15);
		catPai3.setDescricao("Eletro domésticos");
		catPai3.setSubcategorias(subCategoria3);
		em.persist(catPai3);
		
		Categoria catPai4 = new Categoria();
		Categoria catFilho16 = new Categoria(catPai4);
		Categoria catFilho17 = new Categoria(catPai4);
		Categoria catFilho18 = new Categoria(catPai4);
		Categoria catFilho19 = new Categoria(catPai4);
		Categoria catFilho20 = new Categoria(catPai4);
		catFilho16.setDescricao("Harry Potter e a ordem da fênix");
		catFilho17.setDescricao("Harry Potter e a cálice de fogo");
		catFilho18.setDescricao("As crônicas de gelo e fogo - a espada da tormenta");
		catFilho19.setDescricao("As crônicas de gelo e fogo - Game of Thrones");
		catFilho20.setDescricao("O colecionador de ossos");
		List<Categoria> subCategoria4 = new ArrayList<>();
		subCategoria4.add(catFilho16);
		subCategoria4.add(catFilho17);
		subCategoria4.add(catFilho18);
		subCategoria4.add(catFilho19);
		subCategoria4.add(catFilho20);
		catPai4.setDescricao("Livros");
		catPai4.setSubcategorias(subCategoria4);
		em.persist(catPai4);
		
		transaction.commit();
		em.close();
	}

}
