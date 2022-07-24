package com.theone.springboot.controller;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping({ "/" })
public class CommentUserController {

	@Autowired
	MemberService memberService;

	@Autowired
	CommentService commentService;

	@Autowired
	CommentMessageService commentMessageService;

	// 所有評論list
	@RequestMapping("/comments")
	public String listComments(Model model) {
		List<Comment> listcomment = commentService.findAllByOrderByCommentIdDesc();// 倒敘
		model.addAttribute("listComment", listcomment);
		model.addAttribute("commentMessageService", commentMessageService);

		return "comment/commentlist";
	}

	// 個人評論list
	@RequestMapping("/user/comments")
	public String listMyComments(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("loginMember");
		List<Comment> myComments = commentService.findByCommentMemberIdNumber(member.getIdNumber());
		model.addAttribute("listComment", myComments);
		model.addAttribute("commentMessageService", commentMessageService);
		return "comment/commentlist";
	}

	// list分析
	@RequestMapping("/comments/analysis")
	public String listAnalysisComments(Model model) {
		model.addAttribute("listComment", commentService.findAll());
		return "comment/commentanalysis";
	}

	// 儲存評論
	@PostMapping("/CommentSave")
	public String saveComment(HttpSession session, @ModelAttribute("comment") Comment comment) {
		Member member = (Member) session.getAttribute("loginMember");
		comment.setCommentMember(member);
		commentService.saveOrUpdate(comment);
		return "redirect:./comments";
	}

	// 刪除評論
	@GetMapping(value = "/CommentDelete")
	public String deleteComment(@RequestParam("id") Integer id) {
		commentService.deleteById(id);
		return "redirect:/comments";
	}

	// 送出新增評價的空白表單
	@RequestMapping("/user/comment/new")
	public String showCommentForm(@ModelAttribute("comment") Comment comment) {
		return "comment/commentform";
	}

	// 送出新增評價的空白表單
	@RequestMapping("/CommentEdit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		return "comment/commentform";
	}

	// 送出評價的詳細資料
	@RequestMapping("/CommentDetail/{id}")
	public String showDetailForm(@PathVariable("id") Integer id,
			@ModelAttribute("commentMessage") CommentMessage commentMessage, 
			HttpSession session, 
			Model model) {

		// show comment detail
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		// get reply comment list
		List<CommentMessage> messages = commentMessageService
				.findByCommentCommentIdAndMessageReplyOrderByMessageIdDesc(id, 0);
		List<CommentMessage> totelmessage = commentMessageService.findByCommentCommentId(id);
		model.addAttribute("messages", messages);
		model.addAttribute("totelmessage", totelmessage);
		
		// get message count
		model.addAttribute("commentMessage", commentMessage);

		if (messages.size() != 0) {
			CommentMessage maxMessageId = messages.stream().max(Comparator.comparing(CommentMessage::getMessageOrder))
					.get();
			commentMessage.setMessageOrder((maxMessageId.getMessageOrder()) + 1);
		} else {
			commentMessage.setMessageOrder(1);
		}

		return "comment/commentdetail";
	}

	// 儲存留言
	@PostMapping("/user/{id}/CommentMessageSave")
	public String saveCommentMessage(@PathVariable("id") Integer id,
			@ModelAttribute("commentMessage") CommentMessage commentMessage,
			@ModelAttribute("message") CommentMessage message, 
			HttpSession session, 
			HttpServletRequest request,
			Model model) {
		String referer = request.getHeader("Referer");
		Member member = (Member) session.getAttribute("loginMember");
		Comment comment = commentService.findById(id).get();
		commentMessage.setComment(comment);
		commentMessage.setMember(member);
		commentMessageService.saveOrUpdate(commentMessage);
		return "redirect:"+ referer;
	}

	// 儲存留言
	@PostMapping("/{cid}/{mid}/CommentMessageUpdate")
	public String saveCommentMessage(@PathVariable("cid") Integer cid, 
			@PathVariable("mid") Integer mid,
			@ModelAttribute("message") CommentMessage message, 
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		String referer = request.getHeader("Referer");
		Member member = (Member) session.getAttribute("loginMember");
		Comment comment = commentService.findById(cid).get();
		message.setComment(comment);
		message.setMessageId(mid);
		message.setMember(member);
		commentMessageService.saveOrUpdate(message);
		return "redirect:"+ referer;
	}

	// 刪除留言
	@GetMapping(value = "/CommentMessageDelete")
	public String deleteCommentMessage(@RequestParam("id") Integer id, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		commentMessageService.deleteByMessageId(id);
		return "redirect:"+ referer;
	}

	// 評論查詢
	@RequestMapping("/comments/search")
	private String jobSearch(String title, String searchType, Model model) {

		if (searchType.contains("compName")) {
			List<Comment> commentByComp = commentService.findByCompNameLike("%" + title + "%");
			model.addAttribute("listComment", commentByComp);

		} else if (searchType.equals("jobName")) {
			List<Comment> commentByJob = commentService.findByJobNameLike("%" + title + "%");
			model.addAttribute("listComment", commentByJob);
		} else if (searchType.equals("jobDescription")) {
			List<Comment> commentByJobDescription = commentService.findByJobDescriptionLike("%" + title + "%");
			model.addAttribute("listComment", commentByJobDescription);
		}

		model.addAttribute("commentMessageService", commentMessageService);

		return "comment/commentlist";
	}

	@GetMapping("/comments/jobtypejson")
	@ResponseBody
	public int[] getJobTypeJson() {
		int[] jobtype = { 0, 0, 0, 0 };
		List<Comment> allComments = commentService.findAll();
		for (Comment comment : allComments) {
//			System.out.println(comment);

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
		return jobtype;
	}
	
	// 討論區分頁
	@RequestMapping("/forum")
	public String showForum() {
		return "interview/page";
	}
}
