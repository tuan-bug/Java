package com.example.demo.controllers;

import com.example.demo.models.productReponsitory;
import com.example.demo.models.productService;
import com.example.demo.models.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class productController {
    @Value("${upload.directory}")
    private String uploadDirectory;

    @Autowired
    productService service;

    @GetMapping("/")
    public String viewHomePage(Model model){
        List<products> list = service.listProducts();
        model.addAttribute("listProduct", list);
        return "index";
    }

    @GetMapping("/admin/add_product")
    public String showFromProduct(Model model){
        products product = new products();
        model.addAttribute("product", product);
        return "admin/product/add_product";
    }

//    @PostMapping("/admin/save_product")
//    public String saveProduct(@ModelAttribute("product") products product){
//        // Lưu ảnh vào thư mục cụ thể
//        String uploadDir = "/path/to/your/image/folder/";
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Path uploadPath = Paths.get(uploadDir);
//
//        try {
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//
//            try (InputStream inputStream = file.getInputStream()) {
//                Path filePath = uploadPath.resolve(fileName);
//                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//                product.setImage(fileName);
//            } catch (IOException e) {
//                throw new IOException("Could not save uploaded file: " + fileName);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        service.save(product);
//        return "redirect:/";
//    }

//    @PostMapping("/admin/save_product")
//    public String saveProduct(@ModelAttribute("product") products product, @RequestParam("image") MultipartFile imageFile) {
//        // Xử lý tệp ảnh
//        if (!imageFile.isEmpty()) {
//            try {
//                // Lấy đường dẫn thư mục lưu trữ ảnh (thay đổi tùy thuộc vào cấu hình của bạn)
//                String uploadDirectory = "src/main/resources/static/upload/image/";
//
//                // Chuẩn bị đường dẫn đầy đủ cho tệp ảnh
//                String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//                Path imagePath = Paths.get(uploadDirectory, fileName);
//
//                // Lưu trữ tệp ảnh vào thư mục lưu trữ
//                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
//
//                // Lưu đường dẫn tệp ảnh vào đối tượng sản phẩm
//                product.setImage("/upload/image/" + fileName); // Điều này phụ thuộc vào cách bạn cấu hình đường dẫn trả về ảnh
//            } catch (IOException e) {
//                e.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi
//            }
//        }
//
//        // Lưu sản phẩm vào cơ sở dữ liệu
//        service.save(product);
//
//        return "redirect:/";
//    }


    @PostMapping("/admin/save_product")
    public String saveProduct(@RequestParam("image") MultipartFile file,
                              @RequestParam("categories") String categories,
                              @RequestParam("name") String name,
                              @RequestParam("price") double price,
                              @RequestParam("priceSale") double priceSale,
                              @RequestParam("description") String description,
                              @RequestParam("unit") String unit,
                              @RequestParam("count") int count
                              )
    {
        try {
//            double price = Double.parseDouble(priceStr);
//            double priceSale = Double.parseDouble(priceSaleStr);

            service.saveProductToDB(categories, name, price, priceSale, description, unit, count, file);
            return "redirect:/";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý lỗi khi giá trị không thể chuyển đổi thành kiểu double
            return "redirect:/error";
        }
    }




}
