package com.theone.springboot.controller;

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
import com.theone.springboot.service.CommentMessageService;
import com.theone.springboot.service.CommentService;

@Controller
@RequestMapping("/dashboard")
public class CommentDashBoardController {

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

	// 新增評論
	@PostMapping("/CommentInsert")
	public String addComment(@ModelAttribute("comment") Comment comment, Model model) {
		commentService.saveOrUpdate(comment);
		return "redirect:./comments";
	}
	
	// 新增留言
	@PostMapping("/{id}/CommentMessageInsert")
	public String addCommentMessage(
			@PathVariable("id") Integer id,
			@ModelAttribute("commentMessage") CommentMessage commentMessage, 
			Model model) {
		Comment comment = commentService.findById(id).get();
		commentMessage.setComment(comment);
		commentMessageService.saveOrUpdate(commentMessage);
		return "redirect:/dashboard/comments";
	}
	

	// 刪除評論
	@GetMapping(value = "/CommentDelete")
	public String deleteComment(@RequestParam("id") Integer id) {
		commentService.deleteById(id);
		return "redirect:./comments";
	}

	// 更新評論
	@RequestMapping("/CommentUpdate")
	public String updateComment(@ModelAttribute("comment") Comment comment) {
		commentService.saveOrUpdate(comment);
		return "redirect:/dashboard/comments";
	}

	// 送出新增評價的空白表單
	@RequestMapping("/CommentNew")
	public String showCommentForm(@ModelAttribute("comment") Comment comment) {
		return "comment_dashboard/commentform";
	}

	// 送出新增留言的空白表單
	@RequestMapping("/CommentDetail/{id}/CommentMessageNew")
	public String showCommentMessageForm(
			@ModelAttribute("commentMessage") CommentMessage commentMessage,
			@PathVariable("id") Integer id,
			Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		model.addAttribute("commentMessage", commentMessage);
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

	@GetMapping("/comments/jobtypejson")
	@ResponseBody
	public int[] getJobTypeJson() {
		int[] jobtype = { 0, 0, 0, 0 };
		System.out.println("OK");
		List<Comment> allComments = commentService.findAll();
		System.out.println(allComments);
		for (Comment comment : allComments) {
			System.out.println(comment);

			if (comment.getJob_description().equals("全職")) {
				jobtype[0]++;
			} else if (comment.getJob_description().equals("兼職")) {
				jobtype[1]++;
			} else if (comment.getJob_description().equals("工讀")) {
				jobtype[2]++;
			} else if (comment.getJob_description().equals("實習")) {
				jobtype[3]++;
			}
		}
		System.out.println("OK");
		return jobtype;
	}

}