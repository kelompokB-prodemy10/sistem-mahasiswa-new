package com.kelompokb.sistemmahasiswabackend.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "tab_nilai")
public class Nilai {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_nilai")
    private Integer idNilai;
    @Column(name = "id_mhs")
    private Integer idMhs;
    @Column(name = "id_ujian")
    private Integer idUjian;
    @Column(name = "nilai")
    private Integer Nilai;

    @OneToOne
    @JoinColumn(name = "id_ujian", insertable = false, updatable = false)
    private Ujian ujian;

    @OneToOne
    @JoinColumn(name = "id_mhs", insertable = false, updatable = false)
    private Mahasiswa mahasiswa;


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

    public Ujian getUjian() {
        return ujian;
    }

    public void setUjian(Ujian ujian) {
        this.ujian = ujian;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
}
