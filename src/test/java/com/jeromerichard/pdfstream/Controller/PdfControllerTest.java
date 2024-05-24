package com.jeromerichard.pdfstream.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Entity.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PdfControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    void savePdf() throws Exception {
        // Mes fichiers resources pour les tests
        InputStream pdfStream = new FileInputStream("src/test/resources/testPdf.pdf");
        InputStream imageStream = new FileInputStream("src/test/resources/defaultImage.jpg");

        // Mon mocks MultipartFile pour l'image (ok image reçue en BDD)
        MockMultipartFile image
                = new MockMultipartFile(
                "image",
                "image.jpg",
                "image/jpeg",
                imageStream
        );

        // Mon mocks MultipartFile pour le pdf (ok pdf reçu en BDD)
        MockMultipartFile pdfFile
                = new MockMultipartFile(
                "pdfFile",
                "document.pdf",
                "application/pdf",
                pdfStream
        );

        // Simulation des autres datas du formulaire
        // un User pour l'auteur
        User author = new User();
        author.setId(73L);
        // une catégorie
        Category category = new Category();
        category.setId(16);

        // Création du DTO (clientdatas dans le controller)
        PdfDTOWayIN clientDatas = new PdfDTOWayIN();
        // Je simule les datas entrées par l'utilisateur dans le formulaire
        clientDatas.setSmallDescription("Small description");
        clientDatas.setDescription("Description");
        clientDatas.setCategories(Set.of(category));
        // Je simule les datas automatiques
        clientDatas.setAuthor(author);
        clientDatas.setCreatedAt(new Date());

        String clientDatasToJson = objectMapper.writeValueAsString(clientDatas);
        MockMultipartFile clientDatasFile = new MockMultipartFile("clientDatas", "clientDatas", "application/json", clientDatasToJson.getBytes());

        // this is clientdatas : PdfDTOWayIN(id=null, title=null, smallDescription=Small description, description=Description, image=null, size=null, pdfFile=null, counter=null, createdAt=Fri May 24 19:26:36 CEST 2024, updateAt=null, categories=[com.jeromerichard.pdfstream.Entity.Category@d4962bd], evaluations=null, alerts=null, donations=null, author=com.jeromerichard.pdfstream.Entity.User@76fa3dd8)
        // C'est ce que je récupère lorsque je créé un pdf => mon clientDatas est donc ok
        System.out.println("this is clientdatas : " + clientDatas);
        // this is clientDatasToJson : {"id":null,"title":null,"smallDescription":"Small description","description":"Description","image":null,"size":null,"pdfFile":null,"counter":null,"createdAt":"2024-05-24T17:40:03.991+00:00","updateAt":null,"categories":[{"id":16,"title":null,"createdAt":null,"updateAt":null}],"evaluations":null,"alerts":null,"donations":null,"author":{"username":null,"password":null,"avatar":null,"email":null,"bio":null,"createdAt":null,"updatedAt":null,"donationsByDonor":[],"donationsByBeneficiary":[],"evaluations":[],"id":73}}
        System.out.println("this is clientDatasToJson : " + clientDatasToJson);
        // Le souci vient de la ligne 76 ... je récupère un PdfDTOWayIN aux clientDatas qui sont null mais image et pdfFile sont ok
        System.out.println("this is clientDatasFile : " + clientDatasFile);
        // PdfDTOWayIN(id=null, title=null, smallDescription=null, description=null, image=org.springframework.mock.web.MockMultipartFile@f3cc2e0, size=null, pdfFile=org.springframework.mock.web.MockMultipartFile@70ba01be, counter=null, createdAt=null, updateAt=null, categories=null, evaluations=null, alerts=null, donations=null, author=null)



        mockMvc.perform(MockMvcRequestBuilders.multipart("/pdf/upload")
                        .file(image)
                        .file(pdfFile)
                        .file(clientDatasFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        pdfStream.close();
        imageStream.close();
    }

    @Test
    void downloadPdf() throws Exception {

    }
}