package sistemaDeVendas.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import sistemaDeVendas.enuns.FormaPagamento;
import sistemaDeVendas.enuns.StatusPedido;
import sistemaDeVendas.enuns.TipoPessoa;
import sistemaDeVendas.model.Cliente;
import sistemaDeVendas.model.Endereco;
import sistemaDeVendas.model.EnderecoEntrega;
import sistemaDeVendas.model.Grupo;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.model.Usuario;

public class CarregaBanco3 {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mySQLConfig");
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		
		
		//em.persist(p);
		transaction.commit();
		em.close();
		
	}

}
