import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Node1<T> {
    T data;
    ArrayList<Node1<T>> children;

    public Node1(T data) {
        this.data = data;
        children = new ArrayList<Node1<T>>();
    }

    public void addChild(Node1<T> child) {
        children.add(child);
    }
    public void removeChild(int ind){
        children.remove(ind);
    }
}

class Question1 {
    static Node1<String> root = new Node1<>("Root");
    final static String API_URL = "https://opentdb.com/api.php?";
    static Node1<String> catNode;
    static Node1<String> ansLeaf;
    static String qsn;

    static String correctAns;
    static JSONArray incAns;
    static JSONObject dataList;
    ArrayList<String> catList;

    ArrayList<Integer> considered;
    static int numberOfQuestions = 12;


    // List of categories by ID needed for the API URL
    ArrayList<String> categoriesByID = new ArrayList<String>(){{
        add("23"); // history id
        add("22"); // geo id
        add("17"); // science id
        add("18"); // Computer id
        add("16"); //Board Games
        add("11"); //Film
        add("32"); //Cartoon & Animation
        add("26"); //Celebrities
        add("10"); //Books
    }};
    static ArrayList<String> categoriesByTitle = new ArrayList<String>(){{
        add("History");
        add("Geography");
        add("Science");
        add("Computers");
        add("BoardGames");
        add("Film");
        add("Cartoon & Animation");
        add("Celebrities");
        add("Books");
    }};

    public Question1() {
        // Assign question to appropriate category
        for(int i=0 ; i<categoriesByID.size(); i++){
            catNode = new Node1<>(categoriesByTitle.get(i));
            getQuestions(categoriesByID.get(i));
            root.addChild(catNode);    // Method from Node1<T> class
        }
    }
    //Adds the category question and the answer to the tree
    public static void addToTree(String catQsn, ArrayList<String> qsnAns){
        Node1<String> qsnNode = new Node1<>(catQsn);
        for(String choice: qsnAns){
            ansLeaf = new Node1<>(choice);
            qsnNode.addChild(ansLeaf);  // Method from Node1<T> class
        }
        catNode.addChild(qsnNode);
    }

    //Gets the relevant questions based on the category picked
    public static void getQuestions(String category){
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "amount="+ numberOfQuestions +"&category="+ category +"&difficulty=medium&type=multiple"))
                .build();
        HttpResponse<String> res = null;
        try {
            res = HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String r = res.body();
        System.out.println(r);
        JSONParser parser = new JSONParser();
        JSONObject jObj = new JSONObject();
        try{
            jObj = (JSONObject) parser.parse(r);
        } catch (ParseException e){
            e.printStackTrace();
        }

        JSONArray data = (JSONArray) jObj.get("results");
        for(int i=0; i< data.size(); i++){
            dataList = (JSONObject) data.get(i);
            qsn = (String) dataList.get("question");
            qsn = qsn.replace("&quot;","\"").replace("&#039","\'");
            correctAns = (String) dataList.get("correct_answer");
            incAns = (JSONArray) dataList.get("incorrect_answers");
            incAns.add(0,correctAns);
            addToTree(qsn,incAns);
        }

    }
    // Gets categories list from the children
    public ArrayList<String> getCategories() {
        catList = new ArrayList<>();
        for (Node1<String> cat : root.children) {
            catList.add(cat.data.toString());
        }
        return catList;
    }
    // Gets a random question from the categories specified
    public Node1<String> getQuestionByCategory(int categoryInd) {
        Random rand = new Random();
        Node1<String> catNode;
        int randInd = rand.nextInt(numberOfQuestions);
        catNode = root.children.get(categoryInd);
        Node1<String> qsNode = catNode.children.get(randInd);
        return qsNode;
    }
    //
    public ArrayList<String> getChoices(Node1<String> qsNode) {
        ArrayList<String> choices = new ArrayList<>();
        for(Node1<String> choice: qsNode.children){
            choices.add(choice.data);
        }
        correctAns = choices.get(0);
        return choices;
    }
    //Checks if the answer selected is the same as the correct answer stored in correctAns
    public boolean checkAnswer(String choice) {
        return choice == correctAns;
    }
}
public class Trivia1 {
    static Question1 q ;

