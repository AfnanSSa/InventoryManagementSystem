package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServices {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductServices productServices;

    public Invoice createInvoice(Invoice invoice) {
        invoice.setProducts(Arrays.asList(productServices.getProductByID(1102)));
        invoice.setCreatedDate(new Date());
        invoice.setTotalAmount(10.0);

        Date dueDate = DateHelperUtils.addDays(invoice.getCreatedDate(), 7);
        invoice.setDueDate(dueDate);

        return invoiceRepository.save(invoice);
    }

    public Invoice getBInvoiceById(Integer id) {
        return invoiceRepository.getInvoiceById(id);
    }

    public List<Invoice> getInvoiceByCreatedDate(Date createdDate) {
        return invoiceRepository.getInvoiceByCreatedDate(createdDate);
    }

    public List<Invoice> getInvoiceByDueDate(Date dueDate) {
        return invoiceRepository.getInvoiceByDueDate(dueDate);
    }

    // method to get invoices due in next few days
    public List<Invoice> getInvoiceDueInNextDays(Integer days){
        Date today = new Date();
        Date dueDate = DateHelperUtils.addDays(today, days);
        return invoiceRepository.getInvoicesByDueDateBetween(today, dueDate);
    }

}
