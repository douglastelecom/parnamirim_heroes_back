package com.example.hero.domain.repository;
import com.example.hero.domain.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>, JpaSpecificationExecutor<Hero> {

  @Query(
      value =
          "SELECT SUM(p.power_Points) from power as p left join hero_power as hp on p.id = hp.power_id left join hero as h on hp.hero_id = h.id where h.id = :n",
      nativeQuery = true)
  Float consultar(Long n);
}