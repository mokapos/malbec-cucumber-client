package cucumber.main;

import cucumber.CucumberMerging;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        try {
            String method = args[0];

            CucumberMerging cucumberMerging = new CucumberMerging();

            if (method.equals("merge")){
                String pathCucumberparent = args[1];
                String pathCucumberRerun = args[2];

                try {
                    cucumberMerging.replaceMethod(pathCucumberparent, pathCucumberRerun,"merged-cucumber.json");
                    System.out.println("successfully merged : merged-cucumber.json");
                } catch (IOException e) {
                    System.err.println("Failed to merge. Error details : " + e.getMessage());
                }

            }else if (method.equals("join")){

                String dirPath = args[1];
                try {
                    cucumberMerging.joinMethod(dirPath, "joined-cucumber.json");
                } catch (IOException e) {
                    System.err.println("Failed to join. Error details : " + e.getMessage());
                }

            }
            else{
                System.out.println("Sorry! For now we only have \"merge\" option");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Error!! " + e.getMessage());
        }
    }
}
