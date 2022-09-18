package com.kelompokb.sistemmahasiswabackend.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_mhs")
public class Mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mhs_generator")
    @SequenceGenerator(name = "mhs_generator", sequenceName = "seq_mhs", initialValue = 1, allocationSize = 1)
    @Column(name = "id_mhs")
    private Integer idMhs;

    private String name;

    @Column(name = "id_jurusan")
    private Integer idJurusan;

    @OneToOne
    @JoinColumn(name = "id_jurusan", insertable = false, updatable = false)
    private Jurusan jurusan;

    @Column(name = "id_user")
    private Integer idUser;

    @OneToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;


    public Integer getIdMhs() {
        return idMhs;
    }

    public void setIdMhs(Integer idMhs) {
        this.idMhs = idMhs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
