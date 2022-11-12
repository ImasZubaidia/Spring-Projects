package com.imas.axsos.controllers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class HomeController {
	public ArrayList<String> activities=new ArrayList<String>();
	@RequestMapping("/Gold")
	public String gold(Model model,HttpSession session) {
		if(session.isNew()) {
			session.setAttribute("gold", 0);
		}
		model.addAttribute("activities",activities);
		return "index.jsp";
	}
	@RequestMapping("/process_money")
	public String money(@RequestParam(value="add")String button, HttpSession session) {
		String format = "EEEEE dd yyyy KK:mm aa";
		SimpleDateFormat dateFormat= new SimpleDateFormat(format);
		String timeStamp = dateFormat.format(new Date());
		Random rand = new Random();
		if(button.equals("farm")) {
			int money=rand.nextInt(10)+10;
			int gold =(int) session.getAttribute("gold");
			gold=gold+money;
			activities.add("<p class=gain>You entered a farm and earned a"+money+" gold ("+timeStamp+")</p>");
		}
		if(button.equals("cave")) {
			int money=rand.nextInt(5)+5;
			int gold =(int) session.getAttribute("gold");
			gold=gold+money;
			activities.add("<p class=gain>You entered a cave and earned a"+money+" gold ("+timeStamp+")</p>");
		}
		if(button.equals("house")) {
			int money=rand.nextInt(3)+2;
			int gold =(int) session.getAttribute("gold");
			gold=gold+money;
			activities.add("<p class=gain>You entered a house and earned a"+money+" gold ("+timeStamp+")</p>");
		}
		if (button.equals("casino")) {
			int money=rand.nextInt(50);
			int gold =(int) session.getAttribute("gold");
			if (rand.nextInt(2)==1) {
				money=-money;
				gold+=money;
				int s=-(money);
				activities.add("<p class=loss>You entered a house and lost a"+s+" gold ("+timeStamp+")</p>");
			}else {
				gold+=money;
				activities.add("<p class=gain>You entered a house and earned a"+money+" gold ("+timeStamp+")</p>");
			}
		}
		return "redirect:/Gold";
	}
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.invalidate();
		activities.removeAll(activities);
		return "redirect:/Gold";
	}

}