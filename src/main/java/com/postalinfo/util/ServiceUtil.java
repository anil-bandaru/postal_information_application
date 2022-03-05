package com.postalinfo.util;

import java.util.List;
import java.util.Optional;

import com.postalinfo.config.PostalDataConfig;
import com.postalinfo.entity.PostalData;

/**
 * Class for maintaining util methods for all service classes
 *
 */
public final class ServiceUtil {

	public static List<PostalData> duplicateData(List<PostalData> postalDtos) {
		List<PostalData> duplicateData=postalDtos.stream().filter(data->{return PostalDataConfig.checkPostalCodeExistance(data.getPostalcode());}).toList();
		return duplicateData;
	}

}
