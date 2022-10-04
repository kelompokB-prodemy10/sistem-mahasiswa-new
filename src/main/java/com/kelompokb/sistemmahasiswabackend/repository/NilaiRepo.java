package com.kelompokb.sistemmahasiswabackend.repository;

import com.kelompokb.sistemmahasiswabackend.model.entity.Nilai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NilaiRepo extends JpaRepository<Nilai, Integer> {
    Optional<Nilai> findByIdMhs(Integer idMhs);
    Optional<Nilai> findByIdUjian(Integer idUjian);
    Optional<Nilai> findByIdJurusan(Integer idJurusan);
    Optional<Nilai> findByIdMatkul(Integer idMatkul);
    Optional<Nilai> findById(Integer idNilai);
}
