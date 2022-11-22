package net.piotrwyrw.mkfont;

public class PackedLetterData {

    private char[] data;
    private int dimension;
    private String designation;

    public PackedLetterData(char data[], String designation) {
        this.data = data;
        this.dimension = data.length;
        this.designation = designation;
    }

    public String asm() {
        String s = "L_" + designation + ":\n";

        for (int i = 0; i < data.length; i++)
            s += "\tdb 0x" + Integer.toHexString(data[i]) + "\n";

        return s;
    }

    public char[] getData() {
        return data;
    }

    public int getDimension() {
        return dimension;
    }

}
