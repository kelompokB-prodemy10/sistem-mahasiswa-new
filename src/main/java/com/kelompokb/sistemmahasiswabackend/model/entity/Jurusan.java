package com.kelompokb.sistemmahasiswabackend.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "tab_jurusan")
public class Jurusan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_jurusan")
    private Integer idJurusan;
    private String namaJurusan;

    public Integer getIdJurusan() {
        return idJurusan;
    }

    public void setIdJurusan(Integer idJurusan) {
        this.idJurusan = idJurusan;
    }

    public String getNamaJurusan() {
        return namaJurusan;
    }

    public void setNamaJurusan(String namaJurusan) {
        this.namaJurusan = namaJurusan;
    }
}
