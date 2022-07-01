package com.theone.springboot.controller;

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
import com.theone.springboot.service.CommentService;



@Controller
@RequestMapping("/dashboard")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	// 所有評論list
	@RequestMapping("/CommentsManager")
	public String listComments(Model model) {
		model.addAttribute("listComment", commentService.findAll());
		return "comment_dashboard/CommentDashBoard";
	}

	// 新增評論
	@PostMapping("/CommentInsert")
	public String addComment(@ModelAttribute("comment") Comment comment, Model model) {
		commentService.saveOrUpdate(comment);
		return "redirect:./CommentsManager";
	}

	// 刪除評論
	@GetMapping(value = "/CommentDelete")
	public String deleteComment(@RequestParam("id") Integer id) {
		commentService.deleteById(id);
		return "redirect:./CommentsManager";
	}
	//更新評論
	@RequestMapping("/CommentUpdate/{id}")
	public String updateComment(@PathVariable("id") Integer id, @ModelAttribute("comment") Comment comment, Model model) {
		comment.setShare_id(id);
		commentService.saveOrUpdate(comment);
		return "redirect:/dashboard/CommentsManager";
	}

	// 送出新增評價的空白表單
	@RequestMapping("/CommentNew")
	public String showCommentForm(@ModelAttribute("comment") Comment comment) {
		return "comment_dashboard/CommentForm";
	}
	
	// 送出新增評價的空白表單
	@RequestMapping("/CommentEdit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		return "comment_dashboard/CommentForm";
	}
	
	// 送出評價的詳細資料
	@RequestMapping("/CommentDetail/{id}")
	public String showDetailForm(@PathVariable("id") Integer id, Model model) {
		Comment comment = commentService.findById(id).get();
		model.addAttribute("comment", comment);
		return "comment_dashboard/CommentConfirm";
	}

	// 確認新增評論的資料
	@RequestMapping("/CommentConfirm")
	public String showConfirmForm(@ModelAttribute("comment") Comment comment) {
		return "comment_dashboard/CommentConfirm";
	}

}