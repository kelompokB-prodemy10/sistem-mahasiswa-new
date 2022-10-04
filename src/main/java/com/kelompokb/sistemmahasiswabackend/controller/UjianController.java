package com.kelompokb.sistemmahasiswabackend.controller;

import com.kelompokb.sistemmahasiswabackend.model.dto.DefaultResponse;
import com.kelompokb.sistemmahasiswabackend.model.dto.UjianDto;
import com.kelompokb.sistemmahasiswabackend.model.entity.Ujian;
import com.kelompokb.sistemmahasiswabackend.repository.MatkulRepo;
import com.kelompokb.sistemmahasiswabackend.repository.UjianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/ujian")
public class UjianController {

    @Autowired
    private UjianRepo ujianRepo;

    @Autowired
    private MatkulRepo matkulRepo;


    @PostMapping("/saveujian")
    public DefaultResponse<UjianDto> saveUjian (@RequestBody UjianDto ujianDto) {
        Ujian ujian = convertDtoToEntity(ujianDto);
        DefaultResponse<UjianDto> df = new DefaultResponse<>();
        Optional<Ujian> optionalMatkul = ujianRepo.findByIdMatkul(ujianDto.getIdMatkul());
        if (optionalMatkul.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Gagal, Data Ujian Sudah Terdaftar");
        } else {
            ujianRepo.save(ujian);
            df.setStatus(Boolean.TRUE);
            df.setData(ujianDto);
            df.setMessage("Data Ujian Tersimpan");
        }
        return df;
    }
    @GetMapping("/listujian") //list nilai OKE
    public List<Ujian> getListUjian() {
        List<Ujian> list = new ArrayList<>();
        for (Ujian ujian : ujianRepo.findAll()) {
            list.add(convertEntityToDto(ujian));
        }
        return list;
    }
    @GetMapping("/getujian/{idUjian}")
    public UjianDto getById(@PathVariable Integer idUjian){
        Optional<Ujian> optionalUjian = ujianRepo.findById(idUjian);
        UjianDto dto = new UjianDto();
        if (optionalUjian.isPresent()){
            Ujian entity = optionalUjian.get();
            dto.setIdUjian(entity.getIdUjian());
            dto.setJudulUjian(entity.getJudulUjian());
            dto.setIdMatkul(entity.getIdMatkul());
            dto.setStatUjian(entity.getStatUjian());
            dto.setMatkul(entity.getMatkul());
        }
        return dto;
    }
    @PutMapping("/update/{idUjian}")
    public DefaultResponse update(@PathVariable Integer idUjian, @RequestBody UjianDto ujianDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Ujian> ujianOptional = ujianRepo.findById(idUjian);
        Ujian ujian = ujianOptional.get();
        if (ujianOptional.isPresent()) {
            ujian.setIdUjian(ujianDto.getIdUjian());
            ujian.setJudulUjian(ujianDto.getJudulUjian());
            ujian.setIdMatkul(ujianDto.getIdMatkul());
            ujian.setStatUjian(ujianDto.getStatUjian());
            ujianRepo.save(ujian);
            df.setStatus(Boolean.TRUE);
            df.setData(ujianDto);
            df.setMessage("Perubahan Berhasil Tersimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("ID Tidak Ditemukan");
        }
        return df;
    }

    @DeleteMapping("/delete/{idUjian}")
    public DefaultResponse deletById(@PathVariable Integer idUjian) {
        DefaultResponse df = new DefaultResponse();
        Optional<Ujian> optionalUjian = ujianRepo.findById(idUjian);
        if (optionalUjian.isPresent()) {
            ujianRepo.delete(optionalUjian.get());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;
    }

    public Ujian convertDtoToEntity (UjianDto dto){
        Ujian ujian = new Ujian();
        ujian.setIdUjian(dto.getIdUjian());
        ujian.setJudulUjian(dto.getJudulUjian());
        ujian.setIdMatkul(dto.getIdMatkul());
        ujian.setStatUjian(dto.getStatUjian());

        return ujian;
    }
    public Ujian convertEntityToDto(Ujian entity){
        Ujian dto = new Ujian();
        dto.setIdUjian(entity.getIdUjian());
        dto.setJudulUjian(entity.getJudulUjian());
        dto.setIdMatkul(entity.getIdMatkul());
        dto.setStatUjian(entity.getStatUjian());
        dto.setMatkul(entity.getMatkul());

        return dto;
    }
}
