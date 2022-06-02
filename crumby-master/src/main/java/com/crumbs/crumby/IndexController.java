
package com.crumbs.crumby;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.crumbs.crumby.Entities.User;
import com.crumbs.crumby.Repository.StockHistoryRepository;
import com.crumbs.crumby.Repository.StonkRepository;
import com.crumbs.crumby.Repository.UserRepository;
import com.crumbs.crumby.dto.Distribution;
import com.crumbs.crumby.dto.StockDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin("http://localhost:4200")
public class IndexController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private StonkRepository stockRepo;
    @Autowired
    private StockHistoryRepository stockHistRepo;
    @Autowired
    private StockService service;

    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@RequestBody User user) {
        user.createdOn = new Date();
        try {
            var result = userRepo.save(user);
            var uri = new URI(String.format("http://localhost:8080/users/%d", result.userId));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> GetUserById(@PathVariable(value = "id") int id) {
        var user = userRepo.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable(value = "id") int id, @RequestBody User user) {
        user.userId = id;
        user.updatedOn = new Date();
        var userInDb = userRepo.findById(id);
        if (userInDb.isEmpty())
            return ResponseEntity.notFound().build();
        var result = userRepo.save(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public String Index() {
        return "index";
    }

    @GetMapping("/stock")
    public ResponseEntity<StockDto> addStock(@RequestParam("stock-name") String stockName) {
        StockDto stockDto = null;
        try {
            stockDto = service.getStockInfo(stockName);
            service.stockList.add(stockDto);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stockDto);
    }

    @GetMapping("/remove")
    public ResponseEntity<StockDto> removeStock(@RequestParam("stock-name") String stockName) {
        StockDto stockDto = null;
        try {
            stockDto = service.getStockInfo(stockName);
            service.stockList.remove(stockDto);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stockDto);
    }

    @PostMapping(value = "/set-distributions")
    public ResponseEntity saveDistributions(@RequestBody Distribution distribution) {
        try {
            distribution.stonks.forEach((s) -> s.addedOn = new Date());
            stockRepo.saveAll(distribution.stonks);
            for (var stonk : distribution.stonks) {
                var histories = service.getHistory(stonk.stockName, 3, "DAILY");
                histories.forEach((s) -> s.setStonk(stonk));
                stockHistRepo.saveAll(histories);
            }
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/createNewPortfolio")
    public String createNewPortfolio(String startingAmount, Model model) throws Exception {
        service.stockList.removeAll(service.stockList);
        service.startingAmount = new BigDecimal(startingAmount);
        model.addAttribute("startingAmount", service.startingAmount);
        return "index";
    }

    @GetMapping("/historyStock")
    public ResponseEntity<StockDto> getHistoryOfStock(@RequestParam("stock-name") String stockName) {
        StockDto stockDto = null;
        try {
            stockDto = service.getStockInfo(stockName);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(stockDto);
    }
}