package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import sistemaDeVendas.jasperReport.ExecutorRelatorio;
import sistemaDeVendas.jsf.util.FacesUtil;

@Named
@RequestScoped
public class RelatoriosJasperReportBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Inject
	private FacesContext context;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private Date dataInicio;
	private Date dataFinal;

	
	public void emitirRelatorio() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFinal);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_pedidos_emitidos.jasper", "pedidoEmitido.pdf", response, parametros);
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if(executor.isRelatorioGerado()) {
			//Utilizamos esse instrução para interromper o ciclo de vida de JSF, 
			//assim ele não vai tentar renderizar nunhuma página, pois estamos gerando um relatório.
			context.responseComplete();
		}else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

	
	@NotNull
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@NotNull
	public Date getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
