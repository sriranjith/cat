package com.sri.cat.resource;

import com.sri.cat.dal.entity.Room;
import com.sri.cat.dal.repository.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/booking")
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class BookingResource {

    private final RecordRepository recordRepository;

    @GetMapping
    @RequestMapping("/rooms")
    public Iterable<Room> getAllRooms() {
        return recordRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public String getAllRooms(@PathVariable(name = "id") long id, Model model) {
        model.addAttribute("myRoom", recordRepository.getRoomsByRoomId(id));
        return "reservations";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
