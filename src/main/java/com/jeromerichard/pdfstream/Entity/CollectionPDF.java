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
    @JoinColumn(name="collPDF_collection_id")
    private Collection collectionId;
    @ManyToOne
    @JoinColumn(name="collPDF_pdf_id")
    private Collection pdfId;

    public CollectionPDF() {
    }

    public CollectionPDF(Collection collectionId, Collection pdfId) {
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

    public Collection getPdfId() {
        return pdfId;
    }

    public void setPdfId(Collection pdfId) {
        this.pdfId = pdfId;
    }
}
