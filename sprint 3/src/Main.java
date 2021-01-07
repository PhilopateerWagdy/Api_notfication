import model.LanguageEnum;
import model.Notification;
import model.Type;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        HttpURLConnectionExample http = new HttpURLConnectionExample() ;
        Scanner input = new Scanner(System.in);
        char choice_1;

        while(true)
        {
            System.out.println("1-Send Notification");
            System.out.println("2-Get all Notifications");
            System.out.println("3-Get Notification by user");
            int choice = input.nextInt();
            String request;
            String subject,content,user;
            LanguageEnum language = null;
            int lang_choice;
            Type type = null;
            int type_choice;
            if(choice == 1)
            {
                System.out.println("enter subject : ");
                subject = input.nextLine();
                System.out.println("enter subject : ");
                content = input.nextLine();
                System.out.println("set language : 1-English 2-Arabic");
                lang_choice = input.nextInt();
                if(lang_choice == 1)
                {
                    language = LanguageEnum.English;
                }
                else if(lang_choice == 2)
                {
                    language = LanguageEnum.Arabic;
                }
                System.out.println("set type : 1-mail 2-sms");
                type_choice = input.nextInt();
                if(type_choice == 1)
                {
                    type = Type.mail;
                }
                else if(type_choice == 2)
                {
                    type = Type.sms;
                }
                System.out.println("enter user name : ");
                user = input.nextLine();
                Notification notification = new Notification(0,subject,content,language,type,user);
                http.sendingPostRequest(notification);
            }
            else if(choice == 2)
            {
                http.sendingGetRequest();
            }
            else if(choice == 3)
            {
                System.out.println("enter user : ");
                user = input.nextLine();
                http.sendingGetRequest(user);
            }
            System.out.println("do you want to continue ? (y/n)");
            choice_1 = input.next().charAt(0);
            if(choice_1 == 'y' || choice_1 == 'Y')
            {
                continue;
            }
            else if(choice_1 == 'n' || choice_1 == 'N')
            {
                break;
            }
        }


    }
}