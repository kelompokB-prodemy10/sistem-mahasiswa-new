package com.kelompokb.sistemmahasiswabackend.service;

import com.kelompokb.sistemmahasiswabackend.model.entity.Matkul;
import com.kelompokb.sistemmahasiswabackend.repository.MatkulRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMatkulImp implements ServiceMatkul {

    @Autowired
    MatkulRepo matkulRepo;

    @Override
    public void updateMatkul(Integer idMatkul, Matkul matkul) {
        Matkul matkul1 = matkulRepo.findById(idMatkul).get();
        System.out.println(matkul1.toString());
        matkul1.setNamaMatkul(matkul.getNamaMatkul());
        matkulRepo.save(matkul1);
    }

    @Override
    public Matkul getMatkulById(Integer idMatkul) {
        return matkulRepo.findById(idMatkul).get();
    }

    @Override
    public void delMatkul(Integer idMatkul) {
        matkulRepo.deleteById(idMatkul);
    }
}