    static Graphics2D g2;

    static ArrayList<String> catList;
    static ArrayList<String> choices;

    int yPos;

    int resAns;

    int catInd;
    Node1<String> qsnNode;

    boolean chooseCat = false;
    boolean qsnDisplay = false;
    boolean answered = false;
    // The general method used to display all graphics
    public void display(Graphics g, String toDisplay, int x, int y, int fontsize) {
        g2 = (Graphics2D) g;

        g2.setFont(new Font("Chalkboard", Font.BOLD, fontsize));
        g2.setColor(Color.WHITE);
        if (toDisplay.length() > 45 &&  toDisplay.length() < 90) {
            String part1 = "";
            for (int i = 0; i < 45; i++) {
                part1 = part1 + toDisplay.charAt(i);
            }
            String part2 = "";
            for (int i = 45; i < toDisplay.length(); i++) {
                part2 = part2 + toDisplay.charAt(i);
            }
            g2.drawString(part1, x, y);
            g2.drawString(part2, x, y + 20);
        }
        else if(toDisplay.length() > 90 ) {
            String part_1 = "";
            for (int i = 0; i < 45; i++) {
                part_1 = part_1 + toDisplay.charAt(i);
            }
            String part_2 = "";
            for (int i = 45; i < 90 ; i++) {
                part_2 = part_2 + toDisplay.charAt(i);
            }
            String part_3 = "";
            for (int i = 90; i < toDisplay.length() ; i++) {
                part_3= part_3 + toDisplay.charAt(i);
            }
            g2.drawString(part_1, x, y);
            g2.drawString(part_2, x, y + 20);
            g2.drawString(part_3, x, y + 40);
        }
        else {
            // display question
            g2.drawString(toDisplay, x, y);
        }
    }
    // Show categories
    public void displayCats(Graphics g) {

        yPos = 330;
        display(g, "Choose a Category", 920, 290, 30);
        int charCode = 65;
        for (String cat : Question1.categoriesByTitle) {
            display(g, (char) charCode + ". " + cat, 920, yPos, 30);
            yPos += 50;
            charCode += 1;
        }

    }

    //Displays the question
    public void displayQuestion(Graphics g) {
        display(g, "Choose right answer", 1000, 250, 20);
        display(g, qsnNode.data, 900, 280, 20);
        displayChoices(g);
    }

