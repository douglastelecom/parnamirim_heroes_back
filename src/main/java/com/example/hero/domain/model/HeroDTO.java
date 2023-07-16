package com.example.hero.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroDTO {
    private Long id;
    private String name;
    private String picture;
    private List<Power> power;
}
