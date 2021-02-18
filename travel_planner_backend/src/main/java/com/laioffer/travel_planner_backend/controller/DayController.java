
=======
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DayController {
		@Autowired
		private DayService DayService;
		
		@RequestMapping(value = "/gen_path/{dayId}", method = RequestMethod.POST)
		public void getDayById(@PathVariable(value="dayId") int dayId){
				Day day = DayService.getDayById(dayId);
				DayService.generateDayPath(day);
		}
		

		
>>>>>>> kaichun
}
