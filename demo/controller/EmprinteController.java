package com.demo.controller;

import com.demo.dao.EmprienteRepository;
import com.demo.dao.UserRepository;
import com.demo.impl.Emprients;
import com.demo.model.Device;
import com.demo.model.Empriente;
import com.demo.model.User;
import com.demo.model.Userwithhashcode;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static javafx.scene.input.KeyCode.F;

@RestController
@RequestMapping(value="/api/v1/empreinte")
public class EmprinteController {

    @Autowired
    private EmprienteRepository emprienteRepository;

    @Autowired
    Emprients emprients;

@Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public List<Empriente> findAll() {
        return emprienteRepository.findAll();
    }


    @PostMapping("/save")
    public ResponseEntity save() {
        // Enumerate device
        NBioBSPJNI.DEVICE_ENUM_INFO deviceEnumInfo;
        NBioBSPJNI bsp = new NBioBSPJNI();
        deviceEnumInfo = bsp.new DEVICE_ENUM_INFO();
        bsp.EnumerateDevice(deviceEnumInfo);
// Device Open
        //mybe we dadd to close the device
        bsp.OpenDevice(deviceEnumInfo.DeviceInfo[0].NameID,
                deviceEnumInfo.DeviceInfo[0].Instance);
        List<String> fingers=emprienteRepository.getFingrs();
        final  String FnVliderBiometrie =emprients.VliderBiometrie(fingers);
        if ((FnVliderBiometrie==null)){
            return ResponseEntity.badRequest().body("existe deja");

        }
        Empriente empriente=new Empriente();
        empriente.setFingerPrint(FnVliderBiometrie);

        User user=new User();
        user.setCin(12345678910L);

/*
        Optional<User> user1 = userRepository.findByCin(cin);
        if (!user1.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune user  a ete trouve avec l cin = " + cin);
        }*/
        user.setLastName("hamdi");
        user.setLastName("jhyu");
        userRepository.save(user);
        empriente.setUser(user);

        //// User user1=   userRepository.findByCin(user.getCin());


        return ResponseEntity.ok(emprienteRepository.save(empriente));

    }

    @PostMapping("/checkuser")
    public ResponseEntity checkuser() {
        // Enumerate device
        NBioBSPJNI.DEVICE_ENUM_INFO deviceEnumInfo;
        NBioBSPJNI bsp = new NBioBSPJNI();
        deviceEnumInfo = bsp.new DEVICE_ENUM_INFO();
        bsp.EnumerateDevice(deviceEnumInfo);
// Device Open
        //mybe we dadd to close the device
        bsp.OpenDevice(deviceEnumInfo.DeviceInfo[0].NameID,
                deviceEnumInfo.DeviceInfo[0].Instance);
        List<String> fingers=emprienteRepository.getFingrs();
        final  String FnVliderBiometrie =emprients.VliderBiometrie(fingers);
        if ((FnVliderBiometrie==null)){
            return ResponseEntity.ok("deja exist");

        }else{
            return ResponseEntity.ok(FnVliderBiometrie);
        }


   

    }

    @PostMapping("/insertuserandempriente")
    public ResponseEntity insertuserf(@RequestBody   Userwithhashcode OneUserwithhashcode) {
        OneUserwithhashcode.User;
        OneUserwithhashcode.hashCode;
 
        Optional<User> oldUser=userRepository.findFirstByCin(OneUserwithhashcode.User.getCin());
       // String hashcode="ss";
    if(oldUser.isPresent()) {
        return ResponseEntity.ok("cin deja exist"+oldUser.get());

    }else{
       
       /*  userRepository.save(OneUserwithhashcode.User); */
        empriente.setUser(OneUserwithhashcode.User);
        empriente.setFingerPrint(OneUserwithhashcode.hashCode);

        return ResponseEntity.ok(emprienteRepository.save(empriente));
    }


      

    }





}




















/*
    @DeleteMapping("/delete/{fingerprint}")
    public ResponseEntity delete(@PathVariable("fingerprint") String fingerprint) {
        if (fingerprint == null) {
            return ResponseEntity.badRequest().body("Impossible de supprimer une empriente  avec un ID null");
        }
        Optional<Empriente> empriente = emprienteRepository.findById(fingerprint);
        if (!empriente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune empriente  trouvee avec l ID = " + fingerprint);
        }
        emprienteRepository.delete(empriente.get());
        return ResponseEntity.ok().body(empriente.get());
    }
*/


