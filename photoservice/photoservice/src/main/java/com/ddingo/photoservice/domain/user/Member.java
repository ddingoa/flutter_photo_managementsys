package com.ddingo.photoservice.domain.user;


import com.ddingo.photoservice.domain.config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "meMember")
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String userName;

    private String password;

    private boolean userCheckInit;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(length = 50, updatable = false)
    private String regCd;

    @Column(length = 50, insertable = false)
    private String modCd;

    @Builder
    public Member(String email, String password, String userName, boolean userCheckInit,String regCd,String modCd,  Authority authority) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.regCd = regCd;
        this.modCd = modCd;
        this.authority = authority;
        this.userCheckInit = userCheckInit;
    }
}
