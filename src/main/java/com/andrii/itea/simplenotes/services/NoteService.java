package com.andrii.itea.simplenotes.services;

import com.andrii.itea.simplenotes.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto getById(int id);
    List<NoteDto> getAll();
    NoteDto create(NoteDto noteDto);
    List<NoteDto> createAll(List<NoteDto> noteDtoList);
    NoteDto editById(int id, NoteDto noteDto);
    NoteDto deleteById(int id);
}
