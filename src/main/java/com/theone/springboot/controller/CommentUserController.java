package com.theone.springboot.controller;

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
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CommentService;

@Controller
@RequestMapping({"/"})
public class CommentUserController {

	@Autowired
	CommentService commentService;

	// 所有評論list
	@RequestMapping("/comments")
	public String listComments(Model model) {
		model.addAttribute("listComment", commentService.findAll());
		return "comment/commentlist";
	}
	
	// 個人評論list
	@RequestMapping("/user/comments")
	public String listMyComments(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("loginMember");
		List<Comment> myComments = commentService.findByUserId(member.getUserid());
		model.addAttribute("listComment", myComments);
		return "comment/commentlist";
	}
	
	// list分析
		@RequestMapping("/comments/analysis")
		public String listAnalysisComments(Model model) {
			model.addAttribute("listComment", commentService.findAll());
			return "comment/commentanalysis";
		}

	// 新增評論
	@PostMapping("/CommentInsert")
	public String addComment(@ModelAttribute("comment") Comment comment, Model model) {
		commentService.saveOrUpdate(comment);
		return "redirect:./comments";
	}

	// 刪除評論
	@GetMapping(value = "/CommentDelete")
	public String deleteComment(@RequestParam("id") Integer id) {
		commentService.deleteById(id);
		return "redirect:/comments";
	}

	// 更新評論
	@RequestMapping("/CommentUpdate")
	public String updateComment(@ModelAttribute("comment") Comment comment) {
		commentService.saveOrUpdate(comment);
		return "redirect:/comments";
	}

	// 送出新增評價的空白表單
	@RequestMapping("/CommentNew")
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
	public String showDetailForm(@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		return "comment/commentdetail";
	}
	
	//討論區分頁
	@RequestMapping("/forum")
	public String showForum() {
		return "interview/page";
	}
}
