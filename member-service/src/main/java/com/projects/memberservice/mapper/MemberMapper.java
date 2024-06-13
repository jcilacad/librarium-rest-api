package com.projects.memberservice.mapper;

import com.projects.memberservice.dto.request.MemberRequest;
import com.projects.memberservice.dto.response.MemberResponse;
import com.projects.memberservice.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member memberRequestToMember(MemberRequest memberRequest);

    MemberResponse memberToMemberResponse(Member member);
}
