package sistemaDeVendas.repositories;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import sistemaDeVendas.filters.PedidoFilter;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.model.Usuario;
import sistemaDeVendas.model.ValorVendaDiaria;

public class PedidoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Pedido> filtrarPedidos(PedidoFilter filter) {

		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pedido.class);

		criteria.createAlias("cliente", "c");
		criteria.createAlias("vendedor", "v");

		if (filter.getNumeroDe() != null)
			criteria.add(Restrictions.ge("id", filter.getNumeroDe()));

		if (filter.getNumeroAte() != null)
			criteria.add(Restrictions.le("id", filter.getNumeroAte()));

		if (filter.getDataCriacaoDe() != null)
			criteria.add(Restrictions.ge("dataCriacao", filter.getDataCriacaoDe()));

		if (StringUtils.isNotBlank(filter.getNomeCliente()))
			criteria.add(Restrictions.ilike("c.nome", filter.getNomeCliente(), MatchMode.ANYWHERE));

		if (StringUtils.isNotBlank(filter.getNomeVendedor()))
			criteria.add(Restrictions.ilike("v.nome", filter.getNomeVendedor(), MatchMode.ANYWHERE));

		if (filter.getStatuses() != null && filter.getStatuses().length > 0)
			criteria.add(Restrictions.in("status", filter.getStatuses()));

		return criteria.addOrder(Order.asc("id")).list();
	}

	public Pedido salvar(Pedido pedido) {
		return this.entityManager.merge(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return entityManager.find(Pedido.class, id);
	}

	public Map<Date, BigDecimal> valoresTotaisPorData(Integer nrDias, Usuario user) {

		Session session = entityManager.unwrap(Session.class);

		nrDias -= 1;

		Calendar dataInicio = Calendar.getInstance();
		dataInicio = DateUtils.truncate(dataInicio, Calendar.DAY_OF_MONTH);
		dataInicio.add(Calendar.DAY_OF_MONTH, nrDias * -1);

		// Criando um MAP vazio somente com as datas das vendas.
		Map<Date, BigDecimal> resultado = criarMapaVazio(dataInicio, nrDias);

		// Usando criteria para recuperar os resultados no banco
		Criteria criteria = session.createCriteria(Pedido.class);

		// Para utilização de functions com sum(...), max(...) e outros, temos
		// que usar projection
		criteria.setProjection(Projections.projectionList()
				// inicio dos projections
				.add(Projections.sqlGroupProjection( // inicio dos projections
						"date(data_criacao) as data", // SQL
						"date(data_criacao)", // groupBy
						new String[] { "data" }, // collumnAliases
						new Type[] { StandardBasicTypes.DATE })) // Tipo da
																	// coluna
				.add(Projections.sum("valorTotal").as("valor"))) // fim dos
																	// projections
				.add(Restrictions.ge("dataCriacao", dataInicio.getTime()));

		if (user != null) {
			criteria.add(Restrictions.eq("vendedor", user));
		}

		@SuppressWarnings("unchecked")
		List<ValorVendaDiaria> valoresPorData = criteria
				.setResultTransformer(Transformers.aliasToBean(ValorVendaDiaria.class)).list();
		for (ValorVendaDiaria vlDiaria : valoresPorData) {
			resultado.put(vlDiaria.getData(), vlDiaria.getValor());
		}

		return resultado;
	}

	private Map<Date, BigDecimal> criarMapaVazio(Calendar dataInicio, Integer nrDias) {

		dataInicio = (Calendar) dataInicio.clone();

		Map<Date, BigDecimal> mapaInicio = new TreeMap<>();

		for (int i = 0; i < nrDias; i++) {
			mapaInicio.put(dataInicio.getTime(), BigDecimal.ZERO);
			dataInicio.add(Calendar.DAY_OF_MONTH, 1);
		}

		return mapaInicio;
	}

}
