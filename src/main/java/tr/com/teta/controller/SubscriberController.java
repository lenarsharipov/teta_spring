package tr.com.teta.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.com.teta.model.Subscriber;
import tr.com.teta.service.SubscriberService;

@ThreadSafe
@Controller
@RequestMapping("/subscribers")
public class SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("subscribers", subscriberService.findAll());
        return "subscribers/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        return "subscribers/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Subscriber subscriber, Model model) {
        try {
            subscriberService.save(subscriber);
            return "redirect:/subscribers";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var subscriberOptional = subscriberService.findById(id);
        if (subscriberOptional.isEmpty()) {
            model.addAttribute("message", "Subscriber with typed id not exist");
            return "errors/404";
        }
        model.addAttribute("subscriber", subscriberOptional.get());
        return "subscribers/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Subscriber subscriber, Model model) {
        try {
            var isUpdated = subscriberService.update(subscriber);
            if (!isUpdated) {
                model.addAttribute("message", "Subscriber with typed id not exist");
                return "errors/404";
            }
            return "redirect:/subscribers";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = subscriberService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Subscriber with typed id not exist");
            return "errors/404";
        }
        return "redirect:/subscribers";
    }

}
