package com.test.auction_system.controller;

import com.test.auction_system.commands.BidCommand;
import com.test.auction_system.commands.CarCommand;
import com.test.auction_system.model.Bid;
import com.test.auction_system.model.Car;
import com.test.auction_system.repositories.BidRepository;
import com.test.auction_system.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class IndexController {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BidRepository bidRepository;

    @GetMapping("/")
    public String homePage(@PageableDefault(size = 12,sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("carlist",carRepository.findAll(pageable));
        return "home";
    }

    @GetMapping("/newcarpage")
    public String InputNewCar(Model model){
        CarCommand carcommand=new CarCommand();
        model.addAttribute("newcar", carcommand);
        return "input_car";
    }

    @GetMapping("/cardetail/{id}")
    public String getcardeail(@PathVariable Long id,Model model){
        BidCommand bidcommand = new BidCommand();
        Car car=carRepository.findById(id).get();
        Sort sort = Sort.by( //construct one sort object, select the highest price record of bid. If there are multi same price, chose the record whose createtime is smaller
                Sort.Order.desc("price"),
                Sort.Order.asc("createtime"));
        Pageable topOne = PageRequest.of(0, 1, sort);
        List<Bid> winnerbid=bidRepository.findwinner(id,topOne); //find who is the current bid winner
        model.addAttribute("car",car);
        model.addAttribute("newbid", bidcommand);
        Pageable history = PageRequest.of(0, 5, Sort.Direction.DESC, "createtime");
        model.addAttribute("bidhistory",bidRepository.findAll(history));
        System.out.println("Size: "+winnerbid.size());
        if(winnerbid.size()!=0)
        model.addAttribute("winnerbid",winnerbid.get(0));
        return "cardetail_page";
    }
    @GetMapping("/gethistory/{id}") //get bid history for one car
    public String gethistory(@PathVariable Long id,Model model,@PageableDefault(size = 5,sort = {"createtime"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        BidCommand bidcommand = new BidCommand();
        Car car=carRepository.findById(id).get();
        Sort sort = Sort.by(
                Sort.Order.desc("price"),
                Sort.Order.asc("createtime"));
        Pageable topOne = PageRequest.of(0, 1, sort);
        List<Bid> winnerbid=bidRepository.findwinner(id,topOne);
        model.addAttribute("car",car);
        model.addAttribute("newbid", bidcommand);
        model.addAttribute("bidhistory",bidRepository.findAll(pageable));
        System.out.println("Size: "+winnerbid.size());
        if(winnerbid.size()!=0)
            model.addAttribute("winnerbid",winnerbid.get(0));
        return "cardetail_page";
    }
    @PostMapping("/cardetail/{id}") //accept bid information for one car
    public String submitbid(
            @ModelAttribute("newbidrecord") @Valid BidCommand bidcommand,
            @PathVariable Long id,
            BindingResult result,
            WebRequest request,
            Errors errors, Model model
    ){

        Calendar startcal = Calendar.getInstance();
        long t= startcal.getTimeInMillis();
        Car car=carRepository.findById(id).get();
        Date starttime=new Date(t);
        if(starttime.compareTo(car.getEndtime())<0) { //check if auction is over
            Bid bid = new Bid();
            bid.setBuyer(bidcommand.getUsername());
            bid.setCarid(id);
            bid.setCreatetime(starttime);
            bid.setPrice(bidcommand.getPrice());
            bidRepository.save(bid);
        }
        else
        {
            model.addAttribute("error","This auction is end now, your bid is not valid");
        }
        BidCommand newbidcommand = new BidCommand();
        Sort sort = Sort.by(
                Sort.Order.desc("price"),
                Sort.Order.asc("createtime"));
        Pageable topOne = PageRequest.of(0, 1, sort);
        List<Bid> winnerbid=bidRepository.findwinner(id,topOne);
        model.addAttribute("car",car);
        model.addAttribute("newbid", newbidcommand);
        Pageable history = PageRequest.of(0, 5, Sort.Direction.DESC, "createtime");
        model.addAttribute("bidhistory",bidRepository.findAll(history));
        System.out.println("Size: "+winnerbid.size());
        if(winnerbid.size()!=0)
            model.addAttribute("winnerbid",winnerbid.get(0));

        return "cardetail_page";
    }

    @PostMapping({"/newcarpage"})
    public String uploadcarrecord(
            @ModelAttribute("newcar") @Valid CarCommand carcommand,
            BindingResult result,
            WebRequest request,
            Errors errors, Model model
    )
    {
        Car newcar = new Car();
        newcar.setCarinfor(carcommand.getCarinfor());
        newcar.setCarname(carcommand.getCarname());
        newcar.setLowestprice(carcommand.getLowestprice());
        newcar.setPublisher(carcommand.getPublisher());
        Calendar startcal = Calendar.getInstance();
        long t= startcal.getTimeInMillis();
        Date starttime=new Date(t);
        Date afterAddingTenMins=new Date(t + (10 * 60000)); //every auction will last for 10 mins
        newcar.setStarttime(starttime);
        newcar.setEndtime(afterAddingTenMins);
        carRepository.save(newcar);
        return "redirect:/";
    }

}
