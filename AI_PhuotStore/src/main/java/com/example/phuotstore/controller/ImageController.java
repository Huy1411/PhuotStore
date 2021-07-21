package com.example.phuotstore.controller;

import com.example.phuotstore.model.Image;
import com.example.phuotstore.model.Product;
import com.example.phuotstore.service.ImageService;
import com.example.phuotstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getAllImage(Model model) {
        Image image = new Image();
        return findPaginated(1, model, image);
    }

    @RequestMapping("show")
    public String getImageShow(Model model) {
        Image image = new Image();
        return findPagShow(1, model, image);
    }

    @RequestMapping("hidden")
    public String getImageHidden(Model model) {
        Image image = new Image();
        return findPagHidden(1, model, image);
    }

    @RequestMapping("/insertImage")
    public String insertImage(Model model)
    {
        Image image = new Image();
        List<Product> products = productService.getProductsByStatus(1);
        model.addAttribute("newImage",image);
        model.addAttribute("products",products);
        return "admin/image/insertImage";
    }

    @RequestMapping(value = "/saveImage",method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("newImage")@Valid Image image, BindingResult result, Model model)throws IOException
    {

        if (result.hasErrors())
        {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products",products);
            model.addAttribute("newImage",image);
            return "admin/image/insertImage";
        }
        boolean checkImageName = imageService.checkImageName(image.getImgName(),image.getProduct().getProductID());
        if (checkImageName==false)
        {
            return "redirect:/admin/image/insertImage?errorimagename=Image Name is existed";
        }
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        image.setImageURL(fileName);
//        Image savedImage = imageService.save(image);

        boolean bl = imageService.saveImage(image);
        if(bl)
        {
            return "redirect:/admin/image/";
        }
        return "redirect:/admin/image?error=Add New Image error";
    }

    @RequestMapping(value = "/editImage")
    public String editImage(@RequestParam("imageID")Integer imageID, Model model)
    {
        Image image = imageService.getImageById(imageID);
        List<Product> products = productService.getProductsByStatus(1);
        model.addAttribute("editImage",image);
        model.addAttribute("products",products);
        return "admin/image/editImage";
    }

    @RequestMapping(path = "/updateImage", method = RequestMethod.POST)
    public String updateImage(@ModelAttribute("editImage") Image image) {
        boolean checkImageName = checkImageName(image.getImgName(), image.getImgID(), image.getProduct().getProductID());
        if (checkImageName == false) {
            return "redirect:/admin/image/editImage?imageID=" + image.getImgID() + "&&errorimagename=Image Name is existed";
        }
        boolean bl = imageService.updateImage(image);
        if (bl) {
            return "redirect:/admin/image/";
        }
        return "redirect:/admin/image?error=Update Image Error";

    }

    @RequestMapping(value = "/detailImage")
    public String detailImageById(@RequestParam("imageID")Integer imageID,Model model)
    {
        Image image = imageService.getImageById(imageID);
        model.addAttribute("detailImage",image);
        return "admin/image/detailImage";
    }

    @RequestMapping(path = "/deleteImage")
    public String deleteImage(@RequestParam("imageID") Integer imageID) {
        boolean bl = imageService.deleteImage(imageID);
        if (bl) {
            return "redirect:/admin/image/";
        }
        return "redirect:admin/image?error=Delete Images Error";
    }


    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, Image image) {
        int pageSize = 10;
        List<Product> products = productService.getAllProducts();
        Page<Image> page = imageService.findPaginated(pageNo, pageSize);
        List<Image> images = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newImage", image);
        model.addAttribute("products",products);
        model.addAttribute("images",images);
        return "admin/image/listImage";
    }

    @RequestMapping("/pageShow/{pageNo}")
    public String findPagShow(@PathVariable(value = "pageNo") int pageNo, Model model, Image image) {
        int pageSize = 10;
        List<Product> products = productService.getAllProducts();
        Page<Image> page = imageService.findPaginatedShow(pageNo, pageSize);
        List<Image> images = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newImage", image);
        model.addAttribute("products",products);
        model.addAttribute("images",images);
        return "admin/image/listImage";
    }

    @RequestMapping("/pageHidden/{pageNo}")
    public String findPagHidden(@PathVariable(value = "pageNo") int pageNo, Model model, Image image) {
        int pageSize = 10;
        List<Product> products = productService.getAllProducts();
        Page<Image> page = imageService.findPaginatedHidden(pageNo, pageSize);
        List<Image> images = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("newImage", image);
        model.addAttribute("products",products);
        model.addAttribute("images",images);
        return "admin/image/listImage";
    }

    public boolean checkImageName(String imageName, int imageId, int productID) {
        Image image = imageService.getImageById(imageId);
        boolean checkImageName = imageService.checkImageName(imageName,productID);
        if (checkImageName==false)
        {
            if (imageName.equals(image.getImgName()) && image.getProduct().getProductID()==productID)
            {
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }

}
