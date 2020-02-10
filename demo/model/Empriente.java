package com.demo.model;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Empriente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(length = 3000)
    private String fingerPrint;

    @ManyToOne
    User user;




}
