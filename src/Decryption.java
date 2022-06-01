import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decryption {
    public static void main(String[] args) {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\User\\IdeaProjects\\JavaProjects\\Shifr2.txt", "rw");
             FileChannel channel = randomAccessFile.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            StringBuilder builder = new StringBuilder();
            channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                builder.append((char) buffer.get());
            }

            int key = 7;
            char[] code = new char[builder.length()];
            for (int i = 0; i < code.length; i++) {
                if (Character.isLetter(builder.charAt(i)) && Character.isUpperCase(builder.charAt(i)))

                    code[i] = (char) ((int) (builder.charAt(i)) - key);
                else if (Character.isLetter(builder.charAt(i)) && Character.isLowerCase(builder.charAt(i))) {
                    code[i] = (char) ((int) (builder.charAt(i)) - key);
                } else {
                    code[i] = builder.charAt(i);
                }
            }
            String code2 = String.copyValueOf(code);
            System.out.println(code2);


            Path file3 = Paths.get("Shifr3.txt");
            Files.createFile(file3);
            Files.write(file3, code2.getBytes());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
