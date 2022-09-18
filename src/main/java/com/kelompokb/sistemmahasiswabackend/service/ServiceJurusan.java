package com.kelompokb.sistemmahasiswabackend.service;

import com.kelompokb.sistemmahasiswabackend.model.entity.Jurusan;

public interface ServiceJurusan {

    void updateJurusan(Integer idJurusan, Jurusan jurusan);

    Jurusan getJurusanById(Integer idJurusan);

    void delJurusan(Integer idJurusan);
}
