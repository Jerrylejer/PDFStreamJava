package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Entity.Pdf;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.PdfRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// 2 Tests unitaires dans mon PdfServiceTest:
// Ces tests vérifient des composants isolés (comme ici un service ou un repository) en utilisant
// des mocks pour les dépendances (utilisation de Mockito).
// Tests d'intégration : Tests qui vérifient comment les différents composants fonctionnent ensemble.
// Un test d'intégration typique vérifie que l'appel à une URL spécifique dans le contrôleur passe correctement
// par le service et le repository pour retourner le résultat attendu.

@ExtendWith(MockitoExtension.class)
class PdfServiceTest {
    @InjectMocks
    private PdfService testPdfService;
    @Mock
    private PdfRepository testPdfRepository;
    private Pdf pdfTemplate() {
        Pdf pdf = new Pdf();
        pdf.setId(1);
        pdf.setTitle("pdfTemplate");
        return pdf;
    }

    @Test
    void findById_should_return_pdf() throws NotFoundException {
        // Le pdf de test
        Pdf pdf = this.pdfTemplate();
        // When
        when(testPdfRepository.findById(1)).thenReturn(Optional.of(pdf));
        Pdf returnedPdf = this.testPdfService.getPdfById(1);
        // Then
        assertEquals(pdf.getId(), returnedPdf.getId());
        verify(this.testPdfRepository).findById(1);
    }

    @Test
    void save_should_create_pdf() {
    }
}