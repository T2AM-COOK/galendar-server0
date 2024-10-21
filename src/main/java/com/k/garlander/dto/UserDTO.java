package com.k.garlander.dto;

import com.k.garlander.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Schema(description = "유저 정보 모델")
@Getter
@NoArgsConstructor
public class UserDTO {
    private String id;
    @Schema(description = "사용자 이메일", example = "example@example.com")
    private String email;
    @Schema(description = "사용자 닉네임", example = "김민규")
    private String nickname;
    @Schema(description = "사용자 비밀번호", example = "1234")
    private String password;

    @Builder
    public UserDTO(String id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static UserDTO of(Optional<UserEntity> userEntity) {
        return UserDTO.builder()
                .email(userEntity.get().getEmail())
                .build();
    }
}
