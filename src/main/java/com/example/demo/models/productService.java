package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class productService {
    @Autowired
    private productReponsitory repo;

    public List<products> listProducts(){
        return repo.findAll();
    }
    public void  saveProductToDB(String categories, String name, double price, double price_sale, String description, String unit, int count, MultipartFile file)
    {
        products p = new products();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
//        try {
//            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        p.setName(name);
        p.setCategories(categories);
        p.setPrice(price);
        p.setPrice_sale(price_sale);
        p.setDescription(description);
        p.setUnit(unit);
        p.setCount(count);
        p.setImage(fileName);
        repo.save(p);
    }

    public void save(products product){
        repo.save(product);
    }

    public products get(long id){
        return repo.findById(id).orElse(null);
    }

    public void delete(long id){
        repo.deleteById(id);
    }
}
