package bot.birthdayJ.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BirthdayController {

    @Autowired
    private BirthdayRepository birthdayRepository;

    @GetMapping("/birthday")
    public String showBirthdayForm(Model model) {
        model.addAttribute("birthday", new Birthday());
        return "birthday-form";
    }

    @PostMapping("/birthday")
    public String submitBirthdayForm(@ModelAttribute Birthday birthday) {
        birthdayRepository.save(birthday);
        return "redirect:/birthday";
    }
}