package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.entity.Member;
import com.theone.springboot.service.MemberService;

@Controller
@RequestMapping("/dashboard")
public class MemberDashBoardController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/members")
	public String toMemberListPage(Model model) {
		List<Member> allMembers = memberService.getAllMembers();
		model.addAttribute("members", allMembers);
		return "member_dashboard/memberlist";
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
	
	@ResponseBody
	@DeleteMapping("/member/{id}")
	public String delete(@PathVariable("id") Integer id) {
		memberService.deleteMember(id);
		return "ok";
	}
	
	
	
	
}
