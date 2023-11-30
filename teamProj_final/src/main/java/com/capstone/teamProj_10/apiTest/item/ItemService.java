package com.capstone.teamProj_10.apiTest.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    @Transactional
    public void saveItem(Product product) {
        itemRepository.save(product);
    }
    public List<Product> findItems() {
        return itemRepository.findAll();
    }
    public Product findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public int deleteAll(){return itemRepository.deleteAll();}

    public Page<Product> getList(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return this.productRepository.findAll(pageable);
    }



    @Transactional
    public void updateItem(Long productId, ItemDto itemDto) {
        Product item = findOne(productId);
        item.updateByItemDto(itemDto);
    }

    @Transactional
    public Long updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        product.updateByItemDto(itemDto);
        return id;
    }

    public List<Product> findGpuItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("gpu", "그래픽카드", "nvidia", "amd", "rtx", "gtx", "rx");

        List<Product> gpuItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return gpuItems;
    }

    public List<Product> findCpuItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("cpu", "intel", "amd");

        List<Product> cpuItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return cpuItems;
    }

    public List<Product> findSsdItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("ssd", "M.2", "6.4cm", "4.6cm");

        List<Product> ssdItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return ssdItems;
    }

    public List<Product> findHddItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("hdd");

        List<Product> hddItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return hddItems;
    }

    public List<Product> findRamItems() {
        List<Product> allItems = itemRepository.findAll();
        //DDR 했더니 글카에 DDR포함된놈이 DDR쪽에 떠버린다
        List<String> keywords = Arrays.asList("ram","램","메모리","DDR");

        List<Product> ramItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return ramItems;
    }

    public List<Product> findComputercaseItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("케이스", "빅타워", "미니타워", "미들타워", "LP", "강화유리", "computer case");

        List<Product> computercaseItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return computercaseItems;
    }

    public List<Product> findMainboardItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("메인보드","mainboard", "MSI", "ASUS", "에이수스",
                "z790", "z690", "z590", "z490", "z390", "z290", "z190",
                "x770", "x670", "x570", "x470", "x370", "x270", "x170",
                "b760", "b660", "b560", "b460", "b360", "b260", "b160",
                "b750", "b650", "b550", "b450", "b350", "b250", "b150",
                "h710", "h610", "h510", "h410", "h310", "h210", "h110"
        );

        List<Product> mainboardItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return mainboardItems;
    }

    public List<Product> findPowerItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("파워","POWER");

        List<Product> powerItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return powerItems;
    }

    public List<Product> findCoolerItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("쿨러","COOLER", "PCCOOLER","DEEPCOOL","NZXT", "Thermalright", "darkFlash",
                "NOCTUA","ARCTIC", "리안리", "잘만");

        List<Product> coolerItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return coolerItems;
    }

    public List<Product> findMonitorItems() {
        List<Product> allItems = itemRepository.findAll();
        List<String> keywords = Arrays.asList("모니터");

        List<Product> monitorItems = allItems.stream()
                .filter(item -> keywords.stream()
                        .anyMatch(keyword -> item.getTitle().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());

        return monitorItems;
    }
}