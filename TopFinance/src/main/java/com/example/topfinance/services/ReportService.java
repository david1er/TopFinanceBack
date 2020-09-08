package com.example.topfinance.services;

import com.example.topfinance.dao.OperationRepository;
import com.example.topfinance.entities.Operation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private OperationRepository operationRepository;

    String path="D:\\backupDB";
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Operation> operations=operationRepository.findAll();
        //Load file and compile it
        File file= ResourceUtils.getFile("classpath:essai.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(operations);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Crée par","David 1er");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);

       // JasperViewer.viewReport(jasperPrint);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\operations.html ");
        }

        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\operations.pdf ");
        }
        return "Report géneré au path: "+path;

    }

    public void report2() throws JRException, FileNotFoundException {

        List<Operation> operations=operationRepository.findAll();
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(operations);

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("Parameter1", itemsJRBean);

        InputStream input = new FileInputStream(new File("C:\\Users\\David 1er\\IdeaProjects\\topfinance\\src\\main\\resources\\essai.jrxml"));
        JasperDesign jasperDesign = JRXmlLoader.load(input);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());

        JasperViewer.viewReport(jasperPrint);

       /* JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(operations);
        InputStream jasperStream = Operation.class.getResourceAsStream("classpath:essai.jrxml");
        JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperStream, parameters, dataSource1);
        JasperViewer viewer = new JasperViewer(jasperPrint1, true);
        viewer.setVisible(true);*/

        //System.out.println("File genereted");
    }


}
