package vn.edu.fpt.projectstore.service;


import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.fpt.projectstore.entity.Staff;
import vn.edu.fpt.projectstore.repository.StaffRepositories;


import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final StaffRepositories staffRepository;
    private final CustomerService customerService;

    public AppUserDetailsService(StaffRepositories staffRepository, CustomerService customerService) {
        this.staffRepository = staffRepository;
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Staff staff = staffRepository.findByUsername(username).orElse(null);
        if (staff != null) {
            if (staff.getStatus() != null && staff.getStatus().equalsIgnoreCase("LOCK")) {
                throw new DisabledException("Tài khoản đã bị khóa");
            }

            String role = staff.getRole();
            if (role == null || role.isBlank()) role = "STAFF";

            return new org.springframework.security.core.userdetails.User(
                    staff.getUsername(),
                    staff.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
            );
        }

        return customerService.loadCustomerByUsername(username);
    }
}
