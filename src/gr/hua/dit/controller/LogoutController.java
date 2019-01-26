package gr.hua.dit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logoutController")
public class LogoutController {
	
	@PostMapping("/logouts")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {

			for (Cookie cookie : cookies) {

				if (cookie.getName().equals("JSESSIONID")) {

					System.out.println("JSESSIONID=" + cookie.getValue());

					break;

				}

			}

		}

		// invalidate the session if exists

		HttpSession session = request.getSession(false);

		System.out.println("User=" + session.getAttribute("user"));

		if (session != null) {

			session.invalidate();

		}

		response.sendRedirect("login");
	}

}
