package com.example.hero.service;

import com.example.hero.domain.model.Hero;
import com.example.hero.domain.model.HeroDTO;
import com.example.hero.domain.repository.Disco;
import com.example.hero.domain.repository.HeroRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Service
public class HeroService {
  private final HeroRepository heroRepository;
  private final Disco disco;
  private final ModelMapper modelMapper;

  public HeroDTO compare(Long a, Long b) {
    if (heroRepository.consultar(a).equals(heroRepository.consultar(b))) {
      HeroDTO heroDTO = new HeroDTO();
      heroDTO.setName("Empate");
      heroDTO.setPicture("Empate.jpg");
      return heroDTO;
    } else if (heroRepository.consultar(a) > heroRepository.consultar(b)) {
      Hero heroa = heroRepository.findById(a).get();
      return convertToHeroDTO(heroa);
    } else {
      Hero herob = heroRepository.findById(b).get();
      return convertToHeroDTO(herob);
    }
  }
  public HeroDTO convertToHeroDTO(Hero hero){
    return modelMapper.map(hero, HeroDTO.class);
  }

  public Hero convertToHero(HeroDTO heroDTO){
    return modelMapper.map(heroDTO, Hero.class);
  }

  public Long save(HeroDTO heroDTO){
    return heroRepository.save(convertToHero(heroDTO)).getId();
  }
  public List<HeroDTO> list(){
    List<HeroDTO> heroDTOList = new ArrayList<>();
    List<Hero> listHero = heroRepository.findAll();
    if(listHero != null){
      for(Hero hero: listHero){
        heroDTOList.add(convertToHeroDTO(hero));
      }
    }
    return heroDTOList;
  }

  public void delete(long heroId){
    heroRepository.deleteById(heroId);
  }

  public void addPicture(MultipartFile picture, Long id){
    disco.salvarFoto(picture, id);
  }
}
