
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Classe que implementa o método de Brent
 *
 * @author Victor Carity
 * @since 22/10/2016
 */
public class OrganizadorBrent {

    //atributos
    private FileChannel canal;

    //construtor
    public OrganizadorBrent(String pathToFile) throws FileNotFoundException {

        File file = new File(pathToFile);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        this.canal = raf.getChannel();

    }

}
