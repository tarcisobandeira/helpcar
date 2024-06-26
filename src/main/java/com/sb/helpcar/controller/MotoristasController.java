package com.sb.helpcar.controller;

import com.sb.helpcar.entities.Motorista;
import com.sb.helpcar.entities.Usuario;
import com.sb.helpcar.repository.MotoristasRepository;
import com.sb.helpcar.repository.UsuariosRepository;
import com.sb.helpcar.request.MotoristasRequestDTO;
import com.sb.helpcar.response.MotoristasResponseDTO;
import com.sb.helpcar.response.UsuariosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MotoristasController {

    @Autowired
    private MotoristasRepository repository;
    @Autowired
    private UsuariosRepository userRepository;

    @PostMapping(value = "motorista/save")
    public void InsertMotorista(@RequestBody MotoristasRequestDTO data){
        UsuariosResponseDTO ur = userRepository.findByid(data.id_usuario());
        Usuario u = new Usuario(ur);
        Motorista m = new Motorista(data, u);
        repository.save(m);
        return;
    }

    @GetMapping(value = "motorista/all")
    public List<MotoristasResponseDTO> getAll(){
        List<MotoristasResponseDTO> motoList = repository.findAll().stream().map(MotoristasResponseDTO::new).toList();
        return motoList;
    }

}
