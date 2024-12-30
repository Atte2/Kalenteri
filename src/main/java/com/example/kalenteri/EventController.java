package com.example.kalenteri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String listEvents(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "event_delete";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "event_list";
    }

    @PostMapping
    public String createEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + id));
        model.addAttribute("event", event);
        return "event_form";
    }

    @PostMapping("/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event updatedEvent) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + id));
        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setEventType(updatedEvent.getEventType());
        eventRepository.save(event);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return "redirect:/events";
    }
}
