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
    @Column(name = "id_matkul")
    private Integer idMatkul;
    @Column(name = "id_jurusan")
    private Integer idJurusan;

    @OneToOne
    @JoinColumn(name = "id_ujian", insertable = false, updatable = false)
    private Ujian ujian;

    @OneToOne
    @JoinColumn(name = "id_mhs", insertable = false, updatable = false)
    private Mahasiswa mahasiswa;

    @OneToOne
    @JoinColumn(name = "id_matkul", insertable = false, updatable = false)
    private Matkul matkul;

    @OneToOne
    @JoinColumn(name = "id_jurusan", insertable = false, updatable = false)
    private Jurusan jurusan;

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

    public Matkul getMatkul() {
        return matkul;
    }

    public void setMatkul(Matkul matkul) {
        this.matkul = matkul;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }
}
