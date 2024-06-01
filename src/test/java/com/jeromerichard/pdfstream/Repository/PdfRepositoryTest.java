package com.jeromerichard.pdfstream.Repository;

import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// 2 Tests unitaires dans mon PdfRepositoryTest:
// Ces tests vérifient des composants isolés (comme ici un service ou un repository) en utilisant
// des mocks pour les dépendances (utilisation de Mockito).
// Tests d'intégration : Tests qui vérifient comment les différents composants fonctionnent ensemble.
// Un test d'intégration typique vérifie que l'appel à une URL spécifique dans le contrôleur passe correctement
// par le service et le repository pour retourner le résultat attendu.

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
// Je n'utilise pas la BDD H2 mais celle en production
class PdfRepositoryTest {
    @Autowired
    PdfRepository pdfRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Test
    void findById_should_return_pdf() {
        // J'utilise le repository et retourne le pdf id=132
        Optional<Pdf> pdf = this.pdfRepository.findById(132);
        System.out.println("PDF ID: " + pdf.get().getId());
        System.out.println("PDF Title: " + pdf.get().getTitle());
        // Then
        assertTrue(true);
    }

    @Test
    void save_should_create_pdf() throws IOException {
        // Charger le fichier PDF depuis les ressources de test
        byte[] pdfBytes;
        try (InputStream pdfStream = new FileInputStream("src/test/resources/testPdf.pdf")) {
            pdfBytes = pdfStream.readAllBytes();
        }
        // Charger le fichier photo depuis les ressources de test
        byte[] photoBytes;
        try (InputStream imageStream = new FileInputStream("src/test/resources/defaultImage.jpg")) {
            photoBytes = imageStream.readAllBytes();
        }
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
        // Création du pdf de test
        Pdf pdf = Pdf.builder()
                .smallDescription("Small description TEST")
                .description("description TEST")
                .pdfFile(pdfBytes)
                .image(photoBytes)
                .categories(Set.of(category))
                .author(user)
                .build();
        // J'utilise le repository pour sauvegarder le pdf
        pdfRepository.save(pdf);
        // Je stocke l'id
        Integer savedPdfID = pdf.getId();
        // Je stocke le nouveau pdf
        Pdf pdfToTest = pdfRepository.findById(savedPdfID).orElseThrow();
        // Je vérifie les attendues aux données en enregistrées
        assertEquals(savedPdfID, pdfToTest.getId());
        assertEquals("description TEST", pdfToTest.getDescription());
        assertEquals("Small description TEST", pdfToTest.getSmallDescription());
        // J'affiche en console
        System.out.println("PDF ID: " + pdfToTest.getId());
        System.out.println("PDF description: " + pdfToTest.getDescription());
        System.out.println("PDF smallDescription: " + pdfToTest.getSmallDescription());
        System.out.println("PDF fileName: " + pdfToTest.getTitle());
        System.out.println("PDF size: " + pdfToTest.getSize());
    }
}