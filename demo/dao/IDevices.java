package com.demo.dao;

import com.demo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDevices extends JpaRepository<Device, Long>{

Optional<Device>findByInstanceAndNameId(short instance,short nameId);


}
	
