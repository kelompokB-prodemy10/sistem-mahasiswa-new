package com.kelompokb.sistemmahasiswabackend.repository;

import com.kelompokb.sistemmahasiswabackend.model.entity.Matkul;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatkulRepo extends JpaRepository<Matkul, Integer> {
    @Override
    Optional<Matkul> findById(Integer integer);
}
