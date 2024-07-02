package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
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
        invoice.setProducts(Arrays.asList(productServices.getProductByID(652)));
        invoice.setCreatedDate(new Date());
        invoice.setTotalAmount(350.0);
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
}
