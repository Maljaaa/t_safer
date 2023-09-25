package com.sj.t_safer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@RequiredArgsConstructor
@Transactional
public class TSaferApplication {

	private final EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(TSaferApplication.class, args);
	}

	@Scheduled(cron = "0 0 6 * * *")
	public void run() {
		entityManager.createNativeQuery("CALL hcp.proc_metal()").executeUpdate();
		entityManager.createNativeQuery("CALL hcp.proc_temp()").executeUpdate();
		System.out.println("데이터가 성공적으로 입력되었습니다.");
	}
}
