package com.kelompokb.sistemmahasiswabackend.model.dto;

import com.kelompokb.sistemmahasiswabackend.model.entity.Jurusan;
import com.kelompokb.sistemmahasiswabackend.model.entity.Matkul;

public class NilaiIdDto {
    private Integer idNilai;
    private Integer idMhs;
    private Integer idUjian;
    private Integer Nilai;
    private Integer idMatkul;
    private Integer idJurusan;
    private Jurusan jurusan;
    private Matkul matkul;

    public Integer getIdNilai() {
        return idNilai;
    }

    public void setIdNilai(Integer idNilai) {
        this.idNilai = idNilai;
    }

    public Integer getIdMhs() {
        return idMhs;
    }

    public void setIdMhs(Integer idMhs) {
        this.idMhs = idMhs;
    }

    public Integer getIdUjian() {
        return idUjian;
    }

    public void setIdUjian(Integer idUjian) {
        this.idUjian = idUjian;
    }

    public Integer getNilai() {
        return Nilai;
    }

    public void setNilai(Integer nilai) {
        Nilai = nilai;
    }

    public Integer getIdMatkul() {
        return idMatkul;
    }

    public void setIdMatkul(Integer idMatkul) {
        this.idMatkul = idMatkul;
    }

    public Integer getIdJurusan() {
        return idJurusan;
    }

    public void setIdJurusan(Integer idJurusan) {
        this.idJurusan = idJurusan;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }

    public Matkul getMatkul() {
        return matkul;
    }

    public void setMatkul(Matkul matkul) {
        this.matkul = matkul;
    }
}