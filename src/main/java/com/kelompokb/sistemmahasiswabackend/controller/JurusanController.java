package com.kelompokb.sistemmahasiswabackend.controller;

import com.kelompokb.sistemmahasiswabackend.model.dto.DefaultResponse;
import com.kelompokb.sistemmahasiswabackend.model.dto.JurusanDto;
import com.kelompokb.sistemmahasiswabackend.model.entity.Jurusan;
import com.kelompokb.sistemmahasiswabackend.repository.JurusanRepo;
import com.kelompokb.sistemmahasiswabackend.service.ServiceJurusanImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jurusan")
public class JurusanController {

    @Autowired
    private JurusanRepo jurusanRepo;

    @Autowired
    private ServiceJurusanImp serviceJurusanImp;

    @PostMapping("/savejurusan")
    public DefaultResponse<JurusanDto> saveJurusan(@RequestBody JurusanDto jurusanDto) {
        Jurusan jurusan = convertDtoToEntity(jurusanDto);
        DefaultResponse<JurusanDto> response = new DefaultResponse<>();
        Optional<Jurusan> optional = jurusanRepo.findById(jurusanDto.getIdJurusan());
        if (optional.isPresent()) {
            response.setMessage("Error, Data Telah Tersedia");
        } else {
            jurusanRepo.save(jurusan);
            response.setMessage("Data Jurusan Berhasil Tersimpan");
            response.setData(jurusanDto);
        }
        return response;
    }

    @GetMapping("/listjurusan")
    public List<JurusanDto> getListJurusan() {
        List<JurusanDto> list = new ArrayList<>();
        for (Jurusan jurusan : jurusanRepo.findAll()) {
            list.add(convertEntityToDto(jurusan));
        }
        return list;
    }

    @GetMapping("/byid/{idJurusan}")
    public DefaultResponse getById(@PathVariable Integer idJurusan) {
        DefaultResponse df = new DefaultResponse();
        Optional<Jurusan> jurusanOps = jurusanRepo.findById(idJurusan);
        if (jurusanOps.isPresent()) {
            df.setStatus(Boolean.TRUE);
            df.setMessage("Jurusan Yang Anda Pilih Telah Tersimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Jurusan Yang Anda Pilih Tidak Tersedia");
        }
        return df;
    }

    @PutMapping("/up/{idJurusan}")
    public ResponseEntity<Jurusan> updateJurusan(@PathVariable("idJurusan") Integer idJurusan, @RequestBody Jurusan jurusan) {
        serviceJurusanImp.updateJurusan(idJurusan, jurusan);
        return new ResponseEntity<>(serviceJurusanImp.getJurusanById(idJurusan), HttpStatus.OK);
    }

    @DeleteMapping("/{idJurusan}")
    public ResponseEntity<Jurusan> delJurusan(@PathVariable("idJurusan") Integer idJurusan) {
        serviceJurusanImp.delJurusan(idJurusan);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public JurusanDto convertEntityToDto(Jurusan entity) {
        JurusanDto dto = new JurusanDto();
        dto.setIdJurusan(entity.getIdJurusan());
        dto.setNamaJurusan(entity.getNamaJurusan());
        return dto;
    }

    public Jurusan convertDtoToEntity(JurusanDto dto) {
        Jurusan entity = new Jurusan();
        entity.setIdJurusan(dto.getIdJurusan());
        entity.setNamaJurusan(dto.getNamaJurusan());
        return entity;
    }
}
