/** This is a class that contains references to the head and tail of a list of ItemInfoNode nodes
 * @author Otto Halbhuber 
 * ID: 116150792
 * Recitation: R30*/

import java.math.BigInteger;

public class ItemList{
    private ItemInfoNode tail;
    private ItemInfoNode head;
    int size;

    public ItemList(){
        head = new ItemInfoNode();
        ItemInfo dummyHeadInfo = new ItemInfo("Dummy Head", -1.0, "80000000", "s00000", "c000");
        head.setInfo(dummyHeadInfo);
        tail = head.getNext();

        size = 0;
    }

    /**inserts a new node into the linked list 
     * @param name
     *   the name of the item
     * @param rfidTag
     *      the rfidTag of the item
     * @param price
     *      the price of the item
     * @param initiPosition
     *      the original location of the item
     * 
     * time complexity: O(n) because the list has to remain sorted so we have to iterate throught the list until we 
     * find a node whose hexadecimal RFIDTag has a greater value than the new node's RFIDTag or there are no more nodes
     * to check, so we have to update the tail
    */
    public void insertInfo(String name, String rfidTag, double price, String initPosition){
        ItemInfoNode newNode = new ItemInfoNode();

        ItemInfo info = new ItemInfo(name, price, rfidTag, initPosition, initPosition);

        newNode.setInfo(info);

        ItemInfoNode temp = head;

        if(temp.getNext() == null){
            temp.setNext(newNode);
            newNode.setPrev(temp);
            tail = newNode; //new tail insertion edge case
            return;
        }

        BigInteger infoValue = new BigInteger(info.getRfidTagNumber(), 16);
        BigInteger tempNextInfoValue = new BigInteger(temp.getNext().getInfo().getRfidTagNumber(), 16);

        while(infoValue.compareTo(tempNextInfoValue) > 0){

            temp = temp.getNext();

            if(temp.getNext() == null){
                tail = newNode;
                break;
            }

            tempNextInfoValue = new BigInteger(temp.getNext().getInfo().getRfidTagNumber(), 16);
        }

        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        newNode.setPrev(temp);

        return;
    }

    /** helper for the removeAllPurched method
     * @param node
     *      the node to be removed
     * 
     * O(1) because we already have the node we want to we just have to update it's forward and backward pointers,
     * with a special case if the node is the tail
     */
    public void remove(ItemInfoNode node){
        
        if(node.equals(tail)){
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
            return; //removing tail edge case
        }

        node.setNext(node.getNext().getNext());
        node.getNext().setPrev(node);
        
    }

    /**  removes all items whose location is "out"
     * time complexity: O(n) because the method has to iterate through the linked list to find all nodes with location "out"
    */
    public void removeAllPurchased(){

        //already has a built-in tail edge case via remove() helper

        System.out.printf("%-15s %-12s %-10s %-10s %6s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("--------------- ------------ ---------- ---------- ------");

        ItemInfoNode node = head;

        while(node != null){
            ItemInfoNode nextNode = node.getNext();

            if(node.getInfo().getCurrentLocation().equals("out")){  
                
                System.out.println(node.getInfo().toString());

                remove(node);
            }

            node = nextNode;
        }
    }

    /** changes the location of the item 
     * @param rfidTag
     *      the rfidTag of the item
     * @param source
     *      the current location of the item, returns false if the given current location and actual current location don't match
     * @param dest
     *      the location the item should be moved to
     * 
     * @return
     *      returns true if the operation succeeds, ie the item is successfully moved
     *      returns false if the item's given current location and actual current location don't match or if the item is not found
     *time complexity: O(n) because the method has to iterate through the linked list to the find the
     *item(s) with the given rfidTag;
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws IllegalArgumentException{

        if(source.equals("out")) throw new IllegalArgumentException("source equals out");
        
        if(!head.getInfo().isValidLocation(dest)){
            throw new IllegalArgumentException("destination is not a valid location");
        }

        ItemInfoNode temp = head;
        String loc = "";

        while(temp != null){
            if(temp.getInfo().getRfidTagNumber().equals(rfidTag)){
                loc = temp.getInfo().getCurrentLocation();

                if(!source.equals(loc)){
                    System.out.println("Given source location and actual source location do not match. Try again.");
                    return false;
                }

                temp.getInfo().setCurrentLocation(dest);
                return true;

            }

            temp = temp.getNext();
        }

        return false;
    }


    /**prints all the items in the linked list
     * time complexity: O(n) because the method has to iterate through the whole list
     */
    public void printAll(){
        
        ItemInfoNode temp = head.getNext();

        System.out.printf("%-15s %-12s %-10s %-10s %6s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("--------------- ------------ ---------- ---------- ------");

        while(temp != null){
            System.out.println(temp.getInfo().toString());

            temp = temp.getNext();
        }
    }

    /** prints all the items in the linked list with the given rfidTagNumber 
     * time complexity: O(n) because the method has to iterate through the list to find the item(s) with the given rfidTagNumber
    */
    public void printByRfidTagNumber(String rfidTagNumber){

        System.out.printf("%-15s %-12s %-10s %-10s %6s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("--------------- ------------ ---------- ---------- ------");

        ItemInfoNode temp = head;

        while(temp != null){
            
            if(temp.getInfo().getRfidTagNumber().equals(rfidTagNumber)){
                System.out.println(temp.getInfo().toString());
            }

            temp = temp.getNext();
        }
    }

    /** prints all the items in the linked list with the given location 
     * time complexity: O(n) because the method has to iterate through the list to find the item(s) with the given location
    */
    public void printByLocation(String location){

        System.out.printf("%-15s %-12s %-10s %-10s %6s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("--------------- ------------ ---------- ---------- ------");
        
        ItemInfoNode temp = head.getNext();

        while(temp != null){

            if(temp.getInfo().getCurrentLocation().equals(location)){
                System.out.println(temp.getInfo().toString());  
            }

            temp = temp.getNext();

        }
    }

    /**puts all items on shelves back to their originl shelves
     * time complexity: O(n) because the method has to iterate through the list to find all the valid items
     */
    public void cleanStore(){

        System.out.printf("%-15s %-12s %-10s %-10s %6s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("--------------- ------------ ---------- ---------- ------");

        ItemInfoNode temp = head.getNext();

        while(temp != null){
            if(temp.getInfo().getCurrentLocation().charAt(0) == 's'){

                ItemInfo info = temp.getInfo();
                info.setCurrentLocation(info.getOriginalLocation());
                temp.setInfo(info);

                System.out.println(temp.getInfo().toString());
                
            }

            temp = temp.getNext();
        }

    }


    /**sets all items with the given cartNumber to "out"
     * prints all those items and keeps track of their total cost
     * @return
     * returns a double representing the total cost of the items
    */
    public double checkOut(String cartNumber){

        System.out.printf("%-15s %-12s %-10s %-10s %6s\n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("--------------- ------------ ---------- ---------- ------");

        ItemInfoNode temp = head.getNext();

        double cost = 0;

        while(temp != null){
            if(temp.getInfo().getCurrentLocation().equals(cartNumber)){

                ItemInfo info = temp.getInfo();
                info.setCurrentLocation("out");
                temp.setInfo(info);

                cost += temp.getInfo().getPrice();

                System.out.println(temp.getInfo().toString());

            }

            temp = temp.getNext();
        }

        return cost;
    }

}