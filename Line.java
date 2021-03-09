package P1;

public class Line {

    String line;
    int pos;

    public Line() {
        line = new String();
        pos = 0;
    }

    String getLine() {

        return line;

    }

    int getPos() {
        return pos;
    }

    void insertChar() {

    }

    void deleteChar() {

    }

    void goLeft() {
        if (pos != 0) {
            pos--;
        }
    }

    void goRight() {
        if (pos != line.length()) {
            pos++;
        }
    }

    void goHome() {
        pos = 0;
    }

    void goEnd() {
        pos = line.length() - 1;
    }

}
