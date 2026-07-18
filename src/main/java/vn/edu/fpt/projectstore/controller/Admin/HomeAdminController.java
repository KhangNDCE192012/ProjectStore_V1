package vn.edu.fpt.projectstore.controller.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.fpt.projectstore.service.*;
import vn.edu.fpt.projectstore.successfullyDat.PathCheck;

@Controller
@RequestMapping("/adminHome")
public class HomeAdminController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private GameAccountService gameAccountService;

    @Autowired
    private GameService gameService;

    @Autowired
    private TypeService typeService;


    @Autowired
    private VoucherService voucherService;

    @Autowired
    private AdministratorService administratorService;


    @Autowired
    private PathCheck pathCheck;


    @GetMapping("")
    public String homeAdmin(Model model) {
        return "admin/AdminIndex";
    }







}
