package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
	

	
    //ajax (jquery)檢查課程名稱是否重複，並回傳JSON物件給前端，顯示課程編號幾號與之重複
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
	
	//後端檢查不重複身分證，若重複就導回新增頁面
	@PostMapping("/CheckMember")
	public String checkMember(@RequestParam String userid, Member member, MultipartFile imageFile)throws IllegalStateException, IOException {

		Member memberid = memberService.getByUserid(userid);
		if (memberid == null) {
			String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
			String ImgUrl = "/memberimages/" + newFileName;

			String saveFilePath = new File("target\\classes\\static\\memberimages\\" + newFileName).getAbsolutePath();

			if (imageFile.getOriginalFilename().length() != 0) {
				member.setImage(ImgUrl);
				imageFile.transferTo(new File(saveFilePath));
			}
			memberService.saveOrUpdate(member);
			return "redirect:/dashboard/members";
		
		}else {
			System.err.println("這是重複身分證字號，重導至新增畫面");
			return "redirect:member";
		}
		
	}
	
	
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
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(java.util.Date.class, ce);
    }
	
	
	@ResponseBody
	@GetMapping("/member/chartdata1")
	public int[] getChartData1() {
		int[] chartdata = {0,0};
		List<Member> allmembers = memberService.getAllMembers();
		for (Member member : allmembers) {
			switch (member.getGender()) {
			case "男": chartdata[0]++; 
			break;
			case "女": chartdata[1]++; 
			break;
			default: break;
			}
		}
		return chartdata;
	}	
	
	@ResponseBody
	@GetMapping("/member/chartdata2")
	public int[] getChartData2() {
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		
		int[] chartdata = {0,0,0,0};
		List<Member> allmembers = memberService.getAllMembers();
		for (Member member : allmembers) {
			if(yearNow - Integer.valueOf(member.getBirth().toString().substring(0, 4)) <= 18){
			chartdata[0]++ ;
			}
			else if(yearNow - Integer.valueOf(member.getBirth().toString().substring(0, 4)) > 19 && yearNow - Integer.valueOf(member.getBirth().toString().substring(0, 4)) <= 40 ){
			chartdata[1]++ ;
			}else if(yearNow - Integer.valueOf(member.getBirth().toString().substring(0, 4)) > 41 && yearNow - Integer.valueOf(member.getBirth().toString().substring(0, 4)) <= 65 ) {
			chartdata[2]++ ;
			}else {
			chartdata[3]++ ;	
			}
		}
		return chartdata;
	}
	
	
	
	
}
