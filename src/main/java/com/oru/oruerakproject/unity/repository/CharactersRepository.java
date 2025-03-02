package com.oru.oruerakproject.unity.repository;

import com.oru.oruerakproject.unity.entitiy.CharactersEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharactersRepository extends JpaRepository<CharactersEntitiy, Integer> {
    Optional<CharactersEntitiy> findBymountainName(String mountainName);
}
