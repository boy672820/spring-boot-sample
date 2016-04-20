package com.example.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Hello;
import com.example.service.HelloRepository;

@Controller
public class HomeController {

	@Autowired
	private HelloRepository helloRepository;

	@RequestMapping( { "/", "/index" } )
	public String index( Model model ) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ssSSS" );

		model.addAttribute( "current_time", dateFormat.format(calendar.getTime() ) );
		return "home/index";
	}

	@RequestMapping( "/form" )
	public String formPage() {
		return "home/form";
	}

	@RequestMapping( "/form/{id}" )
	public String formPage( @PathVariable int id, Model model ) {
		Hello helloEntity = helloRepository.findOne( id );

		model.addAttribute( "helloEntity", helloEntity );
		return "home/form";
	}

	@RequestMapping( value = "/add", method = RequestMethod.POST )
	public String add( Hello hello ) {
		helloRepository.save( hello );
		return "redirect:/list";
	}

	@RequestMapping( "/list" )
	@ResponseBody
	public List<Hello> list( Model model ) {
		return helloRepository.findAll();
	}

	@RequestMapping( "/post/{id}" )
	public String postPage( Model model, @PathVariable int id ) {
		model.addAttribute( "post", helloRepository.findOne( id ) );
		return "home/post";
	}

	@RequestMapping( "/delete/{id}" )
	public String delete( @PathVariable int id ) {
		helloRepository.delete( id );
		return "redirect:/list";
	}

	@RequestMapping( value = "/update", method = RequestMethod.POST )
	public String update( Hello hello ) {
		Hello helloEntity = helloRepository.findOne( hello.id );

		helloEntity.setName( hello.name );

		helloRepository.save( helloEntity );
		return "redirect:/list";
	}

}
