package vn.edu.fpt.projectstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.projectstore.entity.Customer;
import vn.edu.fpt.projectstore.entity.Voucher;
import vn.edu.fpt.projectstore.entity.VoucherCustomer;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoucherCustomerRepository extends JpaRepository<VoucherCustomer, UUID> {

    boolean existsByCustomerAndVoucher(Customer customer, Voucher voucher);

    void deleteByCustomerAndVoucher(Customer customer, Voucher voucher);

    List<VoucherCustomer> findAllByCustomer(Customer customer);

}
