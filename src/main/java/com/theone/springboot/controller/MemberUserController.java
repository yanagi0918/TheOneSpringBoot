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
@RequestMapping("/user")
public class MemberUserController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/members")
	public String toMemberListPage(Model model) { //類似get request attribute 把資料往前端模板引擎丟
		//一樣有session狀態才可以看到
		//使用Member member = memberService.getByUserid取到該會員資訊 
		List<Member> allMembers = memberService.getAllMembers();
		model.addAttribute("members", allMembers);
		return "member_dashboard/memberlist";
	}
	
	
    //ajax (jquery)檢查身分證是否重複，並回傳JSON物件給前端
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
	
	@GetMapping("/member")
	public String toCreatePage() {
		return "member_dashboard/membercreate";
	}
	//target下資料夾還沒建立
	@PostMapping("/member")
	public String saveOrUpdate(Member member, MultipartFile imageFile) throws IllegalStateException, IOException {

		String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
		String ImgUrl = "/memberimages/" + newFileName;

		String saveFilePath = new File("target\\classes\\static\\memberimages\\" + newFileName).getAbsolutePath();

		if (imageFile.getOriginalFilename().length() != 0) {
			member.setImage(ImgUrl);
			imageFile.transferTo(new File(saveFilePath));
		}

		memberService.saveOrUpdate(member);

		return "redirect:/dashboard/members";
	}
	
	@GetMapping("/member/{id}")
	public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
		Member member = memberService.getMember(id).get();
		model.addAttribute("member", member);
		return "member_dashboard/memberupdate";
	}
	
//	@ResponseBody
//	@DeleteMapping("/member/{id}")
//	public String delete(@PathVariable("id") Integer id) {
//		memberService.deleteMember(id);
//		return "ok";
//	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(java.util.Date.class, ce);
    }
	
	
	@PostMapping(value = "/CheckMember")
	public @ResponseBody boolean checkMember(@RequestParam String userid) {
		Member member = memberService.getByUserid(userid);
		if(member == null) {
			System.out.println("in");
			return true;
		}
		System.out.println("out");
		return false;
	}
	
}
