package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="category_PDF")
public class CategoryPDF {
     @Id
     @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="catPDF_category_id")
    private Collection categoryId;
    @ManyToOne
    @JoinColumn(name="catPDF_pdf_id")
    private Collection pdfId;

    public CategoryPDF() {
    }

    public CategoryPDF(Collection categoryId, Collection pdfId) {
        this.categoryId = categoryId;
        this.pdfId = pdfId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Collection categoryId) {
        this.categoryId = categoryId;
    }

    public Collection getPdfId() {
        return pdfId;
    }

    public void setPdfId(Collection pdfId) {
        this.pdfId = pdfId;
    }
}
