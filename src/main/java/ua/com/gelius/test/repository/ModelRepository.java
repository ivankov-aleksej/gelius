package ua.com.gelius.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.gelius.test.model.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
