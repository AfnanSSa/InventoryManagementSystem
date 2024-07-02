package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Services.InvoiceServices;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceServices invoiceServices;
    @Autowired
    SlackService slackService;

    @PostMapping("create")
    public Invoice createInvoice(Invoice invoice){
        slackService.sendMessage("afnan", "New invoice has been created!");
        return invoiceServices.createInvoice(invoice);
    }
}
