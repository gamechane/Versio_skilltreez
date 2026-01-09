package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    /**
     * An error message is written to the error log.
     * @param message
     */
    public static void writeErrorLog(String message) {
        System.out.println("writting error");
        // Basic settings
        String errorFileName = "errors.log";
        String absolutePath = ".";

        String dirFileSP = System.getProperty("file.separator");
        String absoluteFileName = absolutePath + dirFileSP + errorFileName;

        String SP = "; ";

        /**
         * Today's date and time will be generated.
         *
         * Source: https://www.javatpoint.com/java-get-current-date
         */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String todaysDateAndTime = dtf.format(now).replaceAll(" ", SP);

        /**
         * The message will be added as a new line to the file errors.log.
         *
         * Source: https://stackoverflow.com/questions/4614227/how-to-add-a-new-line-of-text-to-an-existing-file-in-java
         */
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(errorFileName, true));  //clears file every time
            output.append(todaysDateAndTime).append(SP).append(message).append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
