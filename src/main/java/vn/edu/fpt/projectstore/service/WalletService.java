package vn.edu.fpt.projectstore.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.projectstore.entity.Customer;
import vn.edu.fpt.projectstore.entity.Transaction;
import vn.edu.fpt.projectstore.repository.CustomerRepositories;
import vn.edu.fpt.projectstore.repository.TransactionRepositories;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletService { //noye

    @Autowired
    private CustomerRepositories customerRepo;

    @Autowired
    private TransactionRepositories transactionRepo;

    @Transactional
    public void topUp(String username, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải > 0");
        }

        Customer c = customerRepo.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy customer"));

        if (c.getBalance() == null) {
            c.setBalance(BigDecimal.ZERO);
        }

        c.setBalance(c.getBalance().add(amount));
        customerRepo.save(c);

        Transaction t = new Transaction();
        t.setCustomer(c);
        t.setAmount(amount);
        t.setDescription("TOPUP");
        t.setDateCreated(LocalDateTime.now());

        transactionRepo.save(t);
    }
}
