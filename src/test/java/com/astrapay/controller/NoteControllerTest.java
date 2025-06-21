package com.astrapay.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;

@WebMvcTest(NoteController.class)
class NoteControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private NoteService noteService;
	
	@Test
	void returnAllNotes() throws Exception{
		List<Note> dummyNotes = List.of(new Note(1, "judul catatan", "isi catatan", LocalDateTime.now()));
		when(noteService.getAllNotes()).thenReturn(dummyNotes);
		
		mockMvc.perform(get("/api/notes")).andExpect(status().isOk()).andExpect(jsonPath("$.data[0].content").value("isi catatan"));
	}
	
	@Test
	void returnRejectedBlankField() throws Exception{
		mockMvc.perform(post("/api/notes").contentType("application/json").content("{\"title\":\"\"}")).andExpect(status().isBadRequest());
	}
}