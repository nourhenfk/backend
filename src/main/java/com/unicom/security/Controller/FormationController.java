package com.unicom.security.Controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.unicom.security.models.Formation;
import com.unicom.security.service.FormationService;


@RestController
@RequestMapping("/api/v1/auth/formations")
public class FormationController {

    @Autowired
    private FormationService formationService;
    
  

    @GetMapping("/AllFormations")
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @GetMapping("/getFormationById/{id}")
    public Formation getFormationById(@PathVariable Long id) {
        return formationService.getFormationById(id);
    }

    @PostMapping("/addFormation")
    public Formation addFormation(@RequestBody Formation formation) {
        return formationService.addFormation(formation);
    }

    @PutMapping("/updateFormation/{id}")
    public Formation updateFormation(@PathVariable Long id, @RequestBody Formation formation) {
        return formationService.updateFormation(id, formation);
    }

    @DeleteMapping("/deleteFormation/{id}")
    public void deleteFormation(@PathVariable Long id) {
    	formationService.deleteFormation(id);
    }
    @PostMapping("/{id}/documents")
    public ResponseEntity<Void> uploadDocuments(
            @PathVariable("id") Long formationId,
            @RequestParam("documents") MultipartFile[] documentFiles) throws IOException {
        List<byte[]> documentDataList = new ArrayList<>();

        for (MultipartFile file : documentFiles) {
            byte[] documentData = file.getBytes();
            documentDataList.add(documentData);
        }

        formationService.uploadDocuments(formationId, documentDataList);

        return ResponseEntity.noContent().build();
    }
}
