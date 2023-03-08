package tr.com.teta.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import tr.com.teta.model.Company;
import tr.com.teta.service.CompanyService;

@ThreadSafe
@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        return "companies/create";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "companies/list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Company company, Model model) {
        try {
            companyService.save(company);
            return "redirect:/companies";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var companyOptional = companyService.findById(id);
        if (companyOptional.isEmpty()) {
            model.addAttribute("message", "Company with typed id not exist");
            return "errors/404";
        }
        model.addAttribute("company", companyOptional.get());
        return "companies/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Company company, Model model) {
        try {
            var isUpdated = companyService.update(company);
            if (!isUpdated) {
                model.addAttribute("message", "Company with typed id not exist");
                return "errors/404";
            }
            return "redirect:/companies";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = companyService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Company with typed id not exist");
            return "errors/404";
        }
        return "redirect:/companies";
    }

}
