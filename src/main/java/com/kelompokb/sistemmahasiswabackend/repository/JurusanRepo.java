package com.kelompokb.sistemmahasiswabackend.repository;

import com.kelompokb.sistemmahasiswabackend.model.entity.Jurusan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JurusanRepo extends JpaRepository<Jurusan, Integer> {
    @Override
    Optional<Jurusan> findById(Integer integer);
}
