package portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import portal.model.Garage;
import portal.model.SaleOrder;
import portal.model.Technician;
import portal.model.user.User;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView applicationIndex() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username,
                              @RequestParam String password) {
        boolean validate = User.validate(username, password);
        if (validate) {
            return mainMenu();
        }
        return applicationIndex();
    }

    @RequestMapping(value = "loggout", method = RequestMethod.POST)
    public ModelAndView loggout() {
        return applicationIndex();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerUser() {
        ModelAndView mv = new ModelAndView("registerUser");
        return mv;
    }

    @RequestMapping(value = "createUser", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute User user, BindingResult errors) {
        user.validateRegisterForm(errors);
        if (errors.hasErrors()) {
            return registerUser();
        }
        boolean result = user.register();
        if (result) {
            return mainMenu();
        }
        return registerUser();
    }

    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public ModelAndView mainMenu() {
        ModelAndView mv = new ModelAndView("mainMenu");
        return mv;
    }

    @RequestMapping(value = "manager/profile", method = RequestMethod.GET)
    public ModelAndView profileManager() {
        ModelAndView mv = new ModelAndView("profileManager");
        return mv;
    }

    @RequestMapping(value = "manager/technician", method = RequestMethod.GET)
    public ModelAndView technicianManager() {
        ModelAndView mv = new ModelAndView("technicianManager");
        return mv;
    }

    @RequestMapping(value = "garage", method = RequestMethod.GET)
    public ModelAndView garageList() {
        ModelAndView mv = new ModelAndView("garageList");
        mv.addObject("garages", Garage.findAll());
        return mv;
    }

    @RequestMapping(value = "garage/{altKeyGarage}", method = RequestMethod.GET)
    public ModelAndView garageDetail(@PathVariable String altKeyGarage) {
        ModelAndView mv = new ModelAndView("garage");
        mv.addObject("garage", Garage.findByAltKey(altKeyGarage));
        return mv;
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public ModelAndView saleOrderList() {
        ModelAndView mv = new ModelAndView("saleOrderList");
//        mv.addObject("salesOrder", SaleOrder.dummyList());
        mv.addObject("salesOrder", SaleOrder.findAll());
        return mv;
    }

    @RequestMapping(value = "order/{altKeyOrder}", method = RequestMethod.GET)
    public ModelAndView saleOrderDetail(@PathVariable String altKeyOrder) {
        ModelAndView mv = new ModelAndView("saleOrder");
//        mv.addObject("saleOrder", SaleOrder.dummy());
        mv.addObject("saleOrder", SaleOrder.findByAltKey(altKeyOrder));
        return mv;
    }

    @RequestMapping(value = "technician", method = RequestMethod.GET)
    public ModelAndView technicianList() {
        ModelAndView mv = new ModelAndView("technicianList");
        mv.addObject("technicians", Technician.findAll());
        return mv;
    }

    @RequestMapping(value = "parking", method = RequestMethod.GET)
    public ModelAndView parkingInfo() {
        ModelAndView mv = new ModelAndView("parkingInfo");
        return mv;
    }
}