    public void displayChoices(Graphics g) {

        yPos = 340;
        for (int i = 0; i < choices.size(); i++) {
            display(g, (i + 1) + ". " + choices.get(i), 900, yPos, 20);
            yPos += 40;
        }

    }
    // Checks if the choice selected by the player is correct, updates the score, and generates a new question
    public void displayResult(Graphics g) {

        if (q.checkAnswer(choices.get(resAns))) {
            QuestionManager.score += 100;
            QuestionManager.quiz = new Trivia1();

            switch (QuestionManager.tilenumber) {
                case 2:
                    QuestionManager.tile2checked = true;
                    Tiles.tile2 = Color.GREEN;
                    break;
                case 4:
                    QuestionManager.tile4checked = true;
                    Tiles.tile4 = Color.GREEN;
                    break;
                case 6:
                    QuestionManager.tile6checked = true;
                    Tiles.tile6 = Color.GREEN;
                    break;
                case 8:
                    QuestionManager.tile8checked = true;
                    Tiles.tile8 = Color.GREEN;
                    break;
                case 10:
                    QuestionManager.tile10checked = true;
                    Tiles.tile10 = Color.GREEN;
                    break;
                case 12:
                    QuestionManager.tile12checked = true;
                    Tiles.tile12 = Color.GREEN;
                    break;
                case 14:
                    QuestionManager.tile14checked = true;
                    Tiles.tile14 = Color.GREEN;
                    break;
                case 16:
                    QuestionManager.tile16checked = true;
                    Tiles.tile16 = Color.GREEN;

                    break;
                case 18:
                    QuestionManager.tile18checked = true;
                    Tiles.tile18 = Color.GREEN;
                    break;
                case 20:
                    QuestionManager.tile20checked = true;
                    Tiles.tile20 = Color.GREEN;
                    break;
                case 22:
                    QuestionManager.tile22checked = true;
                    Tiles.tile22 = Color.GREEN;
                    break;
                case 24:
                    QuestionManager.tile24checked = true;
                    Tiles.tile24 = Color.GREEN;
                    break;

            }

        } else {

            QuestionManager.quiz = new Trivia1();
            QuestionManager.lives--;
            switch (QuestionManager.tilenumber) {
                case 2:
                    Tiles.tile2 = Color.red;
                    break;
                case 4:
                    Tiles.tile4 = Color.red;
                    break;
                case 6:
                    Tiles.tile6 = Color.red;
                    break;
                case 8:
                    Tiles.tile8 = Color.red;
                    break;
                case 10:
                    Tiles.tile10 = Color.red;
                    break;
                case 12:
                    Tiles.tile12 = Color.red;
                    break;
                case 14:
                    Tiles.tile14 = Color.red;
                    break;
                case 16:
                    Tiles.tile16 = Color.red;
                    break;
                case 18:
                    Tiles.tile18 = Color.red;
                    break;
                case 20:
                    Tiles.tile20 = Color.red;
                    break;
                case 22:
                    Tiles.tile22 = Color.red;
                    break;
                case 24:
                    Tiles.tile24 = Color.red;
                    break;

            }


        }
    }
    // Displays the categories,questions and the outcome
    public void displayTriviaPanel(Graphics g) {

        if (!chooseCat) {
            displayCats(g);

        }
        if (qsnDisplay ) {
            displayQuestion(g);

        }
        if (answered) {
            displayResult(g);

        }
    }
    //Shuffles the choices to a random order. Initially the correct answer is always in the first index
    public void shuffleChoices(){
        this.qsnNode = q.getQuestionByCategory(catInd);
        choices = q.getChoices(qsnNode);
        Collections.shuffle(choices,new Random());
    }
    // Handles the implementation of the keys
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();


        switch (c) {
            case 'A':
                catInd = 0;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'B':
                catInd = 1;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'C':
                catInd = 2;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'D':
                catInd = 3;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'E':
                catInd = 4;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'F':
                catInd = 5;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'G':
                catInd = 6;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'H':
                catInd = 7;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case 'I':
                catInd = 8;
                chooseCat = true;
                qsnDisplay = true;
                shuffleChoices();
                break;
            case '1':
                resAns = 0;
                answered = true;
                qsnDisplay = false;
                break;
            case '2':
                resAns = 1;
                answered = true;
                qsnDisplay = false;

                break;
            case '3':
                resAns = 2;
                answered = true;
                qsnDisplay = false;

                break;
            case '4':
                resAns = 3;
                answered = true;
                qsnDisplay = false;
                break;

        }


    }

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

    }

    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();


    }
    //Initializing the trivia class
    public Trivia1() {
        q = new Question1();
        catList = q.getCategories();
    }
}

/**
 * Use Collections.shuffle(options) to shuffle the choices;
 *
 * For handling next question with btn or label.setText
 *      questionLabel.setText(question.getText());
 *             List<String> answerOptions = getAnswerOptions(question);
 *             for (int i = 0; i < answerButtons.size(); i++) {
 *                 answerButtons.get(i).setText(answerOptions.get(i));
 *             }
 *
 *
 *             String[] parts = questionStr.split("\\|");
 * // String text = parts[0];
 * // String correctAnswer = parts[1];
 * // List<String> incorrectAnswers = Arrays.asList(parts).subList(2,
 * // parts.length);return new Question(text,correctAnswer,incorrectAnswers);
 * // }
 */
