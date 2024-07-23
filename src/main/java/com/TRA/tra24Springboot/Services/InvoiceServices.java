package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
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

    @TrackExecutionTime
    public Invoice createInvoice(Invoice invoice) {
        invoice.setProducts(Arrays.asList(productServices.getProductByID(802)));
        invoice.setCreatedDate(new Date());
        invoice.setPaymentDate(new Date());
        invoice.setTotalAmount(350.0);

        Date dueDate = DateHelperUtils.addDays(invoice.getCreatedDate(), 7);
        invoice.setDueDate(dueDate);

        return invoiceRepository.save(invoice);
    }

    @TrackExecutionTime
    public Invoice getBInvoiceById(Integer id) {
        return invoiceRepository.getInvoiceById(id);
    }

    @TrackExecutionTime
    public List<Invoice> getInvoiceByCreatedDate(Date createdDate) {
        return invoiceRepository.getInvoiceByCreatedDate(createdDate);
    }

    @TrackExecutionTime
    public List<Invoice> getInvoiceByDueDate(Date dueDate) {
        return invoiceRepository.getInvoiceByDueDate(dueDate);
    }

    // method to get invoices due in next few days
    @TrackExecutionTime
    public List<Invoice> getInvoiceDueInNextDays(Integer days){
        Date today = new Date();
        Date dueDate = DateHelperUtils.addDays(today, days);
        return invoiceRepository.getInvoicesByDueDateBetween(today, dueDate);
    }

    //method to get overdue invoices
    @TrackExecutionTime
    public List<Invoice> getOverDueInvoices(){
        Date today = new Date();
        return invoiceRepository.getOverdueInvoices(today);
    }

    @TrackExecutionTime
    public List<Invoice> getInvoicesCreatedBetween(Date startDate, Date endDate) {
        return invoiceRepository.getInvoicesCreatedBetween(startDate, endDate);
    }

    @TrackExecutionTime
    public List<Invoice> getPaidInvoicesBetween(Date startDate, Date endDate) {
        return invoiceRepository.getPaidInvoicesBetween(startDate, endDate);
    }

}
