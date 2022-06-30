package com.example.hero.controller;
import com.example.hero.HeroService;
import com.example.hero.domain.model.Hero;
import com.example.hero.domain.model.Herodto;
import com.example.hero.domain.model.Power;
import com.example.hero.domain.repository.Disco;
import com.example.hero.domain.repository.HeroRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/hero")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

    private Disco disco;
    private HeroService heroService;
    private HeroRepository heroRepository;

    @GetMapping
    public List<Hero> listar(){
        return heroRepository.findAll();
    }

    @GetMapping("/duelo")
    public Herodto consultar(Long hero1, Long hero2) {
        Herodto herodto = heroService.compare(hero1,hero2);
        return herodto;
    }

    @PostMapping
    public Long adicionar(@RequestBody Hero hero){
        heroRepository.save(hero);
        System.out.println(hero.getId());
      return hero.getId();
    }

    @PostMapping("/picture")
    public void addPicture(@RequestParam MultipartFile picture, Long id){
        disco.salvarFoto(picture, id);
    }


    @DeleteMapping("/{heroId}")
    public void deletar(@PathVariable long heroId){
        heroRepository.deleteById(heroId);
    }

}