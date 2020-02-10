package com.demo.impl;


import com.demo.dao.EmprienteRepository;
import com.demo.model.Empriente;
import com.nitgen.SDK.BSP.NBioBSPJNI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class Emprients {
private String finger;
    @Autowired
    EmprienteRepository emprienteRepository;

    public Boolean VliderBiometrie(List<String> fingers) {


        //System.out.println(null+"Valider Digital");

        NBioBSPJNI bsp = new NBioBSPJNI();
        bsp.OpenDevice();

        NBioBSPJNI.INPUT_FIR inputFIR = bsp.new INPUT_FIR();
        NBioBSPJNI.INPUT_FIR inputFIR2 = bsp.new INPUT_FIR();
        Boolean bResult = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD payload = bsp.new FIR_PAYLOAD();

        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = bsp.new FIR_TEXTENCODE();
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR2 = bsp.new FIR_TEXTENCODE();



        textSavedFIR2.TextFIR = CaptureDigital();


        inputFIR2.SetTextFIR(textSavedFIR2);



        for (String finger : fingers) {
            textSavedFIR.TextFIR = finger;
            inputFIR.SetTextFIR(textSavedFIR);
                bsp.VerifyMatch(inputFIR, inputFIR2, bResult, payload);
            if (bsp.IsErrorOccured() == false) {
                if (bResult) {
                 this.SetFinger(finger);
                    return true ;
                }

            }

        }
        this.SetFinger(textSavedFIR2.TextFIR);
return false;

    }
    public String CaptureDigital() {

        //System.out.println("Capture Digital!!! ");


        /*    créer un objet de type NBioBSPJNI */
        NBioBSPJNI bsp = new NBioBSPJNI();

        NBioBSPJNI.FIR_HANDLE hSavedFIR;
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR;

        /* ouvrir Device */
        bsp.OpenDevice();

        /*    créer un objet de type FIR_HANDLE  */
        hSavedFIR = bsp.new FIR_HANDLE();

        if (CheckError(bsp)) {
            System.out.println(null +"Erreur de capture"+"Système pfe -Accès central");
            System.exit(0);
        }
        /* faire de capture avec de variable */
        bsp.Capture(hSavedFIR);


        /*   verify si il ya un erreur de  Device */
        if (CheckError(bsp)) {
            System.out.println(null +"Erreur de capture"+"Système pfe -Accès central");
            return null;
        }

        /*    créer un objet de type FIR_TEXTENCODE  */
        textSavedFIR = bsp.new FIR_TEXTENCODE();

        /*     Retrieves fir data */

        bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);

        /*   verify si il ya un erreur de  Device */
        if (CheckError(bsp)) {
            System.out.println(null +"Erreur de capture"+"Système pfe -Accès central");
            return null;
        }

        System.out.println(null+"Succès! \n La biométrie a été capturée avec succès");
        /*        donne un schiffer unique */
        return textSavedFIR.TextFIR;

    }


    public Boolean CheckError(NBioBSPJNI bsp) {
        if (bsp.IsErrorOccured()) {
            System.out.println(null+"Erreur de capture: " + bsp.GetErrorCode()+
                    "Système pfe - Accès central");
            return true;
        }

        return false;
    }


}