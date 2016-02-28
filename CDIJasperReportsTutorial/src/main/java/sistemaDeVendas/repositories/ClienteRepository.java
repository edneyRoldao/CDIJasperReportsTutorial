package sistemaDeVendas.repositories;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import sistemaDeVendas.model.Cliente;

public class ClienteRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	
	public Cliente buscarPorId(Long id) {
		return entityManager.find(Cliente.class, id);
	}
	
	
	public List<Cliente> listarPorNome(String nome) {
		String q = "from Cliente where upper(nome) like :nome";
		return entityManager.createQuery(q, Cliente.class).setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}


}
