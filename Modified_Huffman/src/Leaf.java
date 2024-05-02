
public class Leaf extends Node{
    private final char chr;
    public Leaf(char character,int freq){
super(freq);
chr=character;
    }

    public Character getCharcter() {
        return chr;
    }
}
