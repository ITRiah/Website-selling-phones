package com.example.Project3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Project3.dto.ProductDetailsDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.ProductDetailsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin/product-details")
public class ProductDetailsController {
	@Autowired
	ProductDetailsService productDetailsService;
	
	
	@GetMapping("/")
	public ResponseDTO<List<ProductDetailsDTO>> getByProductId(@RequestParam("id") int id) {
		
		return ResponseDTO.<List<ProductDetailsDTO>>builder()
					.status(200)
					.data(productDetailsService.getByProductId(id))
					.msg("ok")
					.build();
	}
	
	
	
    @PostMapping("/")
    public ResponseDTO<Void> create(@ModelAttribute ProductDetailsDTO pd ) throws IllegalStateException, IOException {
        	MultipartFile file = pd.getFile();
            String fileName = file.getOriginalFilename();
          
                        
            if (fileName != null) {
            	  String filePath = "E:/" + fileName;
                  file.transferTo(new File(filePath));
          		pd.setImages(filePath);

    		}

    		
    		System.out.println(pd.getImages());
    		productDetailsService.create(pd);

    		return ResponseDTO.<Void>builder()
    				.status(200)
    				.msg("done")
    				.build();
    }
    
	
	@GetMapping("/download")
	public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
		File file = new File("E:/" + fileName);
		Files.copy(file.toPath(), response.getOutputStream());// lấy dữ liệu từ file để tải về hình ảnh cho web
	}
}
