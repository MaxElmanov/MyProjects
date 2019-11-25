package ru.maxelmanov.detagramsocket;

import ru.maxelmanov.detagramsocket.objs.Participant;
import ru.maxelmanov.detagramsocket.objs.Question;
import ru.maxelmanov.detagramsocket.thread.QuizThreadObtainer;
import ru.maxelmanov.detagramsocket.thread.QuizThreadSender;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server
{
    private static Integer port = 8888;
    private DatagramSocket socket;

    // счетчик срабатываний таймера
    private static int timerCount = 12;
    //private TimerClass myTimer;

    private List<DatagramPacket> requestsPackets;
    //private String requestMessage;
    //private List<DatagramPacket> packetReplies;
    private DatagramPacket reply;
    private StringBuilder replyMessage;

    private List<String> requestMessages;
    private List<Question> quiz;
    //private List<Participant> participants;
    Participant participant;

    private void init()
    {
        prepareQuestions();

        try {
            socket = new DatagramSocket(port);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }

        //myTimer = new TimerClass(timerCount);

        requestMessages = new ArrayList<>();
        requestsPackets = new ArrayList<>();
        //participants = new ArrayList<>();
        replyMessage = new StringBuilder();

    }

    private void prepareQuestions()
    {
        Question q1 = new Question("Какой год полёта Гагарина в космос?", Arrays.asList("1887", "1953", "1961", "2008"), "1961");
        Question q2 = new Question("Когда умер Иосиф Виссарионович Сталин?", Arrays.asList("1851", "1953", "2014", "1999"), "1953");
        Question q3 = new Question("Какая любимая марка машины Жириновского?", Arrays.asList("BMW", "Mercedes", "Lada", "Renault"), "Mercedes");
        Question q4 = new Question("Какая высота Эйфелевой башни (метры)?", Arrays.asList("590", "0.5", "184", "324"), "324");
        Question q5 = new Question("Лучшая IDE все времен и народов?", Arrays.asList("idea", "QTCreator", "Notepad++", "TXT document"), "idea");

        quiz = new ArrayList<>();
        quiz.add(q1);
        quiz.add(q2);
        quiz.add(q3);
        quiz.add(q4);
        quiz.add(q5);
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //МЕТОД СНИЗУ НЕ ЗАПУСКАЙ
    //ОН ЖРЁТ ПАМЯТЬ И НАДО БУДЕТ КОМП ПЕРЕЗАПУСКАТЬ
    //УДАЛЯТЬ ИЛИ МЕНЯТЬ БЫЛО ЛЕНЬ
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void launch()
    {
//        try {
//            System.out.println("Server is running");
//
//            Thread.sleep(12000);
//
//            byte[] buffer = new byte[1024];
//            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
//            socket.receive(request);
//
//            while(true) {
//                if(request != null){
//                    QuizThreadSender quizThreadSender = new QuizThreadSender(request, quiz, socket);
//                    quizThreadSender.start();
//                }
//
//                QuizThreadObtainer quizThreadObtainer = new QuizThreadObtainer(socket);
//                quizThreadObtainer.start();
//            }
//        }
//        catch (SocketException e) {
//            e.printStackTrace();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args)
    {
        Server server = new Server();
        server.init();
        server.launch();
    }
}