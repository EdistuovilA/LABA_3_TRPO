import java.util.ArrayList;
import java.util.List;

public class Client {
    public static List<Client> clients = new ArrayList<>();
    private String name;
    private int age;
    private EDUCATION education;
    private SOCIAL_STATUS socialSstatus;
    private SEASONAL_VACATION_TIME seasonalVacationTime;
    private RESTING_PLACE restingPlace;

    public Client(String name){
        this.name = name;
        clients.add(this);
    }

    public static void print(){
        for (Client x : clients){
            System.out.println(x.getName());
        }
    }

    public String getName() {
        return name;
    }
}

enum EDUCATION{
    BasicGeneralEducation, //(9 классов)
    SecondarySchool, //(11 классов)
    LowerVocationalEducation, //Среднее профессиональное образование
    BachelorDegree, //Бакалавриат
    MasterDegree //Магистратура
    }

enum SOCIAL_STATUS{
    Peasant,
    Tradesman,
    HonorableSir,
    Nobleman
}

enum  SEASONAL_VACATION_TIME{
    Winter,
    Spring,
    Summer,
    Autumn
}

enum RESTING_PLACE{

}
