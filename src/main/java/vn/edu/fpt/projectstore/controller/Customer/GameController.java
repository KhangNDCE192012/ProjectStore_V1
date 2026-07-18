package vn.edu.fpt.projectstore.controller.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.projectstore.entity.Customer;
import vn.edu.fpt.projectstore.entity.Game;
import vn.edu.fpt.projectstore.entity.GameAccount;
import vn.edu.fpt.projectstore.repository.GameAccountRepositories;
import vn.edu.fpt.projectstore.repository.RentAccountGameRepositories;
import vn.edu.fpt.projectstore.service.CustomerService;
import vn.edu.fpt.projectstore.service.GameAccountService;
import vn.edu.fpt.projectstore.service.GameService;
import vn.edu.fpt.projectstore.service.RentAccountGameService;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/home")
public class GameController {
    @Autowired
    private RentAccountGameService   rentAccountGameService;
    @Autowired
    private GameAccountService gameAccountService;
    @Autowired
    private GameService gameService;
    @Autowired
    private CustomerService  customerService;
    @Autowired
    private GameAccountRepositories gameAccountRepositories;
    @Autowired
    private RentAccountGameRepositories  rentAccountGameRepositories;

    @GetMapping("/game/{gameId}")
    public String gameAccount(Model model, @PathVariable UUID gameId) {

        Game game = gameService.findById(gameId);

        List<BigDecimal> prices = List.of(
                BigDecimal.valueOf(50000),
                BigDecimal.valueOf(100000),
                BigDecimal.valueOf(150000),
                BigDecimal.valueOf(200000)
        );

        model.addAttribute("game", game);
        model.addAttribute("gameId", gameId);
        model.addAttribute("gameName", game.getGameName());
        model.addAttribute("prices", prices);

        return "customer/GameAccount";
    }

    @ModelAttribute("currentUser")
    public Customer currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerService.findCustomerByUsername(username);
    }

    @GetMapping("/home/products")
    public String products(
            @RequestParam(required = false) UUID gameId,
            Model model) {

        List<BigDecimal> prices = List.of(
                BigDecimal.valueOf(50000),
                BigDecimal.valueOf(100000),
                BigDecimal.valueOf(150000),
                BigDecimal.valueOf(200000)
        );

        model.addAttribute("prices", prices);
        model.addAttribute("gameId", gameId);

        return "customer/GameAccount";
    }

    @GetMapping("/GameDetail")
    public String gameDetail(@RequestParam int price,
                             @RequestParam String game,
                             Model model) {

        int skinMin = 0;
        int skinMax = 0;

        switch (price) {
            case 50000:
                skinMin = 10;
                skinMax = 30;
                break;
            case 100000:
                skinMin = 30;
                skinMax = 60;
                break;
            case 150000:
                skinMin = 60;
                skinMax = 90;
                break;
            case 200000:
                skinMin = 90;
                skinMax = 150;
                break;
        }

        int randomLevel = (int) (Math.random() * 50) + 1;
        int randomVip = (int) (Math.random() * 10) + 1;

        model.addAttribute("price", price);
        model.addAttribute("gameName", game);
        model.addAttribute("gameId", gameService.findGameByGameName(game).getGameId());
        model.addAttribute("skinMin", skinMin);
        model.addAttribute("skinMax", skinMax);
        model.addAttribute("level", randomLevel);
        model.addAttribute("vip", randomVip);
        model.addAttribute("gameName", game);

        return "customer/GameDetail";
    }



    @GetMapping("/GameDetails")
    public String gameByPrice(
            @RequestParam BigDecimal price,
            @RequestParam String game,
            Model model
    ) {
        Game gameEntity = gameService.findGameByGameName(game);
        if (gameEntity == null) {
            return "redirect:/home";
        }

        List<GameAccount> activeAccounts =
                gameAccountService.getActiveAccountsByGameAndPrice(
                        gameEntity,
                        price
                );

        List<GameAccount> accounts =
                gameAccountService.removeDuplicateAccounts(activeAccounts);

        model.addAttribute("accounts", accounts);
        model.addAttribute("price", price);
        model.addAttribute("gameName", game);

        return "customer/AccountByPrice";
    }

    @GetMapping("/accDetail/{id}")
    public String gameDetail(@PathVariable UUID id,
                             @RequestParam String game,
                             Model model) {

        GameAccount p = gameAccountService.findGameAccountById(id);
        boolean isRented = rentAccountGameService.isAccountRented(p);
        model.addAttribute("p", p);
        model.addAttribute("gameName", game);
        model.addAttribute("isRented", isRented);

        return "customer/GameDetail";
    }

}
