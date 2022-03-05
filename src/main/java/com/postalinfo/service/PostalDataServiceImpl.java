package com.postalinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.postalinfo.entity.PostalData;
import com.postalinfo.exception.AppError;
import com.postalinfo.exception.AppException;
import com.postalinfo.repository.PostalDataRepository;
import com.postalinfo.util.ServiceUtil;

/**
 * Class/Rule implementor for PostalData manipulation
 *
 */

@Service
public class PostalDataServiceImpl implements PostalDataService {
	@Autowired
	PostalDataRepository postalRepo;

	/**
	 * method for saving the postal data
	 * @param postalDtos
	 */
	@Transactional
	@Override
	public void savePostalData(List<PostalData> postalDtos) {
		List<PostalData> duplicateData = ServiceUtil.duplicateData(postalDtos);
		if (duplicateData != null && duplicateData.size() > 0) {
			throw new AppException(AppError.DATA_EXISTS, duplicateData.stream().map(data -> {
				return data.getPostalcode();
			}).toList().toString());
		}
		postalDtos.forEach(postalRepo::save);
	}

	/**
	 * method for retrieving the postal information based on postal codes
	 * 
	 * @param postCodes
	 */
	@Override
	public Map<String, Object> getSuburbNames(List<String> postCodes) {
		List<PostalData> postalDataList = postalRepo.findByCodeInOrderBySuburbname(postCodes);

		int characterLength = (postalDataList != null && !postalDataList.isEmpty())
				? postalDataList.stream().map(data -> {
					return data.getSuburbname();
				}).mapToInt(String::length).sum()
				: 0;
		if (characterLength == 0) {
			throw new AppException(AppError.DATA_NOT_FOUND, postCodes.toString());
		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("data", postalDataList);
		responseMap.put("characterLength", characterLength);
		return responseMap;
	}

}
