package com.example.do_an_cuoi_khoa.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationStatus {
    APPLIED,
    CANCELLED,
    APPLICATION_ACCEPTED,
    APPLICATION_REJECTED,
    WAIT_FOR_INTERVIEW,
    CANDIDATE_ACCEPTED,
    CANDIDATE_REJECTED
}
