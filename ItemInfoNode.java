/**This is a class that 
 * contains a reference to an ItemInfo object as well as to two other ItemInfoNode objects, referred to as prev and next
@author Otto Halbhuber 
 * ID: 116150792
 * Recitation: R30*/

public class ItemInfoNode{
    private ItemInfo info;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    public ItemInfoNode(){
        this.prev = null;
        this.next = null;
        this.info = null;
    }

    /**info setter
     * @param info
     * the new info in the Node
     */
    public void setInfo(ItemInfo info){
        this.info = info;
    }

    /** info getter
     * @return
     * returns Node's info
      */
    public ItemInfo getInfo(){
        return info;
    }

    /** prev setter
     * @param prev
     * sets node prev
     */
    public void setPrev(ItemInfoNode prev){
        this.prev = prev;
    }

    /** prev getter
     * @return
     * returns previous node
     */
    public ItemInfoNode getPrev(){
        return prev;
    }

    /**next setter
     * @param next
     * the next node
     */
    public void setNext(ItemInfoNode next){
        this.next = next;
    }

    /**next getter
     * @return
     * returns the next node
     */
    public ItemInfoNode getNext(){
        return next;
    }
}