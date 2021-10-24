package com.alex.hibernatetest;

import com.alex.hibernatetest.dao.AccountDAO;
import com.alex.hibernatetest.entity.Account;
import com.alex.hibernatetest.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account("Alex", 24);
        Account account2 = new Account("Alex", 24);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(10));
        bills.add(new Bill(12));
        bills.add(new Bill(14));
        bills.add(new Bill(16));
        bills.add(new Bill(18));
        bills.add(new Bill(20));
        account.setBills(bills);
        Account save = accountDAO.save(account);
        Account save2 = accountDAO.save(account2);
        System.out.println(save);
        System.out.println(save2);

        Account byId = accountDAO.findById(save.getAccountId());
        System.out.println(byId);

        System.out.println(byId.getBills());
    }
}
