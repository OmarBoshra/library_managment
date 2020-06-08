package com.android.librarymanagment.models;

import java.io.Serializable;

public class BookInstModel implements Serializable {

    private   int BOOK_INST_ID ;
    private   int BOOK_INST_LANG_ID ;
    private   int BOOK_INST_FORMAT_ID ;
    private   int BOOK_INST_PUBLICATION_ID ;
    private   int BOOK_INST_EDITION_ID ;
    private   int BOOK_INST_CATEGORY_ID ;
    private   int BOOK_INST_COPIES ;

    public int getBOOK_INST_ID() {
        return BOOK_INST_ID;
    }

    public void setBOOK_INST_ID(int BOOK_INST_ID) {
        this.BOOK_INST_ID = BOOK_INST_ID;
    }

    public int getBOOK_INST_LANG_ID() {
        return BOOK_INST_LANG_ID;
    }

    public void setBOOK_INST_LANG_ID(int BOOK_INST_LANG_ID) {
        this.BOOK_INST_LANG_ID = BOOK_INST_LANG_ID;
    }

    public int getBOOK_INST_FORMAT_ID() {
        return BOOK_INST_FORMAT_ID;
    }

    public void setBOOK_INST_FORMAT_ID(int BOOK_INST_FORMAT_ID) {
        this.BOOK_INST_FORMAT_ID = BOOK_INST_FORMAT_ID;
    }

    public int getBOOK_INST_PUBLICATION_ID() {
        return BOOK_INST_PUBLICATION_ID;
    }

    public void setBOOK_INST_PUBLICATION_ID(int BOOK_INST_PUBLICATION_ID) {
        this.BOOK_INST_PUBLICATION_ID = BOOK_INST_PUBLICATION_ID;
    }

    public int getBOOK_INST_EDITION_ID() {
        return BOOK_INST_EDITION_ID;
    }

    public void setBOOK_INST_EDITION_ID(int BOOK_INST_EDITION_ID) {
        this.BOOK_INST_EDITION_ID = BOOK_INST_EDITION_ID;
    }

    public int getBOOK_INST_CATEGORY_ID() {
        return BOOK_INST_CATEGORY_ID;
    }

    public void setBOOK_INST_CATEGORY_ID(int BOOK_INST_CATEGORY_ID) {
        this.BOOK_INST_CATEGORY_ID = BOOK_INST_CATEGORY_ID;
    }

    public int getBOOK_INST_COPIES() {
        return BOOK_INST_COPIES;
    }

    public void setBOOK_INST_COPIES(int BOOK_INST_COPIES) {
        this.BOOK_INST_COPIES = BOOK_INST_COPIES;
    }

    public BookInstModel(int BOOK_INST_ID, int BOOK_INST_LANG_ID, int BOOK_INST_FORMAT_ID, int BOOK_INST_PUBLICATION_ID, int BOOK_INST_EDITION_ID, int BOOK_INST_CATEGORY_ID, int BOOK_INST_COPIES) {
        this.BOOK_INST_ID = BOOK_INST_ID;
        this.BOOK_INST_LANG_ID = BOOK_INST_LANG_ID;
        this.BOOK_INST_FORMAT_ID = BOOK_INST_FORMAT_ID;
        this.BOOK_INST_PUBLICATION_ID = BOOK_INST_PUBLICATION_ID;
        this.BOOK_INST_EDITION_ID = BOOK_INST_EDITION_ID;
        this.BOOK_INST_CATEGORY_ID = BOOK_INST_CATEGORY_ID;
        this.BOOK_INST_COPIES = BOOK_INST_COPIES;
    }
}

