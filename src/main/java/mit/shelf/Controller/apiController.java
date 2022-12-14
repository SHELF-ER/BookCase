package mit.shelf.Controller;

import io.swagger.annotations.Api;
import mit.shelf.Form.UserForm;
import mit.shelf.domain.Member;
import mit.shelf.domain.User;
import mit.shelf.repository.LibUserRepository;
import mit.shelf.repository.MemberRepository;
import mit.shelf.repository.UserRepository;
import mit.shelf.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class apiController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LibUserRepository libUserRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/users")
    public List<User> apiUserList() {
        return libUserRepository.findAll(); }

    @GetMapping(value = "/books")
    public List<Member> apiBookList() {
        return memberService.findMembers();
    }

    @PostMapping(value = "/user")
    public Map<String, String> insertUser(UserForm form) {
        User member = new User();
        member.setName(form.getName());
        member.setPw(form.getPw());
        member.setUid(form.getUid());
        libUserRepository.save(member);
        Map<String, String> list = new HashMap<>();
        list.put("result", "success");
        return list;
    }

    @PutMapping(value = "/user")
    public Map<String, String> updateUser(String id) {
        Map<String, String> list = new HashMap<>();
        list.put("result", "success");
        return list;
    }

}
