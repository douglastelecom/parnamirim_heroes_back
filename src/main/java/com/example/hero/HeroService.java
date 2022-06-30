package com.example.hero;

import com.example.hero.domain.model.Hero;
import com.example.hero.domain.model.Herodto;
import com.example.hero.domain.repository.HeroRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Service
public class HeroService {
  @Autowired
  private HeroRepository heroRepository;

  private Herodto herodto = new Herodto();

  public Herodto compare(Long a, Long b) {

    if (heroRepository.consultar(a).equals(heroRepository.consultar(b))) {
      herodto.setName("Empate");
      herodto.setPicture("Empate.jpg");
      System.out.println(herodto.getName()+herodto.getPicture());
      return herodto;
    } else if (heroRepository.consultar(a) > heroRepository.consultar(b)) {
      herodto.setName(heroRepository.findById(a).get().getName());
      herodto.setPicture(heroRepository.findById(a).get().getPicture());
      System.out.println(herodto.getName()+herodto.getPicture());
      return herodto;
    } else {
      herodto.setName(heroRepository.findById(b).get().getName());
      herodto.setPicture(heroRepository.findById(b).get().getPicture());
      System.out.println(herodto.getName()+herodto.getPicture());
      return herodto;
    }
  }
}
