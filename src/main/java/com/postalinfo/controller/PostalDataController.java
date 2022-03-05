package com.postalinfo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postalinfo.entity.PostalData;
import com.postalinfo.exception.AppException;
import com.postalinfo.service.PostalDataService;
import com.postalinfo.util.Constants;
import com.postalinfo.util.GlobalResponseUtil;
/**
 * Class contains postal data processing end points
 */
@RestController
@Validated
public class PostalDataController {

	@Autowired
	PostalDataService postalDataService;

	/**
	 * Api endpoint to retrieve suburban data for given list of postal codes
	 * 
	 * @param postalCodes
	 * @return
	 * @throws AppException
	 * @throws Exception
	 */
	@GetMapping("/getSuburbanData")
	public ResponseEntity<GlobalResponseUtil> getSuburbanData(@RequestBody List<String> postalCodes)throws  AppException,Exception{
		GlobalResponseUtil responseUtil=null;
		Map<String, Object> response=postalDataService.getSuburbNames(postalCodes);
		responseUtil=new GlobalResponseUtil(HttpStatus.OK,response,Constants.SUCESS_MESSAGE);
		return ResponseEntity.ok().body(responseUtil);
	}

	/**
	 * Api endpoint to add new postal data into system/application
	 * 
	 * @param inputDatas
	 * @return
	 * @throws AppException
	 * @throws Exception
	 */
	@PostMapping("/add")
	public ResponseEntity<GlobalResponseUtil> updatePostalInfo(@RequestBody List<@Valid PostalData> inputDatas)throws  AppException,Exception {
		GlobalResponseUtil responseUtil=null;
		postalDataService.savePostalData(inputDatas);
		responseUtil=new GlobalResponseUtil(HttpStatus.OK,null,Constants.SUCESS_MESSAGE);
		return ResponseEntity.ok().body(responseUtil);
	}

}
