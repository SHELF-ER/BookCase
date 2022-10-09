package mit.shelf.Controller;

import io.swagger.annotations.ApiOperation;
import mit.shelf.Form.MemberForm;
import mit.shelf.domain.Member;
import io.swagger.annotations.Api;
import mit.shelf.repository.LibUserRepository;
import mit.shelf.repository.MemberRepository;
import mit.shelf.repository.UserRepository;
import mit.shelf.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class BookController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LibUserRepository libUserRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/members") public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "books/memberList"; }


    @GetMapping(value = "/members/errorBookList")
    public String errorBook(Model model){
        List<Member> members = memberService.findMembers();
        Collections.reverse(members);
        model.addAttribute("members", members);

        return "books/eBook";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setBookNum(form.getBookNum());
        member.setBorrower(form.getBorrower());
        member.setUid(form.getUid());
        member.setBookFloor(form.getBookFloor());
        member.setBookCmp(form.getBookCmp());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members/new") public String createForm() {
        return "members/createMemberForm";
    }

    @GetMapping(value = "/books/cmpReset")
    public String bookCmpReset() {
        List<Member> members = memberRepository.findAll();
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            member.setBookCmp(0L);
            memberRepository.save(member);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/books/list")
    public String listCheck(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "books/memberList";
    }

    @RequestMapping(value = "/book/edit", method = RequestMethod.GET)
    public String bookEdit(@RequestParam("id") Long id, Model model) {
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(selectUser -> {
            model.addAttribute("member", member);
        });
        return "books/editBookForm";
    }

    //required 붙여서 무조건 값있도록
    @PostMapping("/book/edit")
    public String updateBook(MemberForm form) {

        Optional<Member> updateUser = memberRepository.findById(form.getId());
        updateUser.ifPresent(member -> {
            member.setName(form.getName());
            member.setBookNum(form.getBookNum());
            member.setBorrower(form.getBorrower());
            member.setUid(form.getUid());
            member.setBookFloor(form.getBookFloor());
            member.setBookCmp(form.getBookCmp());
            memberRepository.save(member);
        });
        return "redirect:/";
    }

    @RequestMapping(value = "/ebook/{uid}", method = RequestMethod.GET)
    public String findEbook(@PathVariable String uid) {
        return "";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {

        List<Member> search = memberRepository.findByName(keyword);
        model.addAttribute("search", search);
        return "borrow";

    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.GET)
    public String deleteBook(@RequestParam("uid") Long uid) {
        memberRepository.deleteById(uid);
        return "/books/memberList";
    }

//    @GetMapping(value = "/donater")
//    public String donater(Model model){
//        List<Member> members = memberRepository.findByBonor();
//        model.addAttribute("members", members);
//
//        return "members/eBook";
//    }
}
