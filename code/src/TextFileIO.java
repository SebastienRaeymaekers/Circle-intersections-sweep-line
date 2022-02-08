import javafx.scene.shape.Circle;

import java.io.*;
import java.util.List;

public class TextFileIO {
    public static void readFromInputFile(CircleIntersectionsProgram circleIntersectionsProgram) throws IOException {
        // open the file
        FileInputStream fstream = new FileInputStream(circleIntersectionsProgram.inputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        // read Algorithm number
        if((strLine = br.readLine()) != null){
            circleIntersectionsProgram.algorithmNumber = Integer.parseInt(strLine);
            System.out.println(circleIntersectionsProgram.algorithmNumber);
        }

        // read number of circles
        if((strLine = br.readLine()) != null){
            circleIntersectionsProgram.numberOfCircles = Integer.parseInt(strLine);
            System.out.println(circleIntersectionsProgram.numberOfCircles);
        }

        // read properties of each circle and add circle object to allcircles list.
        while ((strLine = br.readLine()) != null)   {
            String[] circleProperties = strLine.split(" ");
            circleIntersectionsProgram.allCircles.add(new Circle(Double.parseDouble(circleProperties[0]),
                    Double.parseDouble(circleProperties[1]),
                    Double.parseDouble(circleProperties[2])));
            System.out.println(strLine);
        }
        fstream.close(); // close the input stream
    }

    public static void writeToOutputFile(String file, List<Point> intersectionPoints, long elapsedTime) throws IOException {
        PrintWriter f0 = new PrintWriter(new FileWriter(file));
        for(int i = 0; i < intersectionPoints.size(); i++) { // write line per line in file.
            f0.println(intersectionPoints.get(i));
        }
        f0.println();
        f0.println(elapsedTime);
        f0.close(); // close the output stream
    }

}
