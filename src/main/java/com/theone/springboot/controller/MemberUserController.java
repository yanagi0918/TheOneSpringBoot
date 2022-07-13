package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.MemberService;

@Controller
public class MemberUserController {

	@Autowired
	MemberService memberService;

	@GetMapping("/user/members")
	public String toMemberListPage(HttpSession session, Model model) { //Model是類似request getattribute 把資料往前端模板引擎丟
		//一樣有session狀態才可以看到
		Member member = (Member)session.getAttribute("loginMember");
		String userid = member.getUserid();
		Member memberinfo = memberService.getByUserid(userid); //取到該會員資料
		model.addAttribute("members", memberinfo);
		return "member/memberInfo";
	}


	@PostMapping("/user/members")
	public String toMemberPage(HttpSession session, MultipartFile imageFile ,Member member) throws IllegalStateException, IOException {
		String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
		String ImgUrl = "/memberimages/" + newFileName;
		String saveFilePath = new File("target\\classes\\static\\memberimages\\" + newFileName).getAbsolutePath();
		if (imageFile.getOriginalFilename().length() != 0) {
			member.setImage(ImgUrl);
			imageFile.transferTo(new File(saveFilePath));
		}
		Member member1 = memberService.saveOrUpdate(member);  //前端傳來的參數(更新過的member)接值存進資料庫，命名為member1
		session.setAttribute("loginMember", member1);  //把新的資料setAttribute放進session中
		return "member/memberInfo";
	}


//	@GetMapping("/member")//URL路徑會有user，可能是個問題，要再想一下
//	public String toCreatePage() {
//		return "member_dashboard/membercreate";
//	}


	@PostMapping("/signup")
	public String signUp(HttpSession session, Member member) throws IllegalStateException, IOException {
		Member newmember = memberService.saveOrUpdate(member);  //前端傳來的參數(新的member)接值存進資料庫，命名為newmember
		session.setAttribute("loginMember", newmember);
		//存進資料庫
		//放進Session
		//當session不是空的時候，才能離開登入畫面

		return "member/updateMember";
	}

	@PostMapping("/user/member")
	public String update(HttpSession session, Member member) throws IllegalStateException, IOException {
		Member newmember = memberService.saveOrUpdate(member);  //前端傳來的參數(新的member)接值存進資料庫，命名為newmember
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


	@PostMapping(value = "/user/CheckMember")
	public @ResponseBody boolean checkMember(@RequestParam String userid) {
		Member member = memberService.getByUserid(userid);
		if(member == null) {
			System.out.println("in");
			return true;
		}
		System.out.println("out");
		return false;
	}

	//ajax (jquery)檢查身分證是否重複，並回傳JSON物件給前端
	@PostMapping(path = "/user/members/checkID", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Member> getByUserid(@RequestBody Member member) {
		Member bean = memberService.getByUserid(member.getUserid());
		if (bean != null) {
			return ResponseEntity.status(HttpStatus.OK).body(bean);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	

}
