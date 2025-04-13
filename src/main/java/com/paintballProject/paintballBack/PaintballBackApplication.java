package com.paintballProject.paintballBack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.paintballProject.paintballBack.**.mapper")
public class PaintballBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaintballBackApplication.class, args);
	}

}
