package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value="/results", method = RequestMethod.GET)
    public String processSearch(Model model, @RequestParam(name = "searchType", defaultValue = "") String searchType, @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm){

        if((searchTerm.length() <= 0) || (searchType.length() <= 0)){
            return "redirect:/search";
        }
        ArrayList<HashMap<String, String>> resultSet = new ArrayList<>();
        resultSet = (searchType.equals("all")) ? JobData.findByValue(searchTerm) : JobData.findByColumnAndValue(searchType, searchTerm);
        if(resultSet.size() > 0) model.addAttribute("jobs",resultSet);
        if(searchType != null) model.addAttribute("checkColumn", searchType);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

}
