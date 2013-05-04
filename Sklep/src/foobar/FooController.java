package foobar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/foo")
public class FooController {

    @RequestMapping(method = RequestMethod.POST, value = "bar.json", produces="application/json")
    @ResponseBody
    public A bar(@RequestBody final A b) {
    	A a = new A();
    	a.setY(b.getX());
    	a.setX(b.getY());
    	return a;    	
    }

    @RequestMapping(method = RequestMethod.GET, value = "baz.htm")
    public ModelAndView baz() 
    {
    	return new ModelAndView("HelloWorldPage");	
    }
}
