package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customer")
    public ModelAndView showListCustomer() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveCustomer(@ModelAttribute(name = "customer") Customer customer) {
        ModelAndView modelAndView=new ModelAndView("/create");
        customerService.save(customer);
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id){
       customerService.remove(id);
       return "redirect:/customer";
    }
    @GetMapping("{id}/edit")
    public ModelAndView editCustomer(@PathVariable Long id){
     ModelAndView modelAndView=new ModelAndView("/edit");
     modelAndView.addObject("customer",customerService.findById(id));
     return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
    ModelAndView modelAndView=new ModelAndView("/edit");
    customerService.save(customer);
    modelAndView.addObject("customer",customer);
    return modelAndView;
    }
}
