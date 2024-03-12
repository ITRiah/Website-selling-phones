package com.example.Project3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Project3.dto.ProductDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.ProductService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Value("${upload.folder}")
	private String uploadFolder;
	
	@GetMapping("/")
	public ResponseDTO<List<ProductDTO>> getAll() {
		return ResponseDTO.<List<ProductDTO>>builder()
					.status(200)
					.data(productService.getAll())
					.msg("ok")
					.build();
	}
	
	
	@GetMapping("/get-by-id")
	public ResponseDTO<ProductDTO> getById(@RequestParam("id") int id) {
		
		return ResponseDTO.<ProductDTO>builder()
				.status(200)
				.data(productService.getById(id))
				.msg("ok")
				.build();
	}
	
    @PostMapping("/upload")
    public ResponseDTO<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") int id ) throws IllegalStateException, IOException {
	    	if(!(new File(uploadFolder).exists())) {
				new File(uploadFolder).mkdirs();
			}
    	
    		String fileName = file.getOriginalFilename();
            String filePath = fileName;
            file.transferTo(new File( uploadFolder + filePath));
                        
            productService.updateImage(id, filePath);

            return ResponseDTO.<String>builder()
            		.data(filePath)
	                .status(200)
	                .msg("ok")
	                .build();
    }
    
	
	@GetMapping("/download")
	public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
		File file = new File(uploadFolder + fileName);
		Files.copy(file.toPath(), response.getOutputStream());// lấy dữ liệu từ file để tải về hình ảnh cho web
	}
    
    
	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody @Valid ProductDTO productDTO) throws IllegalStateException, IOException {
//		String fileName = productDTO.getFile().getOriginalFilename();
		
//		if(!productDTO.getFile().isEmpty()) {
//			File file = new File("E:/" + fileName);
//			productDTO.getFile().transferTo(file);
//		}
//		
//		productDTO.setImage(fileName);
		
		productService.create(productDTO);
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}

	@PutMapping("/")
	public ResponseDTO<Void> update(@ModelAttribute ProductDTO productDTO) {
		productService.update(productDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
		
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id) {
		productService.delete(id);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}	
}
