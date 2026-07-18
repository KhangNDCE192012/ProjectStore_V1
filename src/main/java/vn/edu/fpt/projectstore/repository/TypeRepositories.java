package vn.edu.fpt.projectstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.projectstore.entity.Type;


import java.util.UUID;

@Repository
public interface TypeRepositories extends JpaRepository<Type, UUID> {

    Type findByTypeId(UUID id);

    Type findByTypeName(String name);
}
