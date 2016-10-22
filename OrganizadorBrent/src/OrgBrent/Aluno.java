package OrgBrent;

/**
 * Modelo para aluno
 *
 * @author Adrian Costa, Ian Silva, Victor Carity
 * @since 22/10/2016
 */
import java.nio.ByteBuffer;

public class Aluno {

    //atributos
    long matric; // 8 bytes
    String nome; // 60 bytes
    String end;  // 80 bytes
    short idade; // 2 bytes
    String cpf;  // 15 bytes
    public static final int LENGTH = 165;

    //Construtores
    public Aluno(long matric, String nome, String end, short idade,
            String cpf) {
        this.matric = matric;
        this.nome = corrigirTamanho(nome, 60);
        this.end = corrigirTamanho(end, 80);
        this.idade = idade;
        this.cpf = corrigirTamanho(cpf, 15);
    }

    public Aluno(ByteBuffer buf) {
        this.matric = buf.getLong();
        byte[] bNome = new byte[60];
        buf.get(bNome);
        this.nome = new String(bNome);
        byte[] bEnd = new byte[80];
        buf.get(bEnd);
        this.end = new String(bEnd);
        this.idade = buf.getShort();
        byte[] bCpf = new byte[15];
        buf.get(bCpf);
        this.cpf = new String(bCpf);
    }

    //Método que corrige a String, completando com " "
    private String corrigirTamanho(String str, final int tam) {
        int num = tam - str.length();
        if (str.length() > tam) {
            str = str.substring(0, tam);
        } else {
            for (int i = 0; i < num; i++) {
                str = str + " ";
            }
        }
        return str;
    }

    //Método que retorna o buffer
    public ByteBuffer getBuffer() {
        ByteBuffer buf = ByteBuffer.allocate(LENGTH);
        buf.putLong(this.matric);
        buf.put(this.nome.getBytes());
        buf.put(this.end.getBytes());
        buf.putShort(this.idade);
        buf.put(this.cpf.getBytes());
        buf.flip();
        return buf;
    }

    //Métodos get e set
    public long getMatric() {
        return matric;
    }

    public void setMatric(long matric) {
        this.matric = matric;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = corrigirTamanho(nome, 60);
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = corrigirTamanho(end, 80);
    }

    public short getIdade() {
        return idade;
    }

    public void setIdade(short idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = corrigirTamanho(cpf, 15);
    }
}
