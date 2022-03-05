package com.postalinfo.service;

import java.util.List;
import java.util.Map;

import com.postalinfo.entity.PostalData;
/**
 * Interface/Rule definer for PostalData manipulation
 * @author anilkumar.bandaru
 *
 */
public interface PostalDataService {

	void savePostalData(List<PostalData> postalDtos);
	Map<String, Object> getSuburbNames(List<String> postCodes);

}
