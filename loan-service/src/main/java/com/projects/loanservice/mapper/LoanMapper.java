package com.projects.loanservice.mapper;

import com.projects.loanservice.dto.request.LoanRequest;
import com.projects.loanservice.dto.response.LoanResponse;
import com.projects.loanservice.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan loanRequestToLoan(LoanRequest loanRequest);

    LoanResponse loanToLoanResponse(Loan loan);
}
