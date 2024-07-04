package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Services.InvoiceServices;
import com.TRA.tra24Springboot.Services.SlackService;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceServices invoiceServices;
    @Autowired
    SlackService slackService;

    @PostMapping("create")
    public Invoice createInvoice(Invoice invoice) {
        slackService.sendMessage("afnan", "New invoice has been created!");
        return invoiceServices.createInvoice(invoice);
    }

    @Scheduled(cron = "0 0 9 * * ?")
    @PostMapping("dueDate")
    public void senDueDateReminder() {
        Integer remainingDays = 3;
        List<Invoice> invoices = invoiceServices.getInvoiceDueInNextDays(remainingDays);
        for (Invoice invoice : invoices) {
            StringBuilder message = new StringBuilder();
            message.append("Reminder: Invoice #")
                    .append(invoice.getId())
                    .append(" is due on ")
                    .append(invoice.getDueDate().toString());
            slackService.sendMessage("afnan", message.toString());
        }
    }

    @Scheduled(cron = "0 0 9 * * ?")
    @PostMapping("overdue")
    public void sendOverdueReminder(){
        List<Invoice> overdueInvoices = invoiceServices.getOverDueInvoices();
        for (Invoice invoice : overdueInvoices) {
            StringBuilder message = new StringBuilder();
            message.append("Alert: Invoice #")
                    .append(invoice.getId())
                    .append(" is overdue since ")
                    .append(invoice.getDueDate().toString());
            slackService.sendMessage("afnan", message.toString());
        }
    }

    @Scheduled(cron = "0 0 9 * * 0") //runs every Sunday 
    @PostMapping("weeklyReport")
    public void weeklyInvoiceReport(){
        Date today = new Date();
        Date startDate = DateHelperUtils.subtractDays(today, 6); //during the last 7 days

        List<Invoice> createdInvoices = invoiceServices.getInvoicesCreatedBetween(startDate, today);
        List<Invoice> paidInvoices = invoiceServices.getPaidInvoicesBetween(startDate, today);
        List<Invoice> overdueInvoices = invoiceServices.getOverDueInvoices();

        StringBuilder report = new StringBuilder();
        report.append("Weekly Summary Report:\n")
                .append("Invoices Created:\n");
        appendInvoicesToReport(report, createdInvoices);
        report.append("\nInvoices Paid:\n");
        appendInvoicesToReport(report, paidInvoices);
        report.append("\nOverdue Invoices:\n");
        appendInvoicesToReport(report, overdueInvoices);

        slackService.sendMessage("afnan", report.toString());
    }

    private void appendInvoicesToReport(StringBuilder report, List<Invoice> invoices){
        for (Invoice invoice : invoices) {
            report.append("Invoice #")
                    .append(invoice.getId())
                    .append(" - Due on ")
                    .append(invoice.getDueDate().toString())
                    .append("\n");
        }
    }
}

