import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static javax.print.attribute.standard.MediaSizeName.C;

public class ShifrCezary {
    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\User\\IdeaProjects\\JavaProjects\\src\\Shifr", "r");
             FileChannel channel = randomAccessFile.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            StringBuilder builder = new StringBuilder();
            channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                builder.append((char) buffer.get());
            }

            int key = 7;
            char[] shifr = new char[builder.length()];
            for (int i = 0; i < shifr.length; i++) {
                if (Character.isLetter(builder.charAt(i)) && Character.isUpperCase(builder.charAt(i)))

                    shifr[i] = (char) ((int) (builder.charAt(i)) + key);
                else if (Character.isLetter(builder.charAt(i)) && Character.isLowerCase(builder.charAt(i))) {
                    shifr[i] = (char) ((int) (builder.charAt(i)) + key);
                } else {
                    shifr[i] = builder.charAt(i);
                }
            }
            String shifr2 = String.copyValueOf(shifr);
            System.out.println(shifr2);


            Path file3 = Paths.get("Shifr3sdw.txt");
            Files.createFile(file3);
            Files.write(file3, shifr2.getBytes());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
