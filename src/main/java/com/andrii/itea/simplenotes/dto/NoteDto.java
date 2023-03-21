package com.andrii.itea.simplenotes.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class NoteDto {
    private LocalDateTime dateOfModification;
    private String headline;
    private String text;

    public NoteDto() {
        dateOfModification = LocalDateTime.now();
    }

    public NoteDto(LocalDateTime dateOfModification, String headline, String text) {
        this.dateOfModification = dateOfModification;
        this.headline = headline;
        this.text = text;
    }

    public LocalDateTime getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(LocalDateTime dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
