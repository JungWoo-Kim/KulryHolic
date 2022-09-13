package com.example.demo.kulry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
	MainService service; 
	
	@RequestMapping(value = "/main")
	public String main(MainDto param, Model model, HttpSession session) throws IOException {
		// 친황경 목록
		Map<String, Object> main1 = service.getSearch("name", "친환경", "match");
		model.addAttribute("ecoList", main1);
		
		String tab = (String) session.getAttribute("tab");
		
		if(param.getTab() != null) {
			tab = param.getTab();
			session.setAttribute("tab", tab);
		}

		Map<String, Object> main2 = service.getTabList("http://172.31.7.97:5000/item_rcmd/"+(String) session.getAttribute("userId")+"/"+tab);
		model.addAttribute("suggestionList", main2);
		
		if(!tab.equals("3")) {
			Map<String, Object> main3 = service.getmeaningList("http://172.31.7.97:5000/meaning_rcmd");
			model.addAttribute("meaningList", main3);
		}else {
			model.addAttribute("meaningList", "");
		}

		Map<String, Object> main4 = service.getcateList("http://172.31.7.97:5000/category_rcmd/"+(String) session.getAttribute("userId")+"/"+tab);
		model.addAttribute("categoryList", main4);
		
		Map<String, Object> main5 = service.realTimeBest(tab);
		model.addAttribute("realTimeBestList", main5);
		
		
		model.addAttribute("tabCode", tab);
		
		return "direngine";
	}

	@RequestMapping(value = "/category")
	public String category(MainDto param, Model model) throws IOException {
		Map<String, Object> main = new HashMap<String, Object>();
		main = service.getSearch(param.getCate(), param.getKeyword(), param.getType());
		model.addAttribute("list", main);
		model.addAttribute("categoryNm", param.keyword);
		
		return "category";
	}

	@RequestMapping(value = "/detail")
	public String detail(MainDto param, Model model) throws IOException {
		Map<String, Object> main1 = service.getSearch(param.getCate(), param.getKeyword(), param.getType());
		List<Object> list = (List<Object>) main1.get("hits");
		Map<String, Object> hits = (Map<String, Object>) list.get(0);
		Map<String, Object> source = (Map<String, Object>) hits.get("_source");
		model.addAttribute("info", source);
		
		return "detail";
	}
	
	@RequestMapping(value = "/mypage")
	public String mypage(MainDto param, Model model, HttpSession session) throws IOException {
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> jjinList = service.termSearch("jjim", "user_id", userId);
		model.addAttribute("jjinList", jjinList);
		
		Map<String, Object> buyList = service.termSearch("order", "user_id", userId);
		model.addAttribute("buyList", buyList);
		
		return "mypage";
	}

	@RequestMapping(value = "/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		
		return "login";
	}
	
	@RequestMapping(value = "/member/login")
	public @ResponseBody Map<String, Object> memberLogin(MainDto param, HttpSession session) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		if(param.getId().equals(param.getPassword())) {
			Map<String, Object> main1 = service.userLogin(param.getId(), param.getPassword());
			
			if(main1.get("max_score") != null && main1.get("max_score") != "") {
				List<Object> list = (List<Object>) main1.get("hits");
				Map<String, Object> hits = (Map<String, Object>) list.get(0);
				Map<String, Object> source = (Map<String, Object>) hits.get("_source");

				session.setAttribute("userId", source.get("user_id"));
				session.setAttribute("tab", "1");
				
				result.put("code", "0000");
				result.put("message", "success");
			} else {
				result.put("code", "9999");
				result.put("message", "아이디 또는 패스워드가 잘못되었습니다.");
			}
		} else {
			result.put("code", "9999");
			result.put("message", "아이디 또는 패스워드가 잘못되었습니다.");
		}
		
		return result;
	}

	@RequestMapping(value = "/test")
	public String direngine() {
		return "main";
	}
	
}
