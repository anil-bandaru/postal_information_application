package com.postalinfo.config;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.postalinfo.entity.PostalData;
import com.postalinfo.repository.PostalDataRepository;

/**
 * Class to maintain postal data in cache
 *
 */
@Component
public class PostalDataConfig {
	
	private static Map<String,String> postalCache;

	@Autowired
	PostalDataRepository postalDataRepo;
	
	@PostConstruct
    public void initCache() {
        List<PostalData> allConfigs = postalDataRepo.findAll();
        buildCache(allConfigs);
    }
	private void buildCache(List<PostalData> allConfigs) {
		allConfigs.forEach(data->{postalCache.put(data.getPostalcode(), data.getSuburbname());});
	}
	public static Map<String,String> getPostalCache(){
		return postalCache;
	}
	public static boolean checkPostalCodeExistance(String postalCode){
		return postalCache.containsKey(postalCode);
	}
}
