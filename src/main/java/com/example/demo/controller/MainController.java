package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.HocSinh;
import com.example.demo.domain.HocSinhRequest;
import com.example.demo.repository.HocSinhRepository;
import com.example.demo.service.HocSinhService;
import com.example.demo.util.CsvUtil;
import com.example.demo.util.PdfReport;

@Controller
public class MainController {
	
	@Autowired
	private HocSinhService hocSinhService;
	
	@Autowired
	private HocSinhRepository hocSinhRepository;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	@GetMapping("/user")
    public String user(Model model) {
		List<HocSinh> dsHocSinh = (List<HocSinh>) hocSinhRepository.findAll();
		model.addAttribute("users", dsHocSinh);
		model.addAttribute("hocSinhRequest", new HocSinhRequest());
        return "user";
    }
	
	@GetMapping("/admin") 
    public String admin() {
        return "admin";
    }
	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping(value = "/upload-csv-file", consumes = "multipart/form-data")
	public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
		try {
			hocSinhRepository.saveAll(CsvUtil.read(HocSinh.class, file.getInputStream()));
		} catch (IOException e) {
			System.out.println("lỗi: "+e.getMessage());
		}
		
		return "redirect:/admin";
	}
	
	@PostMapping(value = "/upload-csv-file", consumes = "text/csv")
    public String uploadSimple(@RequestBody InputStream body) {
		try {
			hocSinhRepository.saveAll(CsvUtil.read(HocSinh.class, body));
		} catch (IOException e) {
			System.out.println("lỗi: "+e.getMessage());
		}
		return "redirect:/admin";
    }
	
	@GetMapping(value = "/delete")
	public String xoaHocSinh(@RequestParam("id") Long hocsinhId, Model model) {  
	    hocSinhService.xoaHocSinh(hocsinhId); 
	    return "redirect:/user";  
	}  
	@GetMapping(value = "/edit")
	public String editHocSinh(@RequestParam("id") Long hocsinhId, Model model) {  
	    Optional<HocSinh> hocSinhEdit = hocSinhService.layHocSinh(hocsinhId); 
	    hocSinhEdit.ifPresent(hocSinh -> model.addAttribute("hocSinh", hocSinh));  
	    return "suaHocSinh";  
	}
	
	@PostMapping(value="/save")
	public String save(HocSinh hocSinh) {  
	    hocSinhService.luuHocSinh(hocSinh);
	    return "redirect:/user";  
	}  
	
	@PostMapping(value="/search")
	public String searchHocSinh(@ModelAttribute("hocSinhRequest") HocSinhRequest hocSinhRequest, Model model) {  
	    List<HocSinh> listHocSinh = hocSinhService.layDanhSachHocSinhTheoTenVaTuoi(hocSinhRequest.getTen(),hocSinhRequest.getTuoi());
	    if(!listHocSinh.isEmpty()) {
	    	model.addAttribute("users", listHocSinh);
	    }else {
	    	model.addAttribute("users", (List<HocSinh>) hocSinhRepository.findAll());
	    }
	    return "user";  
	}  
	
	@GetMapping(value = "/download-pdf-file")
	public ModelAndView xuatPdfFile(HttpServletRequest request, HttpServletResponse response) {
		String typeReport = request.getParameter("type");
		List<HocSinh> dsHocSinh = (List<HocSinh>) hocSinhRepository.findAll();
		if(typeReport != null && typeReport.equals("pdf")) {
			return new ModelAndView(new PdfReport(),"dsHocSinh",dsHocSinh);
		}
		return null;
	}
	
}
