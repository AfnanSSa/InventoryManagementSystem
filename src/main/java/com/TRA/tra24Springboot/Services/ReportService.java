package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ReportService {
    @Autowired
    InventoryServices inventoryServices;
    public void createSchoolReport() throws FileNotFoundException, JRException {
        List<InventoryDTO> inventoryDTOList = inventoryServices.getInventory();
        UUID uuid = UUID.randomUUID();
        String timeStamp = DateHelperUtils.formatDate(new Date(), "yyyy-MM-dd_HH-mm-ss");

        String pathToSave = "C:\\Users\\TRA\\Downloads\\";

        File templateFile = ResourceUtils.getFile("C:\\Users\\TRA\\Documents\\AfnansInventorySolution\\InventoryManagementSystem\\src\\main\\resources\\Inventory_Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inventoryDTOList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap<>(),dataSource);
        String fileName = pathToSave + uuid + "_" + timeStamp +".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,fileName );
        System.out.println("Report is printed: "+ fileName);
    }
}
