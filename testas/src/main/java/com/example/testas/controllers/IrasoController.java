package com.example.testas.controllers;


import com.example.testas.dto.IrasoDto;
import com.example.testas.entities.Irasas;
import com.example.testas.entities.Vartotojas;
import com.example.testas.service.IrasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/irasai/")
public class IrasoController {

    @Autowired
    private IrasoService irasoService;

    @PostMapping
    public ResponseEntity createIrasa(@RequestBody IrasoDto irasoDto){
        irasoService.createIrasa(irasoDto);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<IrasoDto>> showAllIrasai() {
        return new ResponseEntity<>(irasoService.showAllIrasai(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<IrasoDto> getIrasa(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(irasoService.readIrasa(id), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<IrasoDto> deleteIrasa(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(irasoService.deleteIrasa(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateIrasa(@RequestBody IrasoDto irasoDto, @PathVariable Long id){
        irasoService.updateIrasa(irasoDto, id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/users/{uEmail}")
    public ResponseEntity findPosts(@RequestBody @PathVariable String uEmail) {
        return new ResponseEntity<>(irasoService.findPosts(uEmail), HttpStatus.OK);
    }


}
