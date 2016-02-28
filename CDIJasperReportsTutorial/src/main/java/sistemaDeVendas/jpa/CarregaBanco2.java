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

public class CarregaBanco2 {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mySQLConfig");
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Pedido p = new Pedido();
		
		Endereco e = new Endereco();
		e.setCep("023-4578");
		e.setCidade("São Paulo");
		e.setComplemento("casa");
		e.setNumero("4721");
		e.setUf("SP");
		e.setLogradouro("rua maria das flores");
		
		Cliente c = new Cliente();
		c.setDocumentoReceitaFederal("abc123");
		c.setEmail("edney@mail.com");
		c.setNome("Edney");
		c.setTipo(TipoPessoa.JURIDICA);
		List<Endereco> listaEnd = new ArrayList<>();
		listaEnd.add(e);
		c.setEnderecos(listaEnd);
		e.setCliente(c);
		em.persist(c);

		
		p.setCliente(c);
		p.setDataCriacao(new Date());
		p.setDataEntrega(new Date());
		
		EnderecoEntrega endEnt = new EnderecoEntrega();
		endEnt.setCep("4547-455");
		endEnt.setCidade("São Pulo");
		endEnt.setComplemento("avenida");
		endEnt.setLogradouro("Av paulista");
		endEnt.setNumero("2235");
		endEnt.setUf("SP");
		
		p.setEnderecoEntrega(endEnt);
		p.setFormaPagamento(FormaPagamento.DINHEIRO);
		p.setObservacao("em analise");
		p.setStatus(StatusPedido.ORCAMENTO);
		p.setValorDesconto(new BigDecimal("25.35"));
		p.setValorFrete(new BigDecimal("45.75"));
		p.setValorTotal(new BigDecimal("115.23"));
		
		Usuario u = new Usuario();
		u.setEmail("vendr@mail.com");
		u.setNome("Joao");
		u.setSenha("11234");
		
		Grupo g = new Grupo();
		g.setDescricao("grupo");
		g.setNome("conjunto");
		List<Grupo> l = new ArrayList<>();
		l.add(g);
		
		u.setGrupos(l);
		em.persist(u);
		
		p.setVendedor(u);
		
		em.persist(p);
		transaction.commit();
		em.close();
		
	}

}
