package com.spendsense.splitx.service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.UserGroupMapping;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.RepaymentsRepository;
import com.spendsense.splitx.repository.UserGroupMappingRepository;
import com.spendsense.splitx.repository.UserTransactionMappingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummyUserService {
    @Autowired
    private UserGroupMappingRepository userGroupMappingRepository;

    @Autowired
    private RepaymentsRepository repaymentsRepository;

    @Autowired
    private UserTransactionMappingRepository userTransactionMappingRepository;

    @Transactional(rollbackOn = Exception.class)
    public void replaceDummyUserDetails(long tempUserId, long actualUserId, Group group) {
        log.info("Replacing dummy user details: tempUserId={}, actualUserId={}", tempUserId, actualUserId);
        // Implementation to replace dummy user details goes here
        repaymentsRepository.replaceTempUserDetails(tempUserId, actualUserId);
        userTransactionMappingRepository.replaceTempUserDetails(tempUserId, actualUserId);
        UserGroupMapping tempUserMapping = userGroupMappingRepository.findAllByUserId(tempUserId).getFirst();
        userGroupMappingRepository.delete(tempUserMapping);
    }
}
