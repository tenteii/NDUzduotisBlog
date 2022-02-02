package com.example.testas.service;

import com.example.testas.dto.IrasoDto;
import com.example.testas.entities.Irasas;
import com.example.testas.exception.PostNotFoundException;
import com.example.testas.repositorijos.IrasuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IrasoService {

    @Autowired
    private AuthService authService;
    @Autowired
    private IrasuRepo irasuRepo;

    public List<IrasoDto> showAllIrasai() {
        List<Irasas> irasai = irasuRepo.findAll();
        return irasai.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    public void createIrasa(IrasoDto irasoDto) {
        Irasas irasas = mapFromDtoToPost(irasoDto);
        irasuRepo.save(irasas);
    }

    private IrasoDto mapFromPostToDto(Irasas irasas) {
        IrasoDto irasoDto = new IrasoDto();
        irasoDto.setId(irasas.getId());
        irasoDto.setTitle(irasas.getTitle());
        irasoDto.setText(irasas.getText());
        irasoDto.setUsername(irasas.getuEmail());
        return irasoDto;
    }

    public Irasas mapFromDtoToPost(IrasoDto irasoDto){
        Irasas irasas = new Irasas();
        irasas.setTitle(irasoDto.getTitle());
        irasas.setText(irasoDto.getText());
        User username = authService.getCurrentUser().orElseThrow(()-> new IllegalArgumentException("Joks vartotojas neprijungtas"));
        irasas.setuEmail(username.getUsername());
        irasas.setCreatedOn(Instant.now());
        irasas.setUpdatedOn(Instant.now());
        return irasas;
    }

    public IrasoDto readIrasa(Long id) {
        Irasas irasas = irasuRepo.findById(id).orElseThrow(()->new PostNotFoundException("Nerastas ID " + id));
        return mapFromPostToDto(irasas);
    }

    public IrasoDto deleteIrasa(Long id) {
        irasuRepo.deleteById(id);
        return null;
    }


    public void updateIrasa(IrasoDto irasoDto,Long id) {
        Irasas irasas = irasuRepo.findById(id).orElseThrow(()->new PostNotFoundException("Nerastas ID " + id));
        irasas.setTitle(irasoDto.getTitle());
        irasas.setText(irasoDto.getText());
        irasas.setUpdatedOn(Instant.now());
        irasuRepo.saveAndFlush(irasas);
    }

    public IrasoDto findPosts(String uEmail) {
        Irasas irasas = irasuRepo.findByuEmail(uEmail).orElseThrow(()->new PostNotFoundException("Nerastas email " + uEmail));
        return mapFromPostToDto(irasas);
    }
}
