package com.ecw.deidtool.storage;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

//@ConfigurationProperties(prefix="ccda.storage")
@Component
@Getter
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = "temp";// + File.separator + "Vema"; //  + File.separator + "Rich Vema"

//	public String getLocation() {
//		return location;
//	}


}
