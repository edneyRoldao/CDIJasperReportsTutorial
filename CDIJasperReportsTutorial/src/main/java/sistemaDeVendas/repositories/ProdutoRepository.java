package sistemaDeVendas.repositories;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sistemaDeVendas.exceptions.BusinessException;
import sistemaDeVendas.filters.ProdutoFilter;
import sistemaDeVendas.jpa.Transactional;
import sistemaDeVendas.model.Produto;

public class ProdutoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager entityManager;

	public Produto guardar(Produto produto) {
		return entityManager.merge(produto);
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = buscarPorId(produto.getId());
			entityManager.remove(produto);
			entityManager.flush();
		} catch (PersistenceException e) {
			throw new BusinessException("Produto não pode ser excluido pois está sendo utilizado !");
		}
	}

	public Produto buscarPorSKU(String sku) {
		try {
			return entityManager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter produtoFilter) {

		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (StringUtils.isNotBlank(produtoFilter.getSku())) {
			criteria.add(Restrictions.eq("sku", produtoFilter.getSku()));
		}

		if (StringUtils.isNotBlank(produtoFilter.getNome())) {
			criteria.add(Restrictions.ilike("nome", produtoFilter.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto buscarPorId(Long id) {
		return entityManager.find(Produto.class, id);
	}

	public List<Produto> buscarPorNome(String nome) {
		return entityManager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

}
