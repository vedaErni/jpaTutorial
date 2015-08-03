package ch.erni.jpa.tutorial.mvc.controller;


import ch.erni.jpa.tutorial.dao.DepartmentDao;
import ch.erni.jpa.tutorial.dao.DepartmentDaoRemote;
import ch.erni.jpa.tutorial.dao.EmployeeDao;
import ch.erni.jpa.tutorial.model.Department;
import ch.erni.jpa.tutorial.util.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.interceptor.Interceptors;
import javax.naming.*;
import javax.validation.Valid;

/**
 * Created by veda on 7/22/2015.
 */

@Controller(value = "home")
public class HomeController {

    //@Resource(mappedName = "java:global/JpaTutorial/DepartmentDao!ch.erni.jpa.tutorial.dao.DepartmentDao")
    @EJB(mappedName = "global/jpaTutorial/DepartmentDao")
    private DepartmentDaoRemote departmentDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws NamingException {

        InitialContext ctx = new InitialContext();

        NamingEnumeration<NameClassPair> list = ctx.list("");
        while (list.hasMore()) {
            System.out.println(list.next().getName());
        }
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public ModelAndView saveGame(@ModelAttribute @Valid Department department, BindingResult bindingResult) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        departmentDao.save(department);
        modelAndView.addObject("dept_name", department.getDeptName());
        modelAndView.addObject("stage", department.getStage());

        return modelAndView;
    }

}
