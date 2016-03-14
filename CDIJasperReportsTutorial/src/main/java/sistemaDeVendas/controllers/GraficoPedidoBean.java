package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import sistemaDeVendas.model.Usuario;
import sistemaDeVendas.repositories.PedidoRepository;
import sistemaDeVendas.security.SystemUser;
import sistemaDeVendas.security.UserLogged;

@Named
@RequestScoped
public class GraficoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	@Inject
	PedidoRepository pedidoRepository;
	
	@Inject
	@UserLogged
	SystemUser userLogged;
	
	
	private CartesianChartModel model;

	public void preRender() {
		model = new CartesianChartModel();
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", userLogged.getUsuario());
	}

	private void adicionarSerie(String rotulo, Usuario user) {

		Map<Date, BigDecimal> valoresPorData = pedidoRepository.valoresTotaisPorData(15, user);
		ChartSeries series = new ChartSeries(rotulo);
		
		for(Date data : valoresPorData.keySet()) {
			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}

		model.addSeries(series);
	}

	public CartesianChartModel getModel() {
		return model;
	}

	public void setModel(CartesianChartModel model) {
		this.model = model;
	}

}
