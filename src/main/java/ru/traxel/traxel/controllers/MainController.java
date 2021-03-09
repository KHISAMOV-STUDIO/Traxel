package ru.traxel.traxel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.traxel.traxel.dao.MusicDAO;
import ru.traxel.traxel.models.Music;

import java.util.Optional;

@Controller
public class MainController {

    final MusicDAO musicDAO;

    @Autowired
    public MainController(MusicDAO musicDAO) {
        this.musicDAO = musicDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("first", (getMusic(1).getAuthorName() + " - " + getMusic(1).getTitle()));
        return "index";
    }

    @GetMapping("/index")
    public String main() {
        return "index";
    }

    private Music getMusic(long id) {
        Optional<Music> music = musicDAO.show(id);

        return music.orElseGet(
                () -> new Music());
    }
}
