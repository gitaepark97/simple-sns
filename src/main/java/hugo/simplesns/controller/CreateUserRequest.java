package hugo.simplesns.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

record CreateUserRequest(
    @NotBlank
    @Pattern(
        regexp = "^[a-zA-Z0-9]{4,30}$",
        message = "사용자 이름은 알파벳과 숫자로만 구성된 4자리 이상, 30자리 이하의 문자입니다."
    )
    String username,

    @NotBlank
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
        message = "비밀번호는 대소문자, 숫자, 특수문자가 포함된 8자리 이상, 20자리 이하의 문자입니다."
    )
    String password
) {

}

