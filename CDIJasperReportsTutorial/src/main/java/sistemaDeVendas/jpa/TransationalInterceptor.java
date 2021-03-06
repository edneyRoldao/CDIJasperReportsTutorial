package sistemaDeVendas.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransationalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@AroundInvoke
	public Object invoke(InvocationContext context)throws Exception {
		EntityTransaction trx = entityManager.getTransaction();
		boolean criador = false;
		
		try {
			if(!trx.isActive()) {
				trx.begin();
				trx.rollback();
				
				trx.begin();
				criador = true;
			}
			
			return context.proceed();
			
		} catch (Exception e) {
			if(trx != null && criador) {
				trx.rollback();
			}
			throw e;
		}finally {
			if(trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}
	

}
