package com.project;

public class Main {


    public static void main(String[] args) {


        Production production = new Production();

        production.startProduction();

        /*

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //scan i package navn efter komponeter
        context.scan("");
        //skal man kalde
        context.refresh();




        for (Map.Entry<String, iConnect> iConnectEntry: context.getBeansOfType(iConnect.class).entrySet()) {
            System.out.println(iConnectEntry);
            System.out.println(iConnectEntry.getKey());
            System.out.println(iConnectEntry.getValue());
            iConnectEntry.getValue().connect();

        }

        for (Map.Entry<String, com.project.IAGVControlSystem> iagvControlSystemEntry: context.getBeansOfType(com.project.IAGVControlSystem.class).entrySet()) {
            System.out.println("henlo");
            iagvControlSystemEntry.getValue().loadProgram("MoveToStorageOperation", "1");
        }

        // kan lave andre for loops til andre com.project.interfaces kig på Jan

        // kan også lave andre metoder som Jan gør (render og draw?)


         */
    }

}
