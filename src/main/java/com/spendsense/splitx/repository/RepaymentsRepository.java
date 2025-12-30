package com.spendsense.splitx.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.Repayments;

import java.util.List;

@Repository
public interface RepaymentsRepository extends JpaRepository<Repayments, Long>{

	String replaceTempUserDetailsQuery = "UPDATE REPAYMENTS SET FROM_ID = CASE WHEN FROM_ID = :tempUserId THEN :actualUserId ELSE FROM_ID END, TO_ID = CASE WHEN TO_ID = :tempUserId THEN :actualUserId ELSE TO_ID END WHERE FROM_ID = :tempUserId OR TO_ID = :tempUserId";
	
	@Query(value = "INSERT INTO REPAYMENTS (FROM_ID, TO_ID, AMOUNT, TXN_ID) VALUES (:fromId, :toId, :amount, :txnId)", nativeQuery = true)
	Repayments saveById(long fromId, long toId, double amount, long txnId);
	@Modifying
	@Transactional
	@Query(value = replaceTempUserDetailsQuery, nativeQuery = true)
	int replaceTempUserDetails(long tempUserId, long actualUserId);
}
