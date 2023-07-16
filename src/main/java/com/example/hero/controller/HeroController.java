package com.example.hero.controller;
import com.example.hero.service.HeroService;
import com.example.hero.domain.model.HeroDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/hero")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {
    private HeroService heroService;
    @GetMapping
    public List<HeroDTO> listar(){
        return heroService.list();
    }
    @GetMapping("/duelo")
    public HeroDTO consultar(Long hero1, Long hero2) {
        HeroDTO herodto = heroService.compare(hero1,hero2);
        return herodto;
    }
    @PostMapping
    public Long adicionar(@RequestBody HeroDTO heroDTO){
      return heroService.save(heroDTO);
    }
    @PostMapping("/picture")
    public void addPicture(@RequestParam MultipartFile picture, Long id){
        heroService.addPicture(picture, id);
    }

    @DeleteMapping("/{heroId}")
    public void deletar(@PathVariable long heroId){
        heroService.delete(heroId);
    }
}