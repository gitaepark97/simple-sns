package hugo.simplesns.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record PostCreateRequest(
    @NotBlank
    @Size(max = 100, message = "제목은 100자 이하의 문자입니다.")
    String title,

    @NotBlank
    String content
) {

}
