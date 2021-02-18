package com.laioffer.travel_planner_backend.controller;


@Controller
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @RequestMapping (value = "place/addPlace", method = RequestMathod.POST)
    public String addPlace (@ModelAttribute Place place, BindindResult result); {
        if (result.hasErrors()) {
            return "addPlace";
        }
        placeService.addPlace(place);
        return "redirect:/getAllPlace";
    }

    @RequestMapping (value = "delete/{placeId}")
    public String deletePlace(@PathVariable(value = "placeId") int placeId) {
        placeService.deletePlace(palceId);
        return "redirect:/getAllProduct";
    }

}