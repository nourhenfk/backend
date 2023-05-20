package com.unicom.security.Controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unicom.security.models.FormateurExterne;
import com.unicom.security.service.FormateurExService;

@RestController
@RequestMapping("/api/v1/auth/formateurEx")
public class FormateurExController {
	
	 @Autowired
	    private FormateurExService formateurExService;
	    
	  

	    @GetMapping("/AllFormateurs")
	    public List<FormateurExterne> getAllFormateurs() {
	        return formateurExService.getAllFormateurs();
	    }

	    @GetMapping("/getFormateurById/{id}")
	    public FormateurExterne getFormateurById(@PathVariable Long id) {
	        return formateurExService.getFormateurById(id);
	    }

	    @PostMapping("/addFormateur")
	    public FormateurExterne addFormateur(@RequestBody FormateurExterne formateurExterne) {
	        return formateurExService.addFormateur(formateurExterne);
	    }

	    @PutMapping("/updateFormateur/{id}")
	    public FormateurExterne updateFormateur(@PathVariable Long id, @RequestBody FormateurExterne formateurExterne) {
	        return formateurExService.updateFormateur(id, formateurExterne);
	    }

	    @DeleteMapping("/deleteFormateur/{id}")
	    public void deleteFormateur(@PathVariable Long id) {
	    	formateurExService.deleteFormateur(id);
	    }
	    
	    @PutMapping(path = "/UpdatePhoto/{id}")
		public ResponseEntity<FormateurExterne> updatePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
	    	FormateurExterne updatedFormateurExterne = formateurExService.updatePhotoById(id, file.getBytes());
		    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		            .path("/downloadFile/")
		            .path(updatedFormateurExterne.getId().toString())
		            .toUriString();
		    updatedFormateurExterne.setPhotoUrl(fileDownloadUri);
		    return ResponseEntity.ok(updatedFormateurExterne);
		}
	    
	
}
