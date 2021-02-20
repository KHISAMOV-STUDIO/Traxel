package ru.traxel.traxel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.traxel.traxel.dao.MusicDAO;
import ru.traxel.traxel.models.Music;

@Controller
@RequestMapping("/music")
public class MusicController {

    final MusicDAO musicDAO;

    @Autowired
    public MusicController(MusicDAO musicDAO) {
        this.musicDAO = musicDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("musics", musicDAO.list());
        return "music/index";
    }

    @GetMapping("/index")
    public String music(Model model) {
        model.addAttribute("musics", musicDAO.list());
        return "music/index";
    }


    @GetMapping("/new")
    public String newMusic(@ModelAttribute("music") Music music) {
        return "music/new";
    }

    @PostMapping("/new")
    public String create(@RequestParam(value="authorname", required = true) String authorName,
                         @RequestParam(value="year", required = true) int year,
                         @RequestParam(value="title", required = true) String title,
                         @RequestParam(value="price", required = true) int price) {
        Music music = new Music();
        music.setAuthorName(authorName);
        music.setYear(year);
        music.setTitle(title);
        music.setPrice(price);
        musicDAO.save(music);
        return "redirect:/music";
    }
}
