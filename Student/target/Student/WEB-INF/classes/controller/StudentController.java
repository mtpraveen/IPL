package controller;
@Controller
@SessionAttributes("student")
public class StudentController {
  
  @Autowired
  private StudentService studentService;
      
  @RequestMapping(value="/signup", method=RequestMethod.GET)
  public String signup(Model model) {
      Student student = new Student();        
      model.addAttribute("student", student);     
      return "signup";
  }
  
  @RequestMapping(value="/signup", method=RequestMethod.POST)
  public String signup(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {        
      if(result.hasErrors()) {
          return "signup";
      } else if(studentService.findByUserName(student.getUserName())) {
          model.addAttribute("message", "User Name exists. Try another user name");
          return "signup";
      } else {
          studentService.save(student);
          model.addAttribute("message", "Saved student details");
          return "redirect:login.html";
      }
  }

  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String login(Model model) {          
      StudentLogin studentLogin = new StudentLogin();     
      model.addAttribute("studentLogin", studentLogin);
      return "login";
  }
  
  @RequestMapping(value="/login", method=RequestMethod.POST)
  public String login(@Valid @ModelAttribute("studentLogin") StudentLogin studentLogin, BindingResult result) {
      if (result.hasErrors()) {
          return "login";
      } else {
          boolean found = studentService.findByLogin(studentLogin.getUserName(), studentLogin.getPassword());
          if (found) {                
              return "success";
          } else {                
              return "failure";
          }
      }
      
  }
}