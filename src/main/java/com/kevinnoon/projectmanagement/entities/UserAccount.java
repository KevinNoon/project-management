package com.kevinnoon.projectmanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name="user_accounts")
public class UserAccount {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_accounts_seq")
    @SequenceGenerator(name="user_accounts_seq",sequenceName="user_accounts_seq", allocationSize = 1)
    private long userId;

    @Column(name = "username")
    private String userName;
    private String email;
    private String password;
    private boolean enabled = true;

}
