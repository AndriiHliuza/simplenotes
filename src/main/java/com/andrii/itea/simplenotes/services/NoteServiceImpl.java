package com.andrii.itea.simplenotes.services;

import com.andrii.itea.simplenotes.dto.NoteDto;
import com.andrii.itea.simplenotes.entity.NoteEntity;
import com.andrii.itea.simplenotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public NoteDto getById(int id) {
        if (id < 0 || !noteRepository.existsById(id)) {
            throw new RuntimeException("illegal id");
        }
        NoteDto noteDto = new NoteDto();
        NoteEntity noteEntity = noteRepository.getById(id);

        noteDto.setDateOfModification(noteEntity.getDateOfModification());
        noteDto.setHeadline(noteEntity.getHeadline());
        noteDto.setText(noteEntity.getText());

        return noteDto;
    }

    @Override
    public List<NoteDto> getAll() {
        List<NoteDto> noteDtoList = new ArrayList<>();
        List<NoteEntity> noteEntityList = noteRepository.findAll();

        for (NoteEntity noteEntity : noteEntityList) {
            noteDtoList.add(new NoteDto(
                    noteEntity.getDateOfModification(),
                    noteEntity.getHeadline(),
                    noteEntity.getText()));
        }

        return noteDtoList;
    }

    @Override
    public NoteDto create(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity(
                noteDto.getDateOfModification(),
                noteDto.getHeadline(),
                noteDto.getText());

        NoteEntity savedNoteEntity = noteRepository.save(noteEntity);

        return new NoteDto(
                savedNoteEntity.getDateOfModification(),
                savedNoteEntity.getHeadline(),
                savedNoteEntity.getText());
    }

    @Override
    public List<NoteDto> createAll(List<NoteDto> noteDtoList) {

        List<NoteEntity> noteEntityList = new ArrayList<>();
        for (NoteDto noteDto : noteDtoList) {
            noteEntityList.add(new NoteEntity(
                    noteDto.getDateOfModification(),
                    noteDto.getHeadline(),
                    noteDto.getText()));
        }

        List<NoteEntity> savedListOfNoteEntities = noteRepository.saveAll(noteEntityList);

        List<NoteDto> noteDtoListToReturn = new ArrayList<>();

        for (NoteEntity noteEntity : savedListOfNoteEntities) {
            noteDtoListToReturn.add(new NoteDto(
                    noteEntity.getDateOfModification(),
                    noteEntity.getHeadline(),
                    noteEntity.getText()));
        }

        return noteDtoListToReturn;
    }

    @Override
    public NoteDto editById(int id, NoteDto noteDto) {
        if (id < 0 || !noteRepository.existsById(id)) {
            throw new RuntimeException("illegal id");
        }
        boolean isEntityModified = false;
        NoteEntity noteEntityToEdit = noteRepository.getById(id);

        if ((noteDto.getHeadline() != null) && !noteDto.getHeadline().isBlank() && !noteEntityToEdit.getHeadline().equals(noteDto.getHeadline())) {
            noteEntityToEdit.setHeadline(noteDto.getHeadline());
            isEntityModified = true;
        }
        if ((noteDto.getText() != null) && !noteDto.getText().isBlank() && !noteEntityToEdit.getText().equals(noteDto.getText())) {
            noteEntityToEdit.setText(noteDto.getText());
            isEntityModified = true;
        }

        if (isEntityModified) {
            noteEntityToEdit.modifyDate();
        }

        NoteEntity savedNoteEntity = noteRepository.save(noteEntityToEdit);


        return new NoteDto(
                savedNoteEntity.getDateOfModification(),
                savedNoteEntity.getHeadline(),
                savedNoteEntity.getText());
    }

    @Override
    public NoteDto deleteById(int id) {
        if (id < 0 || !noteRepository.existsById(id)) {
            throw new RuntimeException("illegal id");
        }
        NoteEntity noteEntityToDelete = noteRepository.getById(id);
        noteRepository.deleteById(id);
        return new NoteDto(noteEntityToDelete.getDateOfModification(),
                noteEntityToDelete.getHeadline(),
                noteEntityToDelete.getText());
    }
}
