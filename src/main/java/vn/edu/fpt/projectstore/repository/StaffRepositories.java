package vn.edu.fpt.projectstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.projectstore.entity.Staff;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepositories extends JpaRepository<Staff, UUID> {

    Optional<Staff> findByUsername(String username);

    List<Staff> findByUsernameContainingIgnoreCase(String username);

    List<Staff> findByRole(String role);
}
