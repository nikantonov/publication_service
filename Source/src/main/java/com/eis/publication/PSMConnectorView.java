package com.eis.publication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.eis.publication.PublicationRepository;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.List;

//Used for Thymeleaf pages
//https://spring.io/guides/gs/handling-form-submission

@Controller
public class PSMConnectorView {
    PublicationRepository inMemoryRepository = PublicationRepository.getRepository();
    int GlobalUserId = 5;
    InstanceMgmtService ims = new InstanceMgmtService();

    @GetMapping("/view/{userid}/publication")
    public String greetingForm(Model model, @PathVariable("userid") int uid) {
        GlobalUserId = uid;
        boolean r = ims.check(GlobalUserId);
        if(r == false){
            ims.add(GlobalUserId);
        }
        return "start";
    }

    @GetMapping("/error")
    public String greetingForm() {
        return "error";
    }

    @GetMapping("/view")
    public String greetingForm(Model model) {
        model.addAttribute("publication", new Publication());
        //try {
        //   inMemoryRepository.addPublication(pp);
        //return new ResponseEntity(HttpStatus.OK);
        //}catch(Exception e){
        //return new ResponseEntity(HttpStatus.BAD_REQUEST);
        // }
        return "publication";
    }

    @GetMapping("/view/searchpage")
    public String button2(Model model){
        model.addAttribute("searchkey", new Publication());
        return "search_template";
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String submit(
            @ModelAttribute("publication") Publication pub,
            BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        pub.setUserId(GlobalUserId);
        model.addAttribute("UserId", pub.getUserId());
        model.addAttribute("Titel", pub.getTitel());
        model.addAttribute("Author", pub.getAuthor());
        model.addAttribute("Year", pub.getYear());
        model.addAttribute("Publisher", pub.getPublisher());

        try {
            inMemoryRepository.addPublication(pub);
            //return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
           // return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return "result";
    }

    @GetMapping("/view/search/")
    public String button3(@ModelAttribute("searchkey") Publication pub, BindingResult allyourbooks, ModelMap model){
        if (allyourbooks.hasErrors()) {
            return "error";
        }
        model.addAttribute("Titel", pub.getTitel());
        List<Publication> p = null;
        try {
            p = inMemoryRepository.retrieveByTitel(pub.getTitel());
        }catch(Exception e) {
        }
        Publication[] array = p.toArray(new Publication[p.size()]);
        model.addAttribute("publications", array);
        return "allyourbooks";
    }

    @GetMapping("/view/getbyid")
    public String button(Model model){
        List<Publication> p = null;
        try {
            p = inMemoryRepository.retrieveByUser(GlobalUserId);
        }catch(Exception e) {
        }
        Publication[] array = p.toArray(new Publication[p.size()]);
        model.addAttribute("publications", array);
        return "allyourbooks";
    }


    //localhost:8080/view/searchnull?titel=German

    /*@GetMapping(path="/getbytitel/{titel}")
    @ResponseBody
    public Publication getByTitel(@PathVariable("titel") String titel){
        Publication p = null;
        try {
            p = inMemoryRepository.retrieveByTitel(titel);
        }catch(Exception e){
        }
        return p;
    }*/

    /*@GetMapping(path="/getbyid/{userid}")
    @ResponseBody
    public List<Publication> getById(@PathVariable("userid") int userid){
        List<Publication> p = null;
        try {
            p = inMemoryRepository.retrieveByUser(userid);
        }catch(Exception e) {
        }
        return p;
    }*/

    /*@PostMapping("/view")
    public String greetingSubmit(@ModelAttribute Publication greeting) {
        return "result";
    }*/

    /*@PostMapping("/add")
    public @ResponseBody
    ResponseEntity newPublication(@RequestBody Publication p){
        Publication pp = new Publication(p.getUserId(), p.getTitel(), p.getAuthor(), p.getYear(),p.getPublisher());
        System.out.println(pp.getTitel());
        try {
            inMemoryRepository.addPublication(pp);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }*/


    @DeleteMapping("/delete/{titel}")
    public void deletePub(@PathVariable String titel){
        try {
            inMemoryRepository.deletePublication(titel);
        }catch(Exception e){
            System.out.println("Error");
        }
    }
    /*@DeleteMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        usersearchRepository.deleteById(id);
    }*/

}


//http://localhost:8181/admin/42/home