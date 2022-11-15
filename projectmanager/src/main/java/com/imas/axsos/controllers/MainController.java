package com.imas.axsos.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imas.axsos.models.LoginUser;
import com.imas.axsos.models.Project;
import com.imas.axsos.models.Task;
import com.imas.axsos.models.User;
import com.imas.axsos.services.ProjectService;
import com.imas.axsos.services.ProjectTaskService;
import com.imas.axsos.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectTaskService taskService;
	
	@GetMapping("/")
	public String index(Model model) {
	    model.addAttribute("newUser", new User());
	    model.addAttribute("newLogin", new LoginUser());
	    return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, Model model, HttpSession session) {

	    User user = userService.register(newUser, result);
	     
	    if(result.hasErrors()) {
	        model.addAttribute("newLogin", new LoginUser());
	        return "index.jsp";
	    }
	    session.setAttribute("userId", user.getId());
	 
	    return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
			BindingResult result, Model model, HttpSession session) {
	     
		User user = userService.login(newLogin, result);
	 
	    if(result.hasErrors() || user==null) {
	        model.addAttribute("newUser", new User());
	        return "index.jsp";
	    }
	     
	    session.setAttribute("userId", user.getId());
	 
	    return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
	 
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		model.addAttribute("user", userService.findById(userId));
		model.addAttribute("unassignedProjects", projectService.getUnassignedUsers(userService.findById(userId)));
		model.addAttribute("assignedProjects", projectService.getAssignedUsers(userService.findById(userId)));
		 
		return "dashboard.jsp";
	}
	
	@RequestMapping("/dashboard/join/{id}")
	public String joinTeam(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		Project project = projectService.findById(id);
		User user = userService.findById(userId);
		
		user.getProjects().add(project);
		userService.updateUser(user);
		
		model.addAttribute("user", userService.findById(userId));
		model.addAttribute("unassignedProjects", projectService.getUnassignedUsers(user));
		model.addAttribute("assignedProjects", projectService.getAssignedUsers(user));
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping("/dashboard/leave/{id}")
	public String leaveTeam(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		Project project = projectService.findById(id);
		User user = userService.findById(userId);
		
		user.getProjects().remove(project);
		userService.updateUser(user);
		
		model.addAttribute("user", userService.findById(userId));
		model.addAttribute("unassignedProjects", projectService.getUnassignedUsers(user));
		model.addAttribute("assignedProjects", projectService.getAssignedUsers(user));
		
		return "redirect:/dashboard";
	}

	@GetMapping("/projects/{id}")
	public String viewProject(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Project project = projectService.findById(id);
		model.addAttribute("project", project);
		return "view_project.jsp";
	}
	
	@GetMapping("/projects/edit/{id}")
	public String openEditProject(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Project project = projectService.findById(id);
		model.addAttribute("project", project);
		return "edit_project.jsp";
	}
	
	@PostMapping("/projects/edit/{id}")
	public String editProject(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("project") Project project, 
			BindingResult result, 
			HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		User user = userService.findById(userId);
		
		if(result.hasErrors()) {
			return "edit_project.jsp";
		}else {
			Project thisProject = projectService.findById(id);
			project.setUsers(thisProject.getUsers());
			project.setLead(user);
			projectService.updateProject(project);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		User user = userService.findById(userId);
		
		Project project = projectService.findById(id);
		
		// We need to make sure all tasks associated with the project we are deleting are deleted first
		for(Task t:taskService.projectTasks(id)) {
			taskService.deleteTask(t);
		}
		
		// Once the tasks are deleted, we can safely delete our project
		projectService.deleteProject(project);
		model.addAttribute("unassignedProjects", projectService.getUnassignedUsers(user));
		model.addAttribute("assignedProjects", projectService.getAssignedUsers(user));
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null); 
	    return "redirect:/";
	}
	
	@GetMapping("/projects/new")
	public String newProject(@ModelAttribute("project") Project project, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		return "new_project.jsp";
	}
	
	@PostMapping("/projects/new")
	public String addNewProject(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		if(result.hasErrors()) {
			return "new_project.jsp";
		}else {
			User user = userService.findById(userId);
			Project newProject = new Project(project.getTitle(), project.getDueDate(), project.getDescription(), project.getLead());
			newProject.setLead(user);
			projectService.addProject(newProject);
			user.getProjects().add(newProject);
			userService.updateUser(user);
			return "redirect:/dashboard";
		}
	}

	@GetMapping("/projects/{id}/tasks")
	public String viewProjectTasks(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("task") Task task) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Project project = projectService.findById(id);
		model.addAttribute("project", project);
		model.addAttribute("tasks", taskService.projectTasks(id));
		return "tasks.jsp";
	}
	
	@PostMapping("/projects/{id}/tasks")
	public String newProjectTask(
			@PathVariable("id") Long id, 
			HttpSession session, 
			Model model, 
			@Valid @ModelAttribute("task") Task task, 
			BindingResult result) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		Project project = projectService.findById(id);
		
		if(result.hasErrors()) {
			model.addAttribute("project", project);
			model.addAttribute("tasks", taskService.projectTasks(id));
			return "tasks.jsp";
		}else {
			Task newTask = new Task(task.getName());
			newTask.setProject(project);
			newTask.setCreator(userService.findById(userId));
			taskService.addTask(newTask);
			return "redirect:/projects/" + id + "/tasks";
		}
	}
	
}