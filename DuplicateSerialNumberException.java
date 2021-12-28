public class DuplicateSerialNumberException extends Exception {
    static String DuplicateMessage = "This serial number is a duplicate";

    public DuplicateSerialNumberException(){
        System.out.println(DuplicateMessage);
    }
}