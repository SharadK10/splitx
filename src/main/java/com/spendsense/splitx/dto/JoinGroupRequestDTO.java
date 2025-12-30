package com.spendsense.splitx.dto;

import com.spendsense.splitx.entity.Group;
import com.spendsense.splitx.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinGroupRequestDTO {
    private Group group;
    private Long userId;
}
