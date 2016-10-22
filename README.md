
Nome da Tarefa:
Implementação do Método de Brent
Descrição:
Introdução

Segundo o site de notícias G1 8.478.096 alunos se inscreveram no Exame Nacional de Cursos (Enem) 2015. Para que volumes de dados dessa proporção possam ser armazenados e acessados de forma eficiente, faz-se necessário o uso de técnicas especializadas de organização de dados em arquivos.
Objetivos

O objetivo deste trabalho é medir e comparar o desempenho de técnicas de organização de arquivos quando submetidos a operações de armazenamento e acesso a grandes volumes de dados. As técnicas de organização que deverão ser avaliadas são:

    Sequencial
    Método de Brent

Tarefas:

Tarefa 1. Implementar o método de Brent.  Para isso, cada grupo deve usar a interface IFileOrganizerlistada abaixo.

/**
 * Interface genérica que define as operações de organizadores
 * de arquivos de alunos em disco.
 * @author Tarcisio Rocha
 */
public interface IFileOrganizer {

 /**
  * Dada uma instância da classe Aluno, este método
  * adiciona os dados da instância em um arquivo seguindo o
  * método de organização de arquivos especificado.
  * @param p Instância da classe Aluno
  * @return True se o registro foi adicionado com sucesso;
  *         False se o registro não pode ser adicionado.
  */
 public boolean putReg(Aluno p);

 /**
  * Dado um número de matrícula, este método consulta o arquivo de
  * alunos e devolve uma instância que encapsula
  * aos dados do aluno que contém a matrícula fornecida.
  * @param matric Número de matrícula para a consulta.
  * @return Instância da classe Aluno correspondente
  *         à matrícula fornecida;
  *         Null se a matrícula informada não existe no arquivo.
  */
 public Aluno getReg(int matric);

 /**
  * Dado um número de matrícula, localiza e exclui o registro do
  * arquivo de alunos que corresponde à matrícula
  * fornecida.
  * @param matric Matrícula do aluno a ser excluído.
  * @return Instância com os dados do aluno excluido
  *         Null se a matrícula informada não existe na base de
  *         alunos.
  */
 public Aluno delReg(int matric);
}

A partir dessa inteface, deve-se implementar o método de Brent a partir de uma classe chamada OrganizadorBrent. O esqueleto dessa classe está listado abaixo:

package Enem.index;

public class OrganizadorBrent implements IFileOrganizer{

      FileChannel channel;

      public OrganizadorBrent(String fileName){

            File file = new File(fileName);

            RandomAccessFile rf = new RandomAccessFile(file);

            channel = rf.getChannel();

      }

      ...

}

Observações Importantes:

A. A função de incremento do método de Brent deve ser: Inc(chave) = (chave mod (P-2)) + 1, onde P é um valor primo que corresponde ao tamanho da tabela.

B. O tamanho da tabela (P) deve ser de 10.000.019 registros. Esse será o número máximo de registros que o arquivo conseguirá armazenar.

C. O nome do arquivo sobre o qual a implementação do método Brent deve manipular é "enem_brent.db" (disponibilizado pelo professor). Na prática, esse arquivo disponibilizado é composto por 10.000.019 "registros vazios" de alunos com 165 bytes cada. Cada foi projetado com os seguintes campos de tamanho fixo:

long matric=0;       // 8  bytes

String nome="";     // 60 bytes

String endereco=""; // 80 bytes

short idade=0;      // 2  bytes

String cpf="";     // 15  byte


Tarefa 2: Migrar dados para o arquivo "enem_brent.db" (arquivo disponibilizado pelo professor)

Esta tarefa consiste em migrar dados de  8.478.096 alunos de um arquivo de dados aleatórios chamado enem_aleat.db para o arquivo enem_brent.db. O arquivo enem_aleat.db é disponibilizado pelo professor. Para isso, deve ser implementado um programa de migração que lê sequencialmente cada registro do arquivo enem_aleat.db e o insere no arquivo enem_brent.db. Esta inserção deve consistir em chamar a operação addReg da implementação do método de Brent (OrganizadorBrent). Exemplo do programa de migração:

package Enem.index;

public class MigracaoBrent{

    public static void main(String[] args){
         File fOrigem = new File("enem_aleat.db");
         RandomAccessFile fileOrigem = new RandomAcessFile(fOrigem, "r");
         FileChannel channelOrigem = fileOrigem.getChannel();

         File fDestino = new File("enem_brent.db"); // referencia o arquivo organizado pelo método implementado
         IFileOrganization org = new OrganizadorBrent(fDestino);

         // Ler cada aluno do arquivo de origem e inserir no de destino
         for (int i=0; i<8478096; i++)  {

            // Ler da origem
            ByteBuffer buff = ByteBuffer.allocate(165);
            channelOrigem.read(buff);

            buff.flip();
            Aluno a = new Aluno(buff);

            // Inserir no destino
            org.addReg(a)
         }
         channelOrigem.close();
    }

}

Observações:

 1. Este trabalho deve ser realizado em dupla ou trio (no máximo)

2. Para submeter o trabalho, deve ser usada essa área do SIGAA. 

3. Somente um dos alunos do grupo submete o trabalho no SIGAA informando o nomes dos demais integrantes do grupo na submissão.

4. O arquivo de submissao deve ser compactado com a extensão .rar ou .zip contendo todo o código fonte do trabalho. 

5. O nome do arquivo de submissão deve ser composto pelas matrículas dos alunos do grupo separadas por "_". Exemplo: "201211029934_2011238238111.zip" 

6. O arquivo "enem_brent.db" não deve ser submetido, mas cada grupo deve guardá-lo para mostrá-lo no dia da apresentação
Período:
Inicia em 04/10/2016 às 00h00 e finaliza em 24/10/2016 às 23h59
