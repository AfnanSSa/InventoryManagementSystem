package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    InventoryServices inventoryServices;
    @Autowired
    JavaMailSender mailSender;

    public void createSchoolReport() throws Exception {
        List<InventoryDTO> inventoryDTOList = inventoryServices.getInventory();
        String timeStamp = DateHelperUtils.formatDate(new Date(), "yyyy-MM-dd_HH-mm-ss");

        String pathToSave = "C:\\Users\\TRA\\Downloads\\";

        File templateFile = ResourceUtils.getFile("C:\\Users\\TRA\\Documents\\AfnansInventorySolution\\InventoryManagementSystem\\src\\main\\resources\\Inventory_Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inventoryDTOList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        String fileName = pathToSave + "Report_" + timeStamp + ".pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
        System.out.println("Report is printed: " + fileName);

        sendReportByEmail(fileName); //sending the generated report via email
    }

    private void sendReportByEmail(String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(" ");
        helper.setSubject("Hourly Inventory Report");
        helper.setText("Please find attached the most recent inventory report.");

        File reportFile = new File(filePath);
        helper.addAttachment(reportFile.getName(), reportFile);

        mailSender.send(message);
        System.out.println("Email sent successfully with attachment: " + reportFile.getName());
    }
}
