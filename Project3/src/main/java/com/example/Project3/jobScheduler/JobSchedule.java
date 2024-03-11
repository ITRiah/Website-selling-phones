package com.example.Project3.jobScheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Project3.entity.User;
import com.example.Project3.repo.UserRepo;
import com.example.Project3.service.EmailService;

@Component
public class JobSchedule {
	@Autowired
	UserRepo userRepo;

	@Autowired
	EmailService emailService;

	@Scheduled(cron = "0 0 8 * * *")
	public void hello() {
		LocalDate date = LocalDate.now();
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();

		List<User> users = userRepo.searchByBirthDay(day, month);

		for (User user : users) {
			emailService.sendMailBirthday(user.getEmail(), user.getName());
		}
	}
}
