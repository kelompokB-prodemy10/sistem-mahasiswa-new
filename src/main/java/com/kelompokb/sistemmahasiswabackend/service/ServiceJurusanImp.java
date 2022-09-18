package com.kelompokb.sistemmahasiswabackend.service;

import com.kelompokb.sistemmahasiswabackend.model.entity.Jurusan;
import com.kelompokb.sistemmahasiswabackend.repository.JurusanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceJurusanImp implements ServiceJurusan {

    @Autowired
    JurusanRepo jurusanRepo;

    @Override
    public void updateJurusan(Integer idJurusan, Jurusan jurusan) {
        Jurusan jurusan1 = jurusanRepo.findById(idJurusan).get();
        System.out.println(jurusan1.toString());
        jurusan1.setNamaJurusan(jurusan.getNamaJurusan());
        jurusanRepo.save(jurusan1);
    }

    @Override
    public Jurusan getJurusanById(Integer idJurusan) {
        return jurusanRepo.findById(idJurusan).get();
    }

    @Override
    public void delJurusan(Integer idJurusan) {
        jurusanRepo.deleteById(idJurusan);
    }
}
