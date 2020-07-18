package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.PersonaDto;
import com.tutorial.crud.entity.Persona;
import com.tutorial.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {
    @Autowired
    PersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return  new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if (!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("not exist"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @GetMapping("/detail/{nombre}")
    public ResponseEntity<Persona> getByNombre(@PathVariable("nombre") String nombre){
        if (!personaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("not exist"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getByNombre(nombre).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody PersonaDto personaDto){
      if(StringUtils.isEmpty(personaDto.getNombre()))
          return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
      if(personaDto.getBirth()==null || personaDto.getBirth() == "" )
          return new ResponseEntity(new Mensaje("la fecha debe ser mayor que 0 ó null"), HttpStatus.BAD_REQUEST);
      if(personaService.existsByNombre(personaDto.getNombre()))
          return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
      Persona persona = new Persona(personaDto.getNombre(), personaDto.getBirth());
      personaService.save(persona);
      return new ResponseEntity(new Mensaje("persona creada"), HttpStatus.OK);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id")int id){
    if(!personaService.existsById(id))
        return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
    personaService.delete(id);
        return new ResponseEntity(new Mensaje("persona eliminada"), HttpStatus.OK);
    }



}
