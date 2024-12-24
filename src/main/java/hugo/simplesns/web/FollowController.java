package hugo.simplesns.web;

import hugo.simplesns.core.service.FollowService;
import hugo.simplesns.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/follows")
class FollowController {

    private final FollowService followService;
    private final UserService userService;

}
