/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

/**
 *
 * @author Talal Mansour
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class FileReader {
    public static String loadFileIntoString(String filePath, String fileEncoding) throws
     FileNotFoundException, IOException {
        return IOUtils.toString(new FileInputStream(filePath), fileEncoding);
    }
}