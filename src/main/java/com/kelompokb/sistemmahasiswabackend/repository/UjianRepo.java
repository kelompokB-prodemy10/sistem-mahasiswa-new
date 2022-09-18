package com.kelompokb.sistemmahasiswabackend.repository;

import com.kelompokb.sistemmahasiswabackend.model.entity.Ujian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UjianRepo extends JpaRepository <Ujian, Integer> {
    Optional<Ujian> findByIdMatkul (Integer idMatkul);
}
