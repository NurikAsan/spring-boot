package com.asankulov.post.controllers;
import com.asankulov.post.Model.People;
import com.asankulov.post.PersonDAO.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/first")
public class PersonControllers {
    private final PersonRepository personRepository;

    public PersonControllers(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @GetMapping
    public String print(Model model){
        model.addAttribute("person",personRepository.print());
        return "first/show";
    }
    @GetMapping("{id}")
    public String index(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personRepository.index(id));
        return "first/index";
    }
    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("person",new People());
        return "first/newStudent";
    }
    @PostMapping
    public String post(@ModelAttribute("person") People people){
        personRepository.save(people);
        return "redirect:/first";
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id")int id,Model model){
        model.addAttribute("person",personRepository.index(id));
        return "first/edit";
    }
    @PatchMapping("{id}")
    public String Update(@ModelAttribute("person") People person,@PathVariable("id") int id){
        personRepository.update(id,person);
        return "redirect:/first";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")int id){
        personRepository.delete(id);
        return "redirect:/first";
    }
}