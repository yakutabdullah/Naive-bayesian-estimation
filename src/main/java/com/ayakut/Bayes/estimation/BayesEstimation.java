package com.ayakut.Bayes.estimation;

import com.ayakut.Bayes.dto.PlayGolfDto;
import com.ayakut.Bayes.interfaces.UserCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BayesEstimation extends Thread {

    public PlayGolfDto userVal = new PlayGolfDto();

    public List<PlayGolfDto> dataSetList = new ArrayList<PlayGolfDto>();

    public void run() {

        readValueFromFile();
        Integer val = 0;

        //Question
        userVal.setOutlook(UserCommand.USER_OUTLOOK);
        userVal.setTemp(UserCommand.USER_TEMP);
        userVal.setHumidity(UserCommand.USER_HUMIDITY);
        userVal.setWindly(UserCommand.USER_WINDLY);

        double x0Val = 0;//P(X0|YES) || P(X0|No)
        double x1Val = 0;//P(X1|YES) || P(X0|No)
        double x2Val = 0;//P(X2|YES) || P(X0|No)
        double x3Val = 0;//P(X3|YES) || P(X0|No)
        double x4Val = 0;//P(X4|YES) || P(X0|No)

        for (PlayGolfDto dt : dataSetList) {

            //P(YES|X) = P(X0|YES) * P(X1|YES) * P(X2|YES) * P(X3|YES) * P(YES)

            if (dt.getOutlook().equals(userVal.getOutlook())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_YES)) {
                    x0Val++;
                }
            }
            if (dt.getTemp().equals(userVal.getTemp())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_YES)) {
                    x1Val++;
                }
            }
            if (dt.getHumidity().equals(userVal.getHumidity())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_YES)) {
                    x2Val++;
                }
            }
            if (dt.getWindly().equals(userVal.getWindly())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_YES)) {
                    x3Val++;
                }
            }
            if (dt.getIsPlay().equals(UserCommand.COMMAND_YES)) {
                x4Val++;
            }
        }

        System.out.println("P(X0|YES)   :" + x0Val + "/" + x4Val);
        System.out.println("P(X1|YES)   :" + x1Val + "/" + x4Val);
        System.out.println("P(X2|YES)   :" + x2Val + "/" + x4Val);
        System.out.println("P(X3|YES)   :" + x3Val + "/" + x4Val);
        System.out.println("P(YES)      :" + x4Val + "/" + dataSetList.size());
        //P(YES|X) =
        double yesVal = ((x0Val / x4Val) * (x1Val / x4Val) * (x2Val / x4Val) *
                (x3Val / x4Val) * (x4Val / dataSetList.size()));

        System.out.println("");
        System.out.println("P(YES|X)    :" + yesVal);

        System.out.println("\n");
        x0Val = 0;
        x1Val = 0;
        x2Val = 0;
        x3Val = 0;
        x4Val = 0;

        for (PlayGolfDto dt : dataSetList) {

            //P(No|X) = P(X0|No) * P(X1|No) * P(X2|No) * P(X3|No) * P(No)

            if (dt.getOutlook().equals(userVal.getOutlook())) {
                if (dt.getIsPlay().equals("No")) {
                    x0Val++;
                }
            }
            if (dt.getTemp().equals(userVal.getTemp())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_NO)) {
                    x1Val++;
                }
            }
            if (dt.getHumidity().equals(userVal.getHumidity())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_NO)) {
                    x2Val++;
                }
            }
            if (dt.getWindly().equals(userVal.getWindly())) {
                if (dt.getIsPlay().equals(UserCommand.COMMAND_NO)) {
                    x3Val++;
                }
            }
            if (dt.getIsPlay().equals(UserCommand.COMMAND_NO)) {
                x4Val++;
            }
        }
        //P(No|X) =
        double noVal = ((x0Val / x4Val) * (x1Val / x4Val) * (x2Val / x4Val) *
                (x3Val / x4Val) * (x4Val / dataSetList.size()));

        System.out.println("P(X0|No)        :" + x0Val + "/" + x4Val);
        System.out.println("P(X1|No)        :" + x1Val + "/" + x4Val);
        System.out.println("P(X2|No)        :" + x2Val + "/" + x4Val);
        System.out.println("P(X3|No)        :" + x3Val + "/" + x4Val);
        System.out.println("P(No)           :" + x4Val + "/" + dataSetList.size());
        System.out.println("");
        System.out.println("P(No|X) : " + noVal);
        System.out.println("");

        if (yesVal > noVal) {
            userVal.setIsPlay("Yes");
        } else {
            userVal.setIsPlay("No");
        }
        System.out.println("=============PLAY GOLF===============");
        System.out.println(userVal.getOutlook() + "  " + userVal.getTemp() + "  " + userVal.getHumidity() + "  " + userVal.getWindly() + "  " + userVal.getIsPlay());
        System.out.println("=====================================");


    }

    void getOutlook() {
        Integer type = 0;
        try {
            System.out.print("========= Outlook ============ \n");
            System.out.print("            1. Rainy.         \n");
            System.out.print("            2. Overcast.      \n");
            System.out.print("            3. Sunny.         \n");
            System.out.print("            4. Exit.          \n");
            System.out.print("Enter your choice: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            type = Integer.parseInt(br.readLine());
            switch (type) {
                case 1:
                    userVal.setOutlook("Rainy");
                    break;
                case 2:
                    userVal.setOutlook("Overcast");
                    break;
                case 3:
                    userVal.setOutlook("Sunny");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } catch (IOException ex) {
            System.exit(0);
        }

    }

    void getTemp() {
        Integer type = 0;
        try {
            System.out.print("========= Temp ============ \n");
            System.out.print("            1. Hot.           \n");
            System.out.print("            2. Mild.          \n");
            System.out.print("            3. Cool.          \n");
            System.out.print("            4. Exit.          \n");
            System.out.print("Enter your choice: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            type = Integer.parseInt(br.readLine());
            switch (type) {
                case 1:
                    userVal.setTemp("Hot");
                    break;
                case 2:
                    userVal.setTemp("Mild");
                    break;
                case 3:
                    userVal.setTemp("Cool");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    void getHumidity() {
        Integer type = 0;
        try {
            System.out.print("========= Humidity ============ \n");
            System.out.print("            1. High.           \n");
            System.out.print("            2. Normal.          \n");
            System.out.print("            4. Exit.          \n");
            System.out.print("Enter your choice: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            type = Integer.parseInt(br.readLine());
            switch (type) {
                case 1:
                    userVal.setHumidity("High");
                    break;
                case 2:
                    userVal.setHumidity("Normal");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    void getWindly() {
        Integer type = 0;
        try {
            System.out.print("========= Windly ============ \n");
            System.out.print("            1. False.             \n");
            System.out.print("            2. True.              \n");
            System.out.print("            4. Exit.              \n");
            System.out.print("Enter your choice: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            type = Integer.parseInt(br.readLine());
            switch (type) {
                case 1:
                    userVal.setWindly("False");
                    break;
                case 2:
                    userVal.setWindly("True");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public List<PlayGolfDto> readValueFromFile() {
        FileInputStream inputStream = null;

        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File configFile = new File(classLoader.getResource("golf.cvs").getFile());
            inputStream = new FileInputStream(configFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                dataSetList.add(new PlayGolfDto(line.split(",")[0].trim(), line.split(",")[1].trim(),
                        line.split(",")[2].trim(),
                        line.split(",")[3].trim(), line.split(",")[4].trim()));
            }
            System.out.println("\n");
            reader.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataSetList;
    }
}

