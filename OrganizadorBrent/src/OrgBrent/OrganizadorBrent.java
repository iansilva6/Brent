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
    //private static final long SIZE = 8478101;//É PRIMO!
    private static final long SIZE = 7;//tamanho de teste

    //construtor
    public OrganizadorBrent(String pathToFile) throws FileNotFoundException {

        File file = new File(pathToFile);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        this.canal = raf.getChannel();

    }

    //Métodos de Organização Sequencial de Brent
    @Override
    public boolean putReg(Aluno a) {
        long pos = (a.getMatric() % SIZE);
        ByteBuffer buf = ByteBuffer.allocate(Aluno.LENGTH);
        try {
            this.canal.read(buf, pos * Aluno.LENGTH);
        } catch (IOException ex) {
            return false;
        }
        buf.flip();
        Aluno b = new Aluno(buf);
        if (b.getMatric() == 0) {
            try {
                this.canal.write(a.getBuffer(), pos * Aluno.LENGTH);
            } catch (IOException ex) {
                return false;
            }
        } else {
            //caso 1, calcula o incremento do que vai entrar, e procura uma posição livre
            long incEntrar = (((a.getMatric()) % (SIZE - 2)) + 1);
            long posicaoEntradaC1 = -1;
            long custo1 = 0;
            for (long i = pos * Aluno.LENGTH; i > -1; i += (Aluno.LENGTH
                    * incEntrar)) {
                if (i >= SIZE * Aluno.LENGTH) {
                    i = i - SIZE * Aluno.LENGTH;
                }
                ByteBuffer buf2 = ByteBuffer.allocate(4);
                try {
                    this.canal.read(buf2, i);
                } catch (IOException ex) {
                    Logger.getLogger(OrganizadorBrent.class.getName()).log(
                            Level.SEVERE, null, ex);
                    return false;
                }
                buf2.flip();
                int n = buf2.getInt();
                if (n == 0 || n == -1) {
                    posicaoEntradaC1 = i;
                    i = -1;
                    break;
                } else {
                    custo1++;
                }
            }

            //caso2, calcula incremento que já está lá, e procura uma pos livre
            long incElenPos = (((b.getMatric()) % (SIZE - 2)) + 1);
            long posicaoEntradaC2 = -1;
            long custo2 = 0;
            for (long i = pos * Aluno.LENGTH; i > -1; i += Aluno.LENGTH
                    * incElenPos) {
                if (i >= SIZE * Aluno.LENGTH) {
                    i = i - SIZE * Aluno.LENGTH;
                }
                ByteBuffer buf2 = ByteBuffer.allocate(4);
                try {
                    this.canal.read(buf2, i);
                } catch (IOException ex) {
                    Logger.getLogger(OrganizadorBrent.class.getName()).log(
                            Level.SEVERE, null, ex);
                    return false;
                }
                buf2.flip();
                int n = buf2.getInt();
                if ((n == 0 || n == -1)) {
                    posicaoEntradaC2 = i;
                    i = -1;
                    break;
                } else {
                    custo2++;
                }
            }
            if (custo1 <= custo2) {
                try {
                    this.canal.write(a.getBuffer(), posicaoEntradaC1);
                } catch (IOException ex) {
                    Logger.getLogger(OrganizadorBrent.class.getName()).log(
                            Level.SEVERE, null, ex);
                    return false;
                }
            } else {
                if (custo2 < custo1) {
                    try {
                        this.canal.write(a.getBuffer(), pos * Aluno.LENGTH);
                        this.canal.write(b.getBuffer(), posicaoEntradaC2);
                    } catch (IOException ex) {
                        Logger.getLogger(OrganizadorBrent.class.getName()).log(
                                Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Aluno getReg(int matric) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Aluno delReg(int matric) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private long getHash(int matric) {
        return (matric % SIZE);
    }

    private long getInc(int matric) {
        return ((matric % (SIZE - 2)) + 1);
    }

}
