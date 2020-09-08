package com.example.topfinance.web;

import com.example.topfinance.dao.OperationRepository;
import com.example.topfinance.entities.Operation;
import com.github.royken.converter.FrenchNumberToWords;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class ReportRestService {

    @Autowired
    private OperationRepository operationRepository;

    @Value("${reports.dir}")
    private String reportDir;

    @RequestMapping(value = "reportEssai2/{code}", method = RequestMethod.GET)
    public void reportEssai2(HttpServletResponse response,@PathVariable String code) throws Exception,FileNotFoundException{
        response.setContentType("text/html");

        List<Operation> operations=operationRepository.findAll();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operations);

        //InputStream jrxmlInput = this.getClass().getResourceAsStream("classpath:essai.jrxml");
        InputStream jrxmlInput = new FileInputStream(new File("D:/essai.jrxml"));
        JasperDesign design = JRXmlLoader.load(jrxmlInput);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);

        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();

        response.setContentType("application/pdf");
        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");

        OutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(pdfReportStream.toByteArray());
        responseOutputStream.close();
        pdfReportStream.close();
    }

    @Autowired
    ResourceLoader resourceLoader;

    public Resource loadEmployees() {
        return resourceLoader.getResource(
                "classpath:data/employees.dat");
    }

    @Autowired
    ServletContext context;


    @RequestMapping(value = "recuVers/{numero_operation}", method = RequestMethod.GET)
    public void reportDepot(HttpServletResponse response,@PathVariable Long numero_operation) throws Exception,FileNotFoundException{
        response.setContentType("text/html");

        List<Operation> operations=operationRepository.findAll();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operations);
        //Recuperation de l'operation
        Optional<Operation> operation= operationRepository.findById(numero_operation);

        //Convertion en entier avant de proceder ....
        int entier = (int) Math.floor(operation.get().getMontant());

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("montant", operation.get().getMontant());
        parameters.put("numero_operation", operation.get().getNumero_operation());
        parameters.put("montant_lettre", FrenchNumberToWords.convert(entier)+"francs");
        parameters.put("date_operation", operation.get().getDate_operation());
        parameters.put("date_valeur", operation.get().getDate_valeur());
        parameters.put("solde", operation.get().getCompte().getSolde());
        parameters.put("code_compte", operation.get().getCompte().getCode_compte());
        parameters.put("nom_client", operation.get().getCompte().getClient().getNom_client());
        parameters.put("employe", operation.get().getEmploye());
        parameters.put("libelle_reglement", operation.get().getMode().getLibelle_reglement());

        InputStream jrxmlInput = new FileInputStream(new File(reportDir+"RecuDepot.jrxml"));
        JasperDesign design = JRXmlLoader.load(jrxmlInput);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();

        response.setContentType("application/pdf");
        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");

        OutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(pdfReportStream.toByteArray());
        responseOutputStream.close();
        pdfReportStream.close();
    }

    @RequestMapping(value = "recuRet/{numero_operation}", method = RequestMethod.GET)
    public void reportRetrait(HttpServletResponse response,@PathVariable Long numero_operation) throws Exception,FileNotFoundException{
        response.setContentType("text/html");

        List<Operation> operations=operationRepository.findAll();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operations);
        //Recuperation de l'operation
        Optional<Operation> operation= operationRepository.findById(numero_operation);

        //Convertion en entier avant de proceder ....
        int entier = (int) Math.floor(operation.get().getMontant());

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("montant", operation.get().getMontant());
        parameters.put("numero_operation", operation.get().getNumero_operation());
        parameters.put("montant_lettre", FrenchNumberToWords.convert(entier)+"francs");
        parameters.put("date_operation", operation.get().getDate_operation());
        parameters.put("date_valeur", operation.get().getDate_valeur());
        parameters.put("solde", operation.get().getCompte().getSolde());
        parameters.put("code_compte", operation.get().getCompte().getCode_compte());
        parameters.put("nom_client", operation.get().getCompte().getClient().getNom_client());
        parameters.put("employe", operation.get().getEmploye());
        parameters.put("libelle_reglement", operation.get().getMode().getLibelle_reglement());

        InputStream jrxmlInput = new FileInputStream(new File(reportDir+"RecuRetrait.jrxml"));
        JasperDesign design = JRXmlLoader.load(jrxmlInput);
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();

        response.setContentType("application/pdf");
        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");

        OutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(pdfReportStream.toByteArray());
        responseOutputStream.close();
        pdfReportStream.close();
    }
}

