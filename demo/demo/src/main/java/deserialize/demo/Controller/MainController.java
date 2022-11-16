package deserialize.demo.Controller;
import java.io.*;

import deserialize.demo.Serial;
import deserialize.demo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/serialize")
    public String showform(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return "serialize";
    }
    @PostMapping("/serialize")
    public static String submitform(@ModelAttribute Student student,Model model) throws IOException {
        String data = Serial.toBase64(student);
        model.addAttribute("test",data);
        return "deserialize";
    }
    @PostMapping("/deserialize")
    public String deser(@RequestParam("deserialize") String data,Model model)throws IOException, ClassNotFoundException{
        Object o=Serial.fromBase64(data);
        System.out.println(o);
        model.addAttribute("test2",o);
        return "deserialize";
    }
    @GetMapping("/deserialize")
    public String deser2(Model model){
        return "deserialize";
    }
}
