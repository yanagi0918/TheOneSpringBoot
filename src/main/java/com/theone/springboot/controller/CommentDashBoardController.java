package com.theone.springboot.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Comment;
import com.theone.springboot.entity.CommentMessage;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CommentMessageService;
import com.theone.springboot.service.CommentService;
import com.theone.springboot.service.MemberService;

@Controller
@RequestMapping("/dashboard")
public class CommentDashBoardController {

	@Autowired
	MemberService memberService;

	@Autowired
	CommentService commentService;

	@Autowired
	CommentMessageService commentMessageService;

	// 所有評論list
	@RequestMapping("/comments")
	public String listComments(Model model) {
		model.addAttribute("listComment", commentService.findAll());
		model.addAttribute("listCommentMessages", commentMessageService.findAll());
		return "comment_dashboard/commentlist";
	}

	// 儲存評論
	@PostMapping("/CommentSave")
	public String saveComment(@ModelAttribute("comment") Comment comment, @RequestParam("userid") String userid) {
//		Member member = memberService.getMember(idNumber).get();
		Member member = memberService.getByUserid(userid);
		comment.setCommentMember(member);
		commentService.saveOrUpdate(comment);
		return "redirect:./comments";
	}

	// 儲存留言
	@PostMapping("/{id}/CommentMessageSave/{member}")
	public String saveCommentMessage(@PathVariable("id") Integer id, @PathVariable("member") Member member,
			@ModelAttribute("commentMessage") CommentMessage commentMessage, Model model) {
		Comment comment = commentService.findById(id).get();
		commentMessage.setComment(comment);
//		Member member = memberService.getMember(rid).get();
		commentMessage.setMember(member);
		commentMessageService.saveOrUpdate(commentMessage);
		return "redirect:/dashboard/comments";
	}

	// 刪除評論
	@GetMapping(value = "/CommentDelete")
	public String deleteComment(@RequestParam("id") Integer id) {
		commentService.deleteById(id);
		return "redirect:./comments";
	}

	// 刪除留言
	@GetMapping(value = "/CommentMessageDelete")
	public String deleteCommentMessage(@RequestParam("id") Integer id) {
		commentMessageService.deleteByMessageId(id);
		return "redirect:./comments";
	}

	// 送出評價的空白表單
	@RequestMapping("/CommentNew")
	public String showCommentForm(@ModelAttribute("comment") Comment comment) {
		return "comment_dashboard/commentform";
	}

	// 送出留言的表單
	@RequestMapping("/CommentDetail/{id}/CommentMessageNew")
	public String showCommentMessageForm(@ModelAttribute("commentMessage") CommentMessage commentMessage,
			@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
//		Member member = memberService.getMember(1).get();
//		model.addAttribute("member", member);
		model.addAttribute("commentMessage", commentMessage);
		List<CommentMessage> messages = commentMessageService.findByCommentCommentId(id);
		if (messages.size() != 0) {
			CommentMessage maxMessageId = messages.stream().max(Comparator.comparing(CommentMessage::getMessageOrder))
					.get();
			commentMessage.setMessageOrder((maxMessageId.getMessageOrder()) + 1);
		} else {
			commentMessage.setMessageOrder(1);
		}
		return "comment_dashboard/commentmessageform";
	}

	// 送出新增評價的空白表單
	@RequestMapping("/CommentEdit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		return "comment_dashboard/commentform";
	}

	// 送出評價的詳細資料
	@RequestMapping("/CommentDetail/{id}")
	public String showDetailForm(@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		return "comment_dashboard/commentdetail";
	}

	// 送出留言的詳細資料
	@RequestMapping("/Comment/{cid}/Message/{mid}")
	public String showMessageDetailForm(@PathVariable("cid") Integer cid, @PathVariable("mid") Integer mid,
			Model model) {
		Comment comment = commentService.findById(cid).get();
		CommentMessage commentMessage = commentMessageService.findById(mid).get();
		Member member = commentMessage.getMember();
		model.addAttribute("comment", comment);
		model.addAttribute("member", member);
		model.addAttribute("commentMessage", commentMessage);
		commentMessage.setComment(comment);
		commentMessage.setMember(member);
		commentMessageService.saveOrUpdate(commentMessage);
		return "comment_dashboard/commentmessageform";
	}

	@GetMapping("/comments/jobtypejson")
	@ResponseBody
	public int[] getJobTypeJson() {
		int[] jobtype = { 0, 0, 0, 0 };
		System.out.println("OK");
		List<Comment> allComments = commentService.findAll();
		System.out.println(allComments);
		for (Comment comment : allComments) {
			System.out.println(comment);

			if (comment.getJobDescription().equals("全職")) {
				jobtype[0]++;
			} else if (comment.getJobDescription().equals("兼職")) {
				jobtype[1]++;
			} else if (comment.getJobDescription().equals("工讀")) {
				jobtype[2]++;
			} else if (comment.getJobDescription().equals("實習")) {
				jobtype[3]++;
			}
		}
		System.out.println("OK");
		return jobtype;
	}

	@GetMapping("/comments/commenttimejson")
	@ResponseBody
	public int[] getCommentTimeJson() {
		int[] monthlist = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		List<Comment> allComments = commentService.findAll();
		for (Comment comment : allComments) {
			
			int month = comment.getCreateTime().getMonth();
			switch (month) {
			case 1:
				monthlist[0]++;
				break;
			case 2:
				monthlist[1]++;
				break;
			case 3:
				monthlist[2]++;
				break;
			case 4:
				monthlist[3]++;
				break;
			case 5:
				monthlist[4]++;
				break;
			case 6:
				monthlist[5]++;
				break;
			case 7:
				monthlist[6]++;
				break;
			case 8:
				monthlist[7]++;
				break;
			case 9:
				monthlist[8]++;
				break;
			case 10:
				monthlist[9]++;
				break;
			case 11:
				monthlist[10]++;
				break;
			case 12:
				monthlist[11]++;
				break;
			}

		}return monthlist;

	}

}