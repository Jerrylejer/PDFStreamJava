package com.jeromerichard.pdfstream.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="collection_pdf")
public class CollectionPDF {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="collectionPDF_collection_id")
    private Collection collectionId;
    @ManyToOne
    @JoinColumn(name="collectionPDF_pdf_id")
    private Pdf pdfId;

    public CollectionPDF() {
    }

    public CollectionPDF(Collection collectionId, Pdf pdfId) {
        this.collectionId = collectionId;
        this.pdfId = pdfId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Collection collectionId) {
        this.collectionId = collectionId;
    }

    public Pdf getPdfId() {
        return pdfId;
    }

    public void setPdfId(Pdf pdfId) {
        this.pdfId = pdfId;
    }
}
