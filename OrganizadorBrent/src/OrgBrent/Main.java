package OrgBrent;

import java.io.FileNotFoundException;

/**
 * Main
 *
 * @author Adrian Costa, Ian Silva, Victor Carity
 * @since 22/10/2016
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        //Chama o construdor do organizador com o caminho do arquivo
        OrganizadorBrent organizador = new OrganizadorBrent("C:\\Users"
                + "\\ASI00\\Documents\\Arquivos de ED2\\FileChannelTest"
                + "\\AlunoTeste.db");
        /*       
        //Cria os  Alunos no arquivo
        Aluno aluno1 = new Aluno(2016200011, "Pedro", "Rua Pirula",(short)11,
                 "001.999.444-11");
        manipulador.putReg(aluno1);
        Aluno aluno2 = new Aluno(2016200022, "Bia", "Rua Pitua",(short)22,
                 "002.999.444-22");
        manipulador.putReg(aluno2);
        Aluno aluno3 = new Aluno(2016200033, "Amanda", "Rua Fofo",(short)33,
                 "003.999.444-33");
        manipulador.putReg(aluno3);
        Aluno aluno4 = new Aluno(2016200044, "Marina", "Rua Falk",(short)44,
                 "004.999.444-44");
        manipulador.putReg(aluno4);
        Aluno aluno5 = new Aluno(2016200055, "Jones", "Rua das Ruas",(short)55,
                 "005.999.444-55");
        manipulador.putReg(aluno5);        
         */
    }

}
