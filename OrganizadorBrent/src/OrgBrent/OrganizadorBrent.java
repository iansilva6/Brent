package OrgBrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que implementa o método de Brent
 *
 * @author Adrian Costa, Ian Silva, Victor Carity
 * @since 22/10/2016
 */
public class OrganizadorBrent implements IFileOrganizer {

    //atributos
    private FileChannel canal;

    //construtor
    public OrganizadorBrent(String pathToFile) throws FileNotFoundException {

        File file = new File(pathToFile);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        this.canal = raf.getChannel();

    }

    @Override
    public boolean putReg(Aluno p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Aluno getReg(int matric) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Aluno delReg(int matric) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
