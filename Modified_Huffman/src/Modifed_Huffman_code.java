import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Modifed_Huffman_code {
    private Node root;
    private final String text;
    private Map<Character, Integer> characterfreq;
    private final Map<Character, String> huffmancode;
    private final Map<String, Character> originalcode;
    private final Map<Character, String> originalcode1;
    Integer min = 100;

    public Modifed_Huffman_code(String txt) {

        text = txt;
        fillCharFrequenciesMap();
        huffmancode = new HashMap<>();
        originalcode = new HashMap<>();
        originalcode1=new HashMap<>();

    }

    public void print() throws FileNotFoundException {
        PrintWriter output_file1=new PrintWriter("C:\\Users\\Youssef Dieaa\\IdeaProjects\\M_huffman\\f.txt");
        output_file1.println("your guide to know the symbol code : "+"\n");
        for (Map.Entry<Character,String> entry : huffmancode.entrySet())
           output_file1.println( entry.getKey() +
                    " : " + entry.getValue());
        output_file1.close();
    }

    private void fillCharFrequenciesMap() {
        int counter = 0;
        characterfreq = new HashMap<>();
        for (char chr : text.toCharArray()) {
            Integer intger = characterfreq.get(chr);
            characterfreq.put(chr, intger != null ? intger + 1 : 1);
        }
        for (char chr : text.toCharArray()) {
            Integer integer = characterfreq.get(chr);
            if (integer < min) {
                min = integer;
            }
        }
        for (char chr : text.toCharArray()) {
            Integer integer = characterfreq.get(chr);
            if (integer == min) {
                characterfreq.remove(chr);
                counter += min;
            }
        }
        characterfreq.put('$', counter);
    }

    private void create_original() {
        int counter = 0;
        int size=0;
        Set<Character> chr=new HashSet<Character>();
        for (int i = 0; i <text.length() ; i++) {
            chr.add(text.charAt(i));
        }
        List<Character>list=new ArrayList<Character>(chr);
        Collections.sort(list);
        for (Character c : list) {
            if (!originalcode.containsValue(c)&&!huffmancode.containsKey(c)) {
                int temp=(int)c-64;
                originalcode.put(Integer.toBinaryString(temp),c);
                originalcode1.put(c,Integer.toBinaryString(temp));
                if (size<Integer.toBinaryString(counter).length()){
                size=Integer.toBinaryString(counter).length();
                }
                counter++;
            }
        }
        }
    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();
        characterfreq.forEach(((character, frequency) -> queue.add(new Leaf(character, frequency))));

        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll()));
        }

        genertaehuffmancode(root = queue.poll(), "");
        return getencodedtxt();
    }

    public String decode(String encodedtxt) {
        Boolean other = false;
        String temp = "";
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for (char chr : encodedtxt.toCharArray()) {
            if (other) {
                temp = temp + chr;
                if (originalcode.containsKey(temp)) {
                    sb.append(originalcode.get(temp));
                    other = false;
                    current = root;
                    temp="";
                    continue;
                }
            }
            if (!other){
                current = chr == '0' ? current.getleft() : current.getright();
                if (current instanceof Leaf) {
                    if (((Leaf) current).getCharcter() == '$') {
                        current = root;
                        other = true;
                    } else {
                        sb.append(((Leaf) current).getCharcter());
                        current = root;
                    }

                }
            }

        }

        return sb.toString();
    }

    private void genertaehuffmancode(Node node, String code) {
        if (node instanceof Leaf) {

            huffmancode.put(((Leaf) node).getCharcter(), code);
            return;

        }
        genertaehuffmancode(node.getleft(), code.concat("0"));
        genertaehuffmancode(node.getright(), code.concat("1"));
    }

    private String getencodedtxt() {
        StringBuilder sb = new StringBuilder();
        for (char chr : text.toCharArray()) {
            if (huffmancode.get(chr) == null) {
                create_original();
                sb.append(huffmancode.get('$') + originalcode1.get(chr));
            } else {
                sb.append(huffmancode.get(chr));
            }
        }
        return sb.toString();
    }
}
