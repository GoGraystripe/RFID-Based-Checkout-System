/** This is a class that contains various information about a specific item that can or 
 * has been sold in a given department store 
 * @author Otto Halbhuber 
 * ID: 116150792
 * Recitation: R30*/

public class ItemInfo{
    private String name; //name of the item
    private double price; //price of the item

    private String rfidTagNumber; //RFID tag number: a String that encodes the radio frequency for scanning the item
    private String originalLocation; //a String that encodes the original shelf position of the item
    private String currentLocation; //a String that represents the location of the item at the current time

    
    /**This is a constructor for an ItemInfo object */
    public ItemInfo(String name, double price, String rfidTagNumber, String originalLocation, String currentLocation){
        this.name = name;
        this.price = price;
        this.rfidTagNumber = rfidTagNumber.trim();
        this.originalLocation = originalLocation.trim();
        this.currentLocation = originalLocation.trim();

        try{
            Long.parseLong(this.rfidTagNumber, 16);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Your RFID tag number is not in valid hexadecimal format");
        }

        if(!isValidLocation(this.currentLocation)){
            throw new IllegalArgumentException("Your location is NOT valid. Please give s[5 digits], c[3 digits], or out");
        }

    }

    /** Helper to determine if the user given location is valid
     * @return 
     * returns true if location is valid, false otherwise
     */
    public boolean isValidLocation(String location){

        if(location == null) return false;

        location = location.trim();

        if(location.isEmpty()) return false;

        if(location.charAt(0) == 's'){
            
            if(location.length() != 6){
                return false;
            }

            for(int i = 1; i < location.length(); i++){
                if(!Character.isDigit(location.charAt(i))){
                    return false;
                }
            }

        }

        else if(location.charAt(0) == 'c'){

            if(location.length() != 4){
                return false;
            }

            for(int i = 1; i < location.length(); i++){
                if(!Character.isDigit(location.charAt(i))){
                    return false;
                }
            }

        }

        else if(!location.equals("out")){
            return false;
        }

        return true;
    }

    /**name getter
     * @return
     *  returns the name of the item
     */

    public String getName(){
        return name;
    }

    /**name setter
     * @param name
     * the new name of the item
     */

    public void setName(String name){
        this.name = name;
    }

    /** price getter
     * @return 
     * returns the price of the item
     */

    public double getPrice(){
        return price;
    }

    /**price setter
     * @param price
     * the new price of the item
     */

    public void setPrice(double price){
        this.price = price;
    }

    /**rfidTagNumber getter 
     * @return 
     * returns the rfidTagNumber of the item
    */

    public String getRfidTagNumber(){
        return rfidTagNumber;
    }

    /**rfidTagNumber setter
     * @param rfidTagNumber
     * the new rfidRTagNumber fo the item
     */

    public void setRfidTagNumber(String rfidTagNumber){

        try{
            Long.parseLong(rfidTagNumber, 16);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Your RFID tag number is not in valid hexadecimal format");
        }

        this.rfidTagNumber = rfidTagNumber;
    }

    /**originalLocation getter
     * @return
     * returns the originalLocation of the item
    */

    public String getOriginalLocation(){
        return originalLocation;
    }

    /**originalLocation setter 
     * @param originalLocation
     * the new originalLocation of the item
    */

    public void setOriginalLocation(String originalLocation){

        if(!isValidLocation(originalLocation)){
            throw new IllegalArgumentException("Your location is NOT valid. Please give s[5 digits], c[3 digits], or out");
        }

        this.originalLocation = originalLocation;
    }

    /**currentLocation getter
     * @return
     * returns the currentLocation of the item
     */

    public String getCurrentLocation(){
        return currentLocation;
    }

    /**currentLocation setter
     * @param currentLocation
     * the new currentLocaton of the item
     */

    public void setCurrentLocation(String currentLocation){

        if(!isValidLocation(currentLocation)){
            throw new IllegalArgumentException("Your location is NOT valid. Please give s[5 digits], c[3 digits], or out");
        }

        this.currentLocation = currentLocation;
    }

    public String toString(){
        return String.format("%-15s %-12s %-10s %-10s %6.2f", 
        name, rfidTagNumber, originalLocation, currentLocation, price);
    }

}