package com.connectionpool_concurrence_test;

import com.integrational_dao.IntegroDishDAO;
import com.integrational_dao.IntegroUserDAO;

public class ConnectionPoolTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntegroDishDAO dishDAO = new IntegroDishDAO();

                System.out.println("thread_1 - getById_1");
                dishDAO.getById();
                System.out.println("thread_1 - getById_2");
                dishDAO.getById();
                System.out.println("thread_1 - getById_3");
                dishDAO.getById();
                System.out.println("thread_1 - getById_4");
                dishDAO.getById();
                System.out.println("thread_1 - getById_5");
                dishDAO.getById();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread_1 - getByName_1");
                dishDAO.getByName();
                System.out.println("thread_1 - getByName_2");
                dishDAO.getByName();
                System.out.println("thread_1 - getByName_3");
                dishDAO.getByName();
                System.out.println("thread_1 - getByName_4");
                dishDAO.getByName();
                System.out.println("thread_1 - getByName_5");
                dishDAO.getByName();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntegroUserDAO userDAO = new IntegroUserDAO();

                System.out.println("thread_2 - getById_1");
                userDAO.getById();
                System.out.println("thread_2 - getById_2");
                userDAO.getById();
                System.out.println("thread_2 - getById_3");
                userDAO.getById();
                System.out.println("thread_2 - getById_4");
                userDAO.getById();
                System.out.println("thread_2 - getById_5");
                userDAO.getById();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread_2 - getByName_1");
                userDAO.getUserByName();
                System.out.println("thread_2 - getByName_2");
                userDAO.getUserByName();
                System.out.println("thread_2 - getByName_3");
                userDAO.getUserByName();
                System.out.println("thread_2 - getByName_4");
                userDAO.getUserByName();
                System.out.println("thread_2 - getByName_5");
                userDAO.getUserByName();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntegroUserDAO userDAO = new IntegroUserDAO();

                System.out.println("thread_3 - getById_1");
                userDAO.getById();
                System.out.println("thread_3 - getById_2");
                userDAO.getById();
                System.out.println("thread_3 - getById_3");
                userDAO.getById();
                System.out.println("thread_3 - getById_4");
                userDAO.getById();
                System.out.println("thread_3 - getById_5");
                userDAO.getById();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread_3 - getByName_1");
                userDAO.getUserByName();
                System.out.println("thread_3 - getByName_2");
                userDAO.getUserByName();
                System.out.println("thread_3 - getByName_3");
                userDAO.getUserByName();
                System.out.println("thread_3 - getByName_4");
                userDAO.getUserByName();
                System.out.println("thread_3 - getByName_5");
                userDAO.getUserByName();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
