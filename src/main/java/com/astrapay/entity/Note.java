package com.astrapay.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
	private long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
}