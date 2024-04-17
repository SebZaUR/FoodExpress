package proyecto.gestoralmuerzo.controller;

import proyecto.gestoralmuerzo.model.Category;
import proyecto.gestoralmuerzo.model.Ingredient;
import proyecto.gestoralmuerzo.model.Plate;
import proyecto.gestoralmuerzo.service.CategoryServices;
import proyecto.gestoralmuerzo.service.IngredientServices;
import proyecto.gestoralmuerzo.service.PlateServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    private final PlateServices plateServices;
    private final CategoryServices categoryServices;
    private final IngredientServices ingredientServices;
    private static final String LINKINICIO = "redirect:/admin/menu";

    public MenuController(PlateServices plateServices, CategoryServices categoryServices, IngredientServices ingredientServices) {
        this.plateServices = plateServices;
        this.categoryServices = categoryServices;
        this.ingredientServices = ingredientServices;
    }

    @GetMapping
    public String showMenu(@RequestParam(name = "sortBy",defaultValue = "name") String sortBy,
                           @RequestParam(name = "categoryFilter",required = false) Long categoryId,
                           Model model) {

        List<Plate> menu;

        if(categoryId != null){
            menu =  plateServices.findByCategoriesId(categoryId);
        }else {

            if ("name".equals(sortBy)) {
                menu = plateServices.getAllPlatesOrderedByName();
            } else if ("price".equals(sortBy)) {
                menu = plateServices.getAllPlatesOrderedByPrice();
            } else {
                menu = plateServices.getAllPlates();
            }
        }
        List<Category> allCategories = categoryServices.getAllCategories();
        List<Ingredient> ingredients = ingredientServices.getAllIngredients();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("menu", menu);
        model.addAttribute("ingredients", ingredients);
        return "admin/menu";
    }


    @PostMapping("/addPlate")
    public String addPlate(@ModelAttribute("plate") Plate plate,
                           @RequestParam(name = "ingredients", required = false) List<Long> idsIngredients,
                           @RequestParam(name = "pictureFile") MultipartFile pictureFile) throws IOException {

        byte[] pictureBytes = pictureFile.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(pictureBytes);

        plate.setRuta(base64Image);
        List<Ingredient> ingredients = ingredientServices.getAllIngredientsByIds(idsIngredients);
        Set<Ingredient> ingredientSet = new HashSet<>(ingredients);

        plate.setIngredients(ingredientSet);
        Set<Plate> plates;
        for (Ingredient ingredient: ingredients) {
            plates = ingredient.getPlates();
            plates.add(plate);
            ingredient.setPlates(plates);
        }

        plateServices.addPlate(plate);


        return LINKINICIO;
    }


    @GetMapping("/editPlate/{id}")
    public String showEditPlateForm(@PathVariable String id, Model model) {
        long plateId = Long.parseLong(id);
        Optional<Plate> plate = plateServices.getPlateById(plateId);
        List<Category> allCategories = categoryServices.getAllCategories();
        List<Ingredient> ingredients = ingredientServices.getAllIngredients();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("plate", plate);
        model.addAttribute("ingredients",ingredients);
        return "admin/editPlate";
    }

    @PostMapping("/editPlate/{id}")
    public String editPlate(@PathVariable String id, @ModelAttribute("plate") Plate plate,
                            @RequestParam(name = "ingredients", required = false) List<Long> idsIngredients) {
        long plateId = Long.parseLong(id);
        Optional<Plate> existingPlate = plateServices.getPlateById(plateId);
        if(existingPlate.isPresent()) {
            List<Ingredient> ingredients = ingredientServices.getAllIngredientsByIds(idsIngredients);
            Set<Ingredient> ingredientSet = new HashSet<>(ingredients);
            plate.setId((int) plateId);
            plate.setIngredients(ingredientSet);
            Set<Plate> plates;
            for (Ingredient ingredient: ingredients) {
                plates = ingredient.getPlates();
                plates.add(plate);
                ingredient.setPlates(plates);
            }
            plateServices.updatePlate(plate);
        }
        return LINKINICIO;
    }

    @RequestMapping("/deletePlate/{id}")
    public String deletePlate(@PathVariable String id) {
        long plateId = Long.parseLong(id);
        plateServices.deletePlate(plateId);
        return LINKINICIO;
    }
}


