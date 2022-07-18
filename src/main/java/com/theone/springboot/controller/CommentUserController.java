package com.theone.springboot.controller;

import java.util.Comparator;
import java.util.List;

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

import com.theone.springboot.entity.Comment;
import com.theone.springboot.entity.CommentMessage;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CommentMessageService;
import com.theone.springboot.service.CommentService;

@Controller
@RequestMapping({ "/" })
public class CommentUserController {

	@Autowired
	CommentService commentService;

	@Autowired
	CommentMessageService commentMessageService;

	// 所有評論list
	@RequestMapping("/comments")
	public String listComments(Model model) {
		List<Comment> listcomment = commentService.findAll();
		model.addAttribute("listComment", listcomment);
		model.addAttribute("commentMessageService", commentMessageService);
		return "comment/commentlist";
	}

	// 個人評論list
	@RequestMapping("/user/comments")
	public String listMyComments(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("loginMember");
		List<Comment> myComments = commentService.findByMemberIdNumber(null);
//		List<Comment> myComments = commentService.findByUserId(member.getUserid());
		model.addAttribute("listComment", myComments);
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
	public String saveComment(@ModelAttribute("comment") Comment comment) {
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
	@RequestMapping("/comment/new")
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
	public String showDetailForm(
			@PathVariable("id") Integer id,
			@ModelAttribute("commentMessage") CommentMessage commentMessage,
			HttpSession httpSession,
			Model model) {

		// show comment detail
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		// get reply list
		List<CommentMessage> messages = commentMessageService.findByCommentCommentId(id);
		model.addAttribute("messages", messages);
		// get message count
		model.addAttribute("commentMessage", commentMessage);

		if (messages.size() != 0) {
			CommentMessage maxMessageId = messages.stream().max(Comparator.comparing(CommentMessage::getMessageOrder))
					.get();
			commentMessage.setMessageOrder((maxMessageId.getMessageOrder()) + 1);
		}else {
			commentMessage.setMessageOrder(1);
		}
//		
//		Object editMessage = httpSession.getAttribute("sessionMessage");
//		model.addAttribute("editMessage", editMessage);
		
//		model.addAttribute("editMessage",commentMessageService.findById(mid));

		return "comment/commentdetail";
	}

	// 儲存留言
	@PostMapping("/{id}/CommentMessageSave")
	public String saveCommentMessage(@PathVariable("id") Integer id,
			@ModelAttribute("commentMessage") CommentMessage commentMessage,
			@ModelAttribute("message") CommentMessage message,
			Model model) {
		Comment comment = commentService.findById(id).get();
		commentMessage.setComment(comment);
		commentMessageService.saveOrUpdate(commentMessage);
		return "redirect:/comments";
	}
	
	// 儲存留言
		@PostMapping("/{cid}/{mid}/CommentMessageUpdate")
		public String saveCommentMessage(
				@PathVariable("cid") Integer cid,
				@PathVariable("mid") Integer mid,
				@ModelAttribute("message") CommentMessage message,
				Model model) {
			Comment comment = commentService.findById(cid).get();
			message.setComment(comment);
			message.setMessageId(mid);
			commentMessageService.saveOrUpdate(message);
			return "redirect:/comments";
		}

	// 刪除留言
	@GetMapping(value = "/CommentMessageDelete")
	public String deleteCommentMessage(@RequestParam("id") Integer id) {
		commentMessageService.deleteByMessageId(id);
		return "redirect:comments";
	}

	// 討論區分頁
	@RequestMapping("/forum")
	public String showForum() {
		return "interview/page";
	}
}
