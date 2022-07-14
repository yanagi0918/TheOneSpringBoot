package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.MemberService;

@Controller
public class MemberUserController {

	@Autowired
	MemberService memberService;

	@GetMapping("/user/members")
	public String toMemberListPage(HttpSession session, Model model) { // Model是類似request getattribute 把資料往前端模板引擎丟
		Member member = (Member) session.getAttribute("loginMember"); // 在有session狀態才可以看到
		String userid = member.getUserid();
		Member memberinfo = memberService.getByUserid(userid); // 取到該會員資料
		model.addAttribute("members", memberinfo);
		return "member/memberInfo";
	}

	@PostMapping("/user/members")
	public String toMemberPage(HttpSession session, MultipartFile imageFile, Member member)
			throws IllegalStateException, IOException {
		String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
		String ImgUrl = "/memberimages/" + newFileName;
		String saveFilePath = new File("target\\classes\\static\\memberimages\\" + newFileName).getAbsolutePath();
		if (imageFile.getOriginalFilename().length() != 0) {
			member.setImage(ImgUrl);
			imageFile.transferTo(new File(saveFilePath));
		}
		Member member1 = memberService.saveOrUpdate(member); // 前端傳來的參數(更新過的member)接值存進資料庫，命名為member1
		session.setAttribute("loginMember", member1); // 把新的資料setAttribute放進session中
		return "member/memberInfo";
	}

	@PostMapping("/signup")
	public String signUp(HttpSession session, Member member, @RequestParam String userid)
			throws IllegalStateException, IOException {

		boolean checkId = checkMember(userid);
		if (checkId) {
			Member newmember = memberService.saveOrUpdate(member); // 前端傳來的參數(新的member)接值存進資料庫，命名為newmember
			session.setAttribute("loginMember", newmember); // 放進Session，當session不是空的時候，才能離開登入畫面

			return "member/updateMember";

		} else {

			return "login/login";
		}

	}

//後端驗證檢查userid是否重複
	public @ResponseBody boolean checkMember(@RequestParam String userid) {
		boolean checkId = false;

		Member member = memberService.getByUserid(userid);
		if (member == null) {
			checkId = true;
		}
		return checkId;
	}

	@PostMapping("/user/member")
	public String update(HttpSession session, Member member) throws IllegalStateException, IOException {
		Member newmember = memberService.saveOrUpdate(member); // 前端傳來的參數(新的member)接值存進資料庫，命名為newmember
		session.setAttribute("loginMember", newmember);
		return "member/updateMember";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.sql.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(java.util.Date.class, ce);
	}

	// ajax (jquery)檢查身分證是否重複，並回傳JSON物件給前端
	@PostMapping(path = "/members/checkID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Member> getByUserid(@RequestBody Member member) {
		Member bean = memberService.getByUserid(member.getUserid());
		if (bean != null) {
			return ResponseEntity.status(HttpStatus.OK).body(bean);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/forgetpwd")
	public String toForgetPwd() {
		return "login/memberforgetpwd";
	}

	@ResponseBody
	@PostMapping("/forgetpwd")
	public String sendNotifyEmail(@RequestParam String userid, @RequestParam String email) {
		if (memberService.getByUserid(userid).getUserid().equals(userid)
				&& memberService.getByUserid(userid).getEmail().equals(email)) {

			Member member = memberService.getByUserid(userid);
			String randomPwd = getRandomPassword();
			member.setPwd(randomPwd);
			String msg = "您的The One帳號: " + userid + "\n" + "您的臨時密碼:" + randomPwd + "\n" + "請登入後立即修改您的新密碼" + "\n"
					+ "重新登入:   " + "http://localhost:8080/login";
			memberService.sendNotifyEmail(email, "TheOne 會員忘記密碼", msg);
			return "請去您的註冊信箱收取新密碼，並立即更新密碼";

		} else {

			return "請輸入正確帳號與信箱";
		}
	}

	// 產生10碼英數亂碼
	private String getRandomPassword() {
		String newPwd = UUID.randomUUID().toString().substring(0, 10);
		return newPwd;
	}

	// ajax (jquery)檢查帳號與email是否符合，並回傳JSON物件給前端
//	@PostMapping(path = "/forgetpwd123", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public boolean checkIdAndEmail(@RequestBody Member member) {
//		System.out.println("Testtest");
//		if (memberService.getByUserid(member.getUserid()).getEmail().equals(member.getEmail())) {
//			return false;
//		} else {
//			return true;
//		}
//	}

}
