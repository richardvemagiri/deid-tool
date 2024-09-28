package com.ecw.deidtool.config;

import com.ecw.deidtool.repository.DeIDConfigDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="ccda")
public class AppProperties {

//    private List<String> xpaths;
//    private String overwritePhrase;
    private String fileNameAppend;
    private Map<String,String> namespaces;


}
