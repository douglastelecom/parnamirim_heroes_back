package com.example.hero.domain.repository;
import com.example.hero.domain.model.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerRepository extends JpaRepository<Power,Long> {
    Power findBy();
}
