package vn.edu.fpt.projectstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.projectstore.entity.Customer;
import vn.edu.fpt.projectstore.entity.Voucher;
import vn.edu.fpt.projectstore.repository.VoucherCustomerRepository;

@Service
public class VoucherCustomerService {

    @Autowired
    private VoucherCustomerRepository voucherCustomerRepository;


    public boolean hasUsed(Customer customer, Voucher voucher) {
        if (customer == null || voucher == null) return false;
        return voucherCustomerRepository.existsByCustomerAndVoucher(customer, voucher);
    }

    public void rollbackUsed(Customer customer, Voucher voucher) {
        if (customer == null || voucher == null) return;
        voucherCustomerRepository.deleteByCustomerAndVoucher(customer, voucher);
    }




}
