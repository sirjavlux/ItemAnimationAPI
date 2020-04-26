package com.coding.sirjavlux.core;

import org.bukkit.Bukkit;

import com.coding.sirjavlux.entity.*;
import com.coding.sirjavlux.registry.RegisterEntity_1_8_R1;
import com.coding.sirjavlux.registry.RegisterEntity_1_8_R2;
import com.coding.sirjavlux.registry.RegisterEntity_1_8_R3;
import com.coding.sirjavlux.service.*;

public class VersionManager {

	private String version;
	private ServiceWrapper service;
	
	public VersionManager() {
		this.version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].substring(1);
		
		switch (version) {
		case "1_8_R1": 
			RegisterEntity_1_8_R1.registerEntity("Bat", 65, CustomEntityBat_1_8_R1.class);
			service = new Service_1_8_R1();
			break;
		case "1_8_R2": 
			RegisterEntity_1_8_R2.registerEntity("Bat", 65, CustomEntityBat_1_8_R2.class);
			service = new Service_1_8_R2();
			break;
		case "1_8_R3": 
			RegisterEntity_1_8_R3.registerEntity("Bat", 65, CustomEntityBat_1_8_R3.class);
			service = new Service_1_8_R3();
			break;
		}
	}
	
	public String getVersion() {
		return version;
	}
	
	public ServiceWrapper getService() {
		return service;
	}
}
