package sistemaDeVendas.repositories;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import sistemaDeVendas.filters.PedidoFilter;
import sistemaDeVendas.model.Pedido;

public class PedidoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<Pedido> filtrarPedidos(PedidoFilter filter) {
		
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pedido.class);
	
		criteria.createAlias("cliente", "c");
		criteria.createAlias("vendedor", "v");
		
		
		if(filter.getNumeroDe() != null) 
			criteria.add(Restrictions.ge("id", filter.getNumeroDe()));
		
		if(filter.getNumeroAte() != null)
			criteria.add(Restrictions.le("id", filter.getNumeroAte()));
		
		if(filter.getDataCriacaoDe() != null)
				criteria.add(Restrictions.ge("dataCriacao", filter.getDataCriacaoDe()));
		
		if(StringUtils.isNotBlank(filter.getNomeCliente()))
			criteria.add(Restrictions.ilike("c.nome", filter.getNomeCliente(), MatchMode.ANYWHERE));

		if(StringUtils.isNotBlank(filter.getNomeVendedor()))
			criteria.add(Restrictions.ilike("v.nome", filter.getNomeVendedor(), MatchMode.ANYWHERE));
		
		if(filter.getStatuses() != null && filter.getStatuses().length > 0)
			criteria.add(Restrictions.in("status", filter.getStatuses()));
		
		return criteria.addOrder(Order.asc("id")).list();
	}
	
	public Pedido salvar(Pedido pedido) {
		return this.entityManager.merge(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return entityManager.find(Pedido.class, id);
	}

}
