package foobar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smiechmateusz.controller.authenticate.Authentication;
import com.smiechmateusz.controller.authenticate.RequireAuthentication;

@Controller
@RequestMapping("/foo")
public class FooController
{

    @RequestMapping(method = RequestMethod.POST, value = "bar.json", produces="application/json")
    @ResponseBody
    public A bar(@RequestBody final A b) {
    	A a = new A();
    	a.setY(b.getX());
    	a.setX(b.getY());
    	return a;    	
    }

    @RequestMapping(method = RequestMethod.GET, value = "baz.htm")
    @RequireAuthentication(authenticationLevel=1)
    public ModelAndView baz() 
    {
    	//logIn("a", "b");
    	return new ModelAndView("HelloWorldPage");	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "bazz.htm")
    public ModelAndView bazz()
    {
    	return new ModelAndView("HelloWorldPage");
    }
}
