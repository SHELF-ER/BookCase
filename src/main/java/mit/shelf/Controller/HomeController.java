package mit.shelf.Controller;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import mit.shelf.domain.Member;
import mit.shelf.domain.User;
import mit.shelf.repository.LibUserRepository;
import mit.shelf.repository.MemberRepository;

import mit.shelf.repository.UserRepository;
import mit.shelf.repository.eBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    //todo: 다른 컨트롤러에서 사용자 페이지 만들기
    //todo: 잘못된 위치의 도서 누르면 상세정보
    @Autowired
    UserRepository userRepository;

    @Autowired
    eBookRepository eBookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LibUserRepository libUserRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String userHome() {
        return "userHome";
    }

    @GetMapping(value = "/main")
    public String main() {
        return "main";
    }

    @GetMapping(value = "/client")
    public String client() {
        return "client";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @GetMapping(value = "/login2")
    public String login2(){
        return "/login2";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "start";
    }

    @GetMapping(value = "/adminLogin")
    public String adminLogin(){
        return "/adminLogin";
    }

    @GetMapping(value = "/userinsert")
    public String userinsert(){
        return "/userinsert";
    }

    @GetMapping(value = "/userupdate")
    public String userupdate(){
        return "/userupdate";
    }

    @GetMapping(value = "/userdelete")
    public String userdelete(){
        return "/userdelete";
    }


    @GetMapping(value = "/borrow")
    public String borrow() {
        return "borrow";
    }

    @GetMapping(value = "/myborrow")
    public String myborrow() {
        return "myborrow";
    }

    @GetMapping("/test")
    @ResponseBody() // JSON
    public String time() {
        return "안녕하세요. " + new Date();
    }


    @RequestMapping(value = "/book/return/{smartUid}", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> findUUid(@PathVariable String smartUid) {

        Optional<Member> user = memberRepository.findBySmartUid(smartUid);
        Optional<User> userN = libUserRepository.findByUUid(smartUid);
        Map<String, String> list = new HashMap<>();

        if (user.isPresent() && userN.isPresent()) {
            Member userIId = user.get();
            User borrowUser = userN.get();
            userIId.setBorrower("X");

            String tmp1 = borrowUser.getBorrow1();
            String tmp2 = borrowUser.getBorrow2();
            String tmp3 = borrowUser.getBorrow3();

            if (tmp1.equals(smartUid)) {
                borrowUser.setBorrow1("X");
            }
            else if (tmp2.equals(smartUid)) {
                borrowUser.setBorrow2("X");
            }
            else if (tmp3.equals(smartUid)) {
                borrowUser.setBorrow3("X");
            }
            memberRepository.save(userIId);
            libUserRepository.save(borrowUser);
            list.put("book", String.valueOf(userIId.getBookNum()));
        } else {
            list.put("book", "error");
        }

        return list;
    }

    @RequestMapping(value = "/book/return/check/{smartUid}/{userUid}", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> findBookName(@PathVariable String smartUid, @PathVariable String userUid) {
        Optional<Member> member = memberRepository.findBySmartUid(smartUid);
        Optional<User> user = libUserRepository.findByUidU(userUid);
        Map<String, String> list = new HashMap<>();

        if (member.isPresent()&& user.isPresent()) {
            Member userIId = member.get();
            User userId = user.get();
            if (userIId.getBorrower().equals(userId.getName())) {
                list.put("book", String.valueOf(userIId.getName()));
            }
            else {
                list.put("book","cant");
            }
        } else {
            list.put("book", "error");
        }
        return list;
    }

    @RequestMapping(value = "/book/lend/check/{smartUid}/{userUid}", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> findBookName2(@PathVariable String smartUid, @PathVariable String userUid) {
        Optional<Member> member = memberRepository.findBySmartUid(smartUid);
        Optional<User> user = libUserRepository.findByUidU(userUid);
        Map<String, String> list = new HashMap<>();

        if (member.isPresent() && user.isPresent()) {
            Member userIId = member.get();
            User userId = user.get();
            if (!userId.getBorrow1().equals("X") && !userId.getBorrow2().equals("X") && !userId.getBorrow3().equals("X")) {
                list.put("book","full");
            }
            else {
                if (userIId.getBorrower().equals("X")) {
                    list.put("book", String.valueOf(userIId.getName()));
                } else {
                    list.put("book", "cant");
                }
            }
        } else {
            list.put("book", "error");
        }
        return list;
    }

    @RequestMapping(value = "/book/lendList/{smartUid}", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> findBorrower(@PathVariable String smartUid) {
        Optional<Member> user = memberRepository.findBySmartUid(smartUid);
        Map<String, String> list = new HashMap<>();

        if (user.isPresent()) {
            Member userIId = user.get();
            list.put("cateNum", String.valueOf(userIId.getBookNum()));
            list.put("bookName", String.valueOf(userIId.getName()));

        } else {
            list.put("Lend", "error");
        }
        return list;
    }

    @RequestMapping(value = "/book/lend/{userUid}/{smartUid}", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> lendBook(@PathVariable String userUid,@PathVariable String smartUid) {
        Optional<Member> book = memberRepository.findBySmartUid(smartUid);
        Optional<User> user = libUserRepository.findByUidU(userUid);
        Map<String, String> list = new HashMap<>();

        if (book.isPresent() && user.isPresent()) {
            Member bookId = book.get();
            bookId.setBorrower(user.get().getName());
            bookId.setCount(bookId.getCount()+1);
            memberRepository.save(bookId);
            User userId = user.get();
            if (userId.getBorrow1().equals("X") || (userId.getBorrow1().equals("대여가능"))) {
                userId.setBorrow1(smartUid);
                libUserRepository.save(userId);
                list.put("result", "success");
                list.put("book", String.valueOf(bookId.getName()));
                list.put("user",String.valueOf(user.get().getName()));
            }
            else if (userId.getBorrow2().equals("X") || (userId.getBorrow2().equals("대여가능"))) {
                userId.setBorrow2(smartUid);
                libUserRepository.save(userId);
                list.put("result", "success");
                list.put("book", String.valueOf(bookId.getName()));
                list.put("user",String.valueOf(user.get().getName()));
            }
            else if (userId.getBorrow3().equals("X") || (userId.getBorrow3().equals("대여가능"))) {
                userId.setBorrow3(smartUid);
                libUserRepository.save(userId);
                list.put("result", "success");
                list.put("book", String.valueOf(bookId.getName()));
                list.put("user", String.valueOf(user.get().getName()));
            }
        } else {
            list.put("result", "error");
        }
        return list;
    }

    @RequestMapping(value = "/book/borrow/{bookUid}/{userUid}", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> borrowBook(@PathVariable String bookUid,@PathVariable Long userUid) {

        Optional<Member> user = memberRepository.findByUid(bookUid);
        Optional<User> userName = libUserRepository.findById(userUid);
        Map<String, String> list = new HashMap<>();

        if (user.isPresent() && userName.isPresent()) {
            Member userIId = user.get();
            User borrowUser = userName.get();
            userIId.setBorrower(borrowUser.getName());
            borrowUser.setBorrow1(userIId.getName());
            memberRepository.save(userIId);
            libUserRepository.save(borrowUser);
            list.put("book", String.valueOf(userIId.getBookNum()));
        } else {
            list.put("book", "error");
        }

        return list;
    }

    //2층으로 수정
    @PostMapping("/books/uid")
    @ResponseBody() // JSON
    public Map<String, String> update(@RequestBody ArrayList<ArrayList<String>> robot) throws JsonProcessingException {
        for (int k = 0; k < robot.size(); k++) {
            ArrayList<String> uidList = robot.get(k);
            Integer countBook = userRepository.countAll();
            int roof = Math.min(countBook, uidList.size());
            List<Member> members = memberRepository.findAllByBookFloor(k+1);
            for (int i = 0; i < roof; i++) {
                int finalI = i;
                int finalK = k;
                members.get(i).setUid(uidList.get(finalI));
                members.get(i).setBookFloor(finalK+1);
                memberRepository.save(members.get(i));
            }
        }
        Map<String, String> list = new HashMap<>();
        list.put("result", "True");
        return list;
    }

    @PostMapping("/books/uidTest")
    @ResponseBody() // JSON
    public ArrayList<String> updateTest(@RequestBody ArrayList<ArrayList<String>> robot) throws JsonProcessingException {
        Map<String, String> list = new HashMap<>();
        ArrayList<String> errorB = new ArrayList<>();
        for (int k = 0; k < robot.size(); k++) {
            ArrayList<String> robotUids = robot.get(k);
            List<Member> members = memberRepository.findAllByBookFloor(k+1);
            int roof = Math.min(robotUids.size(), members.size());
            for (int i = 0; i < roof; i++) {
                String memberUid = members.get(i).getUid();
                if (!(memberUid).equals(robotUids.get(i))) {
                    Optional<Member> member = memberRepository.findByUid(memberUid);
                    errorB.add(member.get().getUid());
                }
            }
            if (insertErrorBook(errorB)) {
                list.put("result", "true");
            } else {
                list.put("result", "false");
            }
        }
        return errorB;
    }

    @PostMapping("/books/check")
    @ResponseBody() // JSON
    public Map<String, String> listCheck(@RequestBody Map<String, ArrayList<String>> robotUid) throws JsonProcessingException {

        ArrayList<String> uidList;
        uidList = robotUid.get("id");

        List<String> bookAllUid = userRepository.selectAllUid();
        ArrayList<String> errorB = new ArrayList<>();
        Map<String, String> list = new HashMap<>();
        Integer countBook = userRepository.countAll();

        for (int i = 0; i < countBook; i++) {
            int finalI = i;
            ArrayList<String> finalUidList = uidList;
            if (!(finalUidList.get(finalI).equals(bookAllUid.get(finalI)))) {
                Optional<Member> member = memberRepository.findByUid(bookAllUid.get(finalI));
                errorB.add(member.get().getUid());
            }
        }
        if (insertErrorBook(errorB)) {
            list.put("result", "true");
        } else {
            list.put("result", "false");
        }
        return list;
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
        return "members/memberList";
    }

    @RequestMapping(value = "/book/edit", method = RequestMethod.GET)
    public String bookEdit(@RequestParam("id") Long id, Model model) {
        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(selectUser -> {
            model.addAttribute("member", member);
        });
        return "members/editBookForm";
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


    public Boolean insertErrorBook(ArrayList<String> eb) {

        for (int i = 0; i <= eb.size() - 1; i++) {
            Optional<Member> updateUser = memberRepository.findByUid(eb.get(i));
            updateUser.ifPresent(selectUser -> {

                selectUser.setBookCmp(Long.valueOf(1));
                memberRepository.save(selectUser);
            });
        }
        return true;
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
        return "/members/memberList";
    }


    // book/rent?bookUid=aaa&userUid=bbb
    @RequestMapping(value = "/book/rent", method = RequestMethod.GET)
    @ResponseBody() // JSON
    public Map<String, String> rentBook(@RequestParam(value = "bookUid") String bookUid,
                                        @RequestParam(value = "userUid") String userUid) {
        Optional<Member> book = memberRepository.findByUid(bookUid);
        Optional<User> user = libUserRepository.findByUid(userUid);
        Map<String, String> list = new HashMap<>();

        if (book.isPresent() && user.isPresent()) {
            Member bookId = book.get();
            bookId.setBorrower(user.get().getName());
            memberRepository.save(bookId);
            list.put("result", "success");
            list.put("book", String.valueOf(bookId.getName()));
            list.put("user",String.valueOf(user.get().getName()));
        } else {
            list.put("result", "error");
        }

        return list;
    }

//    @GetMapping(value = "/donater")
//    public String donater(Model model){
//        List<Member> members = memberRepository.findByBonor();
//        model.addAttribute("members", members);
//
//        return "members/eBook";
//    }

}
