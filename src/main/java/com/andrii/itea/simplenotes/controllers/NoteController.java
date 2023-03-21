package com.andrii.itea.simplenotes.controllers;


import com.andrii.itea.simplenotes.dto.NoteDto;
import com.andrii.itea.simplenotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("notes/{id}")
    public NoteDto getNote(@PathVariable("id") int id) {
        return noteService.getById(id);
    }

    @GetMapping("notes")
    public List<NoteDto> getAllNotes() {
        return noteService.getAll();
    }

    @PostMapping("notes/create")
    public NoteDto createNote(@RequestBody NoteDto noteDto) {
        return noteService.create(noteDto);
    }

    @PostMapping("notes/create/all")
    public List<NoteDto> createAllNotes(@RequestBody List<NoteDto> noteDtoList) {
        return noteService.createAll(noteDtoList);
    }

    @PostMapping("notes/edit/{id}")
    public NoteDto editNoteById(@PathVariable("id") int id, @RequestBody NoteDto noteDto) {
        return noteService.editById(id, noteDto);
    }

    @GetMapping("notes/delete/{id}")
    public NoteDto deleteNoteById(@PathVariable("id") int id) {
        return noteService.deleteById(id);
    }
}
