package com.astrapay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequestDto {
	@NotBlank(message = "Judul tidak boleh kosong!")
	@Size(max = 30, message = "Panjang judul tidak boleh lebih dari 30 karakter!")
	private String title;
	
	@NotBlank(message = "Catatan tidak boleh kosong!")
	private String content;
}