package com.demo.controller;

import java.util.List;
import java.util.Optional;

import com.demo.dao.IDevices;
import com.demo.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.service.DevicesService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/v1/device")

public class RestControllerDevices {


    @Autowired
    IDevices iDevices;

    @GetMapping("/all")
    public List<Device> findAll() {
        return iDevices.findAll();
    }

    @GetMapping("/one/{id}")
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        if (id== null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Device> device = iDevices.findById(id);
        if (!device.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune Device  a ete trouve avec l'ID = " + id);
        }
        return ResponseEntity.ok(device.get());
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Device device) {
        if (device == null) {
            return ResponseEntity.badRequest().body("Impossible d'ajouter  une Device  NULL");
        }
        Optional<Device> oldDevice = iDevices.findByInstanceAndNameId(device.getInstance(),device.getNameId());
        if (oldDevice.isPresent()) {
            return ResponseEntity.badRequest().body(" Device existe deja");

        }

        return  ResponseEntity.ok(iDevices.save(device));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Impossible de supprimer Device  avec un ID null");
        }
        Optional<Device> device = iDevices.findById(id);
        if (!device.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune Device trouvee avec l ID = " + id);
        }
        iDevices.delete(device.get());
        return ResponseEntity.ok().body(device.get());
    }

}
