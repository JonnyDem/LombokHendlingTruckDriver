package tears.services.printservice.impl;

import tears.dao.TruckDriverDao;
import tears.dao.factory.DaoFactory;
import tears.services.printservice.PrintDriverService;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Ваня on 06.09.2017.
 */
public class PrintDriverServiceImpl implements PrintDriverService {

    @Override
    public void printDriverFromFile() throws IOException {
        TruckDriverDao truckDriverDao= DaoFactory.getTruckDriverDao();
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter path of file you want to get from (example: C:\\Users\\Ваня\\IdeaProjects\\HendlingTruckDriver\\src\\main\\resources\\Data of drivers.txt )");
        String path=scan.nextLine();
        File file = new File(path);
        if(!file.exists()){
            System.out.println("This file doesn't exist");
            printDriverFromFile();
            return;
        } else {
            System.out.println("Enter driver id you want to get from file");
            int driverId = scan.nextInt();
            if(!truckDriverDao.isDriverExists(driverId)){
                System.out.println("This driver doesn't exist in data base.");
                printDriverFromFile();
                return;
            }
            FileReader fr=new FileReader(path);
            BufferedReader bufferedReader=new BufferedReader(fr);
            String buff;
            int count=0;
            while ((buff=bufferedReader.readLine())!=null){
                if(buff.equals("Driver id: "+driverId)){
                    count++;
                   while (!buff.equals("")){
                       System.out.println(buff);
                       buff=bufferedReader.readLine();
                   }
                }
            } if(count==0){
                System.out.println("This driver doesn't exist in the file");
            }
        }

    }
}
