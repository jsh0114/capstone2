package com.capstone.teamProj_10.apiTest.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {
    private final SearchRequestController searchRequestController;
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @PostMapping("/deleteAll")
    public ResponseEntity<?> deleteAllProducts() {
        itemService.deleteAll();
        log.info("deleteAllProducts method is finished");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchResult")
    public String searchResult(Model model) {
        List<Product> productList = itemService.findItems(); // 모든 상품 목록을 가져옵니다.
        model.addAttribute("productList", productList); // 상품 목록을 모델에 추가합니다.
        return "searchResult";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String query, Model model) {
        itemRepository.deleteAll();
        List<Product> productList = searchRequestController.getItems(query);
        model.addAttribute("productList", productList);
        model.addAttribute("query", query);
        return "/searchResult"; // 뷰 이름
    }

    @GetMapping("/cpu")
    public String cpu(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findCpuItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/cpu";
    }

    @GetMapping("/gpu")
    public String gpu(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findGpuItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/gpu";
    }

    @GetMapping("/ssd")
    public String ssd(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findSsdItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/ssd";
    }

    @GetMapping("/hdd")
    public String hdd(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findHddItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/hdd";
    }

    @GetMapping("/ram")
    public String ram(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findRamItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/ram";
    }

    @GetMapping("/computercase")
    public String computercase(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findComputercaseItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/computercase";
    }

    @GetMapping("/mainboard")
    public String mainboard(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findMainboardItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/mainboard";
    }

    @GetMapping("/power")
    public String power(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findPowerItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/power";
    }

    @GetMapping("/cooler")
    public String cooler(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findCoolerItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/cooler";
    }

    @GetMapping("/monitor")
    public String monitor(Model model,@RequestParam(value = "page",defaultValue = "0")int page) {
        List<Product> productList = itemService.findMonitorItems();
        Page<Product>paging = this.itemService.getList(page);
        model.addAttribute("paging",paging);
        model.addAttribute("productList", productList);
        return "/Products/monitor";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypage() {
        return "/mypage";
    }


    @GetMapping("/center")
    public String center() {
        return "/center";
    }


}