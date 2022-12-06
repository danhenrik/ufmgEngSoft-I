package scratch;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.server.ExportException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class Scratch {
  private static BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

  private static boolean exit = false;

  private static Map<String, TSEProfessional> TSEMap = new HashMap<>();

  private static Map<String, Voter> VoterMap = new HashMap<>();

  private static Election currentElection;

  private static void print(String output) {
    System.out.println(output);
  }

  private static String readString() {
    try {
      return scanner.readLine();
    } catch (Exception e) {
      print("Erro na leitura de entrada, digite novamente");
      return readString();
      // return "";
    }
  }

  private static int readInt() {
    try {
      return Integer.parseInt(readString());
    } catch (Exception e) {
      print("Erro na leitura de entrada, digite novamente");
      return readInt();
      // return -1;
    }
  }

  private static void startMenu() {
    try {
      while (!exit) {
        print("Escolha uma opção:\n");
        print("(1) Entrar (Eleitor)");
        print("(2) Entrar (TSE)");
        print("(0) Fechar aplicação");
        int command = readInt();
        switch (command) {
          case 1 -> startVoter();
          case 2 -> startTSE();
          case 0 -> exit = true;
          default -> print("Comando inválido\n");
        }
      }
    } catch (Exception e) {
      print("Error: " + e.getMessage() + "\n");
    }
  }

  private static Voter getVoter() {
    print("Insira seu título de eleitor");
    String electoralCard = readString();
    Voter voter = VoterMap.get(electoralCard);
    if (voter == null) {
      print("Eleitor não encontrado, por favor confirme se a entrada está correta e tente novamente");
    } else {
      print("Olá, você é " + voter.name + " de " + voter.state + "?\n");
      print("(1) Sim\n(2) Não\n");
      int command = readInt();
      if (command == 1)
        return voter;
      else if (command == 2) {
        print("Ok, você será redirecionado para o menu inicial");
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      }
    }
    return null;
  }

  private static boolean votePresident(Voter voter) {
    print("Digite o número do candidato escolhido por você para presidente:");
    try {
      String vote = readString();
      // Branco
      if (vote.equals("br")) {
        print("Você está votando branco\n");
        print("(1) Confirmar\n(2) Mudar voto\n");
        int confirm = readInt();
        if (confirm == 1) {
          voter.vote(0, currentElection, "President", true);
          return true;

        } else
          votePresident(voter);
      } else {
        try {
          int voteNumber = Integer.parseInt(vote);
          // Nulo
          if (voteNumber == 0) {
            print("Você está votando nulo\n");
            print("(1) Confirmar\n(2) Mudar voto\n");
            int confirm = readInt();
            if (confirm == 1) {
              voter.vote(0, currentElection, "President", false);
              return true;
            } else
              votePresident(voter);
          }

          // Normal
          President candidate = currentElection.getPresidentByNumber(voteNumber);
          print(candidate.name + " do " + candidate.party + "\n");
          print("(1) Confirmar\n(2) Mudar voto\n");
          int confirm = readInt();
          if (confirm == 1) {
            voter.vote(voteNumber, currentElection, "President", false);
            return true;
          } else if (confirm == 2)
            votePresident(voter);
        } catch (Exception e) {
          print("Entrada inválida");
          print(e.getMessage());
          return false;
        }
      }
      return true;
    } catch (Exception e) {
      print("Não foi possível registrar seu voto");
      print(e.getMessage());
      return false;
    }
  }

  private static boolean voteFederalDeputy(Voter voter, int counter) {
    print("Digite o número do " + counter + "º candidato escolhido por você para deputado federal:\n");
    try {
      String vote = readString();
      // Branco
      if (vote.equals("br")) {
        print("Você está votando branco\n");
        print("(1) Confirmar\n (2) Mudar voto\n");
        int confirm = readInt();
        if (confirm == 1) {
          voter.vote(0, currentElection, "FederalDeputy", true);
          return true;

        } else
          voteFederalDeputy(voter, counter);
      } else {
        try {
          int voteNumber = Integer.parseInt(vote);
          // Nulo
          if (voteNumber == 0) {
            print("Você está votando nulo\n");
            print("(1) Confirmar\n (2) Mudar voto\n");
            int confirm = readInt();
            if (confirm == 1) {
              voter.vote(0, currentElection, "FederalDeputy", false);
              return true;
            } else
              voteFederalDeputy(voter, counter);
          }

          // Normal
          FederalDeputy candidate = currentElection.getFederalDeputyByNumber(voter.state, voteNumber);
          print(candidate.name + " do " + candidate.party + "(" + ((FederalDeputy) candidate).state + ")\n");
          print("(1) Confirmar\n (2) Mudar voto\n");
          int confirm = readInt();
          if (confirm == 1) {
            voter.vote(voteNumber, currentElection, "FederalDeputy", false);
            return true;
          } else if (confirm == 2)
            voteFederalDeputy(voter, counter);
        } catch (Exception e) {
          print("Entrada inválida");
          print(e.getMessage());
          return false;
        }
      }
      return true;
    } catch (Exception e) {
      print("Não foi possível registrar seu voto");
      print(e.getMessage());
      return false;
    }
  }

  private static void startVoter() {
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    Voter voter = getVoter();
    if (voter == null)
      return;

    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    print("Vamos começar!\n");
    print(
        "OBS:\n- A partir de agora caso você queira votar nulo você deve usar um numero composto de 0 (00 e 0000)\n- A partir de agora caso você queira votar branco você deve escrever br\n");

    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    if (votePresident(voter))
      print("Voto para presidente registrado com sucesso");

    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    if (voteFederalDeputy(voter, 1))
      print("Primeiro voto para deputado federal registrado com sucesso");

    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    if (voteFederalDeputy(voter, 2))
      print("Segundo voto para deputado federal registrado com sucesso");
  }

  private static TSEProfessional getTSEProfessional() {
    print("Insira seu usuário");
    String user = readString();
    TSEProfessional tseProfessional = TSEMap.get(user);
    if (tseProfessional == null) {
      print("Funcionário do TSE não encontrado, por favor confirme se a entrada está correta e tente novamente");
    } else {
      print("Insira sua senha");
      String password = readString();
      // Deveria ser um hash na pratica
      if (tseProfessional.password.equals(password))
        return tseProfessional;
      print("Senha inválida, tente novamente");
    }
    return null;
  }

  private static void addCandidate(TSEEmployee tseProfessional) {
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    print("Qual a categoria de seu candidato?");
    print("(1) Presidente");
    print("(2) Deputado Federal");
    int candidateType = readInt();

    print("Qual o nome do candidato?");
    String name = readString();

    print("Qual o numero do candidato?");
    int number = readInt();

    print("Qual o partido do candidato?");
    String party = readString();

    Candidate candidate = null;
    if (candidateType == 2) {
      print("Qual o estado do candidato?");
      String state = readString();

      print("\nCadastrar o candidato deputado federal " + name + " Nº " + number + " do " + party + "(" + state + ")?");
      candidate = new FederalDeputy.Builder()
          .name("name")
          .number(123)
          .party("test")
          .state(state)
          .build();
    } else if (candidateType == 1) {
      print("/nCadastrar o candidato a presidente " + name + " Nº " + number + " do " + party + "?");
      candidate = new President.Builder()
          .name("name")
          .number(123)
          .party("test")
          .build();
    }

    print("(1) Sim\n(2) Não\n");
    int save = readInt();
    if (save == 1 && candidate != null) {
      print("Insira a senha da urna");
      String pwd = readString();
      tseProfessional.addCandidate(candidate, currentElection, pwd);
    }
  }

  private static void removeCandidate(TSEEmployee tseProfessional) {
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    print("Qual a categoria de seu candidato?");
    print("(1) Presidente");
    print("(2) Deputado Federal");
    int candidateType = readInt();

    Candidate candidate = null;
    print("Qual o numero do candidato?");
    int number = readInt();
    if (candidateType == 2) {
      print("Qual o estado do candidato?");
      String state = readString();

      candidate = currentElection.getFederalDeputyByNumber(state, number);
      if (candidate == null) {
        print("Candidato não encontrado");
        return;
      }
      print("/Remover o candidato a deputado federal " + candidate.name + " Nº " + candidate.number + " do "
          + candidate.party + "("
          + ((FederalDeputy) candidate).state + ")?");
    } else if (candidateType == 1) {
      candidate = currentElection.getPresidentByNumber(number);
      if (candidate == null) {
        print("Candidato não encontrado");
        return;
      }
      print("/Remover o candidato a presidente " + candidate.name + " Nº " + candidate.number + " do " + candidate.party
          + "?");
    }
    print("(1) Sim\n(2) Não\n");
    int remove = readInt();
    if (remove == 1) {
      print("Insira a senha da urna");
      String pwd = readString();
      tseProfessional.removeCandidate(candidate, currentElection, pwd);
    }
  }

  private static void startTSE() {
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    TSEProfessional tseProfessional = getTSEProfessional();
    if (tseProfessional == null)
      return;
    print("aaaaaaaaaaaaaaaa");
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    print("Escolha uma opção:");
    if (tseProfessional instanceof TSEEmployee) {
      print("(1) Cadastrar candidato");
      print("(2) Remover candidato");
      int command = readInt();
      switch (command) {
        case 1 -> addCandidate((TSEEmployee) tseProfessional);
        case 2 -> removeCandidate((TSEEmployee) tseProfessional);
        default -> print("Comando inválido\n");
      }
    } else if (tseProfessional instanceof CertifiedProfessional) {
      print("(1) Iniciar sessão");
      print("(2) Finalizar sessão");
      print("(3) Mostrar resultados");
      int command = readInt();
      switch (command) {
        case 1 -> addCandidate((TSEEmployee) tseProfessional);
        case 2 -> removeCandidate((TSEEmployee) tseProfessional);
        case 3 -> removeCandidate((TSEEmployee) tseProfessional);
        default -> print("Comando inválido\n");
      }
    }

  }

  private static void loadVoters() {
    try {
      File myObj = new File("voterLoad.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        var voterData = data.split(",");
        VoterMap.put(voterData[0],
            new Voter.Builder().electoralCard(voterData[0]).name(voterData[1]).state(voterData[2]).build());
      }
      myReader.close();
    } catch (Exception e) {
      print("Erro na inicialização dos dados");
      exit(1);
    }
  }

  private static void loadTSEProfessionals() {
    TSEMap.put("cert", new CertifiedProfessional.Builder()
        .user("cert")
        .password("54321")
        .build());
    TSEMap.put("emp", new TSEEmployee.Builder()
        .user("emp")
        .password("12345")
        .build());
  }

  public static void main(String[] args) {
    // Startup the current election instance
    currentElection = new Election.Builder()
        .password("securePassword")
        .build();

    // Startar todo os eleitores e profissionais do TSE
    loadVoters();
    loadTSEProfessionals();
    startMenu();
  }
}
