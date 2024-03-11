package com.example.Project3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.dto.SearchDTO;
import com.example.Project3.dto.UserDTO;
import com.example.Project3.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired // Tìm và gán đối tượng đã được tạo trước nếu không nó là null
	UserService service;
	
	@PostMapping("/upload")
    public ResponseDTO<String> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") int id ) throws IllegalStateException, IOException {
            String fileName = file.getOriginalFilename();
            String filePath = "E:/" + fileName;
            file.transferTo(new File(filePath));
                        
            service.updateImage(id, filePath);

            return ResponseDTO.<String>builder()
            		.data(filePath)
	                .status(200)
	                .msg("ok")
	                .build();
    }
	
	@PostMapping("/")
	public ResponseDTO<Void> newUser(@ModelAttribute @Valid UserDTO u){
	
		service.creat(u);

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

	@GetMapping("/list")
	public ResponseDTO<Page<UserDTO>> getList(@ModelAttribute SearchDTO abc) {
//		-Với ModelAttribute thì khi đặt tên , tên đó sẽ được sử dựng trong trang html
//		-Khi không đặt tên thì tên sử dụng trong trang sẽ là tên của đối tượng (không phải tên class)
		Page<UserDTO> page = service.getAll(abc);
		return ResponseDTO.<Page<UserDTO>>builder()
				.status(200)
				.msg("done")
				.data(page)
				.build();
	}
	
	@GetMapping("/get-by-id")
	public ResponseDTO<UserDTO> getById(@RequestParam("id") int id) {
		UserDTO userDTO = service.getByID(id);
		
		return  ResponseDTO.<UserDTO>builder().status(200).data(userDTO).msg("done").build();

	}

	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id) {
		service.delete(id);
		return ResponseDTO.<Void>builder().status(200).msg("done").build();
	}

	@PutMapping("/")
	public ResponseDTO<UserDTO> updateUser(UserDTO u)
			throws IllegalStateException, IOException {
		
		if (!u.getFile().isEmpty()) {
			String fileName = u.getFile().getOriginalFilename();
			File file = new File("E:/" + fileName);
			u.getFile().transferTo(file);
			u.setAvatar(fileName);
		}

		service.update(u);

		return ResponseDTO.<UserDTO>builder()
				.status(200).msg("done")
				.data(u)
				.build();
	}

	@PostMapping("/search")
	public ResponseDTO<List<UserDTO>> search(@RequestParam("name") String name) {
		List<UserDTO> lst = service.searchByName(name);
		return ResponseDTO.<List<UserDTO>>builder()
					.status(200)
					.msg("done")
					.data(lst)
					.build();
	}
}
