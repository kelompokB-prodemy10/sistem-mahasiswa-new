package com.kelompokb.sistemmahasiswabackend.controller;

import com.kelompokb.sistemmahasiswabackend.model.dto.DefaultResponse;
import com.kelompokb.sistemmahasiswabackend.model.dto.NilaiDto;
import com.kelompokb.sistemmahasiswabackend.model.dto.NilaiIdDto;
import com.kelompokb.sistemmahasiswabackend.model.entity.Nilai;
import com.kelompokb.sistemmahasiswabackend.repository.*;
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
    @Autowired
    private MatkulRepo matkulRepo;
    @Autowired
    private JurusanRepo jurusanRepo;

    @CrossOrigin
    @PostMapping("/savenilai") //save nilai dengan autogenerate OKE
    public DefaultResponse<NilaiIdDto> saveNilai(@RequestBody NilaiIdDto nilaiDto) {
        Nilai nilai = convertDtoToEntity(nilaiDto);
        DefaultResponse<NilaiIdDto> df = new DefaultResponse<>();
        Optional<Nilai> optionalMhs  = nilaiRepo.findByIdMhs(nilaiDto.getIdMhs());
        Optional<Nilai> optionalUjian = nilaiRepo.findByIdUjian(nilaiDto.getIdUjian());
        Optional<Nilai> optionalJurusan = nilaiRepo.findByIdJurusan(nilaiDto.getIdJurusan());
        Optional<Nilai> optionalMatkul = nilaiRepo.findByIdMatkul(nilaiDto.getIdMatkul());
        if (optionalMhs.isPresent() && optionalUjian.isPresent() && optionalJurusan.isPresent() && optionalMatkul.isPresent()) {
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
    @CrossOrigin
    @GetMapping("/listnilai") //list nilai OKE
    public List<NilaiDto> getListNilai() {
        List<NilaiDto> list = new ArrayList<>();
        for (Nilai nilai : nilaiRepo.findAll()) {
            list.add(convertEntityToDto(nilai));
        }
        return list;
    }

    @CrossOrigin
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
            dto.setIdMatkul(entity.getMatkul().getIdMatkul());
            dto.setIdJurusan(entity.getJurusan().getIdJurusan());
            dto.setJudulUjian(entity.getUjian().getJudulUjian());
            dto.setStatUjian(entity.getUjian().getStatUjian());
//            dto.setMatkul(entity.getMatkul());
//            dto.setJurusan(entity.getJurusan());
            dto.setNilai(entity.getNilai());
        }
        return dto;
    }
    @CrossOrigin
    @PutMapping("/update/{idNilai}")
    public DefaultResponse update(@PathVariable Integer idNilai, @RequestBody NilaiDto nilaiDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Nilai> optionalNilai = nilaiRepo.findById(idNilai);
        Nilai nilai = optionalNilai.get();
        if (optionalNilai.isPresent()) {
            nilai.setIdNilai(nilaiDto.getIdNilai());
            nilai.setIdMhs(nilaiDto.getIdMhs());
            nilai.setIdUjian(nilaiDto.getIdUjian());
            nilai.setIdJurusan(nilaiDto.getIdJurusan());
            nilai.setIdMatkul(nilaiDto.getIdMatkul());
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

    @CrossOrigin
    @DeleteMapping("/delete/{idNilai}")
    public DefaultResponse deletById(@PathVariable Integer idNilai) {
        DefaultResponse df = new DefaultResponse();
        Optional<Nilai> optionalNilai = nilaiRepo.findById(idNilai);
        if (optionalNilai.isPresent()){
            nilaiRepo.delete(optionalNilai.get());
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
        dto.setIdJurusan(entity.getJurusan().getIdJurusan());
        dto.setIdMatkul(entity.getMatkul().getIdMatkul());
        dto.setNamaJurusan(entity.getJurusan().getNamaJurusan());
        dto.setNamaMatkul(entity.getMatkul().getNamaMatkul());
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
        nilai.setIdJurusan(nilaiDto.getIdJurusan());
        nilai.setIdMatkul(nilaiDto.getIdMatkul());
        nilai.setNilai(nilaiDto.getNilai());

        return nilai;
    }

    public Nilai convertDtoToEnNilai(NilaiDto dto){
        Nilai n = new Nilai();
        n.setIdNilai(dto.getIdNilai());
        n.setIdMhs(dto.getIdMhs());
        n.setIdUjian(dto.getIdUjian());
        n.setIdJurusan(dto.getIdJurusan());
        n.setIdMatkul(dto.getIdMatkul());
        n.setNilai(dto.getNilai());
        return n;
    }
}