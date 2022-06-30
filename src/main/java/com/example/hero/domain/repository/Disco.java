package com.example.hero.domain.repository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.hero.domain.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Disco {

    @Autowired
    HeroRepository heroRepository;

    @Value("${contato.disco.raiz}")
    private String raiz;

    @Value("${contato.disco.diretorio-fotos}")
    private String diretorioFotos;




    public void salvarFoto(MultipartFile foto, Long id) {
        this.salvar(this.diretorioFotos, foto, id);
    }

    public void salvar(String diretorio, MultipartFile arquivo, Long id) {
        Path diretorioPath = Paths.get(this.raiz, diretorio);
        String pictureName = id+arquivo.getContentType().replace("image/",".");
        Path arquivoPath = diretorioPath.resolve(pictureName);
        Hero h = heroRepository.findById(id).get();
        h.setPicture(pictureName);
        heroRepository.save(h);

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());

        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
        }
    }
}
