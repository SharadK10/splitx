package com.spendsense.splitx.repository;

import com.spendsense.splitx.entity.Repayments;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.UserTransactionMapping;

import java.util.List;

@Repository
public interface UserTransactionMappingRepository extends JpaRepository<UserTransactionMapping, Long>{
    String replaceTempUserDetailsQuery = "UPDATE user_transaction_mapping SET USER_ID = :actualUserId WHERE USER_ID = :tempUserId";
    @Modifying
    @Transactional
    @Query(value = replaceTempUserDetailsQuery, nativeQuery = true)
    int replaceTempUserDetails(long tempUserId, long actualUserId);
}
