package com.galendar.domain.contest.mapper;

import com.galendar.domain.contest.dto.request.RegisterContestRequest;
import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.entity.ContestTargetEntity;
import com.galendar.domain.target.entity.TargetEntity;
import com.galendar.domain.target.mapper.TargetMapper;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContestMapper {

    public ContestEntity createEntity(RegisterContestRequest request, UserEntity userEntity){
        return ContestEntity.builder()
                .userEntity(
                        userEntity
                )
                .title(request.getTitle())
                .content(request.getContent())
                .cost(request.getCost())
                .link(request.getLink())
                .imgLink(request.getImgLink())
                .submitStartDate(request.getSubmitStartDate())
                .submitEndDate(request.getSubmitEndDate())
                .contestStartDate(request.getContestStartDate())
                .contestEndDate(request.getContestEndDate())
                .strNo(request.getStrNo())
                .build();
    }

}