import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import static java.lang.System.exit;

public class Urna {
  private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

  private static boolean exit = false;

  private static final Map<String, TSEProfessional> TSEMap = new HashMap<>();

  private static final Map<String, Voter> VoterMap = new HashMap<>();

  private static Election currentElection;

  private static void print(String output) {
    System.out.println(output);
  }

  private static String readString() {
    try {
      return scanner.readLine();
    } catch (Exception e) {
      print("\nErro na leitura de entrada, digite novamente");
      return readString();
      // return "";
    }
  }

  private static int readInt() {
    try {
      return Integer.parseInt(readString());
    } catch (Exception e) {
      print("\nErro na leitura de entrada, digite novamente");
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
          case 1 -> voterMenu();
          case 2 -> tseMenu();
          case 0 -> exit = true;
          default -> print("Comando inválido\n");
        }
        print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      }
    } catch (Exception e) {
      print("Erro inesperado\n");
    }
  }

  private static Voter getVoter() {
    print("Insira seu título de eleitor:");
    String electoralCard = readString();
    Voter voter = VoterMap.get(electoralCard);
    if (voter == null) {
      print("Eleitor não encontrado, por favor confirme se a entrada está correta e tente novamente");
    } else {
      print("Olá, você é " + voter.name + " de " + voter.state + "?\n");
      print("(1) Sim\n(2) Não");
      int command = readInt();
      if (command == 1)
        return voter;
      else if (command == 2)
        print("Ok, você será redirecionado para o menu inicial");
      else {
        print("Entrada inválida, tente novamente");
        return getVoter();
      }
    }
    return null;
  }

  private static boolean votePresident(Voter voter) {
    print("(ext) Desistir");
    print("Digite o número do candidato escolhido por você para presidente:");
    String vote = readString();
    if (vote.equals("ext"))
      throw new StopTrap("Saindo da votação");
    // Branco
    else if (vote.equals("br")) {
      print("Você está votando branco\n");
      print("(1) Confirmar\n(2) Mudar voto");
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
          print("(1) Confirmar\n(2) Mudar voto");
          int confirm = readInt();
          if (confirm == 1) {
            voter.vote(0, currentElection, "President", false);
            return true;
          } else
            votePresident(voter);
        }

        // Normal
        President candidate = currentElection.getPresidentByNumber(voteNumber);
        if (candidate == null) {
          print("Nenhum candidato encontrado com este número, tente novamente");
          print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
          return votePresident(voter);
        }
        print(candidate.name + " do " + candidate.party + "\n");
        print("(1) Confirmar\n(2) Mudar voto");
        int confirm = readInt();
        if (confirm == 1) {
          voter.vote(voteNumber, currentElection, "President", false);
          return true;
        } else if (confirm == 2)
          return votePresident(voter);
      } catch (Warning e) {
        print(e.getMessage());
        return votePresident(voter);
      } catch (Error e) {
        print(e.getMessage());
        throw e;
      } catch (Exception e) {
        print("Ocorreu um erro inesperado");
        return false;
      }
    }
    return true;

  }

  private static boolean voteFederalDeputy(Voter voter, int counter) {
    print("(ext) Desistir");
    print("Digite o número do " + counter + "º candidato escolhido por você para deputado federal:\n");
    String vote = readString();
    if (vote.equals("ext"))
      throw new StopTrap("Saindo da votação");
    // Branco
    if (vote.equals("br")) {
      print("Você está votando branco\n");
      print("(1) Confirmar\n(2) Mudar voto");
      int confirm = readInt();
      if (confirm == 1) {
        voter.vote(0, currentElection, "FederalDeputy", true);
        return true;
      } else
        return voteFederalDeputy(voter, counter);
    } else {
      try {
        int voteNumber = Integer.parseInt(vote);
        // Nulo
        if (voteNumber == 0) {
          print("Você está votando nulo\n");
          print("(1) Confirmar\n(2) Mudar voto\n");
          int confirm = readInt();
          if (confirm == 1) {
            voter.vote(0, currentElection, "FederalDeputy", false);
            return true;
          } else
            return voteFederalDeputy(voter, counter);
        }

        // Normal
        FederalDeputy candidate = currentElection.getFederalDeputyByNumber(voter.state, voteNumber);
        if (candidate == null) {
          print("Nenhum candidato encontrado com este número, tente novamente");
          print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
          return voteFederalDeputy(voter, counter);
        }
        print(candidate.name + " do " + candidate.party + "(" + candidate.state + ")\n");
        print("(1) Confirmar\n(2) Mudar voto");
        int confirm = readInt();
        if (confirm == 1) {
          voter.vote(voteNumber, currentElection, "FederalDeputy", false);
          return true;
        } else if (confirm == 2)
          return voteFederalDeputy(voter, counter);
      } catch (Warning e) {
        print(e.getMessage());
        return voteFederalDeputy(voter, counter);
      } catch (Error e) {
        print(e.getMessage());
        throw e;
      } catch (Exception e) {
        print("Ocorreu um erro inesperado");
        return false;
      }
    }
    return true;

  }

  private static void voterMenu() {
    try {
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      if (!currentElection.getStatus()) {
        print("A eleição ainda não foi inicializada, verifique com um funcionário do TSE");
        return;
      }

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
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");

    } catch (Warning e) {
      print(e.getMessage());
    } catch (StopTrap e) {
      print(e.getMessage());
    } catch (Exception e) {
      print("Erro inesperado");
    }
  }

  private static TSEProfessional getTSEProfessional() {
    print("Insira seu usuário:");
    String user = readString();
    TSEProfessional tseProfessional = TSEMap.get(user);
    if (tseProfessional == null) {
      print("Funcionário do TSE não encontrado, por favor confirme se a entrada está correta e tente novamente");
    } else {
      print("Insira sua senha:");
      String password = readString();
      // Deveria ser um hash na pratica
      if (tseProfessional.password.equals(password))
        return tseProfessional;
      print("Senha inválida, tente novamente");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    }
    return null;
  }

  private static void addCandidate(TSEEmployee tseProfessional) {
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    print("Qual a categoria de seu candidato?\n");
    print("(1) Presidente");
    print("(2) Deputado Federal");
    int candidateType = readInt();

    if (candidateType > 2 || candidateType < 1) {
      print("Comando inválido");
      addCandidate(tseProfessional);
    }

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
          .name(name)
          .number(123)
          .party(party)
          .state(state)
          .build();
    } else if (candidateType == 1) {
      print("\nCadastrar o candidato a presidente " + name + " Nº " + number + " do " + party + "?");
      candidate = new President.Builder()
          .name(name)
          .number(123)
          .party(party)
          .build();
    }

    print("(1) Sim\n(2) Não");
    int save = readInt();
    if (save == 1 && candidate != null) {
      print("Insira a senha da urna");
      String pwd = readString();
      tseProfessional.addCandidate(candidate, currentElection, pwd);
      print("Candidato cadastrado com sucesso");
    }
  }

  private static void removeCandidate(TSEEmployee tseProfessional) {
    print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    print("Qual a categoria de seu candidato?");
    print("(1) Presidente");
    print("(2) Deputado Federal");
    int candidateType = readInt();

    if (candidateType > 2 || candidateType < 1) {
      print("Comando inválido");
      removeCandidate(tseProfessional);
    }

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

    print("(1) Sim\n(2) Não");
    int remove = readInt();
    if (remove == 1) {
      print("Insira a senha da urna:");
      String pwd = readString();
      tseProfessional.removeCandidate(candidate, currentElection, pwd);
      print("Candidato removido com sucesso");
    }
  }

  private static void startSession(CertifiedProfessional tseProfessional) {
    try {
      print("Insira a senha da urna");
      String pwd = readString();
      tseProfessional.startSession(currentElection, pwd);
      print("Sessão inicializada");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private static void endSession(CertifiedProfessional tseProfessional) {
    try {
      print("Insira a senha da urna:");
      String pwd = readString();
      tseProfessional.endSession(currentElection, pwd);
      print("Sessão finalizada com sucesso");
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private static void showResults(CertifiedProfessional tseProfessional) {
    try {
      print("Insira a senha da urna");
      String pwd = readString();
      print(tseProfessional.getFinalResult(currentElection, pwd));
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    } catch (Warning e) {
      print(e.getMessage());
    }
  }

  private static void tseMenu() {
    try {
      TSEProfessional tseProfessional = getTSEProfessional();
      if (tseProfessional == null)
        return;
      print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
      boolean back = false;
      while (!back) {
        print("Escolha uma opção:");
        if (tseProfessional instanceof TSEEmployee) {
          print("(1) Cadastrar candidato");
          print("(2) Remover candidato");
          print("(0) Sair");
          int command = readInt();
          switch (command) {
            case 1 -> addCandidate((TSEEmployee) tseProfessional);
            case 2 -> removeCandidate((TSEEmployee) tseProfessional);
            case 0 -> back = true;
            default -> print("Comando inválido\n");
          }
        } else if (tseProfessional instanceof CertifiedProfessional) {
          print("(1) Iniciar sessão");
          print("(2) Finalizar sessão");
          print("(3) Mostrar resultados");
          print("(0) Sair");
          int command = readInt();
          switch (command) {
            case 1 -> startSession((CertifiedProfessional) tseProfessional);
            case 2 -> endSession((CertifiedProfessional) tseProfessional);
            case 3 -> showResults((CertifiedProfessional) tseProfessional);
            case 0 -> back = true;
            default -> print("Comando inválido\n");
          }
        }
      }
    } catch (Warning e) {
      print(e.getMessage());
    } catch (Exception e) {
      print("Ocorreu um erro inesperado");
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
    String electionPassword = "password";

    currentElection = new Election.Builder()
        .password(electionPassword)
        .build();

    President presidentCandidate1 = new President.Builder().name("João").number(123).party("PDS1").build();
    currentElection.addPresidentCandidate(presidentCandidate1, electionPassword);
    President presidentCandidate2 = new President.Builder().name("Maria").number(124).party("ED").build();
    currentElection.addPresidentCandidate(presidentCandidate2, electionPassword);
    FederalDeputy federalDeputyCandidate1 = new FederalDeputy.Builder().name("Carlos").number(12345).party("PDS1")
        .state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate1, electionPassword);
    FederalDeputy federalDeputyCandidate2 = new FederalDeputy.Builder().name("Cleber").number(54321).party("PDS2")
        .state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate2, electionPassword);
    FederalDeputy federalDeputyCandidate3 = new FederalDeputy.Builder().name("Sofia").number(11211).party("IHC")
        .state("MG").build();
    currentElection.addFederalDeputyCandidate(federalDeputyCandidate3, electionPassword);

    // Startar todo os eleitores e profissionais do TSE
    loadVoters();
    loadTSEProfessionals();

    startMenu();
  }
}
