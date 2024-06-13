package com.projects.memberservice.service;

import com.projects.memberservice.dto.request.MemberRequest;
import com.projects.memberservice.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {

    MemberResponse createMember(MemberRequest memberRequest);

    List<MemberResponse> getAllMembers();

    MemberResponse getMemberById(Long id);

    MemberResponse updateMember(Long id, MemberRequest memberRequest);

    void deleteMember(Long id);
}
