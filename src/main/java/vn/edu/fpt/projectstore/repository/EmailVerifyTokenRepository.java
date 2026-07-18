package vn.edu.fpt.projectstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.projectstore.entity.EmailVerifyToken;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailVerifyTokenRepository
        extends JpaRepository<EmailVerifyToken, UUID> {

    Optional<EmailVerifyToken> findByToken(String token);
}
