package com.demo.service;

import java.util.*;
import java.util.List;

import com.demo.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.demo.dao.IDevices;
import com.nitgen.SDK.BSP.NBioBSPJNI;

import javax.transaction.Transactional;


@Service
public class DevicesService implements DeviceServiceImpl {
	@Autowired
	private IDevices iDevices;
	
	private NBioBSPJNI.DEVICE_ENUM_INFO     deviceEnumInfo;
	NBioBSPJNI bsp = new NBioBSPJNI();


	@Override
	public void InitDeviceList() {
		int i, n;
		deviceEnumInfo = bsp.new DEVICE_ENUM_INFO();
		bsp.EnumerateDevice(deviceEnumInfo);
		try {
			n = deviceEnumInfo.DeviceCount;
			System.out.println(" n : "+n);
			
			for (i = 0; i < n; i++)  {
	    		Device d=new Device();
	    		d.setName(deviceEnumInfo.DeviceInfo[i].Name);
	    		d.setInstance(deviceEnumInfo.DeviceInfo[i].Instance);
	    		d.setNameId(deviceEnumInfo.DeviceInfo[i].NameID);

	    		//System.out.println("Name devices :"+ deviceEnumInfo.DeviceInfo[i].Name);
	    		//System.out.println("Instance devices :"+ deviceEnumInfo.DeviceInfo[i].Instance);
	    		//System.out.println("Name ID devices :"+ deviceEnumInfo.DeviceInfo[i].NameID);

	    		iDevices.save(d);
	    		}

			//check = true;
		} catch (Exception e) {
			// TODO: handle exception
			//check = false;
		}
    	//return check;
    	}

	} 

	

	


