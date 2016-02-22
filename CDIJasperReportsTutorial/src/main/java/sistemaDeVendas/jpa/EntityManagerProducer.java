package sistemaDeVendas.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("mySQLConfig");
	}
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	//A anotação presente nesse método é um callBack do CDI que faz referencia ou requestScoped do metodo acima
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
	
}
