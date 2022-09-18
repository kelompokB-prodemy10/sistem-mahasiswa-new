package com.kelompokb.sistemmahasiswabackend.repository;

import com.kelompokb.sistemmahasiswabackend.model.entity.Dosen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DosenRepo extends JpaRepository<Dosen, Integer> {
}
