package com.kelompokb.sistemmahasiswabackend.model.dto;

public class MatkulDto {
    private String namaMatkul;
    private Integer IdMatkul;

    public Integer getIdMatkul() {
        return IdMatkul;
    }

    public void setIdMatkul(Integer idMatkul) {
        IdMatkul = idMatkul;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }
}
