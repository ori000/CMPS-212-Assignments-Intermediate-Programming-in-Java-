public class HuffmanNode implements Comparable<HuffmanNode>{
    
    HuffmanNode left;
    HuffmanNode right;
    private Integer freq;
    private Character elem;

    public HuffmanNode(HuffmanNode left, HuffmanNode right, Character elem, Integer freq){
        this.left = left;
        this.right = right;
        this.elem = elem;
        this.freq = freq;
    }
    public HuffmanNode(Character elem, Integer freq){
        this.elem = elem;
        this.freq = freq;
    }//return freq
    public Integer getFreq(){
        return freq;
    }//return elem
    public Character getElem(){
        return elem;
    }

    @Override//sort the nodes
    public int compareTo(HuffmanNode node) {
        return Integer.compare(freq, node.freq);
    }
    public String toString(){
        return String.format("|Character: %c| |Frequency: %d|", getElem(), getFreq());
    }
}
