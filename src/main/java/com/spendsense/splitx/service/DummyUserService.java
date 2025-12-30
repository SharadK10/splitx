package com.spendsense.splitx.service;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.entity.UserGroupMapping;
import com.spendsense.splitx.repository.GroupRepository;
import com.spendsense.splitx.repository.RepaymentsRepository;
import com.spendsense.splitx.repository.UserGroupMappingRepository;
import com.spendsense.splitx.repository.UserTransactionMappingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DummyUserService {
    @Autowired
    private UserGroupMappingRepository userGroupMappingRepository;

    @Autowired
    private RepaymentsRepository repaymentsRepository;

    @Autowired
    private UserTransactionMappingRepository userTransactionMappingRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Transactional(rollbackOn = Exception.class)
    public void replaceDummyUserDetails(long tempUserId, long actualUserId, Group group) {
        log.info("Replacing dummy user details: tempUserId={}, actualUserId={}", tempUserId, actualUserId);
        // Implementation to replace dummy user details goes here
        repaymentsRepository.replaceTempUserDetails(tempUserId, actualUserId);
        userTransactionMappingRepository.replaceTempUserDetails(tempUserId, actualUserId);
        UserGroupMapping tempUserMapping = userGroupMappingRepository.findAllByUserId(tempUserId).getFirst();
        userGroupMappingRepository.delete(tempUserMapping);
    }

    public List<User> getDummyUsersInGroup(String groupCode) {
        Group group = groupRepository.findByGroupCode(groupCode);
        if(group == null) {
            throw new RuntimeException("Group not found with code: " + groupCode);
        }
        List<UserGroupMapping> userGroupMappings = userGroupMappingRepository.findAllByGroupId(group.getId());
        List<User> dummyUsers = new ArrayList<>();
        for(UserGroupMapping mapping : userGroupMappings) {
            User user = mapping.getUser();
            if(user.isDummyUser() != null && user.isDummyUser()) {
                dummyUsers.add(user);
            }
        }
        return dummyUsers;
    }
}
