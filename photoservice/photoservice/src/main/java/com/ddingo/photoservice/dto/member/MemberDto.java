package com.ddingo.photoservice.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private String email;
    private String fabCode;
    private String userName;
    private String fabName;
    private String lineCode;
    private String lineName;
    private String workCode;
    private String workName;
    private boolean authority;
    private  boolean userCheckInit;
    private TokenDto tokenDto;
}
