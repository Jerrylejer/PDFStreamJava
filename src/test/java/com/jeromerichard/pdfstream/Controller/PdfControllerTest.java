package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.PdfDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Entity.User;
import com.jeromerichard.pdfstream.Repository.CategoryRepository;
import com.jeromerichard.pdfstream.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 2 Tests d'intégration dans mon PdfController:
// Tests qui vérifient comment les différents composants fonctionnent ensemble.
// Un test d'intégration typique vérifie que l'appel à une URL spécifique dans le contrôleur passe correctement
// par le service et le repository pour retourner le résultat attendu.
// Tests unitaires : Ces tests vérifient des composants isolés (comme un service ou un repository) en utilisant
// des mocks pour les dépendances (utilisation de Mockito).

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PdfControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    // #######################################################################################################
    // ############################### ESSAI DE CONNEXION DOCKER ET TESTCONTAINER ############################
    //@Testcontainers
    // ...
    //@Container
    //@ServiceConnection // Configuration de la connexion à la BDD en automatique
    // Instanciation d'un container mySql (https://hub.docker.com/_/mysql/ pour trouver l'image la + récente))
    //private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.1.0");
    // => La connexion est toujours en attente ....
    // #######################################################################################################
    @Test
    void findById_should_return_pdf() throws Exception {
        // mockMvc va simuler les requêtes Http et vérifier les réponses sans l'usage de serveur
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pdf/download/{id}", 132)
                        .accept(MediaType.APPLICATION_PDF))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/pdf"));
        // Je reçois un pdf et non un JSON => ligne suivante impossible
        //.andExpect(jsonPath("$.id").value("132"));
    }
    @Test
    // Avant de lancer => Supprimer le testUser en BDD
    // Avant de lancer => Augmenter L'id du pdf
    void save_should_create_pdf() throws Exception {
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
        // Je créé un user pour le besoin du test (supprimer en bdd si nouveau test)
        User user = User.builder()
                .username("userTEST")
                .email("emailTest@TEST.com")
                .password("azertyTEST")
                .build();
        // Sauvegarder l'instance sinon considérée comme transient et pas persistante par entityManager
        userRepository.save(user);
        // Idem pour la catégorie
        Category category = Category.builder()
                .title("CategoryTEST")
                .build();
        // Sauvegarder l'instance sinon considérée comme transient et pas persistante par entityManager
        categoryRepository.save(category);
        // Mon objet qui correspond au @ModelAttribute de mon controller (il faut donner une valeur au @ModelAttribute dans le controller)
        PdfDTOWayIN pdf = PdfDTOWayIN.builder()
                .smallDescription("Small description TEST")
                .description("description TEST")
                .categories(Set.of(category))
                .author(user)
                .build();
        // mockMvc va simuler les requêtes Http et vérifier les réponses sans l'usage de serveur
        mockMvc.perform(MockMvcRequestBuilders.multipart("/pdf/upload")
                        .file(image)
                        .file(pdfFile)
                        // flashAttr permet de récupérer mon objet @ModelAttribute
                        .flashAttr("clientDatas", pdf)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                // Vérifier l'index du dernier pdf en BDD et modifier pour le suivant
                .andExpect(jsonPath("$.id").value("215"))
                .andExpect(jsonPath("$.title").value("document.pdf"))
                .andExpect(jsonPath("$.smallDescription").value("Small description TEST"))
                .andExpect(jsonPath("$.description").value("description TEST"))
                .andExpect(jsonPath("$.categories[0].title").value("CategoryTEST"))
                .andExpect(jsonPath("$.author.username").value("userTEST"))
                .andExpect(status().isCreated());

        pdfStream.close();
        imageStream.close();
    }
}