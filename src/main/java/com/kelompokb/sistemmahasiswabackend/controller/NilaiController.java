package com.kelompokb.sistemmahasiswabackend.controller;


import com.kelompokb.sistemmahasiswabackend.model.dto.DefaultResponse;
import com.kelompokb.sistemmahasiswabackend.model.dto.NilaiDto;
import com.kelompokb.sistemmahasiswabackend.model.dto.NilaiIdDto;
import com.kelompokb.sistemmahasiswabackend.model.entity.Nilai;
import com.kelompokb.sistemmahasiswabackend.repository.MahasiswaRepo;
import com.kelompokb.sistemmahasiswabackend.repository.NilaiRepo;
import com.kelompokb.sistemmahasiswabackend.repository.UjianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nilai")
public class NilaiController {

    @Autowired
    private NilaiRepo nilaiRepo;
    @Autowired
    private MahasiswaRepo mahasiswaRepo;
    @Autowired
    private UjianRepo ujianRepo;

    @PostMapping("/savenilai") //save nilai dengan autogenerate OKE
    public DefaultResponse<NilaiIdDto> saveNilai(@RequestBody NilaiIdDto nilaiDto) {
        Nilai nilai = convertDtoToEntity(nilaiDto);
        DefaultResponse<NilaiIdDto> df = new DefaultResponse<>();
        Optional<Nilai> optionalMhs  = nilaiRepo.findByIdMhs(nilaiDto.getIdMhs());
        Optional<Nilai> optionalUjian = nilaiRepo.findByIdUjian(nilaiDto.getIdUjian());
        if (optionalMhs.isPresent() && optionalUjian.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Gagal, Data Nilai Sudah Terdaftar");
        } else {
            nilaiRepo.save(nilai);
            df.setStatus(Boolean.TRUE);
            df.setData(nilaiDto);
            df.setMessage("Data Nilai Tersimpan");
        }
        return df;
    }
//    @PostMapping("/savenilai") //save nilai dengan autogenerate OKE
//    public DefaultResponse<NilaiIdDto> saveNilai(@RequestBody NilaiIdDto nilaiDto) {
//        Nilai nilai = convertDtoToEntity(nilaiDto);
//        DefaultResponse<NilaiIdDto> df = new DefaultResponse<>();
//       Optional<Nilai> optionalNilai = nilaiRepo.findById(nilaiDto.getIdNilai());
//     if (optionalNilai.isPresent()) {
//            df.setStatus(Boolean.FALSE);
//            df.setMessage("Gagal");
//        } else {
//            nilaiRepo.save(nilai);
//           df.setStatus(Boolean.TRUE);
//           df.setMessage("data tersimpan");
//            df.setData(nilaiDto);
//        }
//        return df;
//}

    @GetMapping("/listnilai") //list nilai OKE
    public List<NilaiDto> getListNilai() {
        List<NilaiDto> list = new ArrayList<>();
        for (Nilai nilai : nilaiRepo.findAll()) {
            list.add(convertEntityToDto(nilai));
        }
        return list;
    }

    @GetMapping("/getnilai/{idNilai}")
    public NilaiDto getById(@PathVariable Integer idNilai) {
        Optional<Nilai> optionalNilai = nilaiRepo.findById(idNilai);
        NilaiDto dto = new NilaiDto();
        if (optionalNilai.isPresent()) {
            Nilai entity = optionalNilai.get();
            dto.setIdNilai(entity.getIdNilai());
            dto.setIdMhs(entity.getMahasiswa().getIdMhs());
            dto.setName(entity.getMahasiswa().getName());
            dto.setIdUjian(entity.getUjian().getIdUjian());
            dto.setJudulUjian(entity.getUjian().getJudulUjian());
            dto.setStatUjian(entity.getUjian().getStatUjian());
            dto.setNilai(entity.getNilai());
        }
        return dto;
    }

    @PutMapping("/update")
    public DefaultResponse update(@RequestBody NilaiDto nilaiDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Nilai> optionalMhs  = nilaiRepo.findByIdMhs(nilaiDto.getIdMhs());
        Optional<Nilai> optionalUjian = nilaiRepo.findByIdUjian(nilaiDto.getIdUjian());
        if (optionalMhs.isPresent() && optionalUjian.isPresent()) {
            Nilai nilai = optionalMhs.get();
            nilai.setIdNilai(nilaiDto.getIdNilai());
            nilai.setIdMhs(nilaiDto.getIdMhs());
            nilai.setIdUjian(nilaiDto.getIdUjian());
            nilai.setNilai(nilaiDto.getNilai());
            nilaiRepo.save(nilai);
            df.setStatus(Boolean.TRUE);
            df.setData(nilaiDto);
            df.setMessage("Perubahan Berhasil Tersimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("ID Tidak Ditemukan");
        }
        return df;
    }

    @DeleteMapping("/delete")
    public DefaultResponse deleteById(@RequestBody NilaiDto nilaiDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Nilai> optionalMhs  = nilaiRepo.findByIdMhs(nilaiDto.getIdMhs());
        Optional<Nilai> optionalUjian = nilaiRepo.findByIdUjian(nilaiDto.getIdUjian());
        Nilai nilai = convertDtoToEnNilai(nilaiDto);
        if (optionalMhs.isPresent() && optionalUjian.isPresent()){
            nilaiRepo.delete(nilai);
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;
    }

    public NilaiDto convertEntityToDto(Nilai entity) {
        NilaiDto dto = new NilaiDto();
        dto.setIdNilai(entity.getIdNilai());
        dto.setIdMhs(entity.getMahasiswa().getIdMhs());
        dto.setName(entity.getMahasiswa().getName());
        dto.setIdUjian(entity.getUjian().getIdUjian());
        dto.setJudulUjian(entity.getUjian().getJudulUjian());
        dto.setStatUjian(entity.getUjian().getStatUjian());
        dto.setNilai(entity.getNilai());

        return dto;
    }

    public Nilai convertDtoToEntity(NilaiIdDto nilaiDto) {
        Nilai nilai = new Nilai();
        nilai.setIdNilai(nilaiDto.getIdNilai());
        nilai.setIdMhs(nilaiDto.getIdMhs());
        nilai.setIdUjian(nilaiDto.getIdUjian());
        nilai.setNilai(nilaiDto.getNilai());

        return nilai;
    }

    public Nilai convertDtoToEnNilai(NilaiDto dto){
        Nilai n = new Nilai();
        n.setIdNilai(dto.getIdNilai());
        n.setIdMhs(dto.getIdMhs());
        n.setIdUjian(dto.getIdUjian());
        n.setNilai(dto.getNilai());
        return n;
    }
}
