package mit.shelf.Controller;

//import io.swagger.annotations.*;
import mit.shelf.domain.Member;
import mit.shelf.domain.User;
import mit.shelf.repository.BookLocationRepository;
import mit.shelf.repository.LoginRepository;
import mit.shelf.repository.MemberRepository;
import mit.shelf.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Api(tags = {"API 정보를 제공하는 Controller"}) 오류
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    BookLocationRepository bookLocationRepository;


    @GetMapping(value = "/members") public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList"; }

    @GetMapping(value = "/members/errorBookList")
    public String errorBook(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/eBook";
    }

//    @ApiOperation(value = "모든 책 정보를 반환하는 메소드")
    @GetMapping(value = "books")
    @ResponseBody
    public ArrayList<Map<String, String>> errorBookAPI(){
        List<Member> members = memberService.findMembers();
        ArrayList<Map<String,String>> list=new ArrayList<>();

        for (Member member:members) {
            Map<String, String> result = new HashMap<>();
            result.put("id", String.valueOf(member.getId()));
            result.put("name", String.valueOf(member.getName()));
            result.put("cmp", String.valueOf(member.getBookCmp()));
            result.put("floor", String.valueOf(member.getBookFloor()));
            list.add(result);
        }
        return list;
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

    //기부 명단 만들기
    @PostMapping(value = "/book/donate")
    @ResponseBody()
    public String donateBook(Member form){
        Member member = new Member();
        member.setName(form.getName());
        member.setBonor(form.getBonor());
        memberService.join(member);
        return "success";
    }

    @GetMapping(value = "/sea") public String search(@RequestParam(value = "keyword") String keyword, Model model, @RequestParam(value = "filter")String filter, @RequestParam(value = "categoryfilter")String categoryfilter, String category, @RequestParam(value = "id") Long id) {

        switch (categoryfilter){
            case "2" :
                category = "가정과생활";
                break;
            case "3" :
                category = "경제/비지니스";
                break;
            case "4" :
                category = "인문/사회";
                break;
            case "5" :
                category = "역사";
                break;
            case "6" :
                category = "자연/과학";
                break;
            case "7" :
                category = "취미/건강/여행";
                break;
            case "8" :
                category = "문화예술";
                break;
        }

        if (filter.equals("0")) {
            List<Member> searchs = memberRepository.findByName(keyword);
            model.addAttribute("searchs", searchs);

        }
        if (filter.equals("2")) {
            List<Member> searchs = memberRepository.findByWriter(keyword);
            model.addAttribute("searchs", searchs);
        }

        if (categoryfilter.equals("1")){
            List<Member> searchs = memberService.findMembers();
            model.addAttribute("searchs", searchs);
        }
        if (categoryfilter.equals("2")||categoryfilter.equals("3")||categoryfilter.equals("4")||categoryfilter.equals("5")||
                categoryfilter.equals("6")||categoryfilter.equals("7")||categoryfilter.equals("8")) {
            List<Member> searchs = memberRepository.findByCategory(category);
            model.addAttribute("searchs", searchs);
        }
        User result = loginRepository.userNameIdSharing(id);
        model.addAttribute("loginGo", result.getName());
        model.addAttribute("loginGo2", result.getId());
        return "/borrow";
    }


    @GetMapping(value = "/sea/location") public String searchLocation(@RequestParam(value = "id") Long id, @RequestParam(value = "colorval") String colorval,Model model) {
        int color1 = 0;
        int color2 = 0;
        int color3 = 0;
        int color4 = 0;
        int color5 = 0;
        int color6 = 0;
        int color7 = 0;
        Long result = bookLocationRepository.findBookLocation(id);

        Long bookId1 = bookLocationRepository.findBookLocation(1L);
        Long bookId2 = bookLocationRepository.findBookLocation(2L);
        Long bookId3 = bookLocationRepository.findBookLocation(3L);
        Long bookId4 = bookLocationRepository.findBookLocation(4L);
        Long bookId5 = bookLocationRepository.findBookLocation(5L);
        Long bookId6 = bookLocationRepository.findBookLocation(6L);
        Long bookId7 = bookLocationRepository.findBookLocation(7L);



        if (result == 1)
            color1 = 1;
        else if (result == 2)
            color2 = 2;
        else if (result == 3)
            color3 = 3;
        else if (result == 4)
            color4 = 4;
        else if (result == 5)
            color5 = 5;
        else if (result == 6)
            color6 = 6;
        else if (result == 7)
            color7 = 7;
        model.addAttribute("locations", result);

        model.addAttribute("bookId1", bookId1);
        model.addAttribute("bookId2", bookId2);
        model.addAttribute("bookId3", bookId3);
        model.addAttribute("bookId4", bookId4);
        model.addAttribute("bookId5", bookId5);
        model.addAttribute("bookId6", bookId6);
        model.addAttribute("bookId7", bookId7);
        model.addAttribute("color1", color1);
        model.addAttribute("color2", color2);
        model.addAttribute("color3", color3);
        model.addAttribute("color4", color4);
        model.addAttribute("color5", color5);
        model.addAttribute("color6", color6);
        model.addAttribute("color7", color7);


        return "/members/bookLocation";
    }


}
