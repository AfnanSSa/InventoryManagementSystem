package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Services.InvoiceServices;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

