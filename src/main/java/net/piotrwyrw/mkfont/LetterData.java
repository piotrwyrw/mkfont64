package net.piotrwyrw.mkfont;

public class LetterData {

    private boolean[][] data;
    private int dimension;
    private String designation;

    public LetterData(int dimension, String designation) {
        this.data = new boolean[dimension][dimension];
        this.dimension = dimension;
        this.designation = designation;
    }

    private void validateDomain(int n) {
        if (n >= dimension || n < 0)
            throw new RuntimeException("The X and Y values have to be within the specified domain (0 to " + dimension + ")");
    }

    public void flip(int x, int y) {
        validateDomain(x);
        validateDomain(y);

        data[x][y] = !(data[x][y]);
    }

    // Turn the letter data into a (packed) character array
    public PackedLetterData pack() {
        char data[] = new char[dimension];
        for (int i = 0; i < dimension; i ++) {
            char line = 0;
            for (int j = 0; j < dimension; j ++)
                line |= (0x1 >> j);
            data[i] = line;
        }
        return new PackedLetterData(data, designation);
    }

    public boolean[][] getData() {
        return data;
    }

    public int getDimension() {
        return dimension;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
