package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="category_pdf")
public class PdfCategory {
     @Id
     @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="pdfCategory_category_id")
    private Category categoryId;
    @ManyToOne
    @JoinColumn(name="pdfCategory_pdf_id")
    private Pdf pdfId;

    public PdfCategory() {
    }

    public PdfCategory(Category categoryId, Pdf pdfId) {
        this.categoryId = categoryId;
        this.pdfId = pdfId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Pdf getPdfId() {
        return pdfId;
    }

    public void setPdfId(Pdf pdfId) {
        this.pdfId = pdfId;
    }
}
