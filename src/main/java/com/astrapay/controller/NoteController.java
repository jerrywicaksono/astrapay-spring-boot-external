package com.astrapay.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astrapay.dto.GeneralResponse;
import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.NoteResponseDto;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/notes")
@Api(value = "Note Controller")
@Validated
@Slf4j
public class NoteController {
	@Autowired
	public NoteService noteService;
	
	public NoteController(NoteService noteservice) {
		this.noteService = noteservice;
	}
	
	@GetMapping
	public ResponseEntity<GeneralResponse> getAllNote(){
		List<Note> notes = noteService.getAllNotes();
		List<NoteResponseDto> response = new ArrayList<>();
		for(Note note : notes){
			response.add(new NoteResponseDto(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt()));
		}
		
		return ResponseEntity.ok(new GeneralResponse("Daftar Catatan", response));
	}
	
	@PostMapping
	public ResponseEntity<GeneralResponse> createNote(@Valid @RequestBody NoteRequestDto request) {
		Note note = noteService.addNote(request);
		NoteResponseDto response = new NoteResponseDto(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt());
		return ResponseEntity.ok(new GeneralResponse("Catatan berhasil ditambahkan", response));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GeneralResponse> deleteNote(@PathVariable long id){
		Note deletedNote = noteService.deleteNoteById(id);
		if(deletedNote!=null){
			NoteResponseDto response = new NoteResponseDto(deletedNote.getId(), deletedNote.getTitle(), deletedNote.getContent(), deletedNote.getCreatedAt());
			return ResponseEntity.ok(new GeneralResponse("Catatan berhasil dihapus", response));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GeneralResponse("Catatan tidak ditemukan", null));
		}
	}
}