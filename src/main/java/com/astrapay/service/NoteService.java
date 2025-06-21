package com.astrapay.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.entity.Note;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoteService {
	private List<Note> notes = new ArrayList<>();
	private AtomicInteger idGenerated = new AtomicInteger();
	
	/* get all notes */
	public List<Note> getAllNotes(){
		return notes;
	}
	
	/* add new note */
	public Note addNote(NoteRequestDto request){
		Note note = new Note();
		note.setId(idGenerated.incrementAndGet());
		note.setTitle(request.getTitle());
		note.setContent(request.getContent());
		note.setCreatedAt(LocalDateTime.now());
		notes.add(note);
		log.info("Note: "+note.getTitle()+" telah disimpan!");
		return note;
	}
	
	/* delete note by id */
	public Note deleteNoteById(long id){
		Note selectedNote = null;
		for(Note note:notes) {
			if(note.getId() == id){
				selectedNote = note;
				break;
			}
		}
		if(selectedNote!=null) {
			notes.remove(selectedNote);
			log.info("Note: "+selectedNote.getTitle()+" berhasil dihapus!");
		}else {
			log.warn("Note tidak ditemukan untuk dihapus!");
		}
		return selectedNote;
	}
}