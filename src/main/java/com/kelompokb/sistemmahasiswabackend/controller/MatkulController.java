package com.kelompokb.sistemmahasiswabackend.controller;

import com.kelompokb.sistemmahasiswabackend.model.dto.DefaultResponse;
import com.kelompokb.sistemmahasiswabackend.model.dto.JurusanDto;
import com.kelompokb.sistemmahasiswabackend.model.dto.MatkulDto;
import com.kelompokb.sistemmahasiswabackend.model.entity.Jurusan;
import com.kelompokb.sistemmahasiswabackend.model.entity.Matkul;
import com.kelompokb.sistemmahasiswabackend.repository.MatkulRepo;
import com.kelompokb.sistemmahasiswabackend.service.ServiceMatkulImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/matkul")
public class MatkulController {

    @Autowired
    private MatkulRepo matkulRepo;

    @Autowired
    private ServiceMatkulImp serviceMatkulImp;

    @PostMapping("/savematkul")
    public DefaultResponse<MatkulDto> saveMatkul(@RequestBody MatkulDto matkulDto) {
        Matkul matkul = convertDtoToEntity(matkulDto);
        DefaultResponse<MatkulDto> response = new DefaultResponse<>();
        matkulRepo.save(matkul);
        response.setMessage("Data Matkul Berhasil Tersimpan");
        response.setData(matkulDto);
        return response;
    }

    @GetMapping("/listmatkul")
    public List<MatkulDto> getListMatkul() {
        List<MatkulDto> list = new ArrayList<>();
        for (Matkul matkul : matkulRepo.findAll()) {
            list.add(convertEntityToDto(matkul));
        }
        return list;
    }

    @GetMapping("/byid/{idMatkul}")
    public MatkulDto getMatkulById(@PathVariable Integer idMatkul) {
        Optional<Matkul> optionalMatkul = matkulRepo.findById(idMatkul);
        MatkulDto dto = new MatkulDto();
        if (optionalMatkul.isPresent()) {
            Matkul entity = optionalMatkul.get();
            dto.setIdMatkul(entity.getIdMatkul());
            dto.setNamaMatkul(entity.getNamaMatkul());
        }
        return dto;
    }

    @PutMapping("/up/{idMatkul}")
    public ResponseEntity<Matkul> updateMatkul(@PathVariable("idMatkul") Integer idMatkul, @RequestBody Matkul matkul) {
        serviceMatkulImp.updateMatkul(idMatkul, matkul);
        return new ResponseEntity<>(serviceMatkulImp.getMatkulById(idMatkul), HttpStatus.OK);
    }

    @DeleteMapping("/{idMatkul}")
    public ResponseEntity<Matkul> delMatkul(@PathVariable("idMatkul") Integer idMatkul) {
        serviceMatkulImp.delMatkul(idMatkul);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public MatkulDto convertEntityToDto(Matkul entity) {
        MatkulDto dto = new MatkulDto();
        dto.setIdMatkul(entity.getIdMatkul());
        dto.setNamaMatkul(entity.getNamaMatkul());
        return dto;
    }

    public Matkul convertDtoToEntity(MatkulDto dto) {
        Matkul entity = new Matkul();
        entity.setIdMatkul(dto.getIdMatkul());
        entity.setNamaMatkul(dto.getNamaMatkul());
        return entity;
    }
}
