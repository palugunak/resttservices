package com.learn.restservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.restservices.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	Registration findById(long id);
	List<Registration> findByFirstName(String firstName);
	List<Registration> findByMsisdnNumber(String msisdn);
	List<Registration> findByIdentificationNumber(String identificationnumb);
	List<Registration> findByEmail(String emailaddress);

}
