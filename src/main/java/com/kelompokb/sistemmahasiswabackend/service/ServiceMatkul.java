package com.kelompokb.sistemmahasiswabackend.service;

import com.kelompokb.sistemmahasiswabackend.model.entity.Matkul;

public interface ServiceMatkul {

    void updateMatkul (Integer idMatkul, Matkul matkul);

    Matkul getMatkulById(Integer idMatkul);

    void delMatkul(Integer idMatkul);
}
