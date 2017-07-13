package com.project.Controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.DAO.MongoDbDao;

@Controller
public class HomeController {

	private  static MongoDbDao mgdb=MongoDbDao.getMongoDbDao();
	private String user;
	private HttpSession hs;
	private Map<String, String> mapper;
   private boolean flag=false;
	@RequestMapping(value ={"/","login"},method={RequestMethod.GET,RequestMethod.POST})
	public String welcome(Model m, HttpServletRequest req) {

		hs=req.getSession();
		String view="login";
		String s = "Welcome!";
		m.addAttribute("msg", s);
		if(flag!=false){
			String user=(String) hs.getAttribute("username");
			view="userWelcome";
			m.addAttribute("message","Welcome!");
			m.addAttribute("user", user);
			flag=false;
		}
		return view;

	}

	@RequestMapping(value="userWelcome")
	public String welcomeUser(Model m1,HttpServletRequest req,HttpServletResponse resp){
		

		mapper= mgdb.dbreturn();
		String view="";
		String message="Welcome!";
	    hs=req.getSession();
		user=req.getParameter("UserName");
	    hs.setAttribute("username", user);
	    if(flag==true){
	    	view=new String("userWelcome");
	    	
	    }
	    else if(flag!=true){
		if(mapper.get("Username").equals(req.getParameter("UserName")) && mapper.get("Password").equals(req.getParameter("Password")))
		    {
		      
			  view=new String("userWelcome");
			  m1.addAttribute("message",message);
			  m1.addAttribute("user",user);
			  hs.setAttribute("username",user);
			  
			  
		     }
		    else
		    {
		      m1.addAttribute("errormessage","Invalid Login");
		     view=new String("login");
		     
		   
	    	}
	    
	    }
		
		return view;
	}

	@RequestMapping(value = "Aboutus")
	public String aboutus(Model m2) {
		m2.addAttribute("user", user);
		return "Aboutus";
	}

	@RequestMapping(value = "Contactus")
	public String contactus(Model m3) {
		m3.addAttribute("user", user);
		return "Contactus";
	}
}
