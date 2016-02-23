package sistemaDeVendas.repositories;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import sistemaDeVendas.model.Categoria;

public class CategoriaRepository implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	
	public List<Categoria> buscarTodos() {
		return entityManager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}

	public Categoria buscarPorId(Long id) {
		return entityManager.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarSubcategoriaPorIdPai(Categoria categoriaPai) {
		return entityManager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai)
				.getResultList();
	}

}
