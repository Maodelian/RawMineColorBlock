package app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Compiler {
    private File file;
    private BufferedImage image;
    private final HashMap<Integer, Byte> blocks;
    private final int[] pixels;
    private final byte[] result;
    public Compiler(File file) {
        try {
            image = ImageIO.read(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        blocks = new HashMap<>();
        blocks.put(0xffffffff, (byte) 0);
        blocks.put(0xffff7f27, (byte) 1);
        blocks.put(0xffff00cc, (byte) 2);
        blocks.put(0xff42aaff, (byte) 3);
        blocks.put(0xffffff00, (byte) 4);
        blocks.put(0xffbfff00, (byte) 5);
        blocks.put(0xffff1493, (byte) 6);
        blocks.put(0xff49423d, (byte) 7);
        blocks.put(0xffbbbbbb, (byte) 8);
        blocks.put(0xff30d5c8, (byte) 9);
        blocks.put(0xff472a3f, (byte) 10);
        blocks.put(0xff0000ff, (byte) 11);
        blocks.put(0xff95500c, (byte) 12);
        blocks.put(0xff004524, (byte) 13);
        blocks.put(0xffff0000, (byte) 14);
        blocks.put(0xff000000, (byte) 15);
        pixels = new int[image.getWidth()*image.getWidth()];
        result = new byte[image.getWidth()*image.getWidth()];
    }

    private void convert() {
        int width = image.getWidth();
        int height = image.getHeight();
        int i = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[i] = image.getRGB(col, row);
                i++;
            }
        }
    }

    public void compile() {
        convert();
        for (int i = 0; i < image.getWidth()*image.getWidth(); i++) {
            result[i] = blocks.get(pixels[i]);
        }
    }

    public void save(File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            for (byte b : result) {
                outputStream.write(b);
            }
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
