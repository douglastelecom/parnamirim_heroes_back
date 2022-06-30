package com.example.hero.controller;
import com.example.hero.domain.model.Power;
import com.example.hero.domain.repository.PowerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping (value = "/power")
@CrossOrigin(origins = "http://localhost:4200")
public class PowerController {

    private final PowerRepository powerRepository;
    @GetMapping
    public List<Power> listar(){
        return powerRepository.findAll();
    }

    @GetMapping ("/{powerId}")
    public Optional<Power> buscar(@PathVariable long powerId){
        return powerRepository.findById(powerId);
    }

    @PostMapping
    public void adicionar(@RequestBody Power power){
        powerRepository.save(power);
    }

    @DeleteMapping("/{powerId}")
    public void deletar(@PathVariable long powerId){
        powerRepository.deleteById(powerId);
    }


}
