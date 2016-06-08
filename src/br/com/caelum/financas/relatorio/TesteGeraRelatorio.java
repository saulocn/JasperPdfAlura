package br.com.caelum.financas.relatorio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.financas.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TesteGeraRelatorio {

	public static void main(String[] args) throws SQLException, JRException, IOException {

		JasperCompileManager.compileReportToFile("financas.jrxml");

		Connection conexao = new ConnectionFactory().getConnection();
		Map<String, Object> params = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport("financas.jasper", params, conexao);
		OutputStream stream = new FileOutputStream("financas.pdf");

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();

		stream.close();
		conexao.close();
	}
}
