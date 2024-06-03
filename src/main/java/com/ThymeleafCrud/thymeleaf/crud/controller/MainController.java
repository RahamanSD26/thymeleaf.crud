package com.ThymeleafCrud.thymeleaf.crud.controller;

import com.ThymeleafCrud.thymeleaf.crud.domain.Product;
import com.ThymeleafCrud.thymeleaf.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class MainController {
    @Autowired
    ProductService productService;
    @RequestMapping("/")
    public String home(Model model) throws Exception{
        List<Product> productList=productService.getAllProducts();
        model.addAttribute("products",productList);
        return "home";
    }
    @RequestMapping("/addProduct")
    public String addProduct(){
        return "addProduct";
    }
    @RequestMapping(value = "/handle-product", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute Product product)throws Exception {
        productService.createProduct(product);
        return "redirect:/product/";
    }
   @RequestMapping("/delete/{productId}")
    public String delete(@PathVariable("productId") String productId)throws Exception{
     productService.deleteProduct(productId);
       return "redirect:/product/";
    }
    @RequestMapping("/update/{productId}")
    public String update(@PathVariable("productId") String productId,Model model)throws Exception{
        Optional<Product> product=productService.updateProduct(productId);
        if (product.isPresent()) {
            Product p = product.get(); // Safely get the value
            model.addAttribute("pro",p);
        } else {
            System.out.println("No such Object found");
        }

        return "update";
    }
}
