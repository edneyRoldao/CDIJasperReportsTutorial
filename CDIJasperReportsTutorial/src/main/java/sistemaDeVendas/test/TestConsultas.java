package sistemaDeVendas.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.model.Usuario;
import sistemaDeVendas.model.ValorVendaDiaria;

public class TestConsultas {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mySQLConfig");
		EntityManager em = factory.createEntityManager();
		Session session = em.unwrap(Session.class);

		Map<Date, BigDecimal> values = valoresTotaisPorData(15, null, session);

		for (Date data : values.keySet()) {
			System.out.println(data + " = " + values.get(data));
		}

		em.close();
	}

	public static Map<Date, BigDecimal> valoresTotaisPorData(Integer nrDias, Usuario user, Session session) {

		nrDias -= 1;

		Calendar dataInicio = Calendar.getInstance();
		dataInicio = DateUtils.truncate(dataInicio, Calendar.DAY_OF_MONTH);
		dataInicio.add(Calendar.DAY_OF_MONTH, nrDias * -1);

		// Criando um MAP vazio somente com as datas das vendas.
		Map<Date, BigDecimal> resultado = criarMapaVazio(dataInicio, nrDias);
		
		//Usando criteria para recuperar os resultados no banco
		Criteria criteria = session.createCriteria(Pedido.class);
		
		//Para utilização de functions com sum(...), max(...) e outros, temos que usar projection
		criteria.setProjection(Projections.projectionList()
					//inicio dos projections
					.add(Projections.sqlGroupProjection( 				//inicio dos projections
							"date(data_criacao) as data"	, 					// SQL 
							"date(data_criacao)"			, 					// groupBy
							new String[] {"data"}			, 					// collumnAliases
							new Type[] { StandardBasicTypes.DATE }))			// Tipo da coluna
					.add(Projections.sum("valorTotal").as("valor")))		// fim dos projections
					.add(Restrictions.ge("dataCriacao", dataInicio.getTime()));
					
		if(user != null) {
			criteria.add(Restrictions.eq("vendedor", user));
		}
		
		@SuppressWarnings("unchecked")
		List<ValorVendaDiaria> valoresPorData = criteria.setResultTransformer(Transformers.aliasToBean(ValorVendaDiaria.class)).list();
		for (ValorVendaDiaria vlDiaria : valoresPorData) {
			resultado.put(vlDiaria.getData(), vlDiaria.getValor());
		}
		
		return resultado;
	}

	private static Map<Date, BigDecimal> criarMapaVazio(Calendar dataInicio, Integer nrDias) {

		dataInicio = (Calendar) dataInicio.clone();

		Map<Date, BigDecimal> mapaInicio = new TreeMap<>();

		for (int i = 0; i < nrDias; i++) {
			mapaInicio.put(dataInicio.getTime(), BigDecimal.ZERO);
			dataInicio.add(Calendar.DAY_OF_MONTH, 1);
		}

		return mapaInicio;
	}

}
