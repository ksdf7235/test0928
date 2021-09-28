package org.fintech.controller;

import java.util.ArrayList;

import org.fintech.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

//이 클래스가 Controller 기능을 한다는 어노테이션!!
@Controller
//실행될 url을 선언
@RequestMapping("/sample/*")
//콘솔창에 로그 출력
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic(){
		log.info("basic......");
	}
	
	@RequestMapping(
			value="/basic",
			method= {RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic get방식 실행");
	}
	
	@RequestMapping(
			value="/studentInfo",
			method= {RequestMethod.GET,RequestMethod.POST})
	public void studentGet() {
		log.info("이름:홍길동");
		log.info("나이:23세");
		log.info("주소:부산시");
	}
	
	//전송방식이 get일 경우 처리
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("ex01()" + dto);
		
		return "ex01";
	}
	
	//@RequestParam?
	//
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,
			           @RequestParam("age") int age) {
		log.info("이름:" + name);
		log.info("나이:" + age);
		
		return "ex02";
	}
	
	//@ModelAttribute ?
	//URL에서 매개변수로 전달된 값을 Model 객체에 담아서 JSP로 전달하는
	//어노테이션
	@GetMapping("/ex03")
	public String ex03(SampleDTO dto,
			           @ModelAttribute("page") int page) {
		log.info("dto:" + dto);
		log.info("페이지번호:" + page);
		
		return "/sample/ex03";
	}
	
	//@ResponseBody ?
	//자바 객체를 http Response Body로 전송
	@GetMapping("/ex04")
	public @ResponseBody SampleDTO ex04() {
		log.info("ex04 실행");
		SampleDTO dto = new SampleDTO();
		
		dto.setAge(30);
		dto.setName("이순신");
		
		return dto;
		
	}
	
	//파일 업로드 테스트 2021.09.13
	//jsp 폼 화면을 실행하려면 전송방식이 get방식이므로
	//어노테이션 @GetMapping 사용
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload()");
	}
	
	//파일업로드 버튼 클릭시 url 처리
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		//반복처리를 하는 람다식 표현
		files.forEach(file -> {
			log.info("------------------------------");
			log.info("업로드파일이름:" + file.getOriginalFilename());
			log.info("업로드파일크기:" + file.getSize());
		});
		
	}
}





