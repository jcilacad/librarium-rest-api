package com.projects.memberservice.service.impl;

import com.projects.memberservice.dto.request.MemberRequest;
import com.projects.memberservice.dto.response.MemberResponse;
import com.projects.memberservice.entity.Member;
import com.projects.memberservice.exception.MemberAlreadyExistsException;
import com.projects.memberservice.exception.MemberNotFoundException;
import com.projects.memberservice.mapper.MemberMapper;
import com.projects.memberservice.repository.MemberRepository;
import com.projects.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse createMember(MemberRequest memberRequest) {
        boolean existsByEmail = memberRepository.existsByEmail(memberRequest.getEmail());
        if(existsByEmail) {
            throw new MemberAlreadyExistsException(memberRequest.getEmail());
        }

        Member savedMember = memberRepository.save(MemberMapper.INSTANCE.memberRequestToMember(memberRequest));
        return MemberMapper.INSTANCE.memberToMemberResponse(savedMember);
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberMapper.INSTANCE::memberToMemberResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponse getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(MemberMapper.INSTANCE::memberToMemberResponse)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    public MemberResponse updateMember(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        member.setFirstName(memberRequest.getFirstName());
        member.setLastName(memberRequest.getLastName());
        member.setEmail(memberRequest.getEmail());
        Member savedMember = memberRepository.save(member);
        return MemberMapper.INSTANCE.memberToMemberResponse(savedMember);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        memberRepository.delete(member);
    }
}
