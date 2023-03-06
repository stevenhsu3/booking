package com.javainuse.controller;

import com.javainuse.Enum.RoomType;
import com.javainuse.model.RoomDTO;
import com.javainuse.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping
	public ResponseEntity<?> roomsList(@RequestParam("type") RoomType type) {
		return ResponseEntity.ok(roomService.listRooms(type));
	}

	@PutMapping("/book")
	public ResponseEntity<?> book(@RequestBody RoomDTO roomDTO) {
		try {
			return ResponseEntity.ok(roomService.bookRoom(roomDTO));
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@PutMapping("/cancel")
	public ResponseEntity<?> cancel(@RequestBody RoomDTO roomDTO) {
		try {
			return ResponseEntity.ok(roomService.cancelRoom(roomDTO));
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

}
