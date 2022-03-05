package com.postalinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.postalinfo.entity.PostalData;

/**
 * Repository class for handling PostalData curd operations
 *
 */
public interface PostalDataRepository extends CrudRepository<PostalData, Long> {
	List<PostalData> findByCodeInOrderBySuburbname(List<String> postalCodes);
	List<PostalData> findAll();
}
