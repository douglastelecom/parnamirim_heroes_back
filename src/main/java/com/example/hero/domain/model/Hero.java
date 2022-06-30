package com.example.hero.domain.model;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Hero {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String picture;

    @ManyToMany
    @JoinTable(name="hero_power", joinColumns = @JoinColumn(name="hero_id"), inverseJoinColumns = @JoinColumn(name="power_id"))
    private List<Power> power = new ArrayList<>();

}
