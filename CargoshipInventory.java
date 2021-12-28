import java.io.*;
import java.util.Scanner;

public class CargoshipInventory {
    static String InputFile = "Initial_Cargoship_Info.txt";
    static Cargoship[] wsArr = new Cargoship[FileRecords()];
    static Scanner keyboard = new Scanner(System.in);

    private static void displayFileContents(String inputFileName) {
        Scanner input = null;
        try{
            input = new Scanner(new FileReader(inputFileName));
        }catch(FileNotFoundException e){
            System.out.println("Could not find a file to be displyed");
        }
        System.out.println("Here are the contents of the file " +inputFileName);
        for (int i = 0; i < wsArr.length; i++) {
            Cargoship temp = new Cargoship(input.nextLong(), input.next(), input.nextInt(), input.next(), input.nextDouble(), input.nextInt());
            System.out.println(temp);
        }
    }
    private static int FileRecords() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(InputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lines = 0;
        try {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
        return lines;

    }
    private static void ArrayToFile(String OutputFileName){
        FileWriter outpuTofile = null;
        try{
            outpuTofile = new FileWriter(OutputFileName);
        }catch(IOException e){
            System.out.println("An error occured while trying to store values.");
        }
        for(int i = 0; i <wsArr.length; i++){
            try {
                outpuTofile.write(wsArr[i].toString() + "\n");
            } catch (IOException e) {
                System.out.println("An error occured printing the Cargoships to the file.");
            }
        }
        try {
            outpuTofile.close();
        } catch (IOException e) {
            System.out.println("Unable to close file.");
        }
    }
    private static Cargoship [] fileToArray(String InputFile) {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(InputFile));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < wsArr.length; i++) {
            wsArr[i] = new Cargoship(in.nextLong(), in.next(), in.nextInt(), in.next(), in.nextDouble(), in.nextInt());
        }
        return wsArr;
    }
    private static void fixInvertory(String intputStream, String outputStream){
        Scanner in = null;
        try{
            in = new Scanner(new FileReader(intputStream));
        }catch(FileNotFoundException e){
            System.out.println("Unable to open the input file");
        }
        int count = 0;
        while(in.hasNextLine()){
            Cargoship temp = new Cargoship(in.nextLong(), in.next(), in.nextInt(), in.next(), in.nextDouble(), in.nextInt());
            if(isDuplicate(temp.getSerialNum(), count)){
                while(true){
                    try{
                        System.out.print("Duplicate serial number " + temp.getSerialNum() + " at index #" +count +". ");
                        wsArr[count].setSerialNum(getNewSerial());
                        break;
                    }catch(DuplicateSerialNumberException e){
                        System.out.println("Input was an invalid serial number, it either exists already or an invalid input. Please enter a valid new serial number");
                        continue;
                    }
                }
            }
            ++count;
        }
        in.close();
        ArrayToFile(outputStream);
    }
    private static boolean isDuplicate(long serial, int index) {
        for(int i = 0; i < wsArr.length; ++i) {

            if(i == index){
                continue;
            }else if(wsArr[i] == null){
                break;
            }else if(serial == wsArr[i].getSerialNum()){
                return true;
            }
        }
        return false;
    }
    private static long getNewSerial() throws DuplicateSerialNumberException {
        long newSerial;
        System.out.print("Please enter a new serial number: ");
        try{
            newSerial = Long.parseLong(keyboard.nextLine());
        }catch(NumberFormatException e){
            newSerial = -1;
        }
        if(isDuplicate(newSerial, -1)){
            throw new DuplicateSerialNumberException();
        }else if(newSerial < 0){
            throw new DuplicateSerialNumberException();
        }
        return newSerial;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String OutputFileName;
        System.out.print("Please enter the name of output file, which will have correct information: ");
        Scanner input = new Scanner(System.in);
        OutputFileName = input.nextLine();
        File f = new File(OutputFileName);
        while(f.exists()) {
            System.out.println("Error there is already a file called" +OutputFileName);
            System.out.println("The file already has a size of " +f.length() + " bytes.");
            System.out.println("");
            System.out.println("Please enter another file name to create.");
            OutputFileName = input.nextLine( );
            f = new File(OutputFileName);
        }
        System.out.println("Attempting to open file: "+InputFile);

        System.out.println("The file has " + FileRecords() + " records.");

        if(FileRecords() < 2) {
            System.out.println("There is either 1 or 0 records within this file, no modifications need to be done");
            System.exit(0);
        }else {
            try{
                Cargoship []totalCargoship = fileToArray("Initial_Cargoship_Info.txt");
            }catch(IndexOutOfBoundsException e){
                System.out.println("Array is too small to load the entire file.");

            }
            fixInvertory(InputFile, OutputFileName);
            keyboard.close();
            System.out.println();
            displayFileContents(InputFile);
            System.out.println();
            displayFileContents(OutputFileName);
        }
    }
}
