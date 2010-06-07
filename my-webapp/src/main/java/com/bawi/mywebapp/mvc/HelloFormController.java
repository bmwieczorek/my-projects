package com.bawi.mywebapp.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class HelloFormController extends SimpleFormController {

    /** Logger for this class and subclasses */
    // private final Log logger = LogFactory.getLog(getClass());

    /*
     * public HelloFormController() { logger.error("in conctuctor = "); setCommandClass(HelloFormParams.class);
     * setCommandName("processPostRequest"); setSessionForm(true); }
     */

    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws ServletException, Exception {

        String inputValue = ((HelloFormParams) command).getInputValue();
        String selectValue = ((HelloFormParams) command).getSelectValue();

        Map model = errors.getModel();
        model.put("inputValue", inputValue);
        model.put("selectValue", selectValue);

        return showForm(request, response, errors, model);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {

        HelloFormParams helloFormParams = (HelloFormParams) super.formBackingObject(request);
        helloFormParams.setInputValue("faffsdfsd");

        return helloFormParams;
    }

    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("inputValue", "World");
        List<String> selectValues = new ArrayList<String>();
        selectValues.add("1");
        selectValues.add("2");
        selectValues.add("3");
        selectValues.add("4");
        selectValues.add("5");
        selectValues.add("8");
        selectValues.add("9");
        map.put("selectValues", selectValues);

        return map;
    }
}
