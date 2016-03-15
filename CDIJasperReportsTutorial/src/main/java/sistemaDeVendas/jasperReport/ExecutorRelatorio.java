package sistemaDeVendas.jasperReport;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ExecutorRelatorio implements Work {
	
	private String nomeArquivoEntrada;
	private String nomeArquivoSaida;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private boolean relatorioGerado;
	

	public ExecutorRelatorio(String nomeArquivoEntrada, String nomeArquivoSaida, HttpServletResponse response,
			Map<String, Object> parametros) {
		super();
		this.nomeArquivoEntrada = nomeArquivoEntrada;
		this.nomeArquivoSaida = nomeArquivoSaida;
		this.response = response;
		this.parametros = parametros;
		
		// Com isso a geração de relatório vai entender que a formatação da moeda e no padrão brasileiro
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}


	@Override
	public void execute(Connection connection) throws SQLException {
		
		// Utilizamos essa instrução para recuperar o fluxo de dados de um determinado arquivo em nosso projeto
		// ou seja, estamos pegendo os bytes do arquivo.jasper que está em src/main/resources/relatorios/... 
		try {
			InputStream relatorioStream = getClass().getResourceAsStream(nomeArquivoEntrada);
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, connection);
			
			//Fazendo tratamento de páginas vazias.
			relatorioGerado = print.getPages().size() > 0;
			
			//Expertando o relatório em PDF se existir páginas.
			if(relatorioGerado) {
				JRExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivoSaida + "\"");
				exporter.exportReport();
			}
			
		} catch (Exception e) {
			throw new SQLException("Erro ao executar o relatório " + nomeArquivoEntrada, e);
		}
		
	}
	
	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}
	
}